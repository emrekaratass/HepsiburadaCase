package com.example.hepsiburadacase.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.hepsiburadacase.MainActivity
import com.example.hepsiburadacase.R
import com.example.hepsiburadacase.databinding.FragmentDetailBinding
import com.example.hepsiburadacase.util.delegate.viewBinding
import com.example.hepsiburadacase.util.extension.formattedReleaseTime
import com.example.hepsiburadacase.util.extension.ignoreNull
import com.example.hepsiburadacase.util.extension.loadImage
import com.example.hepsiburadacase.util.extension.toSpanned
import dagger.hilt.android.AndroidEntryPoint
import java.text.NumberFormat
import java.util.*

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val articleViewItemArgs: DetailFragmentArgs by navArgs()
    private val binding: FragmentDetailBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            val collectionPrice: String =
                if (articleViewItemArgs.articleViewItem.collectionPrice == 0.0) {
                    "FREE"
                } else {
                    NumberFormat.getCurrencyInstance(Locale("EN", "US"))
                        .format(articleViewItemArgs.articleViewItem.collectionPrice)
                }

            imageButtonBack.setOnClickListener {
                (activity as MainActivity).onBackPressed()
            }


            imageView.loadImage(articleViewItemArgs.articleViewItem.artworkUrl100)
            imageViewFull.loadImage(articleViewItemArgs.articleViewItem.artworkUrl100)

            textViewTitle.text = articleViewItemArgs.articleViewItem.collectionName
            textViewCollectionPrice.text = collectionPrice
            textViewReleaseDate.text =
                articleViewItemArgs.articleViewItem.releaseDate.formattedReleaseTime()
            textViewLongDescription.text =
                articleViewItemArgs.articleViewItem.description.ignoreNull().toSpanned()
        }
    }
}