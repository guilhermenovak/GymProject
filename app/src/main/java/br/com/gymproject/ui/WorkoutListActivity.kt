package br.com.gymproject.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.gymproject.data.ExerciseRepository
import br.com.gymproject.data.local.database.Workout
import br.com.gymproject.databinding.ActivityExercisesListBinding
import br.com.gymproject.databinding.ActivityWorkoutListBinding
import com.google.firebase.auth.FirebaseAuth

class WorkoutListActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private var bottomSheetPeekHeight = 0
    lateinit var binding: ActivityWorkoutListBinding
    private val exerciseRepository by lazy {
        ExerciseRepository()
    }
    private val workoutListViewModel : WorkoutListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWorkoutListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setClickListeners()
        workoutListViewModel.getAllWorkouts()
        val workoutObserver = Observer<List<Workout?>> {
            if (it.isNotEmpty()) {
                setAdapter(it)
            }
        }
        workoutListViewModel.workout.observe(this, workoutObserver)
    }

    private fun setClickListeners() {
        binding.btnGoToScreenWorkout.setOnClickListener {
            val intent = Intent(this, AddWorkoutActivity::class.java)
            startActivity(intent)
        }
    }


    private fun setAdapter(workout: List<Workout?>){
        val adapter = WorkoutAdapter(workout as MutableList<Workout?>)
        binding.listAllWorkouts.adapter = adapter
        binding.listAllWorkouts.layoutManager = LinearLayoutManager(this)
    }

}