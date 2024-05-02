package br.com.gymproject.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import br.com.gymproject.databinding.ActivityMainBinding
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        auth = Firebase.auth
        setClickListeners()
    }

    private fun setClickListeners() {
        binding.btnExit.setOnClickListener {
            Firebase.auth.signOut()
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            this.finish()
        }
        binding.cardViewExercise.setOnClickListener {
            val intent = Intent(this, ExercisesListActivity::class.java)
            startActivity(intent)
        }
        binding.cardViewWorkouts.setOnClickListener {
            val intent = Intent(this, WorkoutListActivity::class.java)
            startActivity(intent)
        }
    }
}