package com.oazisn.moviecatalog.core.data.local.dao

import androidx.room.*
import com.oazisn.moviecatalog.core.data.local.entity.Tv
import kotlinx.coroutines.flow.Flow

@Dao
interface TvDao {
    @Insert
    suspend fun insert(tv: Tv): Long

    @Delete
    suspend fun delete(tv: Tv)

    @Transaction
    @Query("SELECT EXISTS(SELECT 1 FROM tb_tv WHERE id = :id LIMIT 1)")
    fun isExist(id: Int?): Boolean

    @Query("SELECT * FROM tb_tv")
    fun getData(): Flow<List<Tv>>

}