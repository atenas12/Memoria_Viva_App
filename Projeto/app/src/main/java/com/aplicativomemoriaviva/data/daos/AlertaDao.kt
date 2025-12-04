package com.aplicativomemoriaviva.data.daos

import androidx.room.*
import com.aplicativomemoriaviva.data.entities.AlertaMedicacao
import kotlinx.coroutines.flow.Flow

@Dao
interface AlertaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(alerta: AlertaMedicacao): Long

    @Query("SELECT * FROM alertas_medicacoes WHERE id_medicacao = :medId")
    fun getByMedicacaoFlow(medId: Int): Flow<List<AlertaMedicacao>>
}
