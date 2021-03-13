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

    fun updateFilter(field: String, rawValue: String) {
        val value = when (rawValue) {
            in listOf("Не важно", "Любой", "Любая") -> ""
            else -> rawValue
        }

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
                Timber.tag("123").d("gender = $value")
            }
            "minAge" -> {
                _filterState.value = _filterState.value.copy(minAge = value)
                Timber.tag("123").d("age = $value")
            }
            "maxAge" -> {
                _filterState.value = _filterState.value.copy(maxAge = value)
                Timber.tag("123").d("age = $value")
            }
            "size" -> {
                _filterState.value = _filterState.value.copy(size = value)
                Timber.tag("123").d("size = $value")
            }
            "personality" -> {
                _filterState.value = _filterState.value.copy(personality = value)
                Timber.tag("123").d("personality = $value")
            }
            "hair" -> {
                _filterState.value = _filterState.value.copy(hair = value)
                Timber.tag("123").d("hair = $value")
            }
            "color" -> {
                _filterState.value = _filterState.value.copy(color = value)
                Timber.tag("123").d("color = $value")
            }
        }
    }

    fun cleanFilter() {
        _filterState.value = Filter()
    }
}

data class Filter(
    val location: String = "",
    val type: String = "",
    val gender: String = "",
    val minAge: String = "",
    val maxAge: String = "",
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