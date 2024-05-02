package br.com.gymproject.ui

import android.app.AlarmManager
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.getSystemService
import br.com.gymproject.data.ExerciseRepository
import br.com.gymproject.data.WorkoutRepository
import br.com.gymproject.data.local.database.Workout
import br.com.gymproject.databinding.ActivityAddExerciseBinding
import br.com.gymproject.databinding.ActivityAddWorkoutBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentId
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class AddWorkoutActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityAddWorkoutBinding
    private val workoutRepository by lazy {
        WorkoutRepository()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        binding = ActivityAddWorkoutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        var nome = intent.getStringExtra("nome").orEmpty()
        var descricao = intent.getStringExtra("descricao").orEmpty()
        var id = intent.getStringExtra("id")?.toLong()
        var data = intent.getStringExtra("data").orEmpty()
        var documentId = intent.getStringExtra("documentId").orEmpty()
        val isEdit = !nome.isEmpty()
        if (isEdit){
            binding.txtNameWorkout.setText(nome)
            binding.txtDescription.setText(descricao)
            binding.txtData.setText(data)
        }
        binding.btnCadastrarWorkout.setOnClickListener {
            if (isEdit) {
                val workout = id?.let { it1 -> buildUpdateWorkout(it1, documentId) }
                if (workout != null) {
                    workoutRepository.updateWorkout(workout)
                }
                finish()
            } else {
                val workout = buildWorkout()
                workoutRepository.insertWorkout(workout)
                finish()
            }
        }
    }


    private fun buildUpdateWorkout(id: Long, documentId: String): Workout {
        return Workout(
            id,
            binding.txtNameWorkout.text.toString(),
            binding.txtDescription.text.toString(),
            binding.txtData.text.toString(),

            documentId
        )
    }

    private fun buildWorkout(): Workout {
        return Workout(
            1,
            binding.txtNameWorkout.text.toString(),
            binding.txtDescription.text.toString(),
            binding.txtData.text.toString()
        )
    }
}