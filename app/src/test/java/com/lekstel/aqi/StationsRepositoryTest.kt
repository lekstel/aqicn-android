package com.lekstel.aqi

import com.lekstel.aqi.stations.data.StationsRepository
import com.lekstel.aqi.stations.data.cache.entity.StationDetailsEntity
import com.lekstel.aqi.stations.data.cache.mapper.StationDetailsEntityMapper
import com.lekstel.aqi.stations.data.mapper.StationDetailsDtoMapper
import com.lekstel.aqi.stations.data.mapper.StationOnMapDtoMapper
import com.lekstel.aqi.stations.data.model.StationDetailsDTO
import com.lekstel.aqi.stations.data.source.IStationsDataStore
import com.lekstel.aqi.stations.domain.repository.IStationsRepository
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.given
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.lang.Exception

@RunWith(MockitoJUnitRunner.Silent::class)
class StationsRepositoryTest {

    @Mock
    internal lateinit var remoteDataStore: IStationsDataStore

    @Mock
    internal lateinit var localDataStore: IStationsDataStore

    private lateinit var repo: IStationsRepository

    private val stationDetailsEntityMapper = StationDetailsEntityMapper()

    private val stationDetailsDtoMapper = StationDetailsDtoMapper()

    @Before
    fun setUp() {
        repo = StationsRepository(localDataStore, remoteDataStore, StationOnMapDtoMapper(), stationDetailsDtoMapper)
    }

    @Test
    fun testSuccessRemoteDataCase() {
        runBlocking {
            given(remoteDataStore.getStationDetails(any()))
                    .willReturn(retrofitDetails)

            given(localDataStore.getStationDetails(any()))
                    .willReturn(stationDetailsEntityMapper.map(dbDetails))

            val result = repo.getStationDetails(any())

            assertEquals(result, stationDetailsDtoMapper.map(retrofitDetails))
        }
    }

    @Test
    fun testEmptyRemoteDataCase() {
        runBlocking {
            given(remoteDataStore.getStationDetails(any()))
                    .willReturn(null)

            given(localDataStore.getStationDetails(any()))
                    .willReturn(stationDetailsEntityMapper.map(dbDetails))

            val result = repo.getStationDetails(any())

            assertEquals(result, stationDetailsEntityMapper.map(dbDetails).let { stationDetailsDtoMapper.map(it) })
        }
    }

    @Test(expected = Exception::class)
    fun testFailedRemoteDataCase() {
        runBlocking {
            given(remoteDataStore.getStationDetails(any()))
                    .willThrow(Exception("network error"))

            given(localDataStore.getStationDetails(any()))
                    .willReturn(stationDetailsEntityMapper.map(dbDetails))

            val result = repo.getStationDetails(any())

            assertEquals(result, stationDetailsEntityMapper.map(dbDetails).let { stationDetailsDtoMapper.map(it) })
        }
    }

    private val retrofitDetails = StationDetailsDTO(
            idx = 1,
            city = StationDetailsDTO.CityDTO(
                    geo = listOf(0.0, 0.0),
                    name = "Station #1, New York"
            ),
            iaqi = StationDetailsDTO.IaqiDTO(
                    pm10 = StationDetailsDTO.IaqiDTO.Pm10DTO(1.0),
                    pm25 = StationDetailsDTO.IaqiDTO.Pm25DTO(2.0)
            ),
            time = StationDetailsDTO.TimeDTO("2020-11-12T21:45:00+02:00")
    )

    private val dbDetails = StationDetailsEntity(
            id = 1,
            lat = 0.0,
            lon = 0.0,
            name = "Station #1, New York",
            updateTime = "2020-11-10T20:15:00+02:00",
            pm25 = 3.0,
            pm10 = 4.0
    )
}
