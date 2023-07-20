package com.example.jalan_tuban_mobile.Application

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.jalan_tuban_mobile.dao.RoadDao
import com.example.jalan_tuban_mobile.dao.UserDao
import com.example.jalan_tuban_mobile.model.Road
import com.example.jalan_tuban_mobile.model.User

@Database(entities = [Road::class, User::class], version = 3, exportSchema = false)
abstract class RoadDatabase : RoomDatabase() {
    abstract fun roadDao(): RoadDao
    abstract fun userDao(): UserDao

    companion object {
        private const val DATABASE_NAME = "road_database.db"

        @Volatile
        private var INSTANCE: RoadDatabase? = null

        fun getDatabase(context: Context): RoadDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoadDatabase::class.java,
                    DATABASE_NAME
                )
                    .addMigrations(MIGRATION_1_2)
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }

        private val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE road_table ADD COLUMN latitude REAL DEFAULT NULL")
                database.execSQL("ALTER TABLE road_table ADD COLUMN longitude REAL DEFAULT NULL")
            }
        }
    }
}
