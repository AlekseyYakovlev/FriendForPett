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

    var isDogPicked = MutableStateFlow(false)
    var isCatPicked = MutableStateFlow(false)


    init {
        Timber.tag("123").d("viewModel init")
        viewModelScope.launch(Dispatchers.IO) {
            petsListRepo.insertInitialValues()
        }
    }

    fun getListFlow(): Flow<List<PetsItemData>> =
        petsListRepo.petsListFlow
            .flowOn(Dispatchers.IO)
            .onEach { Timber.tag("123").d("New Value") }
            .map { list ->
                val filter = _filterState.value
                Timber.tag("123").d(filter.toString())
                list.filter { pet ->
                    (filter.location.isEmpty() || filter.location == pet.location) &&
                            (filter.type.isEmpty() || filter.type == pet.type) &&
                            (filter.type.isEmpty() || filter.type == pet.type) &&
                            (filter.gender.isEmpty() || filter.gender == pet.sex) &&
                            (filter.size.isEmpty() || filter.size == pet.size) &&
                            (filter.personality.isEmpty() || filter.personality == pet.personality) &&
                            (filter.hair.isEmpty() || filter.hair == pet.hair) &&
                            (filter.color.isEmpty() || filter.color == pet.color)
                }
                    .map { it.toPetItemData() }
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
            "age" -> {
                _filterState.value = _filterState.value.copy(minAge = value)
                Timber.tag("123").d("age = $value")
            }
            "minAge" -> {
                _filterState.value = _filterState.value.copy(minAge = value)
                Timber.tag("123").d("minAge = $value")
            }
            "maxAge" -> {
                _filterState.value = _filterState.value.copy(maxAge = value)
                Timber.tag("123").d("maxAge = $value")
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
    val age: String = "",
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
    type = type,
)