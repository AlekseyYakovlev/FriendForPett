package ru.friendforpet.ui.pets_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import ru.friendforpet.data.repositoies.PetsListRepo
import ru.friendforpet.model.Pet
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PetsListViewModel @Inject constructor(
    private val petsListRepo: PetsListRepo,
) : ViewModel() {

    init {
       viewModelScope.launch (Dispatchers.IO){
           petsListRepo.insertInitialValues()
       }
    }

    private val filtersMap = mutableMapOf<String, String>()

    fun getListFlow(): Flow<List<PetsItemData>> =
        petsListRepo.petsListFlow
            .flowOn(Dispatchers.IO)
            .onEach { Timber.tag("123").d("New Value") }
            .map { list ->
                list.map { it.toPetItemData() }
            }

    fun handleLike(petId: Int, isLiked: Boolean) {
        viewModelScope.launch (Dispatchers.IO){
            petsListRepo.updateIsLiked(petId,isLiked)
        }
    }

    fun updateFilter(){

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
    isLiked = isLiked,
)