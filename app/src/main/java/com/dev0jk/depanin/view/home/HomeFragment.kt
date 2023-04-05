package com.dev0jk.depanin.view.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.dev0jk.depanin.R
import com.dev0jk.depanin.databinding.FragmentHomeBinding
import com.dev0jk.depanin.model.data.remote.entity.User
import com.dev0jk.depanin.model.entity.Category
import com.dev0jk.depanin.utils.getUser
import com.dev0jk.depanin.vm.UserVM
import com.dev0jk.depanin.vm.WorkerVM


class HomeFragment : Fragment() {

    private var _binding : FragmentHomeBinding? =null
    private val binding get() = _binding!!
    lateinit var workerVM : WorkerVM
    lateinit var userVM: UserVM
    var speciality = String()
    lateinit var lngList: ArrayList<User>
    lateinit var recommendedAdapter: RecommendedAdapter
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

        lngList= arrayListOf()
        binding.profileImage
        workerVM = WorkerVM()
        userVM=UserVM()
         speciality = requireContext().getSharedPreferences("category_name", Context.MODE_PRIVATE)?.getString("category.name", "default")
             .toString()

        Log.println(Log.ASSERT,"userIn", getUser(requireContext()).toString())
        Log.println(Log.ASSERT,"speciality**//", speciality)
            workerVM.getRecommendedWorker(
                getUser(requireContext()).address_municipale.toString(),
                getUser(requireContext()).address_gov.toString()
            ).observe(requireActivity(), Observer {
                if (it.isNullOrEmpty()) {
                    binding.image.visibility = View.VISIBLE
                    binding.recommended.visibility = View.INVISIBLE
                } else {
                    binding.recommended.adapter?.notifyDataSetChanged()
                    lngList.addAll(it)
                     recommendedAdapter = RecommendedAdapter(
                        requireContext(),
                       lngList
                    )
                    binding.recommended.layoutManager = LinearLayoutManager(requireContext())
                    binding.recommended.layoutManager = LinearLayoutManager(
                        requireContext()
                    )

                    binding.recommended.adapter = recommendedAdapter

                }
            }

            )
        /*}else {
            workerVM.filterbySpeciality(
                speciality,
            ).observe(requireActivity(), Observer {
                if (it.isNullOrEmpty()){
                    binding.image.visibility = View.VISIBLE
                    binding.recommended.visibility = View.INVISIBLE
                }else{
                    binding.recommended.adapter?.notifyDataSetChanged()
                    val recommendedAdapter = RecommendedAdapter(requireContext(),
                        it as ArrayList<User>
                    )
                    binding.recommended.layoutManager = LinearLayoutManager(requireContext())
                    binding.recommended.layoutManager = LinearLayoutManager(
                        requireContext()
                    )

                    binding.recommended.adapter = recommendedAdapter
                }

            }
            )
        }*/
        val categoryAdapter = CategoriesAdapter(requireContext(), arrayOfCategories, this)
        binding.categories.layoutManager = LinearLayoutManager(requireContext())
        binding.categories.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL,
            false
        )

        binding.categories.adapter = categoryAdapter






        return binding.root
    }

    fun filtre(speciality : String){
        workerVM.filterbySpeciality(speciality).observe(this) {
            if (it.size==0) {
                binding.image.visibility = View.VISIBLE
                binding.recommended.visibility = View.INVISIBLE
                binding.recommended.adapter?.notifyDataSetChanged()

            } else {
                binding.image.visibility = View.INVISIBLE
                binding.recommended.visibility = View.VISIBLE
                lngList.clear()
                lngList.addAll(it)
                binding.recommended.adapter?.notifyDataSetChanged()
            }
        }
    }


}