package com.example.meditation.view.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProviders
import com.example.meditation.R
import com.example.meditation.viewmodel.MainViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

class TimeSelectDialog : DialogFragment() {

    var selectedItemId = 0
//    private val viewModel: MainViewModel by viewModels()
    private lateinit var viewModel: MainViewModel


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        viewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel::class.java)
        val dialog = AlertDialog.Builder(requireActivity()).apply {
            setTitle(R.string.select_time)
            setSingleChoiceItems(R.array.time_list, selectedItemId){  dialog, which ->
                selectedItemId = which
                viewModel.setTime(selectedItemId)
                dialog.dismiss()
            }

        }.create()
        return dialog
    }
}