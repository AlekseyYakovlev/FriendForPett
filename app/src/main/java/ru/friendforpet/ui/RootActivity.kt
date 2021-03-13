package ru.friendforpet.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import ru.friendforpet.Navigator
import ru.friendforpet.R
import ru.friendforpet.databinding.ActivityRootBinding
import ru.friendforpet.ui.utils.viewBinding
import javax.inject.Inject

@AndroidEntryPoint
class RootActivity : AppCompatActivity() {
    //  private val vb by viewBinding(ActivityRootBinding::bind, R.id.root_container)

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_root)
        setupNavigation(savedInstanceState == null)
    }

    private fun setupNavigation(isInitial: Boolean) {
        if (isInitial) {
            navigator.navigateTo(Navigator.Destination.WELCOME_SCREEN_FRAGMENT)
        }
    }
}