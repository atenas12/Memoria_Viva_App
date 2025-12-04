package com.aplicativomemoriaviva.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aplicativomemoriaviva.data.daos.*
import com.aplicativomemoriaviva.data.entities.*

@Database(
    entities = [
        Usuario::class,
        Idoso::class,
        Observacao::class,
        Medicacao::class,
        AlertaMedicacao::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun usuarioDao(): UsuarioDao
    abstract fun idosoDao(): IdosoDao
    abstract fun observacaoDao(): ObservacaoDao
    abstract fun medicacaoDao(): MedicacaoDao
    abstract fun alertaDao(): AlertaDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "memoria_viva.db"
                ).fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}
