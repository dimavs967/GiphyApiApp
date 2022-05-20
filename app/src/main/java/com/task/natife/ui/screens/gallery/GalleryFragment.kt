package com.task.natife.ui.screens.gallery

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.task.natife.R
import com.task.natife.databinding.FragmentGalleryBinding
import com.task.natife.ui.main.MainActivity
import com.task.natife.ui.screens.adapter.GalleryAdapter
import com.task.natife.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels()
    private var adapter: GalleryAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CoroutineScope(Dispatchers.Main).launch { viewModel.getList() }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGalleryBinding.inflate(layoutInflater)
        adapter = GalleryAdapter()

        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerView.adapter = adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getListLiveData().observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                adapter?.initAdapter(it, R.layout.list_item, requireContext())
            }
        }

        adapter?.setOnClickListener {
            view
                .findNavController()
                .navigate(
                    GalleryFragmentDirections.actionGalleryFragmentToGifFragment(it)
                )
        }

        adapter?.setOnLongClickListener {
            Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
            return@setOnLongClickListener true
        }

        binding.searchBar.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    val inputValue = binding.searchBar.text.toString()

                    if (inputValue.isNotEmpty()) {
                        CoroutineScope(Dispatchers.Main).launch {
                            viewModel.searchRequest(inputValue)
                        }

                        (requireActivity() as MainActivity).hideSoftKeyboard()
                        binding.searchBar.text.clear()
                    }
                    return true
                }
                return false
            }
        })
    }

    override fun onPause() {
        super.onPause()
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.insertGifList()
        }
    }

    override fun onDestroyView() {
        adapter = null
        _binding = null
        super.onDestroyView()
    }

}