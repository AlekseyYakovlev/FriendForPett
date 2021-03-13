package ru.friendforpet.data.db.entities

import android.provider.BaseColumns
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import ru.friendforpet.data.db.ListTypeConverter

@Entity
@TypeConverters(ListTypeConverter::class)
class PetEntity (
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = BaseColumns._ID)
    val _id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name ="sex")
    val sex: String,
    @ColumnInfo(name ="age")
    val age: Int,
    @ColumnInfo(name = "location")
    val location: String,
    @ColumnInfo(name = "size")
    val size: String,
    @ColumnInfo(name = "personality")
    val personality: String,
    @ColumnInfo(name = "hair")
    val hair: String,
    @ColumnInfo(name = "color")
    val color: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "tags")
    val tags: List<String>,
    @ColumnInfo(name = "addedDate")
    val addedDate: String,
    @ColumnInfo(name = "photo")
    val photo: String,
)