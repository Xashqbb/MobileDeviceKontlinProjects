package com.example.travelmate.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize

@Entity(tableName = "tours")
@Parcelize
data class Tour(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val description: String,
    val country: String,
    val imageResource: Int,
    val tags: String,
    val latitude: Double,
    val longitude: Double
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readDouble(),
        parcel.readDouble()
    )

    override fun describeContents(): Int = 0

    companion object : Parceler<Tour> {

        override fun Tour.write(parcel: Parcel, flags: Int) {
            parcel.writeInt(id)
            parcel.writeString(title)
            parcel.writeString(description)
            parcel.writeString(country)
            parcel.writeInt(imageResource)
            parcel.writeString(tags)
            parcel.writeDouble(latitude)
            parcel.writeDouble(longitude)
        }

        override fun create(parcel: Parcel): Tour {
            return Tour(parcel)
        }
    }
}