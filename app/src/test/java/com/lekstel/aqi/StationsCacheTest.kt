package com.lekstel.aqi

import com.google.android.gms.maps.model.LatLng
import com.lekstel.aqi.main.data.cache.AppDatabase
import com.lekstel.aqi.stations.data.cache.IStationsCache
import com.lekstel.aqi.stations.data.cache.StationsCache
import com.lekstel.aqi.stations.data.cache.dao.StationsDao
import com.lekstel.aqi.stations.data.cache.entity.StationDetailsEntity
import com.lekstel.aqi.stations.data.cache.entity.StationOnMapEntity
import com.nhaarman.mockitokotlin2.given
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.Silent::class)
class StationsCacheTest {

    @Mock
    internal lateinit var database: AppDatabase

    private lateinit var cache: IStationsCache

    @Before
    fun setUp() {
        cache = StationsCache(database)
    }

    @Test
    fun testRadiusFiltering() {
        runBlocking {
            given(database.stationsDao()).willReturn(object : StationsDao {
                override fun flowStations() = flow { emit(dbStations) }
                override fun insertStations(list: List<StationOnMapEntity>) {}
                override suspend fun getStationDetails(id: Int): StationDetailsEntity? { return null }
                override suspend fun saveStationsDetails(details: StationDetailsEntity) {}
            })

            val result = cache.flowStations(myLatLng, 5000, 0).first()

            assertEquals(result, radius5KmDbStations)
        }
    }

    @Test
    fun testQualityFiltering() {
        runBlocking {
            given(database.stationsDao()).willReturn(object : StationsDao {
                override fun flowStations() = flow { emit(dbStations) }
                override fun insertStations(list: List<StationOnMapEntity>) {}
                override suspend fun getStationDetails(id: Int): StationDetailsEntity? { return null }
                override suspend fun saveStationsDetails(details: StationDetailsEntity) {}
            })

            val result = cache.flowStations(myLatLng, 10000, 20).first()

            assertEquals(result, min20QualityDbStations)
        }
    }

    private val dbStations = listOf(
            StationOnMapEntity(
                    uid = 1,
                    lat = 50.444,
                    lon = 30.54,
                    aqi = "15",
                    name = "#1",
                    time = "2020-11-12T21:45:00+02:00"
            ),
            StationOnMapEntity(
                    uid = 2,
                    lat = 50.434,
                    lon = 30.432,
                    aqi = "18",
                    name = "#2",
                    time = "2020-11-12T21:45:00+02:00"
            ),
            StationOnMapEntity(
                    uid = 3,
                    lat = 50.411693,
                    lon = 30.619027,
                    aqi = "30",
                    name = "#3",
                    time = "2020-11-12T21:45:00+02:00"
            ),
            StationOnMapEntity(
                    uid = 4,
                    lat = 50.411694,
                    lon = 30.619028,
                    aqi = "-",
                    name = "#4",
                    time = "2020-11-12T21:45:00+02:00"
            )
    )

    private val min20QualityDbStations = listOf(
            StationOnMapEntity(
                    uid = 3,
                    lat = 50.411693,
                    lon = 30.619027,
                    aqi = "30",
                    name = "#3",
                    time = "2020-11-12T21:45:00+02:00"
            ),
            StationOnMapEntity(
                    uid = 4,
                    lat = 50.411694,
                    lon = 30.619028,
                    aqi = "-",
                    name = "#4",
                    time = "2020-11-12T21:45:00+02:00"
            )
    )

    private val radius5KmDbStations = listOf(
            StationOnMapEntity(
                    uid = 1,
                    lat = 50.444,
                    lon = 30.54,
                    aqi = "15",
                    name = "#1",
                    time = "2020-11-12T21:45:00+02:00"
            )
    )

    private val myLatLng = LatLng(50.428, 30.539)
}