package br.com.monstersoftware.mesalivre.data.persistence.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuarios")
data class User(
    @PrimaryKey(autoGenerate = true) var id: Int,
    var userName: String,
    var razaoSocial: String,
    var cnpj: String,
    var password: String
)