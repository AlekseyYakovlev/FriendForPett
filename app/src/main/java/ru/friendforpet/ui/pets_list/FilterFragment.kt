package ru.friendforpet.ui.pets_list

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.friendforpet.R
import ru.friendforpet.databinding.FragmentPetsListFilterBinding
import ru.friendforpet.ui.utils.viewBinding

@AndroidEntryPoint
class FilterFragment : Fragment(R.layout.fragment_pets_list_filter) {
    private val viewModel: PetsListViewModel by viewModels()
    private val vb by viewBinding(FragmentPetsListFilterBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }
    private fun setupViews() {
        vb.btClean.setOnClickListener {
            viewModel.cleanFilter()
            setFilterValuesToDefault()
        }

        vb.spLocation.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            viewModel.updateFilter("location", "")
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
               val location = vb.spLocation.getItemAtPosition(position).toString()
                viewModel.updateFilter("location", location)
            }

        }

        vb.spPetType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                viewModel.updateFilter("type", "")
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
                val location = vb.spPetType.getItemAtPosition(position).toString()
                viewModel.updateFilter("type", location)
            }

        }

    }

    private fun setFilterValuesToDefault(){

    }

}