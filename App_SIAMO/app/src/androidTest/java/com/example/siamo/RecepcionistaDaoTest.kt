package com.example.siamo

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.siamo.data.Dao.RecepcionistaDao
import com.example.siamo.data.Entity.Empleado
import com.example.siamo.data.Entity.Persona
import com.example.siamo.data.Entity.Recepcionista
import com.example.siamo.data.SiamoDatabase
import org.junit.Assert.assertTrue
import org.junit.Assert.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import kotlin.jvm.Throws

@RunWith(AndroidJUnit4::class)
class RecepcionistaDaoTest {
    private lateinit var recepcionistaDao: RecepcionistaDao
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
    private val recepcionista1 = Recepcionista(
        idEmpleado = 1)
    private val recepcionista2 = Recepcionista(
        idEmpleado = 2)

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        siamoDatabase = Room.inMemoryDatabaseBuilder(context, SiamoDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        recepcionistaDao = siamoDatabase.recepcionistaDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        siamoDatabase.close()
    }

    @Test
    @Throws(Exception::class)
    fun daoInsert_insertsRecepcionistaIntoDB() = runBlocking {
        addOneRecepcionistaToDb()
        val allRecepcionistas = recepcionistaDao.getAllRecepcionistas().first()
        assertEquals(allRecepcionistas[0], recepcionista1)
    }

    @Test
    @Throws(Exception::class)
    fun daoGetAllRecepcionistas_returnsAllRecepcionistasFromDB() = runBlocking {
        addTwoRecepcionistasToDb()
        val allRecepcionistas = recepcionistaDao.getAllRecepcionistas().first()
        assertEquals(allRecepcionistas[0], recepcionista1)
        assertEquals(allRecepcionistas[1], recepcionista2)
    }

    @Test
    @Throws(Exception::class)
    fun daoGetRecepcionista_returnsRecepcionistaFromDB() = runBlocking {
        addOneRecepcionistaToDb()
        val recepcionista = recepcionistaDao.getRecepcionista(1)
        assertEquals(recepcionista.first(), recepcionista1)
    }

    @Test
    @Throws(Exception::class)
    fun daoDeleteRecepcionistas_deletesAllRecepcionistasFromDB() = runBlocking {
        addTwoRecepcionistasToDb()
        recepcionistaDao.delete(recepcionista1)
        recepcionistaDao.delete(recepcionista2)
        val allRecepcionistas = recepcionistaDao.getAllRecepcionistas().first()
        assertTrue(allRecepcionistas.isEmpty())
    }

    @Test
    @Throws(Exception::class)
    fun daoUpdateRecepcionistas_updatesRecepcionistasInDB() = runBlocking {
        addTwoRecepcionistasToDb()
        val updatedRecepcionista1 = Recepcionista(
            idRecepcionista = 1,
            idEmpleado = 2)
        val updatedRecepcionista2 = Recepcionista(
            idRecepcionista = 2,
            idEmpleado = 1)

        recepcionistaDao.update(updatedRecepcionista1)
        recepcionistaDao.update(updatedRecepcionista2)

        val allRecepcionistas = recepcionistaDao.getAllRecepcionistas().first()

        assertEquals(allRecepcionistas[0], updatedRecepcionista1)
        assertEquals(allRecepcionistas[1], updatedRecepcionista2)
    }

    private suspend fun addOneRecepcionistaToDb() {
        siamoDatabase.personaDao().insert(persona1)
        siamoDatabase.empleadoDao().insert(empleado1)
        recepcionistaDao.insert(recepcionista1)
    }

    private suspend fun addTwoRecepcionistasToDb() {
        siamoDatabase.personaDao().insert(persona1)
        siamoDatabase.personaDao().insert(persona2)
        siamoDatabase.empleadoDao().insert(empleado1)
        siamoDatabase.empleadoDao().insert(empleado2)
        recepcionistaDao.insert(recepcionista1)
        recepcionistaDao.insert(recepcionista2)
    }
}