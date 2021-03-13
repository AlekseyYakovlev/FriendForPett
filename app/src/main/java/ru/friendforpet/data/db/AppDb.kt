package ru.friendforpet.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
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
        private const val DATA_BASE_NAME = "Pets_Data_Base.db"
        const val TABLE_NAME = "Pets_Table"

        @Volatile
        private var INSTANCE: RoomDatabase? = null


        fun getInstance(context: Context):RoomDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context,AppDb::class.java, DATA_BASE_NAME
                )
                    .build()
                INSTANCE = instance
                instance
            }

        }
    }


}