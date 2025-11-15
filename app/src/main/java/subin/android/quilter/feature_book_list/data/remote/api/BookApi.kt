package subin.android.quilter.feature_book_list.data.remote.api

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import subin.android.quilter.feature_book_list.data.remote.dto.BookResponseDto

interface BookApi {

    @GET("people/mekBot/books/{category}.json")
    fun getBooksByCategory(
        @Path("category") category: String
    ): Single<BookResponseDto>

}