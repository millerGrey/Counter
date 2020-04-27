package grey.counter

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import grey.counter.source.Note
import grey.counter.source.local.LocalDataSource

class NoteViewModel: ViewModel() {


    private var isNewNote = false
    private var _res = MutableLiveData<Int>(0)
    val res: LiveData<Int>
        get() = _res

    private var _date = MutableLiveData<String>(" - ")
    val date: LiveData<String>
        get() = _date

    private val _onSaveEvent = MutableLiveData<Boolean>()
    val onSaveEvent: LiveData<Boolean>
        get() = _onSaveEvent

    fun start(date: String){
        _date.value = date
        Log.d("RV","start NoteVM date = ${_date.value}")
        val note = LocalDataSource.getNote(date)
        note?.let{
            _date.value = note.date
            _res.value = note.res
            return
        }
        isNewNote=true
        _res.value = 0
    }

    /**
     * --------------------------------------------------------------------------------
     * Методы для экрана рассчетов
     *
     */
    fun onPressPositive(id: Int){
        var c = LocalDataSource.getCategory(id).coast
        _res.value = _res.value?.plus(c)

        Log.d("RV","res = ${_res.value}")
    }
    fun onPressNegative(id: Int){
        var c = LocalDataSource.getCategory(id).coast
        _res.value = _res.value?.minus(c)

        Log.d("RV","res = ${_res.value}")
    }

    fun onPressSave(){

        var note = Note()
        _res.value?.let{note.res = it}
        _date.value?.let{note.date = it}
        if(isNewNote) {
            LocalDataSource.addNote(note)
            isNewNote = false
        }else{
            LocalDataSource.editNote(note)
        }
        Log.d("RV","save res = ${_res.value}")
        _onSaveEvent.value = true
    }
    /**-------------------------------------------------------------------------*/

}