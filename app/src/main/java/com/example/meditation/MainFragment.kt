package com.example.meditation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()
//    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.initParameters()
        btnPlay.setBackgroundResource(R.drawable.ic_play_circle_outline_black_24dp)
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.txtLevel.observe(viewLifecycleOwner, Observer { levelTxt ->
            txtLevel.text = levelTxt
        })
        viewModel.txtTheme.observe(viewLifecycleOwner, Observer { themeTxt ->
            txtTheme.text = themeTxt
        })
        viewModel.displayTimeSeconds.observe(viewLifecycleOwner, Observer { displayTime ->
            txtTime.text = displayTime
        })
        viewModel.msgUpperSmall.observe(viewLifecycleOwner, Observer { upperTxt ->
            txtMsgUpperSmall.text = upperTxt
        })
        viewModel.msgLowerLarge.observe(viewLifecycleOwner, Observer { lowerTxt ->
            txtMsgLowerLarge.text = lowerTxt
        })
        viewModel.playStatus.observe(viewLifecycleOwner, Observer {status ->
            when(status) {
                PlayStatus.BEFORE_START -> btnPlay.setBackgroundResource(R.drawable.button_play)
            }
        })
    }


}
