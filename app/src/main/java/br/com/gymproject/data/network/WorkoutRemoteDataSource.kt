package br.com.gymproject.data.network


import android.util.Log
import br.com.gymproject.data.local.database.Workout
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class WorkoutRemoteDataSource {
    val TAG = "FIRESTORE"

    private val db = Firebase.firestore

    fun insertWorkout(workout : Workout) {
        val reference = db.collection("workout")
        val id = reference.document().id
        val mappedWorkouts = hashMapOf(
            "Id" to workout.id,
            "Name" to workout.name,
            "Descricao" to workout.description,
            "Image" to workout.data,
            "DocumentId" to id
        )
        reference
            .document(id)
            .set(mappedWorkouts)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.toString()}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }

    fun updateWorkout(workout: Workout) {
        val reference = db.collection("workout").document(workout.documentId)
        reference
            .update(
                "Name", workout.name,
                "Descricao", workout.description
            )
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully updated!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }
    }

    fun getAllWorkouts(myCallback: (result: MutableList<Workout>) -> Unit) {
        db.collection("workout")
            .get()
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.toString()}")
                val allWorkouts = mutableListOf<Workout>()
                for (document in documentReference) {
                    allWorkouts.add(
                        Workout(
                            document.get("Id") as Long,
                            document.get("Name").toString(),
                            document.get("Descricao").toString(),
                            document.get("Data").toString(),
                            document.get("DocumentId").toString()
                        ))
                }
                myCallback.invoke(allWorkouts)
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }

}
