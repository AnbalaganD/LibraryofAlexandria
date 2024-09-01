package edu.anbu.libraryofalexandria

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Locale

class BookDetailActivity : AppCompatActivity() {
    private lateinit var mBookNameTextView: TextView
    private lateinit var mISBNNameTextView: TextView
    private lateinit var mAuthorTextView: TextView
    private lateinit var mEditionTextView: TextView
    private lateinit var mPublisherTextView: TextView
    private lateinit var mGenreTextView: TextView
    private lateinit var mYearTextView: TextView
    private lateinit var mDescriptionTextView: TextView
    private lateinit var book: Book

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_details)
        val supportActionBar = supportActionBar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mBookNameTextView = findViewById(R.id.book_name_text_view)
        mISBNNameTextView = findViewById(R.id.isbn_text_view)
        mAuthorTextView = findViewById(R.id.book_author_text_view)
        mEditionTextView = findViewById(R.id.edition_text_view)
        mPublisherTextView = findViewById(R.id.publisher_text_view)
        mGenreTextView = findViewById(R.id.genre_text_view)
        mYearTextView = findViewById(R.id.year_text_view)
        mDescriptionTextView = findViewById(R.id.description_text_view)
        val book = intent.getParcelableExtra<Book>("Book") ?: throw RuntimeException("Should provide book")
        setupData(book)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_book_details, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val itemId = item.itemId
        if (item.itemId == android.R.id.home) {
            finish()
        } else if (R.id.edit_menu_item == itemId) {
            onEditBook()
        } else if (R.id.delete_menu_item == itemId) {
            onDeleteBook()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupData(book: Book) {
        this.book = book
        mBookNameTextView.text = book.name
        mISBNNameTextView.text = String.format(Locale.ENGLISH, "%d", book.isbn)
        mAuthorTextView.text = book.author
        mEditionTextView.text = String.format(Locale.ENGLISH, "%d", book.edition)
        mPublisherTextView.text = book.publisher
        mGenreTextView.text = book.genre
        mYearTextView.text = String.format(Locale.ENGLISH, "%d", book.year)
        mDescriptionTextView.text = book.description
    }

    private fun onEditBook() {
        val intent = Intent(this, AddBookActivity::class.java)
        intent.putExtra("Book", book)
        startActivity(intent)
    }

    private fun onDeleteBook() {
        lifecycleScope.launch(Dispatchers.IO) {
            AppDatabase.instance.bookDao().delete(book)
        }
    }
}