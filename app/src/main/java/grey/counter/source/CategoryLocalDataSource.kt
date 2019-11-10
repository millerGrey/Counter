package grey.counter.source

object CategoryLocalDataSource : CategoryDataSource {

    private var list = listOf<Category>(
        Category(
            0,
            "маршрутка",
            45
        ),
        Category(1, "электричка", 46)
    ).toMutableList()//emptyList<Category>().toMutableList()
//    private var list = emptyList<Category>().toMutableList()
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
}