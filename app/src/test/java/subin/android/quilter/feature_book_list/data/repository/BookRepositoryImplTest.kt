package subin.android.quilter.feature_book_list.data.repository

import io.reactivex.rxjava3.core.Single
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import subin.android.quilter.core.model.Resource
import subin.android.quilter.feature_book_list.data.datasource.BookRemoteDataSource
import subin.android.quilter.feature_book_list.domain.model.Book
import subin.android.quilter.feature_book_list.domain.model.BookListTab

@ExperimentalStdlibApi
class BookRepositoryImplTest {

    private val remoteDataSource = mock<BookRemoteDataSource>()
    private lateinit var repository: BookRepositoryImpl

    @Before
    fun setup() {
        repository = BookRepositoryImpl(remoteDataSource)
    }

    @Test
    fun `Repository returns Resource_Success with book list`() {
        val books = listOf(
            Book(
                id = "/works/OL12345W",
                title = "ABC",
                author = "John Doe",
                firstPublishYear = 2020,
                year = 2020,
                coverId = 555,
                loggedDate = "2025/01/01"
            )
        )

        whenever(remoteDataSource.getBooks(BookListTab.WantToRead))
            .thenReturn(Single.just(books))

        val testObserver = repository.getBooks(BookListTab.WantToRead).test()

        testObserver.assertNoErrors()

        val resource = testObserver.values().first()
        assertTrue(resource is Resource.Success)

        val data = (resource as Resource.Success).data

        assertEquals(1, data.size)
        assertEquals("ABC", data.first().title)
        assertEquals("John Doe", data.first().author)
        assertEquals(2020, data.first().year)
        assertEquals("/works/OL12345W", data.first().id)
        assertEquals(555, data.first().coverId)
    }
}