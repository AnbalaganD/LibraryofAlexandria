package edu.anbu.libraryofalexandria

import android.view.View

interface BookItemClickListener {
    fun onItemClicked(v: View?, position: Int)
}