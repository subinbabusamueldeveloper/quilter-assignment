package subin.android.quilter.feature_book_list.presentation

import subin.android.quilter.feature_book_list.domain.model.Book
import subin.android.quilter.feature_book_list.domain.model.BookListTab

sealed class BookListUiState {

    object Loading : BookListUiState()

    data class Success(
        val books: List<Book>
    ) : BookListUiState()

    data class Error(
        val message: String
    ) : BookListUiState()
}
