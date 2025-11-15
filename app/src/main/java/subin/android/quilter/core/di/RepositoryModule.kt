package subin.android.quilter.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import subin.android.quilter.feature_book_list.data.datasource.BookRemoteDataSource
import subin.android.quilter.feature_book_list.data.remote.api.BookApi
import subin.android.quilter.feature_book_list.data.repository.BookRepositoryImpl
import subin.android.quilter.feature_book_list.domain.repository.BookRepository
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideBookRepository(
        api: BookApi
    ): BookRepository = BookRepositoryImpl(api)
}
