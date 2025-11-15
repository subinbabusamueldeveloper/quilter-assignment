package subin.android.quilter.feature_book_list.data.remote.dto

import com.squareup.moshi.Json


data class BookResponseDto(
    @Json(name = "reading_log_entries")
    val readingLogEntries: List<ReadingLogEntryDto> = emptyList()
)

data class ReadingLogEntryDto(
    @Json(name = "work")
    val work: WorkDto,
    @Json(name = "logged_date")
    val loggedDate: String?
)

data class WorkDto(
    @Json(name = "key")
    val key: String,
    @Json(name = "title")
    val title: String?,
    @Json(name = "author_names")
    val authorNames: List<String>?,
    @Json(name = "first_publish_year")
    val firstPublishYear: Int?,
    @Json(name = "cover_id")
    val coverId: Int?
)
