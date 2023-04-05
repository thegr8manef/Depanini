package com.dev0jk.depanin.view.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dev0jk.depanin.databinding.FragmentSearchBinding
import com.dev0jk.depanin.model.data.remote.entity.User
import com.dev0jk.depanin.view.home.RecommendedAdapter
import com.dev0jk.depanin.vm.WorkerVM
import okhttp3.internal.notifyAll

class SearchFragment : Fragment() {
    private var _binding : FragmentSearchBinding? =null
    private val binding get() = _binding!!
    lateinit var workerVM : WorkerVM
    lateinit var searchAdapter: SearchAdapter
    // on below line we are
    // creating variables for listview

    // creating array adapter for listview
    lateinit var listAdapter: ArrayAdapter<User>
    lateinit var lngList: ArrayList<User>

    // creating array list for listview
    lateinit var programmingLanguagesList: ArrayList<User>;

    // creating variable for searchview
    lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        workerVM = WorkerVM()
        lngList= arrayListOf()
        programmingLanguagesList = ArrayList()


        binding.idSV.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // on below line we are checking
                // if query exist or not.
                search(query.toString())
                Log.println(Log.ASSERT,"list:1",programmingLanguagesList.toString())
                return false
            }


            override fun onQueryTextChange(newText: String?): Boolean {
                // if query text is change in that case we
                // are filtering our adapter with
                // new text on below line.
                return false
            }
        })



    return binding.root
}
    fun search(username : String) {
        workerVM.searchByUsername(username).observe(requireActivity()) {
            if (it.isNullOrEmpty()) {

            } else {
                binding.recommended.adapter?.notifyDataSetChanged()
                lngList.clear()
                lngList.addAll(it)
                searchAdapter = SearchAdapter(
                    requireContext(),
                    lngList
                )
                binding.recommended.layoutManager = LinearLayoutManager(requireContext())
                binding.recommended.layoutManager = LinearLayoutManager(
                    requireContext()
                )

                binding.recommended.adapter = searchAdapter

            }

            }

        }


    }