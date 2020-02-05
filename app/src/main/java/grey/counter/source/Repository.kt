package grey.counter.source

import grey.counter.source.local.CategoryDao

class Repository (private val catDao: CategoryDao) {

    val list = catDao.getAllCategories()
    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
//    val allWords: LiveData<List<Word>> = wordDao.getAlphabetizedWords()

//    suspend fun insert(word: Word) {
//        wordDao.insert(word)
//    }
}
