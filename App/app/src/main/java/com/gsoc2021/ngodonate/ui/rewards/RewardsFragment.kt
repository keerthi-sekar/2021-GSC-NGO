package com.gsoc2021.ngodonate.ui.rewards

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.gsoc2021.ngodonate.R
import kotlinx.android.synthetic.main.fragment_rewards.*
import kotlinx.android.synthetic.main.fragment_rewards.view.*
import com.mackhartley.roundedprogressbar.RoundedProgressBar
import kotlinx.android.synthetic.*

class RewardsFragment : Fragment() {

    private lateinit var rewardsViewModel: RewardsViewModel
    private lateinit var hourConfirmationText: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rewardsViewModel =
            ViewModelProviders.of(this).get(RewardsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_rewards, container, false)
       /* val textView: TextView = root.findViewById(R.id.text_rewards)
        rewardsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/
        hourConfirmationText = root.findViewById(R.id.hoursConfirm)
        hourConfirmationText.text = " "
        return root
    }

    fun getHours(view: View)
    {
        hourConfirmationText.text = "Hours Received!"
    }

    fun clickRight(view: View){
        when(cSliderCardTitle.text){
            "NGO1" ->{
                cSliderCardTitle.text = "NGO2"
                cText.text = "User 1"

            }
            "NGO2" -> {
                cSliderCardTitle.text = "NGO3"
                cText.text = "Electronics to CityGospel"

            }
            "NGO3" -> {
                cSliderCardTitle.text = "NGO1"
                cText.text = "CityGospel"

            }
        }
    }
    fun clickLeft(view: View){
        when(cSliderCardTitle.text){
            "NGO2" ->{
                cSliderCardTitle.text = "NGO1"
                cText.text = "CityGospel"

            }
            "NGO3" ->{
                cSliderCardTitle.text = "NGO2"
                cText.text = "User 1"

            }
            "NGO1" ->{
                cSliderCardTitle.text = "NGO3"
                cText.text = "Electronics to CityGospel"

            }
        }

    }

}