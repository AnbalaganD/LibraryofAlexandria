package edu.monash.libraryofalexandria;

import java.util.List;

public interface SearchBookListener {
    void onSearchComplete(List<Book> bookList);
}
