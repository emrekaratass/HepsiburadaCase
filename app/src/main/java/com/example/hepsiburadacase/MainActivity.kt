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

    fun openDetailFragment(articleViewItem: ArticleViewItem) {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container_view, DetailFragment.newInstance(articleViewItem))
            .addToBackStack(null)
            .commit()
    }
}