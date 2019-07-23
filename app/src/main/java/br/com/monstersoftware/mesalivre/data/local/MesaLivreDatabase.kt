package br.com.monstersoftware.mesalivre.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.monstersoftware.mesalivre.data.local.converter.DateConverter
import br.com.monstersoftware.mesalivre.data.local.dao.UserDao
import br.com.monstersoftware.mesalivre.data.local.entity.User

@Database(
    entities = [User::class],
    version = 1
)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_mesa_livre"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}