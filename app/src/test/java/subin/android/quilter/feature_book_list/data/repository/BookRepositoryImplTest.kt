package subin.android.quilter.feature_book_list.data.repository

import io.reactivex.rxjava3.core.Single
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import subin.android.quilter.core.model.Resource
import subin.android.quilter.feature_book_list.data.datasource.BookRemoteDataSource
import subin.android.quilter.feature_book_list.data.remote.api.BookApi
import subin.android.quilter.feature_book_list.data.remote.dto.BookDocDto
import subin.android.quilter.feature_book_list.data.remote.dto.BookResponseDto
import subin.android.quilter.feature_book_list.data.remote.dto.WorkDto
import subin.android.quilter.feature_book_list.domain.model.Book
import subin.android.quilter.feature_book_list.domain.model.BookListTab

@ExperimentalStdlibApi
class BookRepositoryImplTest {

    private val remote = mock<BookApi>()
    private lateinit var repo: BookRepositoryImpl

    @Before
    fun setup() {
        repo = BookRepositoryImpl(remote)
    }

    @Test
    fun `Repository returns mapped book list`() {
        // Arrange
        val dto = BookResponseDto(
            readingLogEntries = listOf(
                BookDocDto(
                    work = WorkDto(
                        title = "ABC",
                        key = "/works/OL12345W",
                        authorNames = listOf("John Doe"),
                        authorKeys = listOf(),
                        lendingEdition = "",
                        firstPublishYear = 2020,
                        editionKey = listOf(),
                        loggedDate = "",
                        coverId = 555
                    ),
                    loggedDate = "2025/01/01"
                )
            )
        )

        whenever(remote.getBooksByCategory("want-to-read"))
            .thenReturn(Single.just(dto))

        // Act
        val testObserver = repo.getBooks(BookListTab.WantToRead).test()

        // Assert
        testObserver.assertNoErrors()
        val result = testObserver.values().first()

        // result IS A LIST – so cast to list
        val data = result as List<Book>

        assertEquals(1, data.size)
        assertEquals("ABC", data.first().title)
        assertEquals("John Doe", data.first().author)
        assertEquals(2020, data.first().year)
        assertEquals("OL12345W", data.first().id)
        assertEquals(555, data.first().coverId)
    }

}

