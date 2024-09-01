package edu.anbu.libraryofalexandria

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.anbu.libraryofalexandria.BookAdapter.BookViewHolder
import java.util.Locale

class BookAdapter : RecyclerView.Adapter<BookViewHolder> {
    private var bookList: MutableList<Book>? = null
    private var isSearchType: Boolean
    private var listener: BookItemClickListener

    internal constructor(
        listener: BookItemClickListener,
        bookList: MutableList<Book>?,
        isSearchType: Boolean
    ) {
        this.listener = listener
        this.bookList = bookList
        this.isSearchType = isSearchType
    }

    internal constructor(listener: BookItemClickListener, isSearchType: Boolean) {
        this.listener = listener
        this.isSearchType = isSearchType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val inflatedResource = if (isSearchType) R.layout.item_search_book else R.layout.item_book
        val view = LayoutInflater.from(parent.context).inflate(inflatedResource, parent, false)
        val viewHolder = BookViewHolder(view, isSearchType)
        view.setOnClickListener { selectedView: View? ->
            listener.onItemClicked(
                selectedView,
                viewHolder.adapterPosition
            )
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.setData(bookList!![position])
    }

    override fun getItemCount(): Int {
        return if (bookList == null) 0 else bookList!!.size
    }

    fun updateItems(books: List<Book>?) {
        if (this.bookList == null) {
            this.bookList = mutableListOf()
        }

        this.bookList?.clear()
        if (books != null) {
            this.bookList?.addAll(books)
        }
        notifyDataSetChanged()
    }

    class BookViewHolder(itemView: View, private val isSearchType: Boolean) :
        RecyclerView.ViewHolder(itemView), OnItemClickListener {
        private var bookImageView: ImageView? = null
        private val bookNameTextView: TextView
        private val authorTextView: TextView
        private var editionTextView: TextView? = null
        private var descriptionTextView: TextView? = null

        init {
            if (isSearchType) {
                descriptionTextView = itemView.findViewById(R.id.book_description_text_view)
            } else {
                bookImageView = itemView.findViewById(R.id.book_image_view)
                editionTextView = itemView.findViewById(R.id.edition_text_view)
            }
            bookNameTextView = itemView.findViewById(R.id.book_name_text_view)
            authorTextView = itemView.findViewById(R.id.book_author_text_view)
        }

        fun setData(book: Book) {
            if (isSearchType) {
                descriptionTextView!!.text = book.description
            } else {
                //Set book
                editionTextView!!.text = String.format(Locale.ENGLISH, "Edition %d", book.edition)
            }
            bookNameTextView.text = book.name
            authorTextView.text = book.author
        }

        override fun onItemClick(adapterView: AdapterView<*>?, view: View, i: Int, l: Long) {
            Log.e("Selcted Item", "onItemClick: $i")
        }
    }
}