package br.com.silas.digiocash.viewmodel

import br.com.silas.digiocash.mock.getErrorCallViewModel
import br.com.silas.digiocash.mock.getSucessCallViewModel
import br.com.silas.digiocash.mock.newHomeResponseViewModel
import br.com.silas.digiocash.repositoy.HomeRepository
import br.com.silas.digiocash.viewmodel.events.HomeViewModelState
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class HomeViewModelTest {

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @MockK
    private lateinit var repository: HomeRepository

    private lateinit var userViewModel: HomeViewModel

    @Before
    fun init() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        Dispatchers.setMain(mainThreadSurrogate)
        userViewModel = HomeViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun `valida se a chamada de api foi um sucesso`() {
        GlobalScope.launch {
            coEvery { repository.getFilesHome() } returns getSucessCallViewModel()
            userViewModel.loadFiles()
            Assert.assertEquals(
                HomeViewModelState.SucessCallApi(newHomeResponseViewModel()),
                userViewModel.viewState.value
            )
        }
    }

    @Test
    fun `valida se a chamada de api deu algum erro`() {
        GlobalScope.launch {
            coEvery { repository.getFilesHome() } returns getErrorCallViewModel()
            userViewModel.loadFiles()
            Assert.assertEquals(
                HomeViewModelState.ErrorCallApi("Erro ao fazer a chamada"),
                userViewModel.viewState.value
            )
        }
    }
}