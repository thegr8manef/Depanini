package com.dev0jk.depanin.view.settings.edit


import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.dev0jk.depanin.R
import com.dev0jk.depanin.databinding.FragmentNewTaskSheetEditSpecialityBinding
import com.dev0jk.depanin.model.data.remote.entity.User
import com.dev0jk.depanin.utils.getUser
import com.dev0jk.depanin.utils.setUser
import com.dev0jk.depanin.view.home.HomeActivity
import com.dev0jk.depanin.view.settings.worker.WorkerSettingsFragment
import com.dev0jk.depanin.vm.WorkerVM
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class NewEditSpeciality : BottomSheetDialogFragment()
{
    private lateinit var binding: FragmentNewTaskSheetEditSpecialityBinding
    private lateinit var workerVM: WorkerVM
    private lateinit var progressDialog: ProgressDialog
    lateinit var workersettingsFragment: WorkerSettingsFragment
    var adapterItems: ArrayAdapter<String>? = null
    private var stausList = arrayListOf<String>("Electricity","Plumbing","Painting","Gardener","Security","Masonry","Carpenter")
    var selected = false
    var item = String()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        workerVM = WorkerVM()
        progressDialog = ProgressDialog(requireContext())
        progressDialog.setTitle(getString(R.string.please_wait))
        progressDialog.setCanceledOnTouchOutside(false)
        workersettingsFragment = WorkerSettingsFragment()
        item = requireActivity().intent.getStringExtra("speciality").toString()

        dropDownList()
        binding.btnSave.setTextColor(Color.parseColor("#1565D8"))
        binding.btnSave.setOnClickListener {
            if (selected){
                progressDialog.setMessage(getString(R.string.updating_user))
                progressDialog.show()
                saveAction()
            }else{
                binding.btnSave.isEnabled = false
            }
        }
            }




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentNewTaskSheetEditSpecialityBinding.inflate(inflater,container,false)

        return binding.root
    }


    private fun saveAction()
    {
        MainScope().launch {
            workerVM.updateWorkerSpeciality(
                getUser(requireContext()).id!!.toLong(),
                item,
            )
        }

        setUser(User(getUser(requireContext()).id!!.toLong(),getUser(requireContext()).username,getUser(requireContext()).password,getUser(requireContext()).address_gov,getUser(requireContext()).address_municipale,getUser(requireContext()).image,getUser(requireContext()).phone,getUser(requireContext()).cin,item,true),requireContext())
        val intent1 = Intent(requireActivity(), HomeActivity::class.java)
        startActivity(intent1)
        dismiss()
    }
    private fun dropDownList() {
        adapterItems = ArrayAdapter(requireContext(), R.layout.list_item, stausList)
        binding.autoCompleteTxt.setAdapter(adapterItems)

        binding.autoCompleteTxt.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                item = parent.getItemAtPosition(position).toString()
                item.dropLast(1)
                val editor = requireActivity().getSharedPreferences("my_prefs", Context.MODE_PRIVATE).edit()
                editor.putString("speciality", item)
                editor.apply()
                selected = true

            }
    }

}
