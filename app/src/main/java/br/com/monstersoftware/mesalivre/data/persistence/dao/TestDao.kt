package br.com.monstersoftware.mesalivre.data.persistence.dao

import androidx.room.*
import br.com.monstersoftware.mesalivre.data.persistence.entity.Test

@Dao
interface TestDao {

    @Delete
    fun delete(test: Test)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(test: Test)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(test: Test)

}