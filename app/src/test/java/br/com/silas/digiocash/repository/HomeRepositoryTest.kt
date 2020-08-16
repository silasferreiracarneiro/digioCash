package br.com.silas.digiocash.repository

import br.com.silas.digiocash.api.Api
import br.com.silas.digiocash.mock.getErrorCallApiRepository
import br.com.silas.digiocash.mock.getSucessCallApiRepository
import br.com.silas.digiocash.repositoy.HomeRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class HomeRepositoryTest {

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @MockK
    private lateinit var api: Api

    private lateinit var repository: HomeRepository

    @Before
    fun init() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        Dispatchers.setMain(mainThreadSurrogate)
        repository = HomeRepository(api)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun `testa se o retorno da api foi com sucesso`() {
        coEvery { api.getHome() } returns getSucessCallApiRepository()
        runBlocking {
            val files = repository.getFilesHome()
            Assert.assertTrue(
                files.isSucess()
            )
        }
    }

    @Test
    fun `testa se o retorno da api veio com erro`() {
        coEvery { api.getHome() } returns getErrorCallApiRepository()
        GlobalScope.launch {
            val files = repository.getFilesHome()
            Assert.assertFalse(
                files.isSucess()
            )
        }
    }
}