package br.com.monstersoftware.mesalivre.data.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.monstersoftware.mesalivre.data.persistence.converter.DateConverter
import br.com.monstersoftware.mesalivre.data.persistence.dao.TestDao
import br.com.monstersoftware.mesalivre.data.persistence.entity.Test

@Database(
    entities = [Test::class],
    version = 1
)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun testDao(): TestDao

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