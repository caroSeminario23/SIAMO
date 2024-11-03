package com.example.siamo.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.siamo.data.Dao.ClienteDao
import com.example.siamo.data.Dao.EmpleadoDao
import com.example.siamo.data.Dao.PersonaDao
import com.example.siamo.data.Dao.RecepcionistaDao
import com.example.siamo.data.Dao.TecnicoDao
import com.example.siamo.data.Entity.Cliente
import com.example.siamo.data.Entity.Empleado
import com.example.siamo.data.Entity.Persona
import com.example.siamo.data.Entity.Recepcionista
import com.example.siamo.data.Entity.Tecnico

@Database(entities = [Persona::class, Empleado::class, Tecnico::class, Recepcionista::class, Cliente::class], version = 1, exportSchema = false)
abstract class SiamoDatabase : RoomDatabase() {
    abstract fun personaDao(): PersonaDao
    abstract fun empleadoDao(): EmpleadoDao
    abstract fun tecnicoDao(): TecnicoDao
    abstract fun recepcionistaDao(): RecepcionistaDao
    abstract fun clienteDao(): ClienteDao

    companion object {
        @Volatile
        private var Instance: SiamoDatabase? = null

        fun getDatabase(context: Context): SiamoDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, SiamoDatabase::class.java, "siamo_database")
                    /**
                    * Setting this option in your app's database builder means that Room
                    * permanently deletes all data from the tables in your database when it
                    * attempts to perform a migration with no defined migration path.
                    */
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}