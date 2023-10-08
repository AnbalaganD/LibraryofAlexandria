package edu.anbu.libraryofalexandria

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.anbu.libraryofalexandria.AppDatabase.Companion.instance

internal class HomeViewModel : ViewModel() {
    private var bookList: MutableLiveData<List<Book>>? = null
    fun getBookList(): LiveData<List<Book>>? {
        if (bookList == null) {
            bookList = MutableLiveData()
        }

        val books = instance.bookDao().all
        bookList?.postValue(books)
        return bookList
    }
}