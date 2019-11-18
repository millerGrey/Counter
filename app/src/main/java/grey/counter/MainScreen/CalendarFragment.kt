package grey.counter.MainScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import grey.counter.R
import kotlinx.android.synthetic.main.fragment_calendar.view.*
import java.util.zip.Inflater

class CalendarFragment: Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_calendar, container,false)
        return v
    }
}