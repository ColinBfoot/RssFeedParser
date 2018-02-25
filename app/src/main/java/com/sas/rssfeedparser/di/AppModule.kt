package com.sas.rssfeedparser.di

import android.arch.lifecycle.ViewModelProvider
import com.sas.rssfeedparser.data.services.FeedService
import com.sas.rssfeedparser.viewmodel.FeedItemViewModelFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import org.simpleframework.xml.convert.AnnotationStrategy
import org.simpleframework.xml.core.Persister
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import javax.inject.Singleton

@Module(subcomponents = [(ViewModelSubComponent::class)])
internal class AppModule {
    @Singleton
    @Provides
    fun provideFeedService(): FeedService {

        return Retrofit.Builder()
                .baseUrl(FeedService.FEED_BASE_URL)
                .client(OkHttpClient())
                .addConverterFactory(SimpleXmlConverterFactory.createNonStrict(
                        Persister(AnnotationStrategy())
                ))
                .build()
                .create(FeedService::class.java)
    }

    @Singleton
    @Provides
    fun provideViewModelFactory(
            viewModelSubComponent: ViewModelSubComponent.Builder): ViewModelProvider.Factory {

        return FeedItemViewModelFactory(viewModelSubComponent.build())
    }
}
