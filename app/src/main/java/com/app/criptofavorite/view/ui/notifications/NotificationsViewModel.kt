package com.app.criptofavorite.view.ui.notifications

import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.criptofavorite.view.LoginActivity
import com.google.firebase.auth.FirebaseAuth

class NotificationsViewModel : ViewModel() {

    // LiveData para sinalizar que o logout foi realizado
    private val _logout = MutableLiveData<Boolean>()
    val logout: LiveData<Boolean> = _logout

    fun logout() {
        FirebaseAuth.getInstance().signOut()
        _logout.value = true
    }
}