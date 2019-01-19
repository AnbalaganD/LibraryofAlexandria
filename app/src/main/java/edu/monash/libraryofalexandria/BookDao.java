package edu.monash.libraryofalexandria;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BookDao {
    @Query("select * from Book")
    List<Book> getAll();

    @Insert
    void insert(Book... books);

    @Update
    void update(Book book);

    @Delete
    void delete(Book book);

    @Query("select * from Book where Book.name like :keyword or Book.author like :keyword or Book.description like :keyword or Book.genre like :keyword or Book.publisher like :keyword")
    List<Book> search(String keyword);
}
