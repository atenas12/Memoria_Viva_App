package com.aplicativomemoriaviva.data.repository

import com.aplicativomemoriaviva.data.daos.*
import com.aplicativomemoriaviva.data.entities.*
import kotlinx.coroutines.flow.Flow

class AppRepository(
    private val usuarioDao: UsuarioDao,
    private val idosoDao: IdosoDao,
    private val observacaoDao: ObservacaoDao,
    private val medicacaoDao: MedicacaoDao,
    private val alertaDao: AlertaDao
) {

    // USUARIO
    suspend fun insertUsuario(u: Usuario) = usuarioDao.insert(u)
    fun usuariosFlow(): Flow<List<Usuario>> = usuarioDao.getAllFlow()
    suspend fun login(email: String, senha: String) = usuarioDao.login(email, senha)

    // IDOSO
    suspend fun insertIdoso(i: Idoso) = idosoDao.insert(i)
    suspend fun updateIdoso(i: Idoso) = idosoDao.update(i)
    suspend fun deleteIdoso(i: Idoso) = idosoDao.delete(i)
    fun idososFlow(): Flow<List<Idoso>> = idosoDao.getAllFlow()
    suspend fun getIdosoById(id: Int) = idosoDao.getById(id)

    // OBSERVACAO
    suspend fun insertObservacao(o: Observacao) = observacaoDao.insert(o)
    fun observacoesByIdosoFlow(id: Int) = observacaoDao.getByIdosoFlow(id)

    // MEDICACAO
    suspend fun insertMedicacao(m: Medicacao) = medicacaoDao.insert(m)
    fun medicacoesByIdosoFlow(id: Int) = medicacaoDao.getByIdosoFlow(id)

    // ALERTA
    suspend fun insertAlerta(a: AlertaMedicacao) = alertaDao.insert(a)
    fun alertasByMedicacaoFlow(id: Int) = alertaDao.getByMedicacaoFlow(id)
}
