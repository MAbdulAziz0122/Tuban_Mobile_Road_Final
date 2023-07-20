package com.example.jalan_tuban_mobile.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "road_table")
data class Road (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val address: String,
    var latitude: Double? = null,
    var longitude: Double? = null,
    var photoPath: String? // Kolom baru untuk menyimpan path foto

) : Parcelable



