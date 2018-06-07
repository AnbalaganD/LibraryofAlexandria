package edu.monash.libraryofalexandria;

import java.util.List;

public interface BookSelectListener {
    void onBookFetched(List<Book> books);
}
