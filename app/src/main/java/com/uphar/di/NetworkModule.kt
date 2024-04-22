package com.jhalakXCorp.vent.di

import com.agrireach.referandreward.datasource.retrofit.VentWebServices
import com.jhalakXCorp.vent.bussinesss.domain.Utils.state.ErrorUtils
import com.jhalakXCorp.vent.datasource.retrofit.VentRetrofitService
import com.jhalakXCorp.vent.datasource.retrofit.VentRetrofitServiceImpl
import com.uphar.bussiness.domain.ImageMapper
import com.uphar.bussiness.domain.datastore.ImageDatasource
import com.uphar.bussiness.domain.datastore.ImageDatasourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Singleton
    @Provides
    fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://jhalakXcoor.com:443")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun VentWebServices(retrofit: Retrofit): VentWebServices {
        return retrofit.create(VentWebServices::class.java)
    }

    @Singleton
    @Provides
    fun provideVentRetrofitService(
        ventWebServices: VentWebServices,
        errorUtils: ErrorUtils
    ): VentRetrofitService {
        return VentRetrofitServiceImpl(ventWebServices, errorUtils)
    }


    @Singleton
    @Provides
    fun provideLoginDatasource(
        ventRetrofitService: VentRetrofitService,
        imageMapper: ImageMapper
    ): ImageDatasource {
        return ImageDatasourceImpl(
            ventRetrofitService,
            imageMapper
        )
    }


    @Singleton
    @Provides
    fun provideErrorUtils(retrofit: Retrofit): ErrorUtils {
        return ErrorUtils(retrofit)
    }


}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AppRetrofit