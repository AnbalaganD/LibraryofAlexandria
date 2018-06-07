package edu.monash.libraryofalexandria;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcelable;
import android.os.Parcel;
import android.support.annotation.NonNull;

/**
 * Created by deepi on 18/03/2018.
 */

@Entity
public class Book implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private long id;
    @NonNull
    private String name;
    @NonNull
    private int isbn;
    @NonNull
    private String author;
    @NonNull
    private int edition;
    @NonNull
    private String publisher;
    @NonNull
    private String genre;
    @NonNull
    private String description;
    @NonNull
    private int year;

    public Book(String name, int isbn, String author, int edition, String publisher, String genre, String description, int year) {
        this.name = name;
        this.isbn = isbn;
        this.author = author;
        this.edition = edition;
        this.publisher = publisher;
        this.genre = genre;
        this.description = description;
        this.year = year;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getIsbn() {
        return isbn;
    }
    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public int getEdition() {
        return edition;
    }
    public void setEdition(int edition) {
        this.edition = edition;
    }

    public String getPublisher() {
        return publisher;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }

    //Parcelable
    protected Book(Parcel in) {
        id = in.readLong();
        name = in.readString();
        isbn = in.readInt();
        author = in.readString();
        edition = in.readInt();
        publisher = in.readString();
        genre = in.readString();
        description = in.readString();
        year = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(name);
        parcel.writeInt(isbn);
        parcel.writeString(author);
        parcel.writeInt(edition);
        parcel.writeString(publisher);
        parcel.writeString(genre);
        parcel.writeString(description);
        parcel.writeInt(year);
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
}