package grey.counter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import grey.counter.source.CategoryDataSource

class ViewModelFactory(
    private val categoryDataSource: CategoryDataSource
):
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        with(modelClass) {
            when {
                isAssignableFrom(CategoryListViewModel::class.java) ->
                   return (CategoryListViewModel() as T)
                else->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        }
    }
}


