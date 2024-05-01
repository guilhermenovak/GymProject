package br.com.gymproject.data.local.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.database.PropertyName

data class User (
    @get: PropertyName("Email") @set: PropertyName("Email") var email: String = ""
)

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey
    val email: String,
)

fun UserEntity.asExternalModel() = User(
    email = email
)