package grey.counter.MainScreen

import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import grey.counter.CategoryListViewModel
import grey.counter.NoteViewModel
import grey.counter.R
import java.util.*


class CalendarFragment : Fragment() {

    private val catListVM by lazy {
        ViewModelProviders.of(requireActivity()).get(CategoryListViewModel::class.java)
    }
    private val noteVM by lazy {
        ViewModelProviders.of(requireActivity()).get(NoteViewModel::class.java)
    }
    private lateinit var expence: TextView
    private lateinit var dateText: TextView
    private lateinit var calendar: CalendarView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("RV", "CalendarFragment onCreateView")
        val v = inflater.inflate(R.layout.fragment_calendar, container, false)
        //TODO DataBinding
        expence = v.findViewById(R.id.value)
        dateText = v.findViewById(R.id.date)
        var str: String
        calendar = v.findViewById<CalendarView>(R.id.calendarView).apply {
            setOnDateChangeListener { _, year, month, dayOfMonth ->
                str = dayOfMonth.toString().padStart(
                    2,
                    '0'
                ) + "." + (month + 1).toString().padStart(2, '0') + ".${year}"
                updateUI(str)
            }
        }
        return v
    }

    fun updateUI(date: String = DateFormat.format("dd.MM.yyyy", Date(calendar.date)).toString()) {
        catListVM.setDate(date)
        dateText.text = date
        noteVM.start(date)
        Log.d("RV", "${noteVM.res.value}")
        if (noteVM.res.value != 0) {
            expence.text = getString(R.string.formatRub, noteVM.res.value)
        } else {
            expence.text = null
        }
    }

    override fun onResume() {
        super.onResume()
        updateUI()
    }
}