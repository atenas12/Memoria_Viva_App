package com.aplicativomemoriaviva.data.daos

import androidx.room.*
import com.aplicativomemoriaviva.data.entities.Idoso
import kotlinx.coroutines.flow.Flow

@Dao
interface IdosoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(idoso: Idoso): Long

    @Update
    suspend fun update(idoso: Idoso)

    @Delete
    suspend fun delete(idoso: Idoso)

    @Query("SELECT * FROM idosos ORDER BY nome_completo")
    fun getAllFlow(): Flow<List<Idoso>>

    @Query("SELECT * FROM idosos WHERE id_idoso = :id LIMIT 1")
    suspend fun getById(id: Int): Idoso?
}
