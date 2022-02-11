package com.example.hepsiburadacase.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.hepsiburadacase.MainActivity
import com.example.hepsiburadacase.R
import com.example.hepsiburadacase.databinding.FragmentDetailBinding
import com.example.hepsiburadacase.ui.entity.ArticleViewItem
import com.example.hepsiburadacase.util.delegate.viewBinding
import com.example.hepsiburadacase.util.extension.loadImage
import com.example.hepsiburadacase.util.extension.toSpanned
import dagger.hilt.android.AndroidEntryPoint
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private lateinit var viewItem: ArticleViewItem
    private val binding: FragmentDetailBinding by viewBinding()

    companion object {
        fun newInstance(viewItem: ArticleViewItem) =
            DetailFragment().apply { this.viewItem = viewItem }
    }

    @SuppressLint("SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            val date: Date = simpleDateFormat.parse(viewItem.releaseDate)
            val releaseTime = SimpleDateFormat("dd/MM/yyyy").format(date)

            val collectionPrice: String = if (viewItem.collectionPrice == 0.0) {
                "FREE"
            } else {
                NumberFormat.getCurrencyInstance(Locale("EN", "US"))
                    .format(viewItem.collectionPrice)
            }

            imageButtonBack.setOnClickListener {
                (activity as MainActivity).onBackPressed()
            }

            imageView.loadImage(viewItem.artworkUrl100)
            imageViewFull.loadImage(viewItem.artworkUrl100)

            textViewTitle.text = viewItem.collectionName
            textViewCollectionPrice.text = collectionPrice
            textViewReleaseDate.text = releaseTime
            textViewLongDescription.text = viewItem.description.toSpanned()
        }
    }
}