package grey.counter.source.local

import androidx.room.*
import grey.counter.source.Category

@Dao
interface CategoryDao {
    @Query("SELECT * FROM CategoryTable")
    fun getAllCategories(): List<Category>

    @Query("SELECT * FROM CategoryTable where id =:id")
    fun getCategoryById(id: Int): Category

    @Insert
    fun insert(cat: Category)

    @Update
    fun update(cat: Category)

    @Delete
    fun delete(cat: Category)
}
