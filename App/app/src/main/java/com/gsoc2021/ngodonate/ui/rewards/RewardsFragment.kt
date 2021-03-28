package com.gsoc2021.ngodonate.ui.rewards

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.gsoc2021.ngodonate.R
import kotlinx.android.synthetic.main.fragment_rewards.*
import kotlinx.android.synthetic.main.fragment_rewards.view.*
import com.mackhartley.roundedprogressbar.RoundedProgressBar

class RewardsFragment : Fragment() {

    private lateinit var rewardsViewModel: RewardsViewModel

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

        return root
    }
    fun swipeRight(view: View){
        when(SliderCardTitle.text){
            "NGO1" ->{
                SliderCardTitle.text = "NGO2"
                item1.text = "User 1"

            }
            "NGO2" -> {
                SliderCardTitle.text = "NGO3"
                item1.text = "Electronics to CityGospel"

            }
            "NGO3" -> {
                SliderCardTitle.text = "NGO1"
                item1.text = "CityGospel"

            }
        }
    }
    fun swipeLeft(view: View){
        when(SliderCardTitle.text){
            "NGO2" ->{
                SliderCardTitle.text = "NGO1"
                item1.text = "CityGospel"

            }
            "NGO3" ->{
                SliderCardTitle.text = "NGO2"
                item1.text = "User 1"

            }
            "NGO1" ->{
                SliderCardTitle.text = "NGO3"
                item1.text = "Electronics to CityGospel"

            }
        }

    }

}