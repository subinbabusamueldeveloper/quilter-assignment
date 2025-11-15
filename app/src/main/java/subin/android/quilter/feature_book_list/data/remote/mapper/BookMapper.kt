package subin.android.quilter.feature_book_list.data.remote.mapper

import subin.android.quilter.feature_book_list.data.remote.dto.BookResponseDto
import subin.android.quilter.feature_book_list.domain.model.Book

fun BookResponseDto.toDomainBooks(): List<Book> {
    return readingLogEntries.map { entry ->
        val work = entry.work
        Book(
            id = work.key.substringAfterLast("/") ?: "",
            title = work.title ?: "Unknown title",
            author = work.authorNames?.firstOrNull() ?: "Unknown author",
            firstPublishYear = work.firstPublishYear ?: 0,
            year = work.firstPublishYear ?: 0,
            coverId = work.coverId ?: 0,
            loggedDate = entry.loggedDate.orEmpty()
        )
    }
}