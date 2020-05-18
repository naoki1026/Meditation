package net.minpro.meditation.view.dialog

import android.app.Dialog
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import com.example.meditation.R
import com.example.meditation.viewmodel.MainViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

class LevelSelectDialog: androidx.fragment.app.DialogFragment() {

    var selectedItemId = 0

    private lateinit var viewModel: MainViewModel
//    private val viewModel: MainViewModel

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        viewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel::class.java)

        //viewModel = ViewModelProviders.of(activity!!).get(MainViewModel::class.java)

        val dialog = AlertDialog.Builder(requireActivity()).apply {
            setTitle(R.string.select_level)
            setSingleChoiceItems(R.array.level_list, selectedItemId){ dialog, which ->
                selectedItemId = which
                viewModel.setLevel(selectedItemId)
                dialog.dismiss()
            }
        }.create()
        return dialog
    }
}