package com.example.jalan_tuban_mobile.repository

import com.example.jalan_tuban_mobile.dao.RoadDao
import com.example.jalan_tuban_mobile.model.Road
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class RoadRepository(private val roadDao: RoadDao) {
    val allroads: Flow<List<Road>> = roadDao.getAllRoad()

    suspend fun insertroad(road: Road){
        withContext(Dispatchers.IO){
            roadDao.insertRoad(road)
        }
    }

    suspend fun deleteroad(road: Road){
        withContext(Dispatchers.IO){
            roadDao.deleteRoad(road)
        }
    }

    suspend fun updateroad(road: Road){
        withContext(Dispatchers.IO){
            roadDao.updateRoad(road)
        }
    }

}