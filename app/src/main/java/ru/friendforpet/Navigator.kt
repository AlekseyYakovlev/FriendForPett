package ru.friendforpet

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import ru.friendforpet.ui.RootActivity
import ru.friendforpet.ui.pets_details.PetsDetailsFragment
import ru.friendforpet.ui.pets_list.PetsListFragment
import timber.log.Timber
import javax.inject.Inject

class Navigator @Inject constructor(
    activity: RootActivity
) {
    private val navigationContainerId = R.id.root_container
    private val fragmentManager = activity.supportFragmentManager.also { fm->
        fm.addOnBackStackChangedListener {
            if (fm.backStackEntryCount == 0) activity.finish()
        }
    }

    fun navigateTo(destination: Destination, arguments: Int? = null) {
        val args: Bundle? = arguments?.let { argValue ->
            Bundle().apply {
                destination.argTag?.let { argTag -> putInt(argTag, argValue) }
                    ?: run { Timber.e("No ARGUMENT_TAG in destination fragment ${destination.fragmentTag}") }
            }
        }

        fragmentManager.commit {
            setReorderingAllowed(true)
            setCustomAnimations(
                R.anim.zoom_in,
                R.anim.slide_out,
                R.anim.slide_in,
                R.anim.zoom_out,
            )
            replace(
                navigationContainerId,
                destination.fragmentClass,
                args,
                destination.fragmentTag
            )
            addToBackStack(null)
        }
    }

    enum class Destination(
        val fragmentClass: Class<out Fragment?>,
        val fragmentTag: String? = null,
        val argTag: String? = null,
    ) {
        PETS_DETAILS_FRAGMENT(
            PetsDetailsFragment::class.java,
            "PETS_DETAILS_FRAGMENT",
            PetsDetailsFragment.ARGUMENT_TAG
        ),
        PETS_LIST_FRAGMENT(
            PetsListFragment::class.java,
            "PETS_LIST_FRAGMENT"
        ),
    }
}