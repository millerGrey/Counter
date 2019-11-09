package grey.counter.source

interface CategoryDataSource {
    fun getAllCategories(): List<Category>
    fun getCategory(id: Int): Category
    fun addCategory(cat: Category)
    fun deleteCategory(cat: Category)
    fun editCategory(cat: Category)
}