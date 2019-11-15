package grey.counter.MainScreen

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import grey.counter.source.Category

object ListBindingAdapter {

    @BindingAdapter("app:items")
    @JvmStatic fun setItems(listView: RecyclerView, items: List<Category>) {
        with(listView.adapter as CategoryListAdapter) {
            Log.d("RV","Refresh")
            refreshList(items)
        }
    }
}