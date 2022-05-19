package com.task.natife.ui.screens

import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.task.natife.databinding.FragmentGalleryBinding
import com.task.natife.ui.main.MainActivity
import com.task.natife.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGalleryBinding.inflate(layoutInflater)

        binding.searchBar.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    val inputValue = binding.searchBar.text.toString()

                    if (inputValue.isNotEmpty()) {
                        CoroutineScope(Dispatchers.Main).launch {

                        }

                        (requireActivity() as MainActivity).hideSoftKeyboard()
                        binding.searchBar.text.clear()
                    } else {
                        Toast.makeText(requireContext(), "Enter text", Toast.LENGTH_SHORT).show()
                    }

                    return true
                }
                return false
            }
        })

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}