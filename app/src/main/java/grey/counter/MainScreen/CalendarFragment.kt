package grey.counter.MainScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import grey.counter.CategoryListViewModel
import android.text.format.DateFormat
import android.util.Log
import android.widget.TextView
import grey.counter.NoteViewModel
import grey.counter.R
import kotlinx.android.synthetic.main.fragment_calendar.view.*
import org.w3c.dom.Text
import java.util.*


class CalendarFragment: Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("RV","CalendarFragment onCreateView")
        val v = inflater.inflate(R.layout.fragment_calendar, container,false)
        var vm = ViewModelProviders.of(requireActivity()).get(CategoryListViewModel::class.java)
        var noteVM = ViewModelProviders.of(requireActivity()).get(NoteViewModel::class.java)
        var date: String
        var expence = v.findViewById<TextView>(R.id.value)
        var dateText = v.findViewById<TextView>(R.id.date)
        val calendar = v.findViewById<CalendarView>(R.id.calendarView).apply{
            setOnDateChangeListener { view, year, month, dayOfMonth ->
                date = dayOfMonth.toString().padStart(2,'0')+"." + (month+1).toString().padStart(2,'0') +".${year}"
                vm.setDate(date)
                dateText.text = date
                noteVM.start(date)
                Log.d("RV","${noteVM.res.value}")
                if(noteVM.res.value!=0){
                    expence.text = noteVM.res.value.toString()
                }else
                {
                    expence.text=null
                }
            }
        }
        date = DateFormat.format("dd.MM.yy", Date(calendar.date)).toString()
        vm.setDate(date)
        dateText.text = date
        noteVM.start(date)
        if(noteVM.res.value!=0){
            expence.text = noteVM.res.value.toString()
        }else
        {
            expence.text=null
        }
        return v
    }
}