package grey.counter.source

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "NoteTable")

//        indices = [Index(value = "date", unique = true)])

data class Note (
    @PrimaryKey
    var date: String =" ",
    var res: Int = 0
)