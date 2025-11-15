package subin.android.quilter.feature_book_list.domain.usecase

import io.reactivex.rxjava3.core.Single
import jakarta.inject.Inject
import subin.android.quilter.core.model.Resource
import subin.android.quilter.feature_book_list.domain.model.Book
import subin.android.quilter.feature_book_list.domain.model.BookListTab
import subin.android.quilter.feature_book_list.domain.repository.BookRepository

class GetBookListUseCase @Inject constructor(
    private val repository: BookRepository
) {
    operator fun invoke(tab: BookListTab): Single<List<Book>> =
        repository.getBooks(tab)
}
