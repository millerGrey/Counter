package grey.counter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import grey.counter.source.Category

object CategoryListBindings {

    @BindingAdapter("items")
    @JvmStatic fun setItems(rescView: RecyclerView, items:List<Category>) {
        (rescView.adapter as RecyclerAdapter).refresh(items)
    }
}
