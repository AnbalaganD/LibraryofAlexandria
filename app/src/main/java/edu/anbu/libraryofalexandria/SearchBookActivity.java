package edu.anbu.libraryofalexandria;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class SearchBookActivity extends AppCompatActivity implements BookItemClickListener {

    private BookAdapter mSearchBookAdapter;
    private List<Book> searchResultList;
    private SearchAsyncTask searchAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_book);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }

        SearchView searchView = findViewById(R.id.search_book_search_view);
        RecyclerView searchResultRecyclerView = findViewById(R.id.search_result_recycler_view);

        searchResultList = new ArrayList<>();
//        addDummyList();
        searchResultRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mSearchBookAdapter = new BookAdapter(this, searchResultList, true);
        searchResultRecyclerView.setAdapter(mSearchBookAdapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                onSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                onSearch(newText);
                return false;
            }
        });
    }

    @Override
    public void onItemClicked(View v, int position) {
        Intent intent = new Intent(this, BookDetailActivity.class);
        intent.putExtra("Book", searchResultList.get(position));
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void onSearch(String keyword) {
        if (keyword == null || keyword.trim().equals("")) {
            searchResultList.clear();
//            mSearchBookAdapter.updateItems();
            return;
        }
        if (searchAsyncTask != null) {
            searchAsyncTask.cancel(true);
        }
        searchAsyncTask = new SearchAsyncTask(bookList -> {
            searchResultList.clear();
            searchResultList.addAll(bookList);
//            mSearchBookAdapter.updateItems();
        });
        searchAsyncTask.execute(keyword.trim());
    }

//    private void addDummyList() {
//        Book book1 = new Book("The Java Programming Language", 1, "James Gosling and ken Arnold", 2, "Programming foundation", "genre", "This book an introduce to programming in Oracle's Java programming language, a widely used programming language software platform.", 2017);
//        Book book2 = new Book("The Java Programming Language", 1, "James Gosling and ken Arnold", 2, "Programming foundation", "genre", "This book an introduce to programming in Oracle's Java programming language, a widely used programming language software platform.", 2017);
//        searchResultList.add(book1);
//        searchResultList.add(book2);
//    }

    private static class SearchAsyncTask extends AsyncTask<String, Void, List<Book>> {

        SearchBookListener listener;

        SearchAsyncTask(SearchBookListener listener) {
            this.listener = listener;
        }

        @Override
        protected List<Book> doInBackground(String... strings) {
            return AppDatabase.getInstance().bookDao().search("%" + strings[0] + "%");
        }

        @Override
        protected void onPostExecute(List<Book> books) {
            super.onPostExecute(books);
            listener.onSearchComplete(books);
        }
    }
}
