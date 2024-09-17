package com.example.kotlin.presentation

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kotlin.data.Repository
import com.example.kotlin.domain.BaseViewModel
import com.example.kotlin.domain.IRepository
import kotlinx.coroutines.launch

class MainViewModel(
     context: Context
):  BaseViewModel() {
    companion object {
       val OAUTH_URL = "https://instagram.com/oauth/authorize?client_id=438863165864035&redirect_uri=https://localhost/callback&scope=user_profile,user_media&response_type=code"
    }

    private val repository: IRepository = Repository.getInstance(context)


    private val _states = MutableLiveData(States("", false))
    private val _events = MutableLiveData<Events>()

    val states: LiveData<States> get() = _states
    val events: LiveData<Events> get() = _events



    data class States(val text : String, val isShowProgress : Boolean)
        
    

    sealed class Events {
        object Nothing : Events()
        data class OpenUrl(val url : String) : Events()
        object HideButtons: Events()
        object ShowButtons: Events()
    }



suspend fun getUsername(){
    _states.value = states.value?.copy(isShowProgress = true)
    val username = repository.getUsername()
    _states.value = states.value?.copy(text = username, false)
}



     suspend fun getToken(authCode: String){
         _states.value = states.value?.copy(isShowProgress = true)
         val token =  repository.getToken(authCode)
         _states.value = states.value?.copy(text = token, isShowProgress = false)
    }

    fun getAuthCode() {
        _events.value  = Events.OpenUrl(OAUTH_URL)
    }
    fun hideButtons(){
        _events.value = Events.HideButtons
    }
    fun showButtons(){
        _events.value  = Events.ShowButtons
    }
}
