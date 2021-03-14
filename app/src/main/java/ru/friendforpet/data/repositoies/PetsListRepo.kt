package ru.friendforpet.data.repositoies

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import ru.friendforpet.data.db.AppDb
import ru.friendforpet.data.db.daos.PetsDao
import ru.friendforpet.data.db.entities.PetEntity
import ru.friendforpet.data.remote.RestService
import ru.friendforpet.data.remote.resp.PetsResponse
import ru.friendforpet.model.Pet
import timber.log.Timber
import javax.inject.Inject

class PetsListRepo @Inject constructor(
    private val db: AppDb,
    private val petsDao: PetsDao,
    private val network: RestService,
) {

    val petsListFlow: Flow<List<Pet>> = petsDao.getPetsFlow().map { list ->
        list.map { it.toPet() }
    }

    suspend fun insertInitialValues() {
        petsDao.insertAll(provideDummyList())
    }

    suspend fun updateIsLiked(petId: Int, isLiked: Boolean) {
        petsDao.updateIsLiked(petId, isLiked)
    }

    fun getPet(petId: Int): Flow<Pet> =
        petsDao.getPetsByIdFlow(petId).filterNotNull().map { it.toPet() }

    suspend fun getPetsFromNet() {
        var petsFromNet = network.getPets()
        try {

        } catch (e: Exception) {
            Timber.tag("123 PetsListRepo").e(e.message)
        }
        petsFromNet.toPetsList()

    }


    private fun provideDummyList(): List<PetEntity> = listOf(
        PetEntity(
            1,
            "Тризор",
            "Мальчик",
            5,
            "Москва",
            "Большая",
            "Дружелюбная",
            "Длинношерстная",
            "Черно-рыжий",
            "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium,\n" +
                    "        totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo.\n" +
                    "        Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam estNemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam estNemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam estNemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam estNemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam estNemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam estNemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam estNemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam estNemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est",
            listOf(
                "Дружелюбный", "Приучен к поводку", "Без агрессии", "Овчарка"
            ),
            "23.03.2021",
            "https://www.friendforpet.ru/api/sites/default/files/2021-01/8tAR8nMAcLc.jpg",
            false,
            "Собака"
        ),
        PetEntity(
            141984,
            "Ника",
            "Девочка",
            2,
            "Москва",
            "Большая",
            "Дружелюбная",
            "Длинношерстная",
            "Бело-рыжий",
            "Отзывчивая, обучаемая, спокойная, ладит с детьми",
            listOf(
                "Дружелюбный", "Приучен к поводку", "Без агрессии", "Сенбернар", "Спасатель"
            ),
            "23.03.2021",
            "https://www.friendforpet.ru/api/sites/default/files/2021-01/_5adN7drnuw.jpg",
            false,
            "Собака"
        ),
        PetEntity(
            141985,
            "Мухтар",
            "Мальчик",
            3,
            "Москва",
            "Большая",
            "Дружелюбная",
            "Длинношерстная",
            "Черно-рыжий",
            "Отзывчивый, обучаемый, спокойный, ладит с детьми",
            listOf(
                "Дружелюбный", "Приучен к поводку", "Без агрессии", "Овчарка"
            ),
            "23.03.2021",
            "https://www.friendforpet.ru/api/sites/default/files/2021-01/kIHxO7Wc2Hs.jpg",
            false,
            "Собака"
        ),
        PetEntity(
            141986,
            "Ника",
            "Девочка",
            2,
            "Санкт-Петербург",
            "Большая",
            "Дружелюбная",
            "Длинношерстная",
            "Бело-рыжий",
            "Отзывчивая, обучаемая, спокойная, ладит с детьми",
            listOf(
                "Дружелюбный", "Приучен к поводку", "Без агрессии", "Сенбернар", "Спасатель"
            ),
            "23.03.2021",
            "https://www.friendforpet.ru/api/sites/default/files/2021-01/s2lr9rjx8KI.jpg",
            type = "Собака"
        ),
        PetEntity(
            141987,
            "Пусик",
            "Мальчик",
            3,
            "Москва",
            "Большая",
            "Дружелюбная",
            "Длинношерстная",
            "Черно-рыжий",
            "Отзывчивый, обучаемый, спокойный, ладит с детьми",
            listOf(
                "Дружелюбный", "Приучен к поводку", "Без агрессии", "Овчарка"
            ),
            "23.03.2021",
            "https://www.friendforpet.ru/api/sites/default/files/2021-02/%D0%9F%D0%BE%D0%BF%D0%BE%D0%BD%D0%BA%D0%B0.jpg",
            false,
            "Кошка"
        )
    )

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

private fun PetsResponse.toPetsList(): List<PetEntity> {

    return map {
        PetEntity(
            _id = it.id,
            name = it.name,
            sex = if (it.gender == 1) "Девочка" else "Мальчик",
            age = it.age,
            location = when (it.cityId) {
                1 -> "Санкт-Петербург"
                2 -> "Казань"
                3 -> "Краснодар"
                else -> "Москва"
            },
            size = when (it.cityId) {
                1 -> "Маленький"
                2 -> "Средний"
                3 -> "Большой"
                else -> "Средний"
            },
            personality = if (it.petCharacter == 1) "Спокойный" else "Активный",
            hair = if (it.furr == 1) "Длинная" else "Короткая",
            color = when (it.color) {
                1 -> "Черный"
                2 -> "Коричневый"
                3 -> "Бежевый"
                4 -> "Рыжий"
                5 -> "Песочный"

                7 -> "Белый"
                8 -> "Дымчатый"
                else -> "Черный"
            },
            description = "",
            tags = listOf(),
            addedDate = "",
            photo = "",
            isLiked = false,
            type = if (it.pet == 1) "Кошка" else "Собака",
        )
    }
}


/**
 * Структура БД
 * @param location - "Moscow"
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
 * @param animalFurr
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
    val location: List<String> = listOf("Moscow"),
    val animalType: Map<String, String> = mutableMapOf(),
    val age: List<String>? = listOf(),
    val gender: Map<String, String> = mutableMapOf(),
    val animalSize: Map<String, String> = mutableMapOf(),
    val animalFurr: Map<String, String> = mutableMapOf(),
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
            location = listOf(
                "Москва",
                "Санкт-Петербург",
                "Казань",
                "Краснодар"
            ),
            gender = getGender(),
            animalSize = getAnimalSize(),
            animalFurr = getAnimalFurr(),
            color = getColor(),
            character = getCharacter()
        )


        private fun getAnimalType(): Map<String, String> = mapOf(
            "1" to "Кошка",
            "2" to "Собака"
        )

        private fun getGender(): Map<String, String> = mapOf(
            "1" to "Girl",
            "2" to "Boy"
        )

        private fun getAnimalSize(): Map<String, String> =
            mapOf(
                "1" to "small",
                "2" to "middle",
                "3" to "Big"
            )

        private fun getAnimalFurr(): Map<String, String> =
            mapOf(
                "1" to "Long hair",
                "2" to "short hair"
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
                "1" to "Calm",
                "2" to "Active"
            )
    }

}




