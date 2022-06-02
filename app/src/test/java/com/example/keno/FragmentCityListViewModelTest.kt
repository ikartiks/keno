package com.example.keno

import com.example.api.client.City
import com.example.keno.vm.FragmentCityListViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlin.test.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.test.KoinTest

@ExperimentalCoroutinesApi
class FragmentCityListViewModelTest : KoinTest {

    private val citiesRepo: CitiesRepo = mockk()
    private lateinit var vm: FragmentCityListViewModel

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        coEvery { citiesRepo.getCitiesList() } returns listOf(City("1","Brisbane"))
        vm = FragmentCityListViewModel(citiesRepo)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun testGetCities(): Unit = runBlocking {
        launch(Dispatchers.Main) {  // Will be launched in the mainThreadSurrogate dispatcher
            val cities = vm.getCitiesList()
            assertEquals(cities.size,1)
            assertEquals(cities[0].name,"Brisbane")
        }
    }
}
