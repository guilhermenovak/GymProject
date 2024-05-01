package br.com.gymproject.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import br.com.gymproject.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
        private lateinit var binding: ActivityLoginBinding
        private val loginViewModel: LoginViewModel by viewModels()
        private lateinit var auth: FirebaseAuth
        private val TAG = "FIREBASE"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setClickListeners()
        auth = Firebase.auth
        FirebaseApp.initializeApp(this)
        val currentUser = auth.currentUser
        if (currentUser != null) {
            // The user is already signed in, navigate to MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // finish the current activity to prevent the user from coming back to the SignInActivity using the back button
        }
    }

    private fun setClickListeners() {
        binding.btnLogin.setOnClickListener {
            if (checkFields()) {
                loginWithEmailAndPassword()
            }
        }
    }

    private fun loginWithEmailAndPassword() {
        auth.signInWithEmailAndPassword(
            binding.txtUser.text.toString(),
            binding.txtPassword.text.toString()
        )
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext, task.exception?.localizedMessage,
                        Toast.LENGTH_SHORT
                    ).show()
                    //updateUI(null)
                }
            }
    }

    private fun checkFields(): Boolean {
        if (binding.txtUser.text.toString().isEmpty()) {
            return false
        } else if (binding.txtPassword.text.toString().isEmpty()) {
            return false
        }
        return true
    }
}