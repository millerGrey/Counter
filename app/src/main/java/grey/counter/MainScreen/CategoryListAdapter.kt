package grey.counter.MainScreen

import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import grey.counter.BR
import grey.counter.Category.ItemClickListener
import grey.counter.CategoryListViewModel
import grey.counter.databinding.ItemCategoryBinding
import grey.counter.source.Category
import java.util.zip.Inflater

class CategoryListAdapter(
    private val itemLayoutId: Int,
    private val categoryListViewModel: CategoryListViewModel?
    ) : RecyclerView.Adapter<CategoryListAdapter.ViewHolder>() {
    private var list: List<Category> = emptyList()
    private lateinit var itemBinding: ViewDataBinding
    init {
        Log.d("RV", "init Adapter ${list.size}")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d("RV","onCreateVH")
        val infl = LayoutInflater.from(parent?.context)
        val view = infl.inflate(itemLayoutId, parent, false)

        itemBinding = DataBindingUtil.bind<ViewDataBinding>(view)!!

        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(list[position], categoryListViewModel!!)
    }


    override fun getItemCount(): Int {
        return list.size
    }

    fun refreshList(lst: List<Category>){
        Log.d("RV","oldList $list")
        Log.d("RV","newList $lst")
        list = lst
        notifyDataSetChanged()
    }


    class ViewHolder(private var binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(list: Category, viewModel: CategoryListViewModel) {
            val userActionListener = object : ItemClickListener {
                override fun onClick() {
                    Log.d("RV", "+press ${layoutPosition}")
                    viewModel.openCategory(layoutPosition)
                }
            }
            binding.apply {
                setVariable(BR.category,list)
                setVariable(BR.listener,userActionListener)
                executePendingBindings()
            }
        }
    }
}
