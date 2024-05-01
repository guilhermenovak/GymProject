package br.com.gymproject.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import br.com.gymproject.data.ExerciseRepository
import br.com.gymproject.data.local.database.Exercise
import br.com.gymproject.databinding.ActivityAddExerciseBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class AddExerciseActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityAddExerciseBinding
    private var imageReference = Firebase.storage.reference
    private val exerciseRepository by lazy {
        ExerciseRepository()
    }
    private val selectImageLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            binding.imgAdd.setImageURI(it)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        binding = ActivityAddExerciseBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnImage.setOnClickListener {
            selectImageFromGallery()
        }
        binding.btnCadastrar.setOnClickListener {
                    val exercises = buildExercise()
                    exerciseRepository.insertExercise(exercises)
                        val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
        }
    }

    private fun buildExercise(): Exercise {
        return Exercise(1,
        binding.txtName.text.toString(),
        binding.txtObservations.text.toString(), " "
        )
    }

    private fun selectImageFromGallery() {
        selectImageLauncher.launch("image/*")
    }
}