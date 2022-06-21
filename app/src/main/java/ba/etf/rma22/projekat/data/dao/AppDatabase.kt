package ba.etf.rma22.projekat.data.dao

import android.content.Context
import androidx.room.*
import ba.etf.rma22.projekat.data.models.*

//Movie::class,Cast::class,SimilarMovies::class
@Database(entities = arrayOf(Account::class, Grupa::class, Istrazivanje::class, Anketa::class, Pitanje::class, Odgovor::class), version = 5)
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun accountDao() : AccountDao
    abstract fun grupaDao() : GrupaDao
    abstract fun istrazivanjeDao() : IstrazivanjeDao
    abstract fun anketaDao() : AnketaDao
    abstract fun pitanjeDao() : PitanjeDao
    abstract fun odgovorDao() : OdgovorDao


    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    println("Unutar date abze")
                    INSTANCE = buildRoomDB(context)
                }
            }
            return INSTANCE!!
        }
        fun setInstance(appdb: AppDatabase):Unit{
            INSTANCE =appdb
        }

        private fun buildRoomDB(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "RMA22DB"
            ).fallbackToDestructiveMigration().build()
    }
}