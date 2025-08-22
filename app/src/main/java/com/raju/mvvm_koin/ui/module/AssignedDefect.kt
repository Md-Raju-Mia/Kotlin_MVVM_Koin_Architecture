package com.raju.mvvm_koin.ui.module

import com.raju.mvvm_koin.databinding.ActivityAssignDefectsBinding
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.raju.mvvm_koin.viewmodel.JobsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.raju.mvvm_koin.adapter.AssignedDefectAdapter
import com.raju.mvvm_koin.api.response.AssignedDefectResponseItem




class AssignedDefect : AppCompatActivity() {


    private val jobsViewModel: JobsViewModel by viewModel()

    private lateinit var assignedDefectAdapter: AssignedDefectAdapter

    private var assignedDefectResponseItemList: List<AssignedDefectResponseItem> = emptyList()

    private val binding by lazy {
        ActivityAssignDefectsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        assignedDefectAdapter = AssignedDefectAdapter(
            mutableListOf(),
            object : AssignedDefectAdapter.OnAssignedDefectClickListener {
                override fun onAssignedDefectClick(item: AssignedDefectResponseItem) {
                    // Handle click here
                }
            }
        )

        binding.recyclerViewAssignedDefect.adapter = assignedDefectAdapter

        jobsViewModel.fetchAssignedDefectList("4245")


        jobsViewModel.assignedDefectList.observe(this) {
            it?.let {
                it.response?.let { list ->
                    //raju
                    //val reverseList = list.reversed()
                    //assignedDefectResponseItemList = reverseList
                    //assignedDefectAdapter.updateList(reverseList)
                    assignedDefectResponseItemList = list
                    assignedDefectAdapter.updateList(list)
                }
            }
        }

       /* // Observe data
        jobsViewModel.assignedDefectList.observe(this) { response ->
            response?.response?.let { list ->
                assignedDefectAdapter.updateList(list)
            }
        }*/


    }

}