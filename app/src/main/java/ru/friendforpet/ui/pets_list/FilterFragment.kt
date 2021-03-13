package ru.friendforpet.ui.pets_list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.friendforpet.R
import ru.friendforpet.databinding.FragmentPetsListFilterBinding
import ru.friendforpet.ui.pets_details.PetsDetailsViewModel
import ru.friendforpet.ui.utils.viewBinding

@AndroidEntryPoint
class FilterFragment : Fragment(R.layout.fragment_pets_list_filter) {
    private val viewModel: PetsDetailsViewModel by viewModels()
    private val vb by viewBinding(FragmentPetsListFilterBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }
    private fun setupViews() {

    }

}