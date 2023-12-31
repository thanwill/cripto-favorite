package com.app.criptofavorite.view.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.app.criptofavorite.databinding.FragmentProfileBinding
import com.app.criptofavorite.view.LoginActivity
import com.google.firebase.auth.FirebaseAuth

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private lateinit var auth: FirebaseAuth
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(ProfileViewModel::class.java)

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.logout.setOnClickListener {
            notificationsViewModel.logout()

            // Redireciona para a tela de login
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)

            // Exibe mensagem de sucesso ao usuário
            //Toast.makeText(activity, "Logout realizado com sucesso!", Toast.LENGTH_SHORT).show()

            // Finaliza a tela atual
            activity?.finish()

        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}