package com.example.siamo

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.siamo.data.Dao.TecnicoDao
import com.example.siamo.data.Entity.Empleado
import com.example.siamo.data.Entity.Persona
import com.example.siamo.data.Entity.Tecnico
import com.example.siamo.data.SiamoDatabase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.io.IOException
import kotlin.jvm.Throws

class TecnicoDaoTest {
    private lateinit var tecnicoDao: TecnicoDao
    private lateinit var siamoDatabase: SiamoDatabase
    private val persona1 = Persona(
        tipoDoc = 0,
        numDoc = "12345678",
        nombres = "Juan Carlos",
        apellidos = "Perez Aguilar",
        direccion = "Jr. Los Pinos 123",
        sexo = "M",
        telefono = "987654321",
        correo = "juanperez@gmail.com")
    private val persona2 = Persona(
        tipoDoc = 0,
        numDoc = "87654321",
        nombres = "Maria Soledad",
        apellidos = "Gomez Torres",
        direccion = "Av. Los Alamos 456",
        sexo = "F",
        telefono = "912345678",
        correo = "mariagomez@gmail.com")
    private val empleado1 = Empleado(
        idPersona = 1,
        fechaIngreso = "2021-10-01",
        codEmpleado = 20240001,
        contrasenia = "123456")
    private val empleado2 = Empleado(
        idPersona = 2,
        fechaIngreso = "2021-10-01",
        codEmpleado = 20240002,
        contrasenia = "123456")
    private val tecnico1 = Tecnico(
        idEmpleado = 1)
    private val tecnico2 = Tecnico(
        idEmpleado = 2)

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        siamoDatabase = Room.inMemoryDatabaseBuilder(context, SiamoDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        tecnicoDao = siamoDatabase.tecnicoDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        siamoDatabase.close()
    }

    @Test
    @Throws(Exception::class)
    fun daoInsert_insertsTecnicoIntoDB() = runBlocking {
        addOneTecnicoToDb()
        val allTecnicos = tecnicoDao.getAllTecnicos().first()
        assertEquals(allTecnicos[0], tecnico1)
    }

    @Test
    @Throws(Exception::class)
    fun daoGetAllTecnicos_returnsAllTecnicosFromDB() = runBlocking {
        addTwoTecnicosToDb()
        val allTecnicos = tecnicoDao.getAllTecnicos().first()
        assertEquals(allTecnicos[0], tecnico1)
        assertEquals(allTecnicos[1], tecnico2)
    }

    @Test
    @Throws(Exception::class)
    fun daoGetTecnico_returnsTecnicoFromDB() = runBlocking {
        addOneTecnicoToDb()
        val tecnico = tecnicoDao.getTecnico(1)
        assertEquals(tecnico.first(), tecnico1)
    }

    @Test
    @Throws(Exception::class)
    fun daoDeleteTecnicos_deletesAllTecnicosFromDB() = runBlocking {
        addTwoTecnicosToDb()
        tecnicoDao.delete(tecnico1)
        tecnicoDao.delete(tecnico2)
        val allTecnicos = tecnicoDao.getAllTecnicos().first()
        assertTrue(allTecnicos.isEmpty())
    }

    @Test
    @Throws(Exception::class)
    fun daoUpdateTecnicos_updatesTecnicosInDB() = runBlocking {
        addTwoTecnicosToDb()
        val updatedTecnico1 = Tecnico(
            idTecnico = 1,
            idEmpleado = 2)
        val updatedTecnico2 = Tecnico(
            idTecnico = 2,
            idEmpleado = 1)

        tecnicoDao.update(updatedTecnico1)
        tecnicoDao.update(updatedTecnico2)

        val allTecnicos = tecnicoDao.getAllTecnicos().first()

        assertEquals(allTecnicos[0], updatedTecnico1)
        assertEquals(allTecnicos[1], updatedTecnico2)
    }

    private suspend fun addOneTecnicoToDb() {
        siamoDatabase.personaDao().insert(persona1)
        siamoDatabase.empleadoDao().insert(empleado1)
        tecnicoDao.insert(tecnico1)
    }

    private suspend fun addTwoTecnicosToDb() {
        siamoDatabase.personaDao().insert(persona1)
        siamoDatabase.personaDao().insert(persona2)
        siamoDatabase.empleadoDao().insert(empleado1)
        siamoDatabase.empleadoDao().insert(empleado2)
        tecnicoDao.insert(tecnico1)
        tecnicoDao.insert(tecnico2)
    }
}