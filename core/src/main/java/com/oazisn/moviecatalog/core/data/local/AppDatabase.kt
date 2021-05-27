package com.oazisn.moviecatalog.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.oazisn.moviecatalog.core.data.local.dao.MovieDao
import com.oazisn.moviecatalog.core.data.local.dao.TvDao
import com.oazisn.moviecatalog.core.data.local.entity.Movie
import com.oazisn.moviecatalog.core.data.local.entity.Tv

/**
 * The Room database for this app
 */
@Database(entities = [Movie::class, Tv::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun tvDao(): TvDao

    companion object {

        const val DATABASE_NAME = "db_moviecatalog"

    }
}
