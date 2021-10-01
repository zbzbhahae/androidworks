package com.zb.jetpack.activity.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.zb.jetpack.R
import com.zb.jetpack.mvvm.viewmodel.FraViewModel
import com.zb.jetpack.utils.P

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SecondFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SecondFragment : BaseFragment() {

    private lateinit var seekBar: SeekBar
    private lateinit var viewModel: FraViewModel
    private var postFlag = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_frist, container, false)
        seekBar = v.findViewById(R.id.seekBar)

        viewModel = ViewModelProvider(requireActivity(),
            ViewModelProvider.AndroidViewModelFactory(requireActivity().application))
            .get(FraViewModel::class.java)


        P.p(viewModel.toString())
        P.p(viewModel.getProgressData().toString())

        viewModel.getProgressData()!!.observe(requireActivity()) {
            P.p("SecondFra->$it")
            if(!postFlag) {
                seekBar.progress = it
            }
        }

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if(postFlag) {
                    viewModel.getProgressData()!!.value = progress
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                postFlag = true
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                postFlag = false
            }
        })

        return v
    }


}