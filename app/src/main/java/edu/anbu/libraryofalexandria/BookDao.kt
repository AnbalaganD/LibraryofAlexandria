package edu.anbu.libraryofalexandria

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface BookDao {
    @get:Query("select * from Book")
    val all: List<Book>

    @Insert
    fun insert(books: List<Book>)

    @Insert
    fun insert(book: Book)

    @Update
    fun update(book: Book)

    @Delete
    fun delete(book: Book)

    @Query("select * from Book where Book.name like :keyword or Book.author like :keyword or Book.description like :keyword or Book.genre like :keyword or Book.publisher like :keyword")
    fun search(keyword: String?): List<Book>
}