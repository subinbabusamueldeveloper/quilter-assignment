package subin.android.quilter.feature_book_list.data.remote.mapper

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Test
import subin.android.quilter.feature_book_list.data.remote.dto.BookDocDto
import subin.android.quilter.feature_book_list.data.remote.dto.WorkDto

class BookMapperKtTest {

    @Test
    fun `BookDocDto maps to domain Book correctly`() {
        val dto = BookDocDto(
            work = WorkDto(
                title = "Sample Book",
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

        val result = dto.toDomain()

        assertNotNull(result)
        assertEquals("Sample Book", result!!.title)
        assertEquals("John Doe", result.author)
        assertEquals("OL12345W", result.id)
        assertEquals(555, result.coverId)
        assertEquals("2025/01/01", result.loggedDate)
    }

}