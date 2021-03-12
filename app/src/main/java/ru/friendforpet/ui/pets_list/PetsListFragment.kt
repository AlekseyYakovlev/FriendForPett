package ru.friendforpet.ui.pets_list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.friendforpet.R
import ru.friendforpet.databinding.FragmentPetsListBinding
import ru.friendforpet.ui.utils.viewBinding

@AndroidEntryPoint
class PetsListFragment: Fragment(R.layout.fragment_pets_list) {
    private val viewModel:PetsListViewModel by viewModels()
    private val vb by viewBinding(FragmentPetsListBinding::bind)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {

    }


}