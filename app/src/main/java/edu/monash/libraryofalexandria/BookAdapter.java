package edu.monash.libraryofalexandria;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {
    private List<Book> bookList;
    private boolean isSearchType;
    private BookItemClickListener listener;

    BookAdapter(BookItemClickListener listener, List<Book> bookList, boolean isSearchType) {
        this.listener = listener;
        this.bookList = bookList;
        this.isSearchType = isSearchType;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int inflatedResource = isSearchType ? R.layout.item_search_book : R.layout.item_book;
        View view = LayoutInflater.from(parent.getContext()).inflate(inflatedResource, parent, false);
        final BookViewHolder viewHolder = new BookViewHolder(view, isSearchType);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClicked(view, viewHolder.getAdapterPosition());
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        holder.setData(bookList.get(position));
    }

    @Override
    public int getItemCount() {
        return bookList == null ? 0 : bookList.size();
    }

    public void updateItems() {
        notifyDataSetChanged();
    }

    static class BookViewHolder extends RecyclerView.ViewHolder implements AdapterView.OnItemClickListener {
        private ImageView bookImageView;
        private TextView bookNameTextView;
        private TextView authorTextView;
        private TextView editionTextView;
        private TextView descriptionTextView;
        private boolean isSearchType;

        BookViewHolder(View itemView, boolean isSearchType) {
            super(itemView);

            this.isSearchType = isSearchType;
            if (isSearchType) {
                descriptionTextView = itemView.findViewById(R.id.book_description_text_view);
            } else {
                bookImageView = itemView.findViewById(R.id.book_image_view);
                editionTextView = itemView.findViewById(R.id.edition_text_view);
            }
            bookNameTextView = itemView.findViewById(R.id.book_name_text_view);
            authorTextView = itemView.findViewById(R.id.book_author_text_view);
        }

        public void setData(Book book) {
            if (isSearchType) {
                descriptionTextView.setText(book.getDescription());
            } else {
                //Set book
                editionTextView.setText(String.format(Locale.ENGLISH, "Edition %d", book.getEdition()));
            }
            bookNameTextView.setText(book.getName());
            authorTextView.setText(book.getAuthor());
        }

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Log.e("Selcted Item", "onItemClick: " + i);
        }
    }
}