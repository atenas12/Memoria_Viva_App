package com.aplicativomemoriaviva.data.entities

import androidx.room.*

@Entity(tableName = "usuarios")
data class Usuario(
    @PrimaryKey(autoGenerate = true) val id_usuario: Int = 0,
    val cpf: String,
    val nome: String,
    val endereco: String,
    val cep: String,
    val email: String,
    val senha: String,
    val tipo: String,
    val data_cadastro: String
)

@Entity(
    tableName = "idosos",
    foreignKeys = [
        ForeignKey(
            entity = Usuario::class,
            parentColumns = ["id_usuario"],
            childColumns = ["responsavel_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("responsavel_id")]
)
data class Idoso(
    @PrimaryKey(autoGenerate = true) val id_idoso: Int = 0,
    val nome_completo: String,
    val data_nascimento: String,
    val altura: Double,
    val peso: Double,
    val doencas_preexistentes: String?,
    val alergias: String?,
    val responsavel_id: Int
)

@Entity(
    tableName = "observacoes",
    foreignKeys = [
        ForeignKey(
            entity = Idoso::class,
            parentColumns = ["id_idoso"],
            childColumns = ["id_idoso"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Usuario::class,
            parentColumns = ["id_usuario"],
            childColumns = ["id_usuario"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("id_idoso"), Index("id_usuario")]
)
data class Observacao(
    @PrimaryKey(autoGenerate = true) val id_observacao: Int = 0,
    val id_idoso: Int,
    val id_usuario: Int,
    val data_observacao: String,
    val texto: String
)

@Entity(
    tableName = "medicacoes",
    foreignKeys = [
        ForeignKey(
            entity = Idoso::class,
            parentColumns = ["id_idoso"],
            childColumns = ["id_idoso"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("id_idoso")]
)
data class Medicacao(
    @PrimaryKey(autoGenerate = true) val id_medicacao: Int = 0,
    val id_idoso: Int,
    val nome_medicamento: String,
    val dose: String,
    val horario: String,
    val observacoes: String?
)

@Entity(
    tableName = "alertas_medicacoes",
    foreignKeys = [
        ForeignKey(
            entity = Medicacao::class,
            parentColumns = ["id_medicacao"],
            childColumns = ["id_medicacao"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("id_medicacao")]
)
data class AlertaMedicacao(
    @PrimaryKey(autoGenerate = true) val id_alerta: Int = 0,
    val id_medicacao: Int,
    val horario_alerta: String,
    val status: String
)
