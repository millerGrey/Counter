package grey.counter.source

import grey.counter.App
import java.util.*

object CategoryLocalDataSource : CategoryDataSource {

    private var note : Note = Note()

    val catDao = App.instance.dataBase.catDao()
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

    override fun getNote(date: Date): Note {
        return note
    }

    override fun addNote(note: Note) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun editNote(note: Note) {
        this.note = note
    }

    override fun deleteNote(note: Note) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}