package subin.android.quilter.feature_book_list.domain.model

data class Book(
    val id: String,
    val title: String?,
    val author: String?,
    val firstPublishYear: Int?,
    val year: Int?,
    val coverId: Int?,
    val loggedDate: String?
)