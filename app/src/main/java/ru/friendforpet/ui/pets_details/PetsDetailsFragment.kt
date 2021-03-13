package ru.friendforpet.ui.pets_details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import coil.load

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import ru.friendforpet.Navigator
import ru.friendforpet.R
import ru.friendforpet.databinding.FragmentPetDetaisBinding
import ru.friendforpet.model.Pet
import ru.friendforpet.ui.utils.viewBinding
import javax.inject.Inject

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
        vb.detailsNavBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        lifecycleScope.launchWhenStarted {
            viewModel.getPetFlow(petId).collectLatest(::renderScreen)
        }
    }

    private fun renderScreen(petData: Pet) {
        with(petData) {
            vb.detailsPetImage.load(photo)
            vb.detailsNamePet.text = name
            vb.detailsAgeDesc.text = "$age лет"
            vb.detailsColorDesc.text = color
            vb.detailsPetAboutDecs.text = description
            vb.detailsSexDesc.text = sex
            vb.detailsWeightDesc.text = size

            vb.icLike.isChecked = isLiked
            vb.icLike.setOnClickListener {
                viewModel.handleLike(_id, !isLiked)
            }
        }
    }

    companion object {
        const val ARGUMENT_TAG = "PET_ID"
    }
}