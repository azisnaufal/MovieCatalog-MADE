package com.oazisn.moviecatalog.core.di

import androidx.room.Room
import com.oazisn.moviecatalog.core.data.local.AppDatabase
import com.oazisn.moviecatalog.core.data.local.MovieDataSource
import com.oazisn.moviecatalog.core.data.local.TvShowDataSource
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val roomModule = module {
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("7Delapan789899#".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, AppDatabase.DATABASE_NAME)
            .openHelperFactory(factory)
            .build()
    }

    single { get<AppDatabase>().movieDao() }

    single { get<AppDatabase>().tvDao() }

    single { MovieDataSource(get()) }
    single { TvShowDataSource(get()) }
}