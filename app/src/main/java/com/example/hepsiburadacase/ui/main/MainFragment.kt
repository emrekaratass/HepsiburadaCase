package com.example.hepsiburadacase.ui.main

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.RadioButton
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.example.hepsiburadacase.MainActivity
import com.example.hepsiburadacase.R
import com.example.hepsiburadacase.databinding.FragmentMainBinding
import com.example.hepsiburadacase.util.delegate.viewBinding
import com.example.hepsiburadacase.util.extension.findLastVisibleItemPosition
import com.example.hepsiburadacase.util.extension.showErrorDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private val binding: FragmentMainBinding by viewBinding()
    private val viewModel: MainViewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java] }

    private var entity = "movie"
    private var query = ""

    private val adapter: ArticlesAdapter by lazy {
        ArticlesAdapter {
            hideKeyboard()
            (activity as MainActivity).openDetailFragment(it)
        }
    }

    private val inputMethodManager by lazy {
        (activity as MainActivity).getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }

    private val recyclerViewOnScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val lastVisibleItemPosition = recyclerView.findLastVisibleItemPosition()
            viewModel.paginateCollection(lastVisibleItemPosition)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()

        with(binding.recyclerView) {
            adapter = this@MainFragment.adapter
            addOnScrollListener(recyclerViewOnScrollListener)
        }

        binding.editText.doAfterTextChanged {
            query = it.toString()
            if (query.length > 2) {
                viewModel.searchCollection(query, entity)
                binding.progressBar.apply { if (!isVisible) isVisible = true }
            }
        }

        binding.radioGroup.check(binding.radioButtonMovies.id)

        binding.radioGroup.setOnCheckedChangeListener { radioGroup, radioButtonId ->
            viewModel.clearData()
            val selectedRadioButton = radioGroup.findViewById<RadioButton>(radioButtonId)
            entity = getSelectedEntity(selectedRadioButton.text.toString())
            if (query.length > 2) {
                viewModel.searchCollection(query, entity)
                binding.progressBar.apply { if (!isVisible) isVisible = true }
            }
        }
    }

    private fun getSelectedEntity(entity: String): String =
        when (entity) {
            "Movies" -> "movie"
            "Music" -> "musicVideo"
            "Apps" -> "software"
            "Books" -> "ebook"
            else -> ""
        }

    private fun observeViewModel() {
        with(viewModel) {
            addListLiveData.observeForever { data ->
                binding.progressBar.isVisible = false
                adapter.addList(data.results)
            }

            setListLiveData.observeForever { data ->
                binding.progressBar.isVisible = false
                binding.recyclerView.scrollToPosition(0)
                adapter.setList(data.results)
            }

            errorLiveData.observeForever { message ->
                binding.progressBar.isVisible = false
                context.showErrorDialog(message)
            }
        }
    }

    private fun hideKeyboard() {
        (activity as MainActivity).currentFocus?.let { view ->
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }

        binding.editText.clearFocus()
    }
}