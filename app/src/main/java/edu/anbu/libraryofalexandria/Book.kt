package edu.anbu.libraryofalexandria

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Book(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var name: String,
    var isbn: Int,
    var author: String,
    var edition: Int,
    var publisher: String,
    var genre: String,
    var description: String,
    var year: Int
) : Parcelable {
    private constructor(parcel: Parcel) : this(
        id = parcel.readLong(),
        name = parcel.readString()!!,
        isbn = parcel.readInt(),
        author = parcel.readString()!!,
        edition = parcel.readInt(),
        publisher = parcel.readString()!!,
        genre = parcel.readString()!!,
        description = parcel.readString()!!,
        year = parcel.readInt()
    )

    override fun describeContents(): Int = 0

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