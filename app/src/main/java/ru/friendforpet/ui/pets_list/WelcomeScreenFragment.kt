package ru.friendforpet.ui.pets_list

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.friendforpet.Navigator
import ru.friendforpet.R
import ru.friendforpet.data.repositoies.Filters
import ru.friendforpet.databinding.FragmentWelcomeScreenBinding
import ru.friendforpet.ui.utils.viewBinding
import javax.inject.Inject

@AndroidEntryPoint
class WelcomeScreenFragment : Fragment(R.layout.fragment_welcome_screen) {
    private val viewModel: PetsListViewModel by activityViewModels()
    private val vb by viewBinding(FragmentWelcomeScreenBinding::bind)

    private val filter = Filters.getMockInstance()
    private val mockCityFilterList: List<String> = filter.location

    @Inject
    lateinit var navigator: Navigator

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        vb.cvCat.setOnClickListener(listenerAnimalType)
        vb.cvDog.setOnClickListener(listenerAnimalType)

        lifecycleScope.launchWhenCreated {
            vb.acDropdown.onItemClickListener = clickMenuListener

        }
        vb.acDropdown.setAdapter(
                ArrayAdapter(
                    view.context,
                    android.R.layout.simple_list_item_1, mockCityFilterList)
                )
        vb.acDropdown.threshold = 100
        vb.cvCat.isCheckable = true
        vb.cvDog.isCheckable = true
        val strokeSize = resources.getDimension(R.dimen.card_stroke_pik).toInt()

        lifecycleScope.launch {
            viewModel.isCatPicked.collectLatest {
                vb.cvCat.isChecked = it
                vb.cvCat.strokeWidth = if (it) strokeSize else 0
            }
        }
        lifecycleScope.launch {
            viewModel.isDogPicked.collectLatest {
                vb.cvDog.isChecked = it
                vb.cvDog.strokeWidth = if (it) strokeSize else 0
            }
        }



    }


    private val listenerAnimalType = View.OnClickListener {
        when (it.id) {
            R.id.cv_cat -> {
                viewModel.apply {
                    viewModel.isCatPicked.value = true
                    viewModel.isDogPicked.value = false
                    viewModel.updateFilter("type", "Кошка")
                }

            }
            R.id.cv_dog -> {
                viewModel.apply {
                    viewModel.isDogPicked.value = true
                    viewModel.isCatPicked.value = false
                    viewModel.updateFilter("type", "Собака")
                }
            }
        }
    }


    private fun setupViews() {
        vb.mbSubmit.setOnClickListener {
            if (vb.acDropdown.text.isBlank()) {
                showWarning(getString(R.string.welcome_drop_down_error))
                vb.acDropdown.showDropDown()
            } else if (!vb.cvCat.isChecked && !vb.cvDog.isChecked) {
                showWarning(getString(R.string.pick_animal_type))
            } else {
                navigator.navigateTo(Navigator.Destination.PETS_LIST_FRAGMENT)
            }
        }
        vb.cvCat.setOnClickListener { }

    }

    private fun showWarning( text: String): Unit =
        Snackbar.make(vb.acDropdown, text, Snackbar.LENGTH_LONG)
            .setBackgroundTint(
                ResourcesCompat.getColor(
                    resources,
                    R.color.colorAccent,
                    null
                )
            ).setTextColor(Color.WHITE).show()


    private val clickMenuListener =
        AdapterView.OnItemClickListener { parent, view, position, id ->
            viewModel.updateFilter("location",  vb.acDropdown.text.toString()) // нет фильтра по городу в VM
        }
}