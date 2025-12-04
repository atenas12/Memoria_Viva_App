package com.aplicativomemoriaviva.data.daos

import androidx.room.*
import com.aplicativomemoriaviva.data.entities.Observacao
import kotlinx.coroutines.flow.Flow

@Dao
interface ObservacaoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(observacao: Observacao): Long

    @Query("SELECT * FROM observacoes WHERE id_idoso = :idosoId ORDER BY data_observacao DESC")
    fun getByIdosoFlow(idosoId: Int): Flow<List<Observacao>>
}
