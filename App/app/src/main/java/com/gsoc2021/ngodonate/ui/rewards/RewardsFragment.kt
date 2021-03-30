package com.gsoc2021.ngodonate.ui.rewards

import android.os.Bundle
import android.util.Log
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
    var currentPoints = 20
    var monthlygoal = 300
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

        var progressPercentage: Int = currentPoints/monthlygoal * 100
        //Card4!!.layoutParams.width  = progressPercentage * Card3.width
        return root
    }

    private fun setProgressBarAttributesProgrammatically(roundedProgressBar: RoundedProgressBar) {
        roundedProgressBar.setProgressColor(R.color.colorPrimary)
        roundedProgressBar.setProgressBgColor(R.color.colorWhite)
        roundedProgressBar.setTextSize(resources.getDimension(R.dimen.text_size))
        roundedProgressBar.setTextColor(R.color.colorBlack)
        roundedProgressBar.setBgTextColor(R.color.colorWhite)
        roundedProgressBar.showProgressText(true)
        //addpoints (current/monthly)
        roundedProgressBar.setAnimationLength(900)
    }

}