package com.example.siamo

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.siamo.data.Dao.ClienteDao
import com.example.siamo.data.Entity.Cliente
import com.example.siamo.data.Entity.Persona
import com.example.siamo.data.SiamoDatabase
import org.junit.Assert.assertTrue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import kotlin.jvm.Throws

@RunWith(AndroidJUnit4::class)
class ClienteDaoTest {
    private lateinit var clienteDao: ClienteDao
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
    private val cliente1 = Cliente(
        idPersona = 1)
    private val cliente2 = Cliente(
        idPersona = 2)

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        siamoDatabase = Room.inMemoryDatabaseBuilder(context, SiamoDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        clienteDao = siamoDatabase.clienteDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        siamoDatabase.close()
    }

    @Test
    @Throws(Exception::class)
    fun daoInsert_insertsClienteIntoDB() = runBlocking {
        addOneClienteToDb()
        val allClientes = clienteDao.getAllClientes().first()
        assertEquals(allClientes[0], cliente1)
    }

    @Test
    @Throws(Exception::class)
    fun daoGetAllClientes_returnsAllClientesFromDB() = runBlocking {
        addTwoClientesToDb()
        val allClientes = clienteDao.getAllClientes().first()
        assertEquals(allClientes[0], cliente1)
        assertEquals(allClientes[1], cliente2)
    }

    @Test
    @Throws(Exception::class)
    fun daoGetCliente_returnsClienteFromDB() = runBlocking {
        addOneClienteToDb()
        val cliente = clienteDao.getCliente(1)
        assertEquals(cliente.first(), cliente1)
    }

    @Test
    @Throws(Exception::class)
    fun daoDeleteClientes_deletesAllClientesFromDB() = runBlocking {
        addTwoClientesToDb()
        clienteDao.delete(cliente1)
        clienteDao.delete(cliente2)
        val allClientes = clienteDao.getAllClientes().first()
        assertTrue(allClientes.isEmpty())
    }

    @Test
    @Throws(Exception::class)
    fun daoUpdateClientes_updatesClientesInDB() = runBlocking {
        addTwoClientesToDb()
        val updatedCliente1 = Cliente(
            idCliente = 1,
            idPersona = 2)
        val updatedCliente2 = Cliente(
            idCliente = 2,
            idPersona = 1)

        clienteDao.update(updatedCliente1)
        clienteDao.update(updatedCliente2)

        val allClientes = clienteDao.getAllClientes().first()

        assertEquals(allClientes[0], updatedCliente1)
        assertEquals(allClientes[1], updatedCliente2)
    }

    private suspend fun addOneClienteToDb() {
        siamoDatabase.personaDao().insert(persona1)
        clienteDao.insert(cliente1)
    }

    private suspend fun addTwoClientesToDb() {
        siamoDatabase.personaDao().insert(persona1)
        siamoDatabase.personaDao().insert(persona2)
        clienteDao.insert(cliente1)
        clienteDao.insert(cliente2)
    }
}