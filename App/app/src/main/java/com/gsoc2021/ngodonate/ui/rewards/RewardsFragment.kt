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
    private lateinit var roundedProgressBar: RoundedProgressBar

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

        val cPoints = 200
        val mPoints = 500
        val dPoints = "500"
        //monthly_goal.text = dPoints
        //setProgressBar(cPoints, mPoints)

        return root
    }


}