package subin.android.quilter.feature_book_list.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import subin.android.quilter.core.model.Resource
import subin.android.quilter.feature_book_list.domain.model.BookListTab
import subin.android.quilter.feature_book_list.domain.model.Book
import subin.android.quilter.feature_book_list.domain.usecase.GetBookListUseCase

@HiltViewModel
class BookListViewModel @Inject constructor(
    private val getBookListUseCase: GetBookListUseCase
) : ViewModel() {

    private val disposables = CompositeDisposable()

    private val _selectedTab = MutableStateFlow(BookListTab.WantToRead)
    val selectedTab: StateFlow<BookListTab> = _selectedTab.asStateFlow()

    private val _uiState = MutableStateFlow<BookListUiState>(BookListUiState.Loading)
    val uiState: StateFlow<BookListUiState> = _uiState.asStateFlow()

    private val _selectedBook = MutableStateFlow<Book?>(null)
    val selectedBook: StateFlow<Book?> = _selectedBook.asStateFlow()

    init {
        loadBooks(BookListTab.WantToRead)
    }

    fun onTabSelected(tab: BookListTab) {
        _selectedTab.value = tab
        loadBooks(tab)
    }

    private fun loadBooks(tab: BookListTab) {
        _uiState.value = BookListUiState.Loading
        val disposable = getBookListUseCase(tab)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { resource ->
                _uiState.value = when (resource) {
                    is Resource.Success ->
                        BookListUiState.Success(resource.data)

                    is Resource.Error ->
                        BookListUiState.Error(resource.message)

                    is Resource.Loading ->
                        BookListUiState.Loading
                }
            }

        disposables.add(disposable)
    }

    fun onBookClicked(book: Book) {
        _selectedBook.value = book
    }

    fun closeBookDetails() {
        _selectedBook.value = null
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}