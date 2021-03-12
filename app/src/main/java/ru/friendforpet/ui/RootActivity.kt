package ru.friendforpet.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import ru.friendforpet.R
import ru.friendforpet.databinding.ActivityRootBinding
import ru.spb.yakovlev.movieapp2020.utils.viewbindingdelegate.viewBinding

@AndroidEntryPoint
class RootActivity : AppCompatActivity(R.layout.activity_root) {
    private val vb by viewBinding(ActivityRootBinding::bind, R.id.root_container)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}