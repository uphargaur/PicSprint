package com.jhalakXCorp.vent.di

import com.uphar.bussiness.domain.datastore.ImageDatasource
import com.uphar.bussiness.repo.ImageRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {



    @Singleton
    @Provides
    fun provideImageRepository(
        imageDatasource: ImageDatasource
    ): ImageRepository {
        return ImageRepository(imageDatasource)
    }


}

