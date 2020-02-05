package grey.counter

import android.app.Application
import androidx.room.Room
import grey.counter.source.local.LocalDB


class App: Application() {
    lateinit var dataBase: LocalDB
    override fun onCreate() {
        super.onCreate()
        instance = this
        dataBase = Room.databaseBuilder(this, LocalDB::class.java, "database.db")
            .allowMainThreadQueries()
            .build()
    }

    companion object {

        lateinit var instance: App
    }
}

