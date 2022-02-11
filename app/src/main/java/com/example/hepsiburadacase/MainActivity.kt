package com.example.hepsiburadacase

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.hepsiburadacase.databinding.ActivityMainBinding
import com.example.hepsiburadacase.ui.detail.DetailFragment
import com.example.hepsiburadacase.ui.entity.ArticleViewItem
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
@RequiresApi(Build.VERSION_CODES.R)
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun openDetailFragment(artObjectViewItem: ArticleViewItem) {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container_view, DetailFragment.newInstance(artObjectViewItem))
            .addToBackStack(null)
            .commit()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        //if (hasFocus)
           // hideSystemUI()
    }

    private fun hideSystemUI() {
        window.setDecorFitsSystemWindows(false)
        val controller = window.insetsController
        if (controller != null) {
            controller.hide(WindowInsets.Type.statusBars())
            controller.systemBarsBehavior =
                WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }
}