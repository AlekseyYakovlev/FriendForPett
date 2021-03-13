package ru.friendforpet.data.repositoies

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import ru.friendforpet.data.db.AppDb
import ru.friendforpet.data.db.daos.PetsDao
import ru.friendforpet.data.db.entities.PetEntity
import ru.friendforpet.model.Pet
import ru.friendforpet.ui.pets_list.PetsItemData
import javax.inject.Inject

class PetsListRepo @Inject constructor(
    private val db: AppDb,
    private val petsDao: PetsDao,
) {

    suspend fun insertInitialValues() {
        petsDao.insertAll(provideDummyList())
    }

    suspend fun updateIsLiked(petId: Int, isLiked: Boolean) {
        petsDao.updateIsLiked(petId, isLiked)
    }

    fun getPet(petId: Int): Flow<Pet> =
        petsDao.getPetsByIdFlow(petId).filterNotNull().map { it.toPet() }


    fun getPetsList(): Flow<List<Pet>> = petsDao.getPetsFlow().map { list ->
        list.map { it.toPet() }
    }



    private fun provideDummyList(): List<PetEntity> = listOf(
        PetEntity(
            141983,
            "Мухтар",
            "Мальчик",
            3,
            "Москва",
            "Большая",
            "Дружелюбная",
            "Длинношерстная",
            "Черно-рыжий",
            "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium,\n" +
                    "        totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo.\n" +
                    "        Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est",
            listOf(
                "Дружелюбный", "Приучен к поводку", "Без агрессии", "Овчарка"
            ),
            "23.03.2021",
            "https://avatars.mds.yandex.net/get-marketcms/1357599/img-61abb65d-e207-4e08-8eef-1499fc23b460.jpeg/optimize"
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
            "https://avatars.mds.yandex.net/get-marketcms/1357599/img-61abb65d-e207-4e08-8eef-1499fc23b460.jpeg/optimize"
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
            "https://avatars.mds.yandex.net/get-marketcms/1357599/img-61abb65d-e207-4e08-8eef-1499fc23b460.jpeg/optimize"
        ),
        PetEntity(
            141986,
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
            "https://avatars.mds.yandex.net/get-marketcms/1357599/img-61abb65d-e207-4e08-8eef-1499fc23b460.jpeg/optimize"
        ),
        PetEntity(
            141987,
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
            "https://avatars.mds.yandex.net/get-marketcms/1357599/img-61abb65d-e207-4e08-8eef-1499fc23b460.jpeg/optimize"
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
    )


}


/**
 * Структура БД
 *
 * city - "Moscow"
 *
 * @param animalType
 * 1 - Cat
 * 2 - Dog
 *
 * @param gender
 * 1 - Girl
 * 2 - Boy
 *
 * @param age -
 * 3 Int
 *
 * @param animalSize
 * 1 - small
 * 2 - middle
 * 3 - Big
 *
 * @param animalFurr
 * 1 - Long hair
 * 2 - short hair
 *
 * @param color
 * 1 - Черный
 * 2 - коричневый
 * 3 - бежевый
 * 4 - рыжий
 * 5 - песочный
 * 7 - белый
 * 8 - дымчатый
 *
 * @param character
 * 1 - Calm
 * 2 - Active
 */
data class Filters(
    val city: List<String> = listOf("Moscow"),
    val animalType: Map<String, String> = mutableMapOf(),
    val age: List<String> = listOf(),
    val gender: Map<String, String> = mutableMapOf(),
    val animalSize: Map<String, String> = mutableMapOf(),
    val animalFurr: Map<String, String> = mutableMapOf(),
    val color: Map<String, String> = mutableMapOf(),
    val character: Map<String, String> = mutableMapOf()
) {
    companion object {

        fun getInstance(age: String, city: String = "Moscow"): Filters = Filters(
            animalType = getAnimalType(),
            age = listOf(age),
            city = listOf(city),
            gender = getGender(),
            animalSize = getAnimalSize(),
            animalFurr = getAnimalFurr(),
            color = getColor(),
            character = getCharacter()
        )

        private fun getAnimalType(): Map<String, String> = mapOf(
            "1" to "Cat",
            "2" to "Dog"
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




