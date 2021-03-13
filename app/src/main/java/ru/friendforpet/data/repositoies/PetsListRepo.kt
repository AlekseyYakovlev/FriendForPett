package ru.friendforpet.data.repositoies

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.friendforpet.data.db.AppDb
import ru.friendforpet.data.db.daos.PetsDao
import ru.friendforpet.model.Pet
import javax.inject.Inject

class PetsListRepo @Inject constructor(
    db: AppDb,
    petsDao: PetsDao,
) {

    //Ф-ция возвращает экземпляр класса Pet
    fun getPet(petId:Int): Flow<Pet> = flow {
        emit(
            Pet(
                petId,
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
    }


    //Функция возвращает список объектов класса Pet
    fun getPetsList(): Flow<List<Pet>> = flow {
        emit(
            listOf(
                Pet(
                    141983,
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
                Pet(
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
                Pet(
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
                Pet(
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
                Pet(
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
        )
    }
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




