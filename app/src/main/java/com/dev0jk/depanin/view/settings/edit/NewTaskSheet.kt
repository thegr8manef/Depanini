package com.dev0jk.depanin.view.settings.edit


import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.widget.doOnTextChanged
import com.dev0jk.depanin.R
import com.dev0jk.depanin.databinding.FragmentNewTaskSheetBinding
import com.dev0jk.depanin.utils.getUser
import com.dev0jk.depanin.view.home.HomeActivity
import com.dev0jk.depanin.vm.WorkerVM
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.util.*

class NewTaskSheet : BottomSheetDialogFragment()
{
    private lateinit var binding: FragmentNewTaskSheetBinding
    lateinit var workerVM : WorkerVM
    var adapterItems: ArrayAdapter<String>? = null
    private var stausList = arrayListOf<String>("English","French")
    var selected = false
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        workerVM = WorkerVM()
        dropDownList()
        binding.btnSave.setTextColor(Color.parseColor("#1565D8"))
        binding.btnSave.setOnClickListener {
            if (selected){
                saveAction()
            }else{
                binding.btnSave.isEnabled = false
            }
        }
            }




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentNewTaskSheetBinding.inflate(inflater,container,false)

        return binding.root
    }


    private fun saveAction()
    {
        val intent1 = Intent(requireActivity(), HomeActivity::class.java)
        startActivity(intent1)
        dismiss()
    }
    private fun dropDownList() {
        adapterItems = ArrayAdapter(requireContext(), R.layout.list_item, stausList)
        binding.autoCompleteTxt.setAdapter(adapterItems)

        binding.autoCompleteTxt.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                 val item = parent.getItemAtPosition(position).toString()
                item.dropLast(1)
                selected = true
                val myLocale =
                    Locale(item.toLowerCase(Locale.ROOT).substring(0, 2))
                val res = resources
                val dm = res.displayMetrics
                val conf: Configuration = res.configuration
                conf.locale = myLocale
                res.updateConfiguration(conf, dm)
            }
    }

}
