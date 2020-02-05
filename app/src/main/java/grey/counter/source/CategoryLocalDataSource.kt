package grey.counter.source

import java.util.*

object CategoryLocalDataSource : CategoryDataSource {


    private var list = emptyList<Category>().toMutableList()
    private var note : Note = Note()
    override fun getAllCategories(): List<Category> {

        return list
    }

    override fun getCategory(id: Int): Category {
        return list[id]
    }

    override fun addCategory(cat: Category) {
        list.add(cat)
    }

    override fun deleteCategory(cat: Category) {
        list.remove(cat)
    }

    override fun editCategory(cat: Category) {
        list[cat.id] = cat
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