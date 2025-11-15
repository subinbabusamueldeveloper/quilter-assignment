package subin.android.quilter.feature_book_list.domain.repository

import io.reactivex.rxjava3.core.Single
import subin.android.quilter.core.model.Resource
import subin.android.quilter.feature_book_list.domain.model.BookListTab
import subin.android.quilter.feature_book_list.domain.model.Book

interface BookRepository {
    fun getBooks(tab: BookListTab): Single<Resource<List<Book>>>
}