package ru.friendforpet.ui.pets_list

import android.graphics.Color
import android.os.Bundle
import android.util.Log
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
    private val mockCityFilterList = filter.city
    private val mockTypeFilterList = filter.animalType


    @Inject
    lateinit var navigator: Navigator

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        vb.cvCat.setOnClickListener(listenerAnimalType)
        vb.cvDog.setOnClickListener(listenerAnimalType)

        lifecycleScope.launchWhenCreated {
            vb.acDropdown.onItemClickListener = clickMenuListener
            vb.acDropdown.setAdapter(
                ArrayAdapter(
                    view.context,
                    android.R.layout.simple_list_item_1, mockCityFilterList
                )
            )
        }
        vb.cvCat.isCheckable = true
        vb.cvDog.isCheckable = true
        val strokeSize = resources.getDimension(R.dimen.card_stroke_pik).toInt()
        lifecycleScope.launch {
            viewModel.isCatPicked.collectLatest {
                Log.d("TAG", "onViewCreated: CAT $it ${vb.cvDog.strokeWidth}")
                vb.cvCat.isChecked = it
                vb.cvCat.strokeWidth = if (it) strokeSize else 0


            }
        }
        lifecycleScope.launch {
            viewModel.isDogPicked.collectLatest {
                Log.d("TAG", "onViewCreated: DOG $it ${vb.cvDog.strokeWidth}")
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
                    //viewModel.updateFilter(FILTER_ANIMAL_TYPE, mockTypeFilterList["1"].toString())
                    viewModel.updateFilter("type", "Кошка")
                }

            }
            R.id.cv_dog -> {
                viewModel.apply {
                    viewModel.isDogPicked.value = true
                    viewModel.isCatPicked.value = false
                    //viewModel.updateFilter(FILTER_ANIMAL_TYPE, mockTypeFilterList["2"].toString())
                    viewModel.updateFilter("type", "Собака")
                }
            }
        }
    }


    private fun setupViews() {
        vb.mbSubmit.setOnClickListener {
            if (vb.acDropdown.text.isBlank()) {

                Snackbar.make(vb.acDropdown, " Выберите город", Snackbar.LENGTH_LONG)
                    .setBackgroundTint(
                        ResourcesCompat.getColor(
                            resources,
                            R.color.colorAccent,
                            null
                        )
                    ).setTextColor(
                        Color.WHITE
                    ).show()

                vb.acDropdown.showDropDown()
            } else {
                navigator.navigateTo(Navigator.Destination.PETS_LIST_FRAGMENT)
            }
        }
        vb.cvCat.setOnClickListener { }

    }


    private val clickMenuListener =
        AdapterView.OnItemClickListener { parent, view, position, id ->
            viewModel.pickedCity = mockCityFilterList[position]
            vb.acDropdown.error = null
        }
}