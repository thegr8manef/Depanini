package com.dev0jk.depanin.view.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.dev0jk.depanin.R
import com.dev0jk.depanin.databinding.FragmentHomeBinding
import com.dev0jk.depanin.model.entity.Category
import com.dev0jk.depanin.model.entity.User
import com.dev0jk.depanin.vm.UserVM
import com.dev0jk.depanin.vm.WorkerVM


class HomeFragment : Fragment() {

    private var _binding : FragmentHomeBinding? =null
    private val binding get() = _binding!!
    lateinit var workerVM : WorkerVM
    lateinit var userVM: UserVM

    val arrayOfCategories = arrayListOf<Category>(
        Category("All", R.drawable.img_all),
        Category("Electricity", R.drawable.img_electricity),
        Category("Plumbing", R.drawable.img_plumbing),
        Category("Painting", R.drawable.img_painting),
        Category("Gardener", R.drawable.img_gardener),
        Category("Security", R.drawable.img_camera),
        Category("Masonry", R.drawable.img_masonry),
        Category("Carpenter", R.drawable.img_carpenter),)

/*    val recommends = arrayListOf<User>(
        User("tt","nn","k","","",""),
        User("tt","nn","k","","",""),
        User("tt","nn","k","","",""),
        User("tt","nn","k","","",""),
        User("tt","nn","k","","",""),
        User("tt","nn","k","","",""),
        User("tt","nn","k","","",""),
        User("tt","nn","k","","",""),
        User("tt","nn","k","","","")
    )*/


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)


        binding.profileImage
        workerVM = WorkerVM()
        userVM=UserVM()
        //userVM.signUpClient("nefza","nefza","nefza",9999)
        //workerVM.signUpWorker("aziz wazzan","aziz","soussa",99420988,1234568,"bac+2")
/*        workerVM.getAllWorker()*/
        //userVM.getAllClient()
        //userVM.getData(123)
/*            workerVM.getAllWorker().observe(viewLifecycleOwner, Observer {
            if (!it.isNullOrEmpty()){
                val recommendedAdapter = RecommendedAdapter(requireContext(), it!!)
                binding.recommended.layoutManager = LinearLayoutManager(requireContext())
                binding.recommended.layoutManager = LinearLayoutManager(
                    requireContext(),


                    )

                binding.recommended.adapter = recommendedAdapter
            }

        })*/

        workerVM.getRecommanded("Beja").observe(viewLifecycleOwner, Observer {
            if (!it.isNullOrEmpty()){
                val recommendedAdapter = RecommendedAdapter(requireContext(), it!!)
                binding.recommended.layoutManager = LinearLayoutManager(requireContext())
                binding.recommended.layoutManager = LinearLayoutManager(
                    requireContext(),


                    )

                binding.recommended.adapter = recommendedAdapter
            }

        })

        val categoryAdapter = CategoriesAdapter(requireContext(), arrayOfCategories)
        binding.categories.layoutManager = LinearLayoutManager(requireContext())
        binding.categories.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL,
            false
        )

        binding.categories.adapter = categoryAdapter






        return binding.root
    }



}