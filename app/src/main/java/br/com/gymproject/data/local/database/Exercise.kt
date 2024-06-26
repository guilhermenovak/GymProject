package br.com.gymproject.data.local.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.errorprone.annotations.Keep
import com.google.firebase.firestore.PropertyName

@Keep
data class Exercise(
    @get: PropertyName("Id") @set: PropertyName("Id") var id: Long,
    @get: PropertyName("Name") @set: PropertyName("Name") var name: String = "",
    @get: PropertyName("Obersation") @set: PropertyName("Observation") var observation: String = "",
    @get: PropertyName("Image") @set: PropertyName("Image") var image: String = "",
    @get: PropertyName("DocumentId") @set: PropertyName("DocumentId") var documentId: String = "",
)

@Entity(tableName = "exercise")
data class ExerciseEntity(
    @PrimaryKey
    val id: Long,
    val name: String,
    val observation: String,
    val image : String,
    val documentId: String
)

fun ExerciseEntity.asExternalModel() = Exercise(
    id = id,
    name = name,
    observation = observation,
    image = image,
    documentId = documentId
)