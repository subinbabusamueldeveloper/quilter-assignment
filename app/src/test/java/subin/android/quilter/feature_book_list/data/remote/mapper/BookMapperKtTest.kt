package subin.android.quilter.feature_book_list.data.remote.mapper

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Test
import subin.android.quilter.feature_book_list.data.remote.dto.BookResponseDto
import subin.android.quilter.feature_book_list.data.remote.dto.ReadingLogEntryDto
import subin.android.quilter.feature_book_list.data.remote.dto.WorkDto

class BookMapperKtTest {

    @Test
    fun `ReadingLogEntryDto maps to Book domain model correctly`() {
        val entry = ReadingLogEntryDto(
            work = WorkDto(
                key = "/works/OL12345W",
                title = "Sample Book",
                authorNames = listOf("John Doe"),
                firstPublishYear = 2020,
                coverId = 555
            ),
            loggedDate = "2025/01/01"
        )

        val dto = BookResponseDto(
            readingLogEntries = listOf(entry)
        )

        val result = dto.toDomainBooks().firstOrNull()

        assertNotNull(result)
        assertEquals("OL12345W", result?.id)
        assertEquals("Sample Book", result?.title)
        assertEquals("John Doe", result?.author)
        assertEquals(555, result?.coverId)
        assertEquals("2025/01/01", result?.loggedDate)
        assertEquals(2020, result?.firstPublishYear)
        assertEquals(2020, result?.year) // matches mapper's year = firstPublishYear
    }
}