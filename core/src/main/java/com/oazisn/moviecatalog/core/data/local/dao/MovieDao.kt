package com.oazisn.moviecatalog.core.data.local.dao

import androidx.room.*
import com.oazisn.moviecatalog.core.data.local.entity.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert
    suspend fun insert(movie: Movie): Long

    @Delete
    suspend fun delete(movie: Movie)

    @Transaction
    @Query("SELECT EXISTS(SELECT 1 FROM tb_movie WHERE id = :id LIMIT 1)")
    fun isExist(id: Int?): Boolean

    @Query("SELECT * FROM tb_movie")
    fun getData(): Flow<List<Movie>>

}