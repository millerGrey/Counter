package grey.counter.source.local

import grey.counter.App
import grey.counter.source.Category
import grey.counter.source.CategoryDataSource
import grey.counter.source.Note
import java.util.*

object LocalDataSource : CategoryDataSource {

//    private var note : Note = Note()

    val catDao = App.instance.dataBase.catDao()
    val noteDao = App.instance.dataBase.noteDao()
    override fun getAllCategories(): List<Category> {

        return catDao.getAllCategories()
    }

    override fun getCategory(id: Int): Category {
        return catDao.getCategoryById(id)
    }

    override fun addCategory(cat: Category) {
        catDao.insert(cat)
    }

    override fun deleteCategory(cat: Category) {
        catDao.delete(cat)
    }

    override fun editCategory(cat: Category) {
        catDao.update(cat)
    }

    override fun getNote(date: String): Note? {
        return noteDao.getNote(date)
    }

    override fun addNote(note: Note) {
        return noteDao.insert(note)
    }

    override fun editNote(note: Note) {
        return noteDao.update(note)
    }

    override fun deleteNote(note: Note) {
        return noteDao.delete(note)
    }
}