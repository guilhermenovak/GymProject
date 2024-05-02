package br.com.gymproject.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.gymproject.R
import br.com.gymproject.databinding.ActivityAddWorkoutBinding
import br.com.gymproject.databinding.ActivityExercisesListBinding
import br.com.gymproject.databinding.ActivityManageWorkoutsBinding

class ManageWorkoutsActivity : AppCompatActivity() {
    lateinit var binding: ActivityManageWorkoutsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityManageWorkoutsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setClickListeners()
    }
    private fun setClickListeners() {
        binding.btnGoToScreenAddWorkout.setOnClickListener {
            val intent = Intent(this, AddWorkoutActivity::class.java)
            startActivity(intent)
        }
    }
}