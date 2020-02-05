package grey.counter.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import grey.counter.source.Category

@Database(entities = arrayOf(Category::class), version = 1)
abstract class LocalDB : RoomDatabase() {
    abstract fun catDao(): CategoryDao

}

//@Database(entities = {Trip.class, Expence.class}, version = 1)
//public abstract class AppDatabase extends RoomDatabase {
//    public abstract TripDao tripDao();
//    public abstract ExpenceDao expenceDao();
//
//
//    public static class DateConverter {
//
//        @TypeConverter
//        public Long dateToTimestamp(Date date) {
//            if (date == null) {
//                return null;
//            } else {
//                return date.getTime();
//            }
//        }
//        @TypeConverter
//        public Date timestampToDate(Long timestamp){
//            return new Date(timestamp);
//        }
//
//    }