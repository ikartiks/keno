package com.example.keno

import com.example.api.client.CityDetailResponse
import com.example.api.client.Weather
import com.example.keno.vm.FragmentCityDetailViewModel
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
class FragmentCityDetailViewModelTest : KoinTest {

    private val citiesRepo: CitiesRepo = mockk()
    private lateinit var vm: FragmentCityDetailViewModel

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        val weather = Weather(1,"Rainy","Rainy",null)
        val list = ArrayList<Weather>()
        list.add(weather)
        coEvery { citiesRepo.getCityDetails(any()) } returns CityDetailResponse("1",
            "Brisbane", weather = list)
        vm = FragmentCityDetailViewModel(citiesRepo)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun testGetCityDetail(): Unit = runBlocking {
        launch(Dispatchers.Main) {  // Will be launched in the mainThreadSurrogate dispatcher
            val city = vm.getCityDetails("1")
            assertEquals(city.name,"Brisbane")
            assertEquals(city.weather[0].description,"Rainy")
        }
    }
}
