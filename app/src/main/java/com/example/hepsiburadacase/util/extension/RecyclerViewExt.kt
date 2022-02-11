package com.example.hepsiburadacase.util.extension

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.findLastVisibleItemPosition(): Int =
    (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()