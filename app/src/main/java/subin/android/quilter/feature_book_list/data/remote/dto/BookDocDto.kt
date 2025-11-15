package subin.android.quilter.feature_book_list.data.remote.dto

import com.squareup.moshi.Json

data class BookDocDto(
    @Json(name = "work") val work: WorkDto?,
    @Json(name = "logged_date") val loggedDate: String?
)

data class WorkDto(
    val title: String?,
    val key: String?,
    @Json(name = "author_keys") val authorKeys: List<String>?,
    @Json(name = "author_names") val authorNames: List<String>?,
    @Json(name = "first_publish_year") val firstPublishYear: Int?,
    @Json(name = "lending_edition_s") val lendingEdition: String?,
    @Json(name = "edition_key") val editionKey: List<String>?,
    @Json(name = "cover_id") val coverId: Int?,
    @Json(name = "logged_date") val loggedDate: String?
)