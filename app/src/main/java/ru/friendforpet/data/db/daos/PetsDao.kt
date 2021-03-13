package ru.friendforpet.data.db.daos

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ru.friendforpet.data.db.entities.PetEntity

@Dao
interface PetsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(genres: List<PetEntity>)

    @Query("SELECT * FROM PetEntity")
     fun getPetsFlow(): Flow<List<PetEntity>>

    @Query("SELECT * FROM PetEntity WHERE _id = :petId LIMIT 1")
     fun getPetsByIdFlow(petId:Int): Flow<PetEntity?>

    @Query("DELETE FROM PetEntity")
    suspend fun clear()

    @Transaction
    suspend fun replaceAll(petsList: List<PetEntity>) {
        clear()
        insertAll(petsList)
    }

    @Query("""
        UPDATE PetEntity
        SET isLiked = :isLiked
        WHERE _id = :petId
    """)
    suspend fun updateIsLiked(petId:Int, isLiked:Boolean)

    @Update
    suspend fun updatePet(pet:PetEntity)
}