package ru.friendforpet.ui.pets_details

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.friendforpet.data.repositoies.PetsListRepo
import javax.inject.Inject

@HiltViewModel
class PetsDetailsViewModel @Inject constructor(
    private val petsListRepo: PetsListRepo,
): ViewModel()  {

}