package br.com.monstersoftware.mesalivre.data.persistence.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "test")
data class Test(@PrimaryKey var id: Int)