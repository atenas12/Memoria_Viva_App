package com.aplicativomemoriaviva.data.daos

import androidx.room.*
import com.aplicativomemoriaviva.data.entities.Medicacao
import kotlinx.coroutines.flow.Flow

@Dao
interface MedicacaoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(medicacao: Medicacao): Long

    @Query("SELECT * FROM medicacoes WHERE id_idoso = :idosoId ORDER BY nome_medicamento")
    fun getByIdosoFlow(idosoId: Int): Flow<List<Medicacao>>
}
