package subin.android.quilter.feature_book_list.domain.usecase

import io.reactivex.rxjava3.core.Single
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
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
    fun `UseCase returns book list when repository succeeds`() {
        // Arrange
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

        // Mock repository (MUST return Single)
        whenever(repository.getBooks(BookListTab.WantToRead))
            .thenReturn(Single.just(books))

        // Act
        val testObserver = useCase(BookListTab.WantToRead).test()

        // Assert
        testObserver.assertNoErrors()
        testObserver.assertValue { list ->
            list.size == 1 &&
                    list.first().title == "Test Book" &&
                    list.first().author == "Author"
        }
    }
}
