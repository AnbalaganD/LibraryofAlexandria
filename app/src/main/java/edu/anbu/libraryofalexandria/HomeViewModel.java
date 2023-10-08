package edu.anbu.libraryofalexandria;

import android.os.Handler;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

class HomeViewModel extends ViewModel {

    private MutableLiveData<List<Book>> bookList;

    MutableLiveData<List<Book>> getBookList() {
        if (bookList == null) {
            bookList = new MutableLiveData<>();
        }
        new Handler().post(() -> {
            List<Book> books = AppDatabase.getInstance().bookDao().getAll();
            bookList.postValue(books);
        });
        return bookList;
    }
}
