package edu.anbu.libraryofalexandria

interface SearchBookListener {
    fun onSearchComplete(bookList: List<Book>?)
}