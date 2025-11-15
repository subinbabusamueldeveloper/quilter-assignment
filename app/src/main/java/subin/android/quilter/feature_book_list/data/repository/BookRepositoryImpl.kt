package subin.android.quilter.feature_book_list.data.repository

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import jakarta.inject.Inject
import jakarta.inject.Singleton
import subin.android.quilter.core.model.Resource
import subin.android.quilter.feature_book_list.data.datasource.BookRemoteDataSource
import subin.android.quilter.feature_book_list.domain.model.Book
import subin.android.quilter.feature_book_list.domain.model.BookListTab
import subin.android.quilter.feature_book_list.domain.repository.BookRepository

@Singleton
class BookRepositoryImpl @Inject constructor(
    private val remote: BookRemoteDataSource
) : BookRepository {

    override fun getBooks(tab: BookListTab): Single<Resource<List<Book>>> {
        return remote.getBooks(tab)
            .map<Resource<List<Book>>> { books ->
                Resource.Success(books)
            }
            .onErrorReturn { error ->
                Resource.Error(error.message ?: "Unknown error")
            }
    }
}