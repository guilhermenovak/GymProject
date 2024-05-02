package br.com.gymproject.data.local.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.errorprone.annotations.Keep
import com.google.firebase.firestore.PropertyName

@Keep
data class Workout(
    @get: PropertyName("Id") @set: PropertyName("Id") var id: Long,
    @get: PropertyName("Name") @set: PropertyName("Name") var name: String = "",
    @get: PropertyName("Description") @set: PropertyName("Description") var description: String = "",
    @get: PropertyName("Data") @set: PropertyName("Data") var data: String = "",
    @get: PropertyName("DocumentId") @set: PropertyName("DocumentId") var documentId: String = "",
)

@Entity(tableName = "workout")
data class WorkoutEntity(
    @PrimaryKey
    val id: Long,
    val name: String,
    val description: String,
    val data : String,
    val documentId: String
)

fun WorkoutEntity.asExternalModel() = Workout(
    id = id,
    name = name,
    description = description,
    data = data,
    documentId = documentId
)