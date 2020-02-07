package grey.counter.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import grey.counter.source.Category
import grey.counter.source.Note

@Database(entities = arrayOf(Category::class, Note::class), version = 1)
abstract class LocalDB : RoomDatabase() {
    abstract fun catDao(): CategoryDao
    abstract fun noteDao(): NoteDao

}