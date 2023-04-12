package com.dev0jk.depanin.view.home


import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.dev0jk.depanin.R
import com.dev0jk.depanin.databinding.FragmentNewFavSheetBinding
import com.dev0jk.depanin.databinding.FragmentNewTaskSheetBinding
import com.dev0jk.depanin.model.data.remote.entity.User
import com.dev0jk.depanin.utils.getUser
import com.dev0jk.depanin.view.search.SearchAdapter
import com.dev0jk.depanin.vm.WorkerVM
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class NewFavSheet : BottomSheetDialogFragment()
{
    private lateinit var binding: FragmentNewFavSheetBinding
    lateinit var workerVM : WorkerVM
    lateinit var lngList: ArrayList<User>
    lateinit var favoriteAdapter: FavoriteAdapter
    var selected = false
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        workerVM = WorkerVM()
        lngList= arrayListOf()
        listAllFavorites(getUser(requireContext()).id!!.toLong())
            }




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentNewFavSheetBinding.inflate(inflater,container,false)

        return binding.root
    }

    fun listAllFavorites(id_client : Long) {
        workerVM.findAllFavorites(id_client).observe(requireActivity()) {
            if (it.isNullOrEmpty()) {

            } else {
                binding.recommended.adapter?.notifyDataSetChanged()
                lngList.clear()
                lngList.addAll(it)
                favoriteAdapter = FavoriteAdapter(
                    requireContext(),
                    lngList
                )
                binding.recommended.layoutManager = LinearLayoutManager(requireContext())
                binding.recommended.layoutManager = LinearLayoutManager(
                    requireContext()
                )

                binding.recommended.adapter = favoriteAdapter

            }

        }

    }

}
