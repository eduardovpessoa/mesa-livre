package br.com.monstersoftware.mesalivre.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuarios")
data class User(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var userName: String,
    var razaoSocial: String,
    var cnpj: String,
    var password: String,
    var email: String,
    var name: String,
    var phoneNumber: String,
    var cpf: String,
    var rg: String,
    var userType: Int
)