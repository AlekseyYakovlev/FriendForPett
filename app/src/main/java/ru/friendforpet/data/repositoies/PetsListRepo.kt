package ru.friendforpet.data.repositoies

import ru.friendforpet.model.Pet

class PetsListRepo {
    fun petListRepoMock(): Pet = Pet(
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
        "23.03.2021"
    )
}