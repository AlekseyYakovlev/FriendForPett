package ru.friendforpet.ui.pets_list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.friendforpet.Navigator
import ru.friendforpet.R
import ru.friendforpet.databinding.FragmentWelcomeScreenBinding
import ru.friendforpet.ui.utils.viewBinding
import javax.inject.Inject

@AndroidEntryPoint
class WelcomeScreenFragment : Fragment(R.layout.fragment_welcome_screen) {
    private val viewModel: PetsListViewModel by viewModels()
    private val vb by viewBinding(FragmentWelcomeScreenBinding::bind)

    @Inject
    lateinit var navigator: Navigator

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {



        vb.mbSubmit.setOnClickListener {
            navigator.navigateTo(Navigator.Destination.PETS_LIST_FRAGMENT)
        }

        vb.cvCat.setOnClickListener {  }

    }
}