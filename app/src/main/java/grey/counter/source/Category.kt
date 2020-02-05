package grey.counter.source

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CategoryTable")
data class Category(
    @PrimaryKey(autoGenerate = true) var id: Int,
    var name: String="",
    var coast: Int=0)