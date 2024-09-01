package edu.anbu.libraryofalexandria

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.anbu.libraryofalexandria.AppDatabase.Companion.instance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SearchBookActivity : AppCompatActivity(), BookItemClickListener {
    private lateinit var mSearchBookAdapter: BookAdapter
    private var searchResultList: MutableList<Book>? = null
    private var searchJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_book)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val supportActionBar = supportActionBar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val rootView = findViewById<LinearLayout>(R.id.root_linear_layout)
        ViewCompat.setOnApplyWindowInsetsListener(rootView) { view, listener ->
            val systemBarsInsets = listener.getInsets(WindowInsetsCompat.Type.systemBars())
            view.updatePadding(top = systemBarsInsets.top)
            listener
        }

        val searchView = findViewById<SearchView>(R.id.search_book_search_view)
        val searchResultRecyclerView = findViewById<RecyclerView>(R.id.search_result_recycler_view)
        searchResultRecyclerView.layoutManager = LinearLayoutManager(this)
        mSearchBookAdapter = BookAdapter(this, searchResultList, true)
        searchResultRecyclerView.adapter = mSearchBookAdapter
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                onSearchNew(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                onSearchNew(newText)
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

    private fun onSearchNew(keyword: String?) {
        searchJob?.cancel()
        if (keyword == null || keyword.trim { it <= ' ' } == "") {
            searchResultList?.clear()
            mSearchBookAdapter.updateItems(null)
            return
        }

        searchJob = lifecycleScope.launch(Dispatchers.IO) {
            val searchKeyword = "%" + keyword.trim { it <= ' ' }.plus("%")
            val books = instance.bookDao().search(searchKeyword)
            searchResultList = books?.toMutableList()
            lifecycleScope.launch(Dispatchers.Main) {
                mSearchBookAdapter.updateItems(searchResultList)
            }
        }
    }
}