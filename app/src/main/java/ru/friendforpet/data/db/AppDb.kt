package ru.friendforpet.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.friendforpet.data.db.daos.PetsDao
import ru.friendforpet.data.db.entities.PetEntity

@Database(
    entities = [PetEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDb : RoomDatabase() {

    abstract fun petsDao():PetsDao

    companion object{
        const val DATA_BASE_NAME = "Pets_Data_Base.db"
        const val TABLE_NAME = "Pets_Table"

    }


}