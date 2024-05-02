package br.com.gymproject.data.local.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.errorprone.annotations.Keep
import com.google.firebase.firestore.PropertyName

@Keep
data class Exercise(
    @get: PropertyName("Id") @set: PropertyName("Id") var id: Long,
    @get: PropertyName("Name") @set: PropertyName("Name") var name: String = "",
    @get: PropertyName("Description") @set: PropertyName("Description") var description: String = "",
    @get: PropertyName("Image") @set: PropertyName("Image") var image: String = "",
    @get: PropertyName("DocumentId") @set: PropertyName("DocumentId") var documentId: String = "",
)

@Entity(tableName = "exercise")
data class ExerciseEntity(
    @PrimaryKey
    val id: Long,
    val name: String,
    val description: String,
    val image : String,
    val documentId: String
)

fun ExerciseEntity.asExternalModel() = Exercise(
    id = id,
    name = name,
    description = description,
    image = image,
    documentId = documentId
)