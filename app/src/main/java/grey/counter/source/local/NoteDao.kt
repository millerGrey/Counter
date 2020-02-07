package grey.counter.source.local

import androidx.room.*
import grey.counter.source.Note

@Dao
interface NoteDao {

    @Query("SELECT * FROM NoteTable where date = :date")
    fun getNote(date: String): Note

    @Insert
    fun insert(note: Note)

    @Update
    fun update(note: Note)

    @Delete
    fun delete(note: Note)


}