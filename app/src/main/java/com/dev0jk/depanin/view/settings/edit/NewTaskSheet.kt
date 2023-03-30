package com.dev0jk.depanin.view.settings.edit


import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import com.dev0jk.depanin.databinding.FragmentNewTaskSheetBinding
import com.dev0jk.depanin.vm.UserVM
import com.dev0jk.depanin.vm.WorkerVM
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class NewTaskSheet : BottomSheetDialogFragment()
{
    private lateinit var binding: FragmentNewTaskSheetBinding
    lateinit var workerVM : WorkerVM
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        workerVM = WorkerVM()


        binding.btnSave.isEnabled = false
        binding.password.doOnTextChanged { _: CharSequence?, _: Int, _: Int, _: Int ->
            binding.username.doOnTextChanged { _: CharSequence?, _: Int, _: Int, _: Int ->
                if (binding.username.text.toString()
                        .isNotEmpty() || binding.password.text.toString().isNotEmpty()
                ) {
                    binding.btnSave.isEnabled = true
                    binding.btnSave.setTextColor(Color.parseColor("#1565D8"))
                    binding.btnSave.setOnClickListener {
                        saveAction()
                    }

                } else {
                    binding.btnSave.isEnabled = false
                    binding.btnSave.setTextColor(Color.parseColor("#D86A6A6A"))
                }
            }
        }


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentNewTaskSheetBinding.inflate(inflater,container,false)

        return binding.root
    }


    private fun saveAction()
    {
        MainScope().launch {
            workerVM.updateWorker(2,binding.username.text.toString(),binding.password.text.toString())
        }
        dismiss()
    }

}
