package edu.monash.libraryofalexandria;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Locale;

public class BookDetailActivity extends AppCompatActivity {

    private TextView mBookNameTextView;
    private TextView mISBNNameTextView;
    private TextView mAuthorTextView;
    private TextView mEditionTextView;
    private TextView mPublisherTextView;
    private TextView mGenreTextView;
    private TextView mYearTextView;
    private TextView mDescriptionTextView;

    private Book book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null)
            supportActionBar.setDisplayHomeAsUpEnabled(true);

        mBookNameTextView = findViewById(R.id.book_name_text_view);
        mISBNNameTextView = findViewById(R.id.isbn_text_view);
        mAuthorTextView = findViewById(R.id.book_author_text_view);
        mEditionTextView = findViewById(R.id.edition_text_view);
        mPublisherTextView = findViewById(R.id.publisher_text_view);
        mGenreTextView = findViewById(R.id.genre_text_view);
        mYearTextView = findViewById(R.id.year_text_view);
        mDescriptionTextView = findViewById(R.id.description_text_view);

        Book book = getIntent().getParcelableExtra("Book");
        setupData(book);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_book_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else if (R.id.edit_menu_item == itemId) {
            onEditBook();
        } else if (R.id.delete_menu_item == itemId) {
            onDeleteBook();
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupData(Book book) {
        if (book == null)
            return;

        this.book = book;
        mBookNameTextView.setText(book.getName());
        mISBNNameTextView.setText(String.format(Locale.ENGLISH, "%d", book.getIsbn()));
        mAuthorTextView.setText(book.getAuthor());
        mEditionTextView.setText(String.format(Locale.ENGLISH, "%d", book.getEdition()));
        mPublisherTextView.setText(book.getPublisher());
        mGenreTextView.setText(book.getGenre());
        mYearTextView.setText(String.format(Locale.ENGLISH, "%d", book.getYear()));
        mDescriptionTextView.setText(book.getDescription());
    }

    private void onEditBook() {
        if (book == null)
            return;
        Intent intent = new Intent(this, AddBookActivity.class);
        intent.putExtra("Book", book);
        startActivity(intent);
    }

    private void onDeleteBook() {
        //TODO implement delete logic here....
    }
}
