package edu.anbu.libraryofalexandria;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HomeActivity extends AppCompatActivity implements BookItemClickListener {

    private BookAdapter bookAdapter;
    private HomeViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        RecyclerView booksRecyclerView = findViewById(R.id.books_recycler_view);
        FloatingActionButton addBookFabButton = findViewById(R.id.add_book_floating_button);

//        addDummyBooks();
        booksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        bookAdapter = new BookAdapter(this, false);
        booksRecyclerView.setAdapter(bookAdapter);
        booksRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL));

        viewModel = ViewModelProviders.of(this).get(HomeViewModel.class);

        addBookFabButton.setOnClickListener(view -> startActivity(new Intent(HomeActivity.this, AddBookActivity.class)));
        addObservables();
    }

    private void addObservables() {
        viewModel.getBookList().observe(this, books -> bookAdapter.updateItems(books));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.search_menu_item) {
            startActivity(new Intent(this, SearchBookActivity.class));
        } else if (itemId == R.id.short_by_author_menu_item) {

        } else if (itemId == R.id.short_by_name_menu_item) {

        } else if (itemId == R.id.short_by_publisher_menu_item) {

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClicked(View v, int position) {
//        Intent intent = new Intent(this, BookDetailActivity.class);
//        intent.putExtra("Book", bookList.get(position));
//        startActivity(intent);
    }

//    private void getAllBook() {
//        new SelectAsyncTask(books -> {
//            bookList.addAll(books);
//            bookAdapter.updateItems();
//        }).execute();
//    }

//    private void addDummyBooks() {
//        Book book1 = new Book("The Java Programming Language", 1, "James Gosling and ken Arnold", 2, "Programming foundation", "genre", "This book an introduce to programming in Oracle's Java programming language, a widely used programming language software platform.", 2017);
//        Book book2 = new Book("The Java Programming Language", 1, "James Gosling and ken Arnold", 2, "Programming foundation", "genre", "This book an introduce to programming in Oracle's Java programming language, a widely used programming language software platform.", 2017);
//        bookList.add(book1);
//        bookList.add(book2);
//    }

//    private static class SelectAsyncTask extends AsyncTask<Void, Void, List<Book>> {
//
//        BookSelectListener listener;
//
//        SelectAsyncTask(BookSelectListener listener) {
//            this.listener = listener;
//        }
//
//        @Override
//        protected List<Book> doInBackground(Void... voids) {
//            return AppDatabase.getInstance().bookDao().getAll();
//        }
//
//        @Override
//        protected void onPostExecute(List<Book> books) {
//            super.onPostExecute(books);
//            Log.e("Book list:", books.toString());
//            listener.onBookFetched(books);
//        }
//    }
}