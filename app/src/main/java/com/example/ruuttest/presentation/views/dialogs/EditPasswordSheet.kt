package com.example.ruuttest.presentation.views.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.ruuttest.databinding.FragmentEditPasswordSheetBinding
import com.example.ruuttest.presentation.viewModels.EditPasswordViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class EditPasswordSheet : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentEditPasswordSheetBinding
    private lateinit var passViewModel: EditPasswordViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity()
        passViewModel = ViewModelProvider(activity)[EditPasswordViewModel::class.java]
        binding.btnSave.setOnClickListener {
            validInputs()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentEditPasswordSheetBinding.inflate(layoutInflater)
        return binding.root
    }

    private fun validInputs() {
        val pass = binding.etPassword.text.toString()
        val confirmPass = binding.etConfirmPassword.text.toString()
        passViewModel.attemptInputPass(pass,confirmPass)

        passViewModel.validInputs.observe(this) {
            if (it) {
                saveChanges()
            } else {
                showErrorInput()
            }
        }
    }

    private fun saveChanges() {
        passViewModel.password.value = binding.etPassword.text.toString()
        passViewModel.confirmPassword.value = binding.etConfirmPassword.text.toString()
        binding.etPassword.setText("")
        binding.etConfirmPassword.setText("")
        dismiss()
    }

    private fun showErrorInput() {
        passViewModel.passwordError.observe(this) { pass ->
            if (pass==0) {
                binding.tilPassword.error = null
            } else {
                binding.tilPassword.run {
                    error = getString(pass)
                    requestFocus()
                }
            }
        }

        passViewModel.confirmPasswordError.observe(this) { confirm ->
            if (confirm==0) {
                binding.tilConfirmPassword.error = null
            } else {
                binding.tilConfirmPassword.run {
                    error = getString(confirm)
                    requestFocus()
                }
            }
        }
    }

}