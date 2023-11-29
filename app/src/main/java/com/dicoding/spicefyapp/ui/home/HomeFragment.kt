package com.dicoding.spicefyapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.spicefyapp.databinding.FragmentHomeBinding
import com.dicoding.spicefyapp.model.FakeSpiceData
import com.dicoding.spicefyapp.ui.dashboard.adapter.SpicelibListAdapter
import com.dicoding.spicefyapp.ui.dashboard.spicelib.SpiceLibActivity
import com.dicoding.spicefyapp.ui.dashboard.spiceloc.MapsActivity
import com.google.firebase.auth.FirebaseAuth

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var auth: FirebaseAuth

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        //kondisi user sedang login atau tidak
        if (user != null){
            binding.tvUserHome.setText(user.email)
        }

        binding.btnImgSpiceLib.setOnClickListener {
            startActivity(Intent(requireContext(), SpiceLibActivity::class.java))
        }
        binding.btnImgSpiceLoc.setOnClickListener {
            startActivity(Intent(requireContext(), MapsActivity::class.java))
        }

//        binding.rvTour.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
//        binding.rvTour.setHasFixedSize(true)
//        binding.rvTour.adapter = SpiceListAdapter(FakeSpiceData.spice)

        binding.rvTour.setHasFixedSize(true)
        binding.rvTour.layoutManager = LinearLayoutManager(requireContext())
        binding.rvTour.adapter = SpicelibListAdapter(FakeSpiceData.spice)


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}