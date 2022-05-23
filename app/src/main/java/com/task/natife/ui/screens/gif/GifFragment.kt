package com.task.natife.ui.screens.gif

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.task.natife.R
import com.task.natife.databinding.FragmentGifBinding
import com.task.natife.ui.screens.adapter.GalleryAdapter
import com.task.natife.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GifFragment : Fragment() {

    private var _binding: FragmentGifBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels()
    private val args: GifFragmentArgs by navArgs()

    private var adapter: GalleryAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGifBinding.inflate(layoutInflater)
        adapter = GalleryAdapter()

        binding.recyclerView.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL,
            false
        )

        viewModel.getListLiveData().observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                adapter?.initAdapter(it, R.layout.fullscreen_item, requireContext())
                binding.recyclerView.layoutManager?.scrollToPosition(args.position)
            }
        }

        binding.recyclerView.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setOnClickListener {
            view
                .findNavController()
                .navigate(GifFragmentDirections.actionGifFragmentToGalleryFragment())

            Navigation.findNavController(view).popBackStack(R.id.gifFragment, true)
        }
    }

    override fun onDestroyView() {
        adapter = null
        _binding = null
        super.onDestroyView()
    }

}