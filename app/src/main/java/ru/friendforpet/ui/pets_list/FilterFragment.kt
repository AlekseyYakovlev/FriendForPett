package ru.friendforpet.ui.pets_list

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
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
        vb.btnApply.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        vb.btnClear.setOnClickListener {
            viewModel.cleanFilter()
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        val adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1)
        vb.acDropdown.setAdapter(adapter)
        vb.acDropdown.threshold = 100
        vb.acDropdown.setOnItemClickListener { _, _, _, _ ->
            viewModel.updateFilter("location", vb.acDropdown.text.toString())
        }
        lifecycleScope.launchWhenCreated {
            viewModel.locationList().collectLatest {
                adapter.addAll(it)
            }
        }
        lifecycleScope.launchWhenCreated {
            viewModel.colorsChipGen(requireContext()).collectLatest {
                vb.cgColorSheet.addView(it)
            }
        }
        lifecycleScope.launchWhenCreated {
            viewModel.typeChipGen(requireContext()).collectLatest {
                vb.cgType.addView(it)
            }
        }
        lifecycleScope.launchWhenCreated {
            viewModel.genderChipGen(requireContext()).collectLatest {
                vb.cgGenderSheet.addView(it)
            }
        }
        lifecycleScope.launchWhenCreated {
            viewModel.sizeChipGen(requireContext()).collectLatest {
                vb.cgSizeSheet.addView(it)
            }
        }
        lifecycleScope.launchWhenCreated {
            viewModel.characterChipGen(requireContext()).collectLatest {
                vb.cgPersonalitySheet.addView(it)
            }
        }
        lifecycleScope.launchWhenCreated {
            viewModel.hairChipGen(requireContext()).collectLatest {
                vb.cgHairSheet.addView(it)
            }
        }

        vb.cgColorSheet.setOnCheckedChangeListener { group, checkedId ->
            tempUpd(group, checkedId)
        }
        vb.cgGenderSheet.setOnCheckedChangeListener { group, checkedId ->
            tempUpd(group, checkedId)
        }
        vb.cgPersonalitySheet.setOnCheckedChangeListener { group, checkedId ->
            tempUpd(group, checkedId)
        }
        vb.cgType.setOnCheckedChangeListener { group, checkedId ->
            tempUpd(group, checkedId)
        }
        vb.cgHairSheet.setOnCheckedChangeListener { group, checkedId ->
            tempUpd(group, checkedId)
        }
        vb.cgSizeSheet.setOnCheckedChangeListener { group, checkedId ->
            tempUpd(group, checkedId)
        }
        lifecycleScope.launchWhenCreated {
            viewModel.filterState.collectLatest {
                vb.acDropdown.setText(it.location, null)
                vb.acDropdown.setTextColor(ResourcesCompat.getColor(resources, R.color.white, null))
            }

        }
    }

    private fun tempUpd(group: ChipGroup, checkedId: Int) {
        group.tag?.let {
            it as String
            if (checkedId != -1) {
                val chip = group.findViewById<Chip>(checkedId)
                viewModel.updateFilter(it, chip.text.toString())

            } else {
                viewModel.cleanFilterItem(it)
            }
        }
    }

}