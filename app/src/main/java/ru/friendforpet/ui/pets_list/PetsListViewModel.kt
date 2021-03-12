package ru.friendforpet.ui.pets_list

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import ru.friendforpet.data.repositoies.PetsListRepo
import ru.friendforpet.model.Pet
import javax.inject.Inject

@HiltViewModel
class PetsListViewModel @Inject constructor(
    private val petsListRepo: PetsListRepo,
) : ViewModel() {

    fun getListFlow() = petsListRepo.getPetsList().map { list ->
        list.map { it.toPetItemData() }
    }

}

fun Pet.toPetItemData() = PetsItemData(
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
    isLiked = false,
)