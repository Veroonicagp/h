package com.example.readytoenjoy.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.readytoenjoy.core.data.adven.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.intellij.lang.annotations.Identifier
import javax.inject.Inject
sealed class UiState {

    object Started: UiState()
    object Loading: UiState()
    object Success:UiState()
    class Error(val message: String): UiState()



}
@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: LoginRepository): ViewModel(){

    private val _user = MutableStateFlow<UiState>(UiState.Loading)
    val user: StateFlow<UiState>
        get() = _user.asStateFlow()

    fun register(identifier:String, password:String) {
        viewModelScope.launch {
            _user.value = if (repository.login(identifier,password)) {
                UiState.Success
            }else {
                UiState.Error("Los datos no estan bien introducidos")
            }
        }
    }
}