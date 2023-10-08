package edu.anbu.libraryofalexandria;

import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class AddBookActivity extends AppCompatActivity {

    private EditText mBookNameEditText;
    private EditText mISBNNameEditText;
    private EditText mAuthorEditText;
    private EditText mEditionEditText;
    private EditText mPublisherEditText;
    private EditText mGenreEditText;
    private EditText mYearEditText;
    private EditText mDescriptionEditText;

    private Book rawBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setHomeAsUpIndicator(R.drawable.ic_close_white);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }

        mBookNameEditText = findViewById(R.id.book_name_edit_text);
        mISBNNameEditText = findViewById(R.id.isbn_edit_text);
        mAuthorEditText = findViewById(R.id.author_edit_text);
        mEditionEditText = findViewById(R.id.edition_edit_text);
        mPublisherEditText = findViewById(R.id.publisher_edit_text);
        mGenreEditText = findViewById(R.id.genre_edit_text);
        mYearEditText = findViewById(R.id.year_edit_text);
        mDescriptionEditText = findViewById(R.id.description_edit_text);

        Book book = getIntent().getParcelableExtra("Book");
        setupData(book);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_book, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == android.R.id.home) {
            finish();
        } else if (itemId == R.id.add_book_menu_item) {
            addBook();
        }
        return super.onOptionsItemSelected(item);
    }

    private void addBook() {
        String bookName = mBookNameEditText.getText().toString();
        String isbnString = mISBNNameEditText.getText().toString();
        String author = mAuthorEditText.getText().toString();
        String editionString = mEditionEditText.getText().toString();
        String publisher = mPublisherEditText.getText().toString();
        String genre = mGenreEditText.getText().toString();
        String yearString = mYearEditText.getText().toString();
        String description = mDescriptionEditText.getText().toString();

        int isbn = 0;
        if (tryParseInt(isbnString)) {
            isbn = Integer.parseInt(isbnString);
        } else {
            Toast.makeText(this, "ISBN should be an integer", Toast.LENGTH_SHORT).show();
        }
        int edition = 0;
        if (tryParseInt(editionString)) {
            edition = Integer.parseInt(isbnString);
        } else {
            Toast.makeText(this, "Edition should be an integer", Toast.LENGTH_SHORT).show();
        }
        int year = 0;
        if (tryParseInt(yearString)) {
            year = Integer.parseInt(isbnString);
        } else {
            Toast.makeText(this, "Year should be an integer", Toast.LENGTH_SHORT).show();
        }
        Book book = new Book(bookName, isbn, author, edition, publisher, genre, description, year);
        if (rawBook != null) {
            book.setId(rawBook.getId());
        }
        new InsertOrUpdateAsyncTask(this.rawBook != null).execute(book);
    }

    private void setupData(Book book) {
        if (book == null)
            return;

        this.rawBook = book;
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            getSupportActionBar().setTitle("Edit Book");
        mBookNameEditText.setText(book.getName());
        mISBNNameEditText.setText(String.format(Locale.ENGLISH, "%d", book.getIsbn()));
        mAuthorEditText.setText(book.getAuthor());
        mEditionEditText.setText(String.format(Locale.ENGLISH, "%d", book.getEdition()));
        mPublisherEditText.setText(book.getPublisher());
        mGenreEditText.setText(book.getGenre());
        mYearEditText.setText(String.format(Locale.ENGLISH, "%d", book.getYear()));
        mDescriptionEditText.setText(book.getDescription());
    }

    //Utility method safe way to parse string to int
    private boolean tryParseInt(String str) {
        try {
            Integer.parseInt(str);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    private static class InsertOrUpdateAsyncTask extends AsyncTask<Book, Void, Void> {

        private boolean isUpdateBook;

        InsertOrUpdateAsyncTask(boolean isUpdate) {
            isUpdateBook = isUpdate;
        }

        @Override
        protected Void doInBackground(Book... books) {
            if (isUpdateBook)
                AppDatabase.getInstance().bookDao().update(books[0]);
            else
                AppDatabase.getInstance().bookDao().insert(books);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(LibraryOfAlexandria.getAppContext(), isUpdateBook ? "Book update successfully" : "Book added successfully", Toast.LENGTH_SHORT).show();
        }
    }
}
