package br.com.gymproject.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.gymproject.data.UserRepository
import br.com.gymproject.data.local.database.User
import kotlinx.coroutines.Dispatchers
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {

    private val userRepository by lazy {
        UserRepository()
    }

    private var _user = MutableLiveData<User?>()
    val user: LiveData<User?> get() = _user

    fun getUserByEmail(email:String?) {
        viewModelScope.launch(Dispatchers.Main) {
            userRepository.getUserByEmail(email)?.collect{
                if(it.isNotEmpty()){
                    _user.value = it[0]
                }
            }
        }
    }
}