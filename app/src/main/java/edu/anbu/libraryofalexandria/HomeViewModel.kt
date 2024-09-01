package edu.anbu.libraryofalexandria

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.anbu.libraryofalexandria.AppDatabase.Companion.instance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

internal class HomeViewModel : ViewModel() {
    private var bookList: MutableLiveData<List<Book>>? = null
    fun getBookList(): LiveData<List<Book>>? {
        if (bookList == null) {
            bookList = MutableLiveData()
        }

        viewModelScope.launch(Dispatchers.IO) {
            val books = instance.bookDao().all
            bookList?.postValue(books)
        }
        return bookList
    }
}