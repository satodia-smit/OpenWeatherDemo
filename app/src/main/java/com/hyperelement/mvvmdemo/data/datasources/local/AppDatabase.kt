package com.hyperelement.mvvmdemo.data.datasources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hyperelement.mvvmdemo.data.datasources.room.city.CityDao
import com.hyperelement.mvvmdemo.data.datasources.room.city.CityEntity

/**
 * Created By: dev1618
 * Created Date: 6/19/2019
 * Purpose: This class will used to have the local database and also we have registered
 * various converters and entities which we want to include on our room database.
 *
 * We have also setup the schema version so that when we update the schema version for next release
 * we can run the migrations on the same when version changes.
 *
 * We have exported the schema in JSON format as well, so that later on we can use for any import mechanism.
 */


@Database(
    entities = [CityEntity::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun cityDao(): CityDao
}