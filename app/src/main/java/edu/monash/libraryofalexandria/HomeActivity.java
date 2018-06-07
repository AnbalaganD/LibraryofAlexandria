package edu.monash.libraryofalexandria;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements BookItemClickListener {

    private List<Book> bookList;
    private BookAdapter bookAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        RecyclerView booksRecyclerView = findViewById(R.id.books_recycler_view);
        FloatingActionButton addBookFabButton = findViewById(R.id.add_book_floating_button);

        bookList = new ArrayList<>();
//        addDummyBooks();
        booksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        bookAdapter = new BookAdapter(this, bookList, false);
        booksRecyclerView.setAdapter(bookAdapter);
        booksRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL));

        addBookFabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, AddBookActivity.class));
            }
        });

        getAllBook();
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
        Intent intent = new Intent(this, BookDetailActivity.class);
        intent.putExtra("Book", bookList.get(position));
        startActivity(intent);
    }

    private void getAllBook() {
        new SelectAsyncTask(new BookSelectListener() {
            @Override
            public void onBookFetched(List<Book> books) {
                bookList.addAll(books);
                bookAdapter.updateItems();
            }
        }).execute();
    }

//    private void addDummyBooks() {
//        Book book1 = new Book("The Java Programming Language", 1, "James Gosling and ken Arnold", 2, "Programming foundation", "genre", "This book an introduce to programming in Oracle's Java programming language, a widely used programming language software platform.", 2017);
//        Book book2 = new Book("The Java Programming Language", 1, "James Gosling and ken Arnold", 2, "Programming foundation", "genre", "This book an introduce to programming in Oracle's Java programming language, a widely used programming language software platform.", 2017);
//        bookList.add(book1);
//        bookList.add(book2);
//    }

    private class SelectAsyncTask extends AsyncTask<Void, Void, List<Book>> {

        BookSelectListener listener;
        SelectAsyncTask(BookSelectListener listener) {
            this.listener = listener;
        }

        @Override
        protected List<Book> doInBackground(Void... voids) {
            return AppDatabase.getInstance().bookDao().getAll();
        }

        @Override
        protected void onPostExecute(List<Book> books) {
            super.onPostExecute(books);
            Log.e("Book list:", books.toString());
            listener.onBookFetched(books);
        }
    }
}