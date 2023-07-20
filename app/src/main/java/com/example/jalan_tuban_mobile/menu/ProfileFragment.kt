package com.example.jalan_tuban_mobile.menu

import android.content.Intent
import android.os.Build.VERSION_CODES.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.jalan_tuban_mobile.Application.RoadDatabase
import com.example.jalan_tuban_mobile.LoginActivity
import com.example.jalan_tuban_mobile.R
import com.example.jalan_tuban_mobile.databinding.FragmentProfileBinding
import com.example.jalan_tuban_mobile.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var roadDatabase: RoadDatabase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inisialisasi RoadDatabase
        roadDatabase = RoadDatabase.getDatabase(requireContext())

        getUserFromDatabase()

        // Mengatur click listener untuk tombol edit profil
        binding.editProfileTextView.setOnClickListener {
//            findNavController().navigate(R.id.action_profileFragment_to_editProfileFragment)
        }

        // Mengatur click listener untuk tombol edit password
        binding.editPasswordTextView.setOnClickListener {
//            findNavController().navigate(R.id.action_profileFragment_to_editPasswordFragment)
        }

        // Mengatur click listener untuk tombol kebijakan privasi
        binding.privacyPolicyTextView.setOnClickListener {
//            findNavController().navigate(R.id.action_profileFragment_to_privacyPolicyFragment)
        }

        // Mengatur click listener untuk tombol logout
        binding.logoutTextView.setOnClickListener {
            logout()
        }

        // Implementasikan logika dan pengaturan lainnya di sini
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getUserFromDatabase() {
        CoroutineScope(Dispatchers.IO).launch {
            val user = roadDatabase.userDao().getUser()
            withContext(Dispatchers.Main) {
                // Update UI dengan data pengguna
                binding.nameTextView.text = user.name
                binding.emailTextView.text = user.email
            }
        }
    }

    private fun logout() {
        val alertDialog = AlertDialog.Builder(requireContext())
            .setTitle("Logout")
            .setMessage("Apakah Anda yakin ingin keluar?")
            .setPositiveButton("Ya") { dialog, _ ->
                // Lakukan operasi logout di sini (misalnya menghapus sesi pengguna)

                // Pindah ke halaman login
                val intent = Intent(requireContext(), LoginActivity::class.java)
                startActivity(intent)
                requireActivity().finish() // Menutup ProfileFragment setelah logout

                dialog.dismiss()
            }
            .setNegativeButton("Tidak") { dialog, _ ->
                dialog.dismiss()
            }
            .create()

        alertDialog.show()
    }




}
