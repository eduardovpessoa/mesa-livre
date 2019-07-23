package br.com.monstersoftware.mesalivre.data.persistence.dao

import androidx.room.*
import br.com.monstersoftware.mesalivre.data.persistence.entity.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(user: User)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(user: User)

    @Query("SELECT * FROM usuarios WHERE userName = :user AND password = :passwd")
    suspend fun findUser(user: String, passwd: String): List<User>

}