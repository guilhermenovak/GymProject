package br.com.gymproject.data.network


import android.util.Log
import br.com.gymproject.data.local.database.Exercise
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ExerciseRemoteDataSource {
    val TAG = "FIRESTORE"

    private val db = Firebase.firestore

    fun insertExercise(exercises : Exercise) {
        val reference = db.collection("exercise")
        val id = reference.document().id
        val mappedExercises = hashMapOf(
            "Id" to exercises.id,
            "Name" to exercises.name,
            "Description" to exercises.description,
            "Image" to exercises.image,
            "DocumentId" to id
        )
        reference
            .document(id)
            .set(mappedExercises)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.toString()}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
}

    fun updateExercise(exercises: Exercise) {
        val reference = db.collection("exercise").document(exercises.documentId)
        reference
            .update(
                "Name", exercises.name,
                "Description", exercises.description
            )
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully updated!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }
    }

    fun getAllExercises(myCallback: (result: MutableList<Exercise>) -> Unit) {
        db.collection("exercise")
            .get()
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.toString()}")
                val allExercises = mutableListOf<Exercise>()
                for (document in documentReference) {
                    allExercises.add(
                        Exercise(
                        document.get("Id") as Long,
                        document.get("Name").toString(),
                        document.get("Description").toString(),
                            document.get("Image").toString(),
                            document.get("DocumentId").toString()
                    ))
                }
                myCallback.invoke(allExercises)
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }

}