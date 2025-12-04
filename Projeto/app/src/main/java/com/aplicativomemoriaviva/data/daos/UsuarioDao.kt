package com.aplicativomemoriaviva.data.daos

import androidx.room.*
import com.aplicativomemoriaviva.data.entities.Usuario
import kotlinx.coroutines.flow.Flow

@Dao
interface UsuarioDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(usuario: Usuario): Long

    @Query("SELECT * FROM usuarios ORDER BY nome")
    fun getAllFlow(): Flow<List<Usuario>>

    @Query("SELECT * FROM usuarios WHERE email = :email AND senha = :senha LIMIT 1")
    suspend fun login(email: String, senha: String): Usuario?
}
