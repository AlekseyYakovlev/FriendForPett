package ru.friendforpet.ui.pets_details

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels

import dagger.hilt.android.AndroidEntryPoint
import ru.friendforpet.R

@AndroidEntryPoint
class PetsDetailsFragment : Fragment(R.layout.fragment_movies_list) {
    private val viewModel: PetsDetailsViewModel by viewModels()

}