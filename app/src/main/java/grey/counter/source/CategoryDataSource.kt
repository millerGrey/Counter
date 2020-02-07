package grey.counter.source

import java.util.*

interface CategoryDataSource {
    fun getAllCategories(): List<Category>
    fun getCategory(id: Int): Category
    fun addCategory(cat: Category)
    fun deleteCategory(cat: Category)
    fun editCategory(cat: Category)
    fun getNote(date: String): Note?
    fun addNote(note: Note)
    fun editNote(note: Note)
    fun deleteNote(note: Note)
}
