package com.hyperelement.mvvmdemo.data.datasources.room.city

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = CityEntity.TABLE_NAME)
data class CityEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = MEMBER_ID)
    var id: Int = 0,

    @ColumnInfo(name = MEMBER_NAME)
    var name: String? = null

) : Parcelable {

    companion object {
        const val TABLE_NAME = "tbl_city"
        const val MEMBER_ID = "_id"
        const val MEMBER_NAME = "name"
    }
}