package ru.friendforpet.ui.pets_details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels

import dagger.hilt.android.AndroidEntryPoint
import ru.friendforpet.R
import ru.friendforpet.databinding.FragmentPetDetaisBinding
import ru.friendforpet.ui.utils.viewBinding

@AndroidEntryPoint
class PetsDetailsFragment : Fragment(R.layout.fragment_pet_detais) {
    private val viewModel: PetsDetailsViewModel by viewModels()
    private val vb by viewBinding(FragmentPetDetaisBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val petId = arguments?.getInt(ARGUMENT_TAG, 0) ?: 0
        setupViews(petId)
    }

    private fun setupViews(petId: Int) {

    }

    companion object {
        const val ARGUMENT_TAG = "PET_ID"
    }
}