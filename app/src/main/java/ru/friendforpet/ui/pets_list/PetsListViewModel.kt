package ru.friendforpet.ui.pets_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.friendforpet.data.repositoies.PetsListRepo
import ru.friendforpet.model.Pet
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PetsListViewModel @Inject constructor(
    private val petsListRepo: PetsListRepo,
) : ViewModel() {

    var pickedCity: String = ""

    private val _filterState = MutableStateFlow(Filter())


    init {
        viewModelScope.launch(Dispatchers.IO) {
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
        viewModelScope.launch(Dispatchers.IO) {
            petsListRepo.updateIsLiked(petId, isLiked)
        }
    }

    fun updateFilter(field: String, value: String) {
        when (field) {
            "location" -> {
                _filterState.value = _filterState.value.copy(location = value)
                Timber.tag("123").d("location = $value")
            }
            "type" -> {
                _filterState.value = _filterState.value.copy(type = value)
                Timber.tag("123").d("type = $value")
            }
            "gender" -> {
                _filterState.value = _filterState.value.copy(gender = value)
            }
            "age" -> {
                _filterState.value = _filterState.value.copy(age = value)
            }
            "size" -> {
                _filterState.value = _filterState.value.copy(size = value)
            }
            "personality" -> {
                _filterState.value = _filterState.value.copy(personality = value)
            }
            "hair" -> {
                _filterState.value = _filterState.value.copy(hair = value)
            }
            "color" -> {
                _filterState.value = _filterState.value.copy(color = value)
            }
        }
    }

    fun cleanFilter(){
        _filterState.value = Filter()
    }
}

data class Filter(
    val location: String = "",
    val type: String = "",
    val gender: String = "",
    val age: String = "",
    val size: String = "",
    val personality: String = "",
    val hair: String = "",
    val color: String = "",
)


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