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
import br.com.gymproject.data.local.database.Exercise
import br.com.gymproject.databinding.ActivityAddExerciseBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentId
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
            binding.imgEdit.setImageURI(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        binding = ActivityAddExerciseBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        var nome = intent.getStringExtra("nome").orEmpty()
        var observacao = intent.getStringExtra("observacao").orEmpty()
        var id = intent.getStringExtra("id")?.toLong()
        var imagem = intent.getStringExtra("image").orEmpty()
        var documentId = intent.getStringExtra("documentId").orEmpty()
        val isEdit = !nome.isEmpty()
        if (isEdit){
            binding.txtName.setText(nome)
            binding.txtObservations.setText(observacao)
        }

        binding.btnImageEdit.setOnClickListener {
            selectImageFromGallery()
        }
        binding.btnCadastrar.setOnClickListener {
            if (isEdit) {
                val exercises = id?.let { it1 -> buildUpdateExercise(it1, documentId) }
                if (exercises != null) {
                    exerciseRepository.updateExercise(exercises)
                }
                finish()
            } else {
                val exercises = buildExercise()
                exerciseRepository.insertExercise(exercises)
                finish()
            }
            }
        }


    private fun buildUpdateExercise(id: Long, documentId: String): Exercise {
        return Exercise(
            id,
            binding.txtName.text.toString(),
            binding.txtObservations.text.toString(), " ",
            documentId
        )
    }

        private fun buildExercise(): Exercise {
            return Exercise(
                1,
                binding.txtName.text.toString(),
                binding.txtObservations.text.toString(), " "
            )
        }

        private fun selectImageFromGallery() {
            selectImageLauncher.launch("image/*")
        }
    }