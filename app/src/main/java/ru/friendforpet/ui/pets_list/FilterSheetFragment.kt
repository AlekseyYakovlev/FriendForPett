package ru.friendforpet.ui.pets_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.size
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import kotlinx.coroutines.flow.collectLatest
import ru.friendforpet.R
import ru.friendforpet.databinding.FilterBottomSheetBinding
import ru.friendforpet.ui.utils.viewBinding

class FilterSheetFragment : BottomSheetDialogFragment() {
    private val viewModel: PetsListViewModel by activityViewModels()
    private val vb by viewBinding(FilterBottomSheetBinding::bind)

    companion object {
        const val TAG = "FilterSheetFragment"
    }

    private lateinit var bottomBehavior: BottomSheetBehavior<ConstraintLayout>


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.filter_bottom_sheet, container)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val bottomSheetView = vb.bottomSheetBehavior
        bottomBehavior = BottomSheetBehavior.from(bottomSheetView)
        bottomBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        val adapter = ArrayAdapter<String>(view.context, android.R.layout.simple_list_item_1 )
        vb.acDropdown.setAdapter(adapter)
        vb.acDropdown.threshold = 100
        vb.acDropdown.setOnItemClickListener { parent, view, position, id ->
            viewModel.updateFilter("location", vb.acDropdown.text.toString())
        }
        lifecycleScope.launchWhenCreated {
            viewModel.locationList().collectLatest {
                adapter.addAll(it)
            }
        }
        lifecycleScope.launchWhenCreated {
            viewModel.colorsChipGen(view.context).collectLatest {
                vb.cgColorSheet.addView(it)
            }
        }
        lifecycleScope.launchWhenCreated {
            viewModel.typeChipGen(view.context).collectLatest {
                vb.cgType.addView(it)
            }
        }
        lifecycleScope.launchWhenCreated {
            viewModel.genderChipGen(view.context).collectLatest {
                vb.cgGenderSheet.addView(it)
            }
        }
        lifecycleScope.launchWhenCreated {
            viewModel.sizeChipGen(view.context).collectLatest {
                vb.cgSizeSheet.addView(it)
            }
        }
        lifecycleScope.launchWhenCreated {
            viewModel.characterChipGen(view.context).collectLatest {
                vb.cgPersonalitySheet.addView(it)
            }
        }
        lifecycleScope.launchWhenCreated {
            viewModel.hairChipGen(view.context).collectLatest {
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
                vb.acDropdown.setTextColor(ResourcesCompat.getColor(resources,R.color.white,null))
            }

        }


    }


    private fun tempUpd(group: ChipGroup, checkedId: Int) {
        Toast.makeText(group.context, " AAASS ${group.size}  id ${checkedId} ", Toast.LENGTH_SHORT)
            .show()
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