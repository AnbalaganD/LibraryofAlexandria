package edu.anbu.libraryofalexandria

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
class Book : Parcelable {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    var name: String
    var isbn: Int
    var author: String
    var edition: Int
    var publisher: String
    var genre: String
    var description: String
    var year: Int

    constructor(
        name: String,
        isbn: Int,
        author: String,
        edition: Int,
        publisher: String,
        genre: String,
        description: String,
        year: Int
    ) {
        this.name = name
        this.isbn = isbn
        this.author = author
        this.edition = edition
        this.publisher = publisher
        this.genre = genre
        this.description = description
        this.year = year
    }
    constructor(`in`: Parcel) {
        id = `in`.readLong()
        name = `in`.readString()!!
        isbn = `in`.readInt()
        author = `in`.readString()!!
        edition = `in`.readInt()
        publisher = `in`.readString()!!
        genre = `in`.readString()!!
        description = `in`.readString()!!
        year = `in`.readInt()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeLong(id)
        parcel.writeString(name)
        parcel.writeInt(isbn)
        parcel.writeString(author)
        parcel.writeInt(edition)
        parcel.writeString(publisher)
        parcel.writeString(genre)
        parcel.writeString(description)
        parcel.writeInt(year)
    }

    companion object CREATOR : Parcelable.Creator<Book> {
        override fun createFromParcel(parcel: Parcel): Book {
            return Book(parcel)
        }

        override fun newArray(size: Int): Array<Book?> {
            return arrayOfNulls(size)
        }
    }
}