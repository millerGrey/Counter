package grey.counter.MainScreen

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import grey.counter.BR
import grey.counter.Category.ItemClickListener
import grey.counter.CategoryListViewModel
import grey.counter.source.Category

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
        Log.d("RV","onBindVH")
        holder.bind(list[position], categoryListViewModel!!)
    }


    override fun getItemCount(): Int {
        return list.size
    }

    fun refreshList(lst: List<Category>){
        list = lst
        notifyDataSetChanged()
    }


    class ViewHolder(private var binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(list: Category, viewModel: CategoryListViewModel) {
            val userActionListener = object : ItemClickListener {
                override fun onClick() {
                    viewModel.openCategory(layoutPosition)
                }
            }
            val userPosList = object : ItemClickListener {
                override fun onClick() {
                    viewModel.onPressPositive(layoutPosition)
                }
            }
            val userNegList = object : ItemClickListener {
                override fun onClick() {
                    viewModel.onPressNegative(layoutPosition)
                }
            }
            binding.apply {
                setVariable(BR.category,list)
                setVariable(BR.listener,userActionListener)
                setVariable(BR.listenerPositive,userPosList)
                setVariable(BR.listenerNegative,userNegList)
                executePendingBindings()
            }
        }
    }
}
