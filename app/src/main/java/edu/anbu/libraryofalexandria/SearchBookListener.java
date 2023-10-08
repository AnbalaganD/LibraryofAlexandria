package edu.anbu.libraryofalexandria;

import java.util.List;

public interface SearchBookListener {
    void onSearchComplete(List<Book> bookList);
}
