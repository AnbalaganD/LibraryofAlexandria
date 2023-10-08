package edu.anbu.libraryofalexandria

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.anbu.libraryofalexandria.AppDatabase.Companion.instance

class SearchBookActivity : AppCompatActivity(), BookItemClickListener {
    private lateinit var mSearchBookAdapter: BookAdapter
    private var searchResultList: MutableList<Book>? = null
    private var searchAsyncTask: SearchAsyncTask? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_book)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val supportActionBar = supportActionBar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val searchView = findViewById<SearchView>(R.id.search_book_search_view)
        val searchResultRecyclerView = findViewById<RecyclerView>(R.id.search_result_recycler_view)
        searchResultRecyclerView.layoutManager = LinearLayoutManager(this)
        mSearchBookAdapter = BookAdapter(this, searchResultList, true)
        searchResultRecyclerView.adapter = mSearchBookAdapter
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                onSearch(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                onSearch(newText)
                return false
            }
        })
    }

    override fun onItemClicked(v: View?, position: Int) {
        val intent = Intent(this, BookDetailActivity::class.java)
        intent.putExtra("Book", searchResultList?.get(position))
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun onSearch(keyword: String?) {
        if (keyword == null || keyword.trim { it <= ' ' } == "") {
            searchResultList?.clear()
            //            mSearchBookAdapter.updateItems();
            return
        }
        if (searchAsyncTask != null) {
            searchAsyncTask?.cancel(true)
        }
        searchAsyncTask = SearchAsyncTask(object : SearchBookListener {
            override fun onSearchComplete(bookList: List<Book>?) {
                searchResultList?.clear()
                searchResultList?.addAll(bookList!!)
            }
        })
        searchAsyncTask?.execute(keyword.trim { it <= ' ' })
    }

    private class SearchAsyncTask(var listener: SearchBookListener) :
        AsyncTask<String?, Void?, List<Book>?>() {
        override fun doInBackground(vararg strings: String?): List<Book>? {
            return instance.bookDao().search("%" + strings[0] + "%")
        }

        override fun onPostExecute(books: List<Book>?) {
            super.onPostExecute(books)
            listener.onSearchComplete(books)
        }
    }
}