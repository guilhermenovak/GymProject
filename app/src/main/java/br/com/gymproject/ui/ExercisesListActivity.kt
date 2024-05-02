package br.com.gymproject.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.gymproject.data.ExerciseRepository
import br.com.gymproject.data.local.database.Exercise
import br.com.gymproject.databinding.ActivityExercisesListBinding
import com.google.firebase.auth.FirebaseAuth

class ExercisesListActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private var bottomSheetPeekHeight = 0
    lateinit var binding: ActivityExercisesListBinding
    private val exerciseRepository by lazy {
        ExerciseRepository()
    }
    private val exercisesListViewModel : ExercisesListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExercisesListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setClickListeners()
        exercisesListViewModel.getAllExercises()
        val exerciseObserver = Observer<List<Exercise?>> {
            if (it.isNotEmpty()) {
                setAdapter(it)
            }
        }
        exercisesListViewModel.exercise.observe(this, exerciseObserver)
    }

    private fun setClickListeners() {
        binding.btnGoScreenExercise.setOnClickListener {
            val intent = Intent(this, AddExerciseActivity::class.java)
            startActivity(intent)
        }
    }


    private fun setAdapter(exercises: List<Exercise?>){
        val adapter = ExerciseAdapter(exercises as MutableList<Exercise?>)
        binding.listAllExercises.adapter = adapter
        binding.listAllExercises.layoutManager = LinearLayoutManager(this)
    }

}