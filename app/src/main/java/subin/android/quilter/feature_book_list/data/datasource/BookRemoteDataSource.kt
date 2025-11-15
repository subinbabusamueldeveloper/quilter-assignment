package subin.android.quilter.feature_book_list.data.datasource

import io.reactivex.rxjava3.core.Single
import jakarta.inject.Inject
import subin.android.quilter.core.model.Resource
import subin.android.quilter.feature_book_list.data.remote.api.BookApi
import subin.android.quilter.feature_book_list.data.remote.dto.BookResponseDto
import subin.android.quilter.feature_book_list.data.remote.mapper.toDomainBooks
import subin.android.quilter.feature_book_list.domain.model.Book
import subin.android.quilter.feature_book_list.domain.model.BookListTab

class BookRemoteDataSource @Inject constructor(
    private val api: BookApi
) {

    fun getBooks(tab: BookListTab): Single<List<Book>> {

        val category = when (tab) {
            BookListTab.WantToRead -> "want-to-read"
            BookListTab.CurrentlyReading -> "currently-reading"
            BookListTab.AlreadyRead -> "already-read"
        }

        return api.getBooksByCategory(category)
            .map { dto -> dto.toDomainBooks() }
    }
}

