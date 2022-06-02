package com.example.keno

import com.example.api.Service
import com.example.api.client.CitiesResponse
import com.example.api.client.City
import com.example.api.client.CityDetailResponse
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
import retrofit2.Response

@ExperimentalCoroutinesApi
class CitiesRepoTest : KoinTest {

    private val service:Service = mockk()
    private lateinit var citiesRepo: CitiesRepo

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        val city = City("1","Brisbane")
        val list = ArrayList<City>()
        list.add(city)
        coEvery { service.fetchCities() } returns Response.success(CitiesResponse("Something", cities = list))
        coEvery { service.fetchCityDetails(any()) } returns Response.success(CityDetailResponse("1","Brisbane"))
        citiesRepo = CitiesRepo(service)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun testGetCities(): Unit = runBlocking {
        launch(Dispatchers.Main) {  // Will be launched in the mainThreadSurrogate dispatcher
            val cities = citiesRepo.getCitiesList()
            assertEquals(cities.size,1)
            assertEquals(cities[0].name,"Brisbane")
        }
    }

    @Test
    fun testCityDetail(): Unit = runBlocking {
        launch(Dispatchers.Main) {  // Will be launched in the mainThreadSurrogate dispatcher
            val cityDetail = citiesRepo.getCityDetails("1")
            assertEquals(cityDetail.name,"Brisbane")
        }
    }
}
