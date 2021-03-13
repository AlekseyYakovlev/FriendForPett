package ru.friendforpet.ui.pets_list

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.friendforpet.R
import ru.friendforpet.databinding.FragmentPetsListFilterBinding
import ru.friendforpet.ui.utils.viewBinding

@AndroidEntryPoint
class FilterFragment : Fragment(R.layout.fragment_pets_list_filter) {
    private val viewModel: PetsListViewModel by activityViewModels()
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

        setupFilterListeners()
    }

    private fun setFilterValuesToDefault() {
        vb.spLocation.setSelection(0)
        vb.spPetType.setSelection(0)
        vb.spGender.setSelection(0)
        vb.spAge.setSelection(0)
        vb.spSize.setSelection(0)
        vb.spPersonality.setSelection(0)
        vb.spHair.setSelection(0)
        vb.spPetType.setSelection(0)
        vb.spColor.setSelection(0)
    }

    private fun setupFilterListeners() {
        vb.spLocation.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                viewModel.updateFilter("location", "")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                val location = vb.spLocation.getItemAtPosition(position).toString()
                viewModel.updateFilter("location", location)
            }
        }

        vb.spPetType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                viewModel.updateFilter("type", "")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                val location = vb.spPetType.getItemAtPosition(position).toString()
                viewModel.updateFilter("type", location)
            }
        }

        vb.spGender.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                viewModel.updateFilter("gender", "")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                val location = vb.spGender.getItemAtPosition(position).toString()
                viewModel.updateFilter("gender", location)
            }
        }

        vb.spAge.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                viewModel.updateFilter("age", "")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                val location = vb.spAge.getItemAtPosition(position).toString()
                viewModel.updateFilter("age", location)
            }
        }

        vb.spSize.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                viewModel.updateFilter("size", "")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                val location = vb.spSize.getItemAtPosition(position).toString()
                viewModel.updateFilter("size", location)
            }
        }

        vb.spPersonality.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                viewModel.updateFilter("personality", "")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                val location = vb.spPersonality.getItemAtPosition(position).toString()
                viewModel.updateFilter("personality", location)
            }
        }

        vb.spHair.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                viewModel.updateFilter("hair", "")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                val location = vb.spHair.getItemAtPosition(position).toString()
                viewModel.updateFilter("hair", location)
            }
        }

        vb.spPetType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                viewModel.updateFilter("type", "")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                val location = vb.spPetType.getItemAtPosition(position).toString()
                viewModel.updateFilter("type", location)
            }
        }

        vb.spColor.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                viewModel.updateFilter("color", "")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                val location = vb.spColor.getItemAtPosition(position).toString()
                viewModel.updateFilter("color", location)
            }
        }
    }

}