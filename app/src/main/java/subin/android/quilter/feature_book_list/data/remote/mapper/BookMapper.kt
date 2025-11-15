package subin.android.quilter.feature_book_list.data.remote.mapper

import subin.android.quilter.feature_book_list.data.remote.dto.BookDocDto
import subin.android.quilter.feature_book_list.domain.model.Book

fun BookDocDto.toDomain(): Book? {
    val w = work ?: return null

    return Book(
        id = w.key?.substringAfterLast("/") ?: "",
        title = w.title ?: "Unknown Title",
        author = w.authorNames?.firstOrNull() ?: "Unknown Author",
        year = w.firstPublishYear,
        firstPublishYear = w.firstPublishYear,
        coverId = w.coverId,
        loggedDate = loggedDate
    )
}