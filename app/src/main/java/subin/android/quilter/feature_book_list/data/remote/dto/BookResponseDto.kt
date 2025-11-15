package subin.android.quilter.feature_book_list.data.remote.dto

import com.squareup.moshi.Json

data class BookResponseDto(
    @Json(name = "reading_log_entries")
    val readingLogEntries: List<BookDocDto>
)