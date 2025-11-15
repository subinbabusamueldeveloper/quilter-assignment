package subin.android.quilter.feature_book_list.domain.usecase

import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import subin.android.quilter.core.model.Resource
import subin.android.quilter.feature_book_list.domain.model.Book
import subin.android.quilter.feature_book_list.domain.model.BookListTab
import subin.android.quilter.feature_book_list.domain.repository.BookRepository

class GetBookListUseCaseTest {

    private val repository = mock<BookRepository>()
    private lateinit var useCase: GetBookListUseCase

    @Before
    fun setup() {
        useCase = GetBookListUseCase(repository)
    }

    @Test
    fun `UseCase returns Success Resource when repository succeeds`() {
        val books = listOf(
            Book(
                id = "OL1W",
                title = "Test Book",
                author = "Author",
                year = 2020,
                coverId = 123,
                firstPublishYear = 2020,
                loggedDate = "2025/01/01"
            )
        )

        whenever(repository.getBooks(BookListTab.WantToRead))
            .thenReturn(Single.just(Resource.Success(books)))

        val testObserver = useCase(BookListTab.WantToRead).test()

        testObserver.assertNoErrors()

        testObserver.assertValue { resource ->
            resource is Resource.Success &&
                    resource.data.size == 1 &&
                    resource.data.first().title == "Test Book" &&
                    resource.data.first().author == "Author"
        }
    }
}
