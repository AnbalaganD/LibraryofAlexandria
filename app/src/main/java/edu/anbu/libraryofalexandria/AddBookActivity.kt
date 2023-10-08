package edu.anbu.libraryofalexandria

import android.os.AsyncTask
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import edu.anbu.libraryofalexandria.AppDatabase.Companion.instance
import java.util.Locale

class AddBookActivity : AppCompatActivity() {
    private lateinit var mBookNameEditText: EditText
    private lateinit var mISBNNameEditText: EditText
    private lateinit var mAuthorEditText: EditText
    private lateinit var mEditionEditText: EditText
    private lateinit var mPublisherEditText: EditText
    private lateinit var mGenreEditText: EditText
    private lateinit var mYearEditText: EditText
    private lateinit var mDescriptionEditText: EditText
    private var rawBook: Book? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_book)
        val supportActionBar = supportActionBar
        if (supportActionBar != null) {
            supportActionBar.setHomeAsUpIndicator(R.drawable.ic_close_white)
            supportActionBar.setDisplayHomeAsUpEnabled(true)
        }
        mBookNameEditText = findViewById(R.id.book_name_edit_text)
        mISBNNameEditText = findViewById(R.id.isbn_edit_text)
        mAuthorEditText = findViewById(R.id.author_edit_text)
        mEditionEditText = findViewById(R.id.edition_edit_text)
        mPublisherEditText = findViewById(R.id.publisher_edit_text)
        mGenreEditText = findViewById(R.id.genre_edit_text)
        mYearEditText = findViewById(R.id.year_edit_text)
        mDescriptionEditText = findViewById(R.id.description_edit_text)
        val book = intent.getParcelableExtra<Book>("Book")
        setupData(book)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_add_book, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val itemId = item.itemId
        if (itemId == android.R.id.home) {
            finish()
        } else if (itemId == R.id.add_book_menu_item) {
            addBook()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun addBook() {
        val bookName = mBookNameEditText.text.toString()
        val isbnString = mISBNNameEditText.text.toString()
        val author = mAuthorEditText.text.toString()
        val editionString = mEditionEditText.text.toString()
        val publisher = mPublisherEditText.text.toString()
        val genre = mGenreEditText.text.toString()
        val yearString = mYearEditText.text.toString()
        val description = mDescriptionEditText.text.toString()
        var isbn = 0
        if (tryParseInt(isbnString)) {
            isbn = isbnString.toInt()
        } else {
            Toast.makeText(this, "ISBN should be an integer", Toast.LENGTH_SHORT).show()
        }
        var edition = 0
        if (tryParseInt(editionString)) {
            edition = isbnString.toInt()
        } else {
            Toast.makeText(this, "Edition should be an integer", Toast.LENGTH_SHORT).show()
        }
        var year = 0
        if (tryParseInt(yearString)) {
            year = isbnString.toInt()
        } else {
            Toast.makeText(this, "Year should be an integer", Toast.LENGTH_SHORT).show()
        }
        val book = Book(bookName, isbn, author, edition, publisher, genre, description, year)
        if (rawBook != null) {
            book.id = rawBook!!.id
        }
        InsertOrUpdateAsyncTask(rawBook != null).execute(book)
    }

    private fun setupData(book: Book?) {
        if (book == null) return
        rawBook = book
        val actionBar = supportActionBar
        if (actionBar != null) supportActionBar!!.title = "Edit Book"
        mBookNameEditText.setText(book.name)
        mISBNNameEditText.setText(String.format(Locale.ENGLISH, "%d", book.isbn))
        mAuthorEditText.setText(book.author)
        mEditionEditText.setText(String.format(Locale.ENGLISH, "%d", book.edition))
        mPublisherEditText.setText(book.publisher)
        mGenreEditText.setText(book.genre)
        mYearEditText.setText(String.format(Locale.ENGLISH, "%d", book.year))
        mDescriptionEditText.setText(book.description)
    }

    //Utility method safe way to parse string to int
    private fun tryParseInt(str: String): Boolean {
        try {
            str.toInt()
        } catch (ex: Exception) {
            return false
        }
        return true
    }

    private inner class InsertOrUpdateAsyncTask(private val isUpdateBook: Boolean) :
        AsyncTask<Book, Void?, Void?>() {
        override fun doInBackground(vararg books: Book): Void? {
            if (isUpdateBook) books[0].let {
                instance.bookDao()
                    .update(it)
            } else instance.bookDao().insert(books.toList())
            return null
        }

        override fun onPostExecute(aVoid: Void?) {
            super.onPostExecute(aVoid)
            Toast.makeText(
                LibraryOfAlexandria.appContext,
                if (isUpdateBook) "Book update successfully" else "Book added successfully",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}