package ru.friendforpet.ui.pets_list

import android.os.Bundle
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import ru.friendforpet.Navigator
import ru.friendforpet.R
import ru.friendforpet.databinding.FragmentPetItemBinding
import ru.friendforpet.databinding.FragmentPetsListBinding
import ru.friendforpet.ui.base.BaseRVAdapter
import ru.friendforpet.ui.utils.viewBinding
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class PetsListFragment : Fragment(R.layout.fragment_pets_list) {
    private val viewModel: PetsListViewModel by activityViewModels()
    private val vb by viewBinding(FragmentPetsListBinding::bind)
    private val petsListRvAdapter by lazy(::setupRecyclerViewAdapter)

    @Inject
    lateinit var navigator: Navigator

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {

        vb.filterExtendedFab.setOnClickListener {

            vb.icCirclePrimary.animate()
                .scaleX(40f)
                .scaleY(40f)
                .setDuration(300L)
                .start()

            vb.icCircleSecondary.animate()
                .scaleX(40f)
                .scaleY(40f)
                .alpha(1.0f)
                .setDuration(300L)
                .withEndAction {
                    navigator.navigateTo(Navigator.Destination.FILTER_FRAGMENT)
                }
                .start()
        }

        vb.rvPetsList.apply {
            adapter = petsListRvAdapter
            setHasFixedSize(true)
        }

        lifecycleScope.launchWhenStarted {
            Timber.tag("1234567").d("launchWhenStarted")
            viewModel.filterState.collectLatest {
                viewModel.getListFlow(it).collectLatest(::renderData)
            }

        }
    }

    private fun renderData(data: List<PetsItemData>) {
        Timber.tag("1234567").d("data.size ${data.size}")
        petsListRvAdapter.updateData(data)
    }

    private fun setupRecyclerViewAdapter() =
        BaseRVAdapter<FragmentPetItemBinding, PetsItemData>(
            viewHolderInflater = { layoutInflater, parent, attachToParent ->
                FragmentPetItemBinding.inflate(layoutInflater, parent, attachToParent)
            },
            viewHolderBinder = { holder, itemData ->
                with(holder) {
                    ivPhoto.load(itemData.photo) {
                        placeholder(R.drawable.placeholder)
                    }
                    val ageStr = resources.getQuantityString(
                        R.plurals.pets_list__year,
                        itemData.age,
                        itemData.age
                    )
                    val temp =
                        "${itemData.name}: $ageStr ${System.lineSeparator()}${itemData.color}"
                    tvNameAge.text = temp
                    imageGender.setImageDrawable(
                        if (itemData.sex == "Мальчик") ResourcesCompat.getDrawable(
                            imageGender.context.resources,
                            R.drawable.ic_baseline_male_24,
                            null
                        ) else ResourcesCompat.getDrawable(
                            imageGender.context.resources,
                            R.drawable.ic_baseline_female_24,
                            null
                        )
                    )
                    icLike.isChecked = itemData.isLiked
                    Timber.tag("123").d("${itemData.name} ${itemData.isLiked}")
                    icLike.setOnClickListener {
                        Timber.tag("123").d(" handleLike ${itemData.name} ${!itemData.isLiked}")
                        viewModel.handleLike(itemData._id, !itemData.isLiked)
                    }

                    root.setOnClickListener {
                        navigator.navigateTo(
                            Navigator.Destination.PETS_DETAILS_FRAGMENT,
                            itemData._id
                        )
                    }
                }
            },
        )
}