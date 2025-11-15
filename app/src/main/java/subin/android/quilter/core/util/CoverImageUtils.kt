package subin.android.quilter.core.util

object CoverImageUtils {
    fun getCoverUrl(coverId: Int?): String {
        return if (coverId != null)
            "https://covers.openlibrary.org/b/id/${coverId}-L.jpg"
        else
            "https://openlibrary.org/images/icons/avatar_book-lg.png"
    }
}