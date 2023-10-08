package edu.anbu.libraryofalexandria

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeActivity : AppCompatActivity(), BookItemClickListener {
    private lateinit var bookAdapter: BookAdapter
    private lateinit var viewModel: HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val booksRecyclerView = findViewById<RecyclerView>(R.id.books_recycler_view)
        val addBookFabButton = findViewById<FloatingActionButton>(R.id.add_book_floating_button)

        booksRecyclerView.layoutManager = LinearLayoutManager(this)
        bookAdapter = BookAdapter(this, false)
        booksRecyclerView.adapter = bookAdapter
        booksRecyclerView.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.HORIZONTAL
            )
        )
        viewModel = ViewModelProviders.of(this)[HomeViewModel::class.java]
        addBookFabButton.setOnClickListener {
            startActivity(
                Intent(
                    this@HomeActivity,
                    AddBookActivity::class.java
                )
            )
        }
        addObservables()
    }

    private fun addObservables() {
        viewModel.getBookList()?.observe(this) { books: List<Book> -> bookAdapter.updateItems(books) }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.search_menu_item -> startActivity(Intent(this, SearchBookActivity::class.java))
            R.id.short_by_author_menu_item -> {}
            R.id.short_by_name_menu_item -> {}
            R.id.short_by_publisher_menu_item -> {}
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onItemClicked(v: View?, position: Int) {
//        Intent intent = new Intent(this, BookDetailActivity.class);
//        intent.putExtra("Book", bookList.get(position));
//        startActivity(intent);
    }
}