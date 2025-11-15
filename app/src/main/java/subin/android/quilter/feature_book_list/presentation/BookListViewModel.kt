package subin.android.quilter.feature_book_list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import subin.android.quilter.feature_book_list.domain.model.BookListTab
import subin.android.quilter.core.model.Resource
import subin.android.quilter.feature_book_list.domain.model.Book
import subin.android.quilter.feature_book_list.domain.usecase.GetBookListUseCase

@HiltViewModel
class BookListViewModel @Inject constructor(
    private val getBookListUseCase: GetBookListUseCase
) : ViewModel() {

    private val _selectedTab = MutableStateFlow(BookListTab.WantToRead)
    val selectedTab: StateFlow<BookListTab> = _selectedTab.asStateFlow()


    private val _uiState = MutableStateFlow<BookListUiState>(BookListUiState.Loading)
    val uiState: StateFlow<BookListUiState> = _uiState.asStateFlow()


    private val _selectedBook = MutableStateFlow<Book?>(null)
    val selectedBook = _selectedBook.asStateFlow()

    private val disposables = CompositeDisposable()

    init {
        loadBooks(BookListTab.WantToRead)
    }

    fun onTabSelected(tab: BookListTab) {
        _selectedTab.value = tab
        loadBooks(tab)
    }

    fun loadBooks(tab: BookListTab) {
        _uiState.value = BookListUiState.Loading

        val disposable = getBookListUseCase(tab)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { books ->
                    _uiState.value = BookListUiState.Success(books)
                },
                { error ->
                    _uiState.value = BookListUiState.Error(error.message ?: "Unknown error")
                }
            )

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
