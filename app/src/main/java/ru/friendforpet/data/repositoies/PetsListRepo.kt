package ru.friendforpet.data.repositoies

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import ru.friendforpet.data.db.daos.PetsDao
import ru.friendforpet.data.db.entities.PetEntity
import ru.friendforpet.data.remote.RestService
import ru.friendforpet.data.remote.resp.PetsResponse
import ru.friendforpet.model.Pet
import javax.inject.Inject

class PetsListRepo @Inject constructor(
    private val petsDao: PetsDao,
    private val network: RestService,
) {
    val petsListFlow: Flow<List<Pet>> =
        petsDao.getPetsFlow().map { list ->
            list.map { it.toPet() }
        }

    suspend fun insertInitialValues() {
        getPetsFromNet()
    }

    suspend fun updateIsLiked(petId: Int, isLiked: Boolean) {
        petsDao.updateIsLiked(petId, isLiked)
    }

    fun getPet(petId: Int): Flow<Pet> =
        petsDao.getPetsByIdFlow(petId).filterNotNull().map { it.toPet() }

    private suspend fun getPetsFromNet() {
        val petsFromNet = network.getPets()
        petsDao.insertAll(petsFromNet.map { it.toPetEntity() })
    }


    private fun PetEntity.toPet() = Pet(
        _id = _id,
        name = name,
        sex = sex,
        age = age,
        location = location,
        size = size,
        personality = personality,
        hair = hair,
        color = color,
        description = description,
        tags = tags,
        addedDate = addedDate,
        photo = photo,
        isLiked = isLiked,
        type = type,
    )
}

private fun PetsResponse.PetsResponseItem.toPetEntity(): PetEntity =
    PetEntity(
        _id = id,
        name = name,
        sex = if (gender == 1) "Девочка" else "Мальчик",
        age = age,
        location = when (cityId) {
            1 -> "Санкт-Петербург"
            2 -> "Казань"
            3 -> "Краснодар"
            else -> "Москва"
        },
        size = when (cityId) {
            1 -> "Маленький"
            2 -> "Средний"
            3 -> "Большой"
            else -> "Средний"
        },
        personality = if (petCharacter == 1) "Спокойный" else "Активный",
        hair = if (furr == 1) "Длинная" else "Короткая",
        color = when (color) {
            1 -> "Черный"
            2 -> "Коричневый"
            3 -> "Бежевый"
            4 -> "Рыжий"
            5 -> "Песочный"

            7 -> "Белый"
            8 -> "Дымчатый"
            else -> "Черный"
        },
        description = description,
        tags = emptyList(), //FIXME
        addedDate = "",
        photo = photos.firstOrNull()?.photoUrl ?: "",
        isLiked = false,
        type = if (pet == 1) "Кошка" else "Собака",
    )


/**
 * Структура БД
 * @param locations - "Moscow"
 * @param animalType
 * 1 - Cat
 * 2 - Dog
 * @param gender
 * 1 - Girl
 * 2 - Boy
 * @param age -
 * 3 Int
 * @param animalSize
 * 1 - small
 * 2 - middle
 * 3 - Big
 * @param animalHair
 * 1 - Long hair
 * 2 - short hair
 * @param color
 * 1 - Черный
 * 2 - коричневый
 * 3 - бежевый
 * 4 - рыжий
 * 5 - песочный
 * 7 - белый
 * 8 - дымчатый
 * @param character
 * 1 - Calm
 * 2 - Active
 */
data class Filters(
    val locations: List<String> = listOf("Moscow"),
    val animalType: Map<String, String> = mutableMapOf(),
    val age: List<String>? = listOf(),
    val gender: Map<String, String> = mutableMapOf(),
    val animalSize: Map<String, String> = mutableMapOf(),
    val animalHair: Map<String, String> = mutableMapOf(),
    val color: Map<String, String> = mutableMapOf(),
    val character: Map<String, String> = mutableMapOf()
) {
    companion object {

        const val FILTER_LOCATION = "location"
        const val FILTER_ANIMAL_TYPE = "type"
        const val FILTER_AGE = "age"
        const val FILTER_GENDER = "gender"
        const val FILTER_ANIMAL_SIZE = "animalSize"
        const val FILTER_ANIMAL_FURR = "animalFurr"
        const val FILTER_COLOR = "color"
        const val FILTER_CHARACTER = "character"

        fun getMockInstance(age: List<String>? = null): Filters = Filters(
            animalType = getAnimalType(),
            age = age,
            locations = listOf(
                "Москва",
                "Санкт-Петербург",
                "Казань",
                "Краснодар"
            ),
            gender = getGender(),
            animalSize = getAnimalSize(),
            animalHair = getAnimalFurr(),
            color = getColor(),
            character = getCharacter()
        )


        private fun getAnimalType(): Map<String, String> = mapOf(
            "1" to "Кошка",
            "2" to "Собака"
        )

        private fun getGender(): Map<String, String> = mapOf(
            "1" to "Мальчик",
            "2" to "Девочка"
        )

        private fun getAnimalSize(): Map<String, String> =
            mapOf(
                "1" to "Маленький",
                "2" to "Средний",
                "3" to "Большой"
            )

        private fun getAnimalFurr(): Map<String, String> =
            mapOf(
                "1" to "Длинная",
                "2" to "Короткая"
            )

        private fun getColor(): Map<String, String> =
            mapOf(
                "1" to "Черный",
                "2" to "коричневый",
                "3" to "бежевый",
                "4" to "рыжий",
                "5" to "песочный",
                "7" to "белый",
                "8" to "дымчатый"

            )

        private fun getCharacter(): Map<String, String> =
            mapOf(
                "1" to "Спокойный",
                "2" to "Активный"
            )
    }
}