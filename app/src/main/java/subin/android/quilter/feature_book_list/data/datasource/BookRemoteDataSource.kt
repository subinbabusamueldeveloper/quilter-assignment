package subin.android.quilter.feature_book_list.data.datasource

import io.reactivex.rxjava3.core.Single
import jakarta.inject.Inject
import subin.android.quilter.feature_book_list.data.remote.api.BookApi
import subin.android.quilter.feature_book_list.data.remote.dto.BookResponseDto

class BookRemoteDataSource @Inject constructor(
    private val api: BookApi
) {
    fun getBooks(category: String): Single<BookResponseDto> =
        api.getBooksByCategory(category)
}