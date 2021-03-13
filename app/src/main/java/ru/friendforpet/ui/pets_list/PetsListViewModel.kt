package ru.friendforpet.ui.pets_list

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import ru.friendforpet.data.repositoies.PetsListRepo
import ru.friendforpet.model.Pet
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PetsListViewModel @Inject constructor(
    private val petsListRepo: PetsListRepo,
) : ViewModel() {

    fun getListFlow(): Flow<List<PetsItemData>> =
        petsListRepo.getPetsList()
            .flowOn(Dispatchers.IO)
            .onEach { Timber.tag("123").d("New Value") }
            .map { list ->
                list.map { it.toPetItemData() }
            }

}

fun Pet.toPetItemData() = PetsItemData(
    _id = _id,
    name = name,
    sex = sex,
    age = "$age года",
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