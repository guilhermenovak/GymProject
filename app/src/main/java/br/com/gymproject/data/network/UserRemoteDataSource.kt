package br.com.gymproject.data.network

import android.util.Log
import br.com.gymproject.data.local.database.User
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.map

class UserRemoteDataSource {
    val TAG = "FIRESTORE"

    private val db = Firebase.firestore

    fun insertUser(user: User) {
        val mappedUser = hashMapOf(
            "Email" to user.email
        )

        db.collection("users")
            .add(mappedUser)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }

    fun getUserByEmail(email: String): Flow<List<User?>> {
        return db.collection("users")
            .whereEqualTo("Email", email)
            .snapshotFlow()
            .map { querySnapshot ->
                querySnapshot.documents.map { it.toObject<User>() }
            }
    }

    private fun Query.snapshotFlow(): Flow<QuerySnapshot> = callbackFlow {
        val listenerRegistration = addSnapshotListener { value, error ->
            if (error != null) {
                close()
                return@addSnapshotListener
            }
            if (value != null)
                trySend(value)
        }
        awaitClose {
            listenerRegistration.remove()
        }
    }

}
