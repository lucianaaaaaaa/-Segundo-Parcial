package com.calyrsoft.ucbp1.di

import android.app.Application
import androidx.room.Room
import com.calyrsoft.ucbp1.R
import com.calyrsoft.ucbp1.features.dollar.data.datasource.RealTimeRemoteDataSource
import com.calyrsoft.ucbp1.features.dollar.data.repository.DollarRepository
import com.calyrsoft.ucbp1.features.dollar.domain.repository.IDollarRepository
import com.calyrsoft.ucbp1.features.dollar.domain.usecase.FetchDollarUseCase
import com.calyrsoft.ucbp1.features.dollar.presentation.DollarViewModel
import com.calyrsoft.ucbp1.features.github.data.api.GithubService
import com.calyrsoft.ucbp1.features.github.data.datasource.GithubRemoteDataSource
import com.calyrsoft.ucbp1.features.github.data.repository.GithubRepository
import com.calyrsoft.ucbp1.features.github.domain.repository.IGithubRepository
import com.calyrsoft.ucbp1.features.github.domain.usecase.FindByNickNameUseCase
import com.calyrsoft.ucbp1.features.github.presentation.GithubViewModel
import com.calyrsoft.ucbp1.features.movies.data.api.MovieService
import com.calyrsoft.ucbp1.features.movies.data.datasource.MovieRemoteDataSource
import com.calyrsoft.ucbp1.features.movies.data.repository.MovieRepository
import com.calyrsoft.ucbp1.features.movies.data.database.AppRoomDatabaseMovies
import com.calyrsoft.ucbp1.features.movies.data.database.dao.IMovieDao
import com.calyrsoft.ucbp1.features.movies.data.datasource.MovieLocalDataSource
import com.calyrsoft.ucbp1.features.movies.domain.repository.IMovieRepository
import com.calyrsoft.ucbp1.features.movies.domain.usecase.GetPopularMoviesUseCase
import com.calyrsoft.ucbp1.features.movies.presentation.MoviesViewModel
import com.calyrsoft.ucbp1.features.profile.application.ProfileViewModel
import com.calyrsoft.ucbp1.features.profile.data.repository.ProfileRepository
import com.calyrsoft.ucbp1.features.profile.domain.repository.IProfileRepository
import com.calyrsoft.ucbp1.features.profile.domain.usecase.GetProfileUseCase
import okhttp3.OkHttpClient
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkConstants {
    const val RETROFIT_GITHUB = "RetrofitGithub"
    const val GITHUB_BASE_URL = "https://api.github.com/"
    const val RETROFIT_MOVIE = "RetrofitMovie"
    const val MOVIE_BASE_URL = "https://api.themoviedb.org/"

}

val appModule = module {


    // OkHttpClient
    single {
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    // Retrofit
    single(named(NetworkConstants.RETROFIT_GITHUB)) {
        Retrofit.Builder()
            .baseUrl(NetworkConstants.GITHUB_BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Retrofit
    single(named(NetworkConstants.RETROFIT_MOVIE)) {
        Retrofit.Builder()
            .baseUrl(NetworkConstants.MOVIE_BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    // GithubService
    single<GithubService> {
        get<Retrofit>(named(NetworkConstants.RETROFIT_GITHUB)).create(GithubService::class.java)
    }
    single { GithubRemoteDataSource(get()) }
    single<IGithubRepository> { GithubRepository(get()) }

    factory { FindByNickNameUseCase(get()) }
    viewModel { GithubViewModel(get(), get()) }

    single<IProfileRepository> { ProfileRepository() }
    factory { GetProfileUseCase(get()) }
    viewModel { ProfileViewModel(get()) }



    single(named("apiKey")) {
        androidApplication().getString(R.string.api_key)
    }


    single<MovieService> {
        get<Retrofit>(named(NetworkConstants.RETROFIT_MOVIE)).create(MovieService::class.java)
    }
    single { MovieRemoteDataSource(get(), get(named("apiKey"))) }

    single {
        Room.databaseBuilder(
            androidApplication() as Application,
            AppRoomDatabaseMovies::class.java,
            "movies.db"
        ).build()
    }
    single<IMovieDao> { get<AppRoomDatabaseMovies>().movieDao() }
    single { MovieLocalDataSource(get()) }

    single<IMovieRepository> { MovieRepository(get(), get()) }
    factory { GetPopularMoviesUseCase(get()) }
    viewModel { MoviesViewModel(get())}

    single{ RealTimeRemoteDataSource() }
    single<IDollarRepository> { DollarRepository(get()) }
    factory { FetchDollarUseCase(get()) }
    viewModel{ DollarViewModel(get()) }

}