package subin.android.quilter.feature_book_list.data.repository

import coil.network.HttpException
import io.reactivex.rxjava3.core.Single
import jakarta.inject.Inject
import jakarta.inject.Singleton
import subin.android.quilter.core.model.Resource
import subin.android.quilter.feature_book_list.data.remote.api.BookApi
import subin.android.quilter.feature_book_list.data.remote.mapper.toDomain
import subin.android.quilter.feature_book_list.domain.model.Book
import subin.android.quilter.feature_book_list.domain.model.BookListTab
import subin.android.quilter.feature_book_list.domain.repository.BookRepository
import java.io.IOException
import java.net.SocketTimeoutException

@Singleton
class BookRepositoryImpl @Inject constructor(
    private val api: BookApi
) : BookRepository {

    override fun getBooks(tab: BookListTab): Single<List<Book>> {
        val category = when (tab) {
            BookListTab.WantToRead -> "want-to-read"
            BookListTab.CurrentlyReading -> "currently-reading"
            BookListTab.AlreadyRead -> "already-read"
        }

        return api.getBooksByCategory(category)
            .map { dto ->
                dto.readingLogEntries.mapNotNull { it.toDomain() }
            }
    }

}
