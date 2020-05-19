package com.example.meditation.view.main

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.example.meditation.viewmodel.MainViewModel
import com.example.meditation.R
import com.example.meditation.databinding.FragmentMainBinding
import com.example.meditation.util.PlayStatus
import org.koin.android.viewmodel.ext.android.sharedViewModel
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

//    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding : FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_main, container, false)
        val view = binding.root
        return view
//        return inflater.inflate(R.layout.fragment_main, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel::class.java)
        viewModel.initParameters()
        binding.apply {
            viewmodel = viewModel
            setLifecycleOwner(activity)
        }

        observeViewModel()
    }
    private fun observeViewModel() {
        viewModel.playStatus.observe(viewLifecycleOwner, Observer {status ->
            updateUi(status!!)
            when(status) {
                PlayStatus.BEFORE_START -> {
                }
                PlayStatus.ON_START  -> {
                    viewModel.countDownBeforeStart()
                }
                PlayStatus.RUNNING -> {
                    viewModel.startMeditation()
                }
                PlayStatus.PAUSE -> {
                    viewModel.pauseMeditation()

                }
                PlayStatus.END -> {
                    viewModel.finishMeditation()
                }
            }
        })
        viewModel.themePicFileResId.observe(viewLifecycleOwner, Observer {themePicResId ->
            loadBackgroundImage(this, themePicResId, meditation_screen)
        })
    }
    private fun updateUi(status: Int) {
        when(status) {
            PlayStatus.BEFORE_START -> {
                binding.apply {
                    btnPlay.apply {
                        visibility = View.VISIBLE
                        setBackgroundResource(R.drawable.button_play)
                    }
                    btnFinish.visibility = View.INVISIBLE
                    txtShowMenu.visibility = View.INVISIBLE
                }
            }
            PlayStatus.ON_START  -> {
                binding.apply {
                    btnPlay.visibility = View.INVISIBLE
                    btnFinish.visibility = View.INVISIBLE
                    txtShowMenu.visibility = View.VISIBLE
                }
            }
            PlayStatus.RUNNING -> {
                binding.apply {
                    btnPlay.apply {
                        visibility = View.VISIBLE
                        setBackgroundResource(R.drawable.button_pause)
                    }
                    btnFinish.visibility = View.INVISIBLE
                    txtShowMenu.visibility = View.VISIBLE
                }
            }
            PlayStatus.PAUSE -> {
                binding.apply {
                    btnPlay.apply {
                        visibility = View.VISIBLE
                        setBackgroundResource(R.drawable.button_play)
                    }
                    btnFinish.apply {
                        visibility = View.VISIBLE
                        setBackgroundResource(R.drawable.button_finish)
                    }
                    txtShowMenu.visibility = View.VISIBLE
                }
            }
            PlayStatus.END -> {

            }
        }
    }
    private fun loadBackgroundImage(mainFragment: MainFragment, themePicResId: Int?, meditationScreen: ConstraintLayout?) {
        Glide.with(mainFragment).load(themePicResId).into(object : SimpleTarget<Drawable>(){
            override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                meditation_screen?.background = resource
            }
        })
    }
}
