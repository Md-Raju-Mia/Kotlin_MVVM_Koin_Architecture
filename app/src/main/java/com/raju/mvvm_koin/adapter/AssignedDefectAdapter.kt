package com.raju.mvvm_koin.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import com.raju.mvvm_koin.api.response.AssignedDefectResponseItem
import androidx.recyclerview.widget.RecyclerView
import com.raju.mvvm_koin.databinding.ItemAssignedDefectBinding

class AssignedDefectAdapter(
    private val listOfAssignedDefectItems: MutableList<AssignedDefectResponseItem>,
    private val onAssignedDefectClickListener: OnAssignedDefectClickListener
) : RecyclerView.Adapter<AssignedDefectAdapter.AssignedDefectViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): AssignedDefectAdapter.AssignedDefectViewHolder {
        val binding = ItemAssignedDefectBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AssignedDefectViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AssignedDefectAdapter.AssignedDefectViewHolder, position: Int) {
        val currentItem = listOfAssignedDefectItems[position]
        if(currentItem.taskStatus=="RUNNING"){
            holder.binding.startedJob.visibility = ViewGroup.VISIBLE
        }else{
            holder.binding.startedJob.visibility = ViewGroup.GONE
        }
        holder.binding.textVehicleRegNo.text = currentItem.vehicleRegNo.toString()
        holder.binding.textDefectID.text = currentItem.defectId.toString()
        holder.binding.textVehicleType.text = currentItem.vehicleType.toString()
        holder.binding.textViewJobCount.text = currentItem.totalService.toString()




        holder.binding.cardViewAssignedDefect.setOnClickListener {
            onAssignedDefectClickListener.onAssignedDefectClick(currentItem)
        }

        val slideInAnimation =
            AnimationUtils.loadAnimation(holder.itemView.context, android.R.anim.slide_in_left)
        holder.itemView.startAnimation(slideInAnimation)
    }

    override fun getItemCount(): Int {
        return listOfAssignedDefectItems.size
    }

    fun updateList(newList: List<AssignedDefectResponseItem>) {
        val diffCallback = AssignedDefectDiffCallback(listOfAssignedDefectItems, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        listOfAssignedDefectItems.clear()
        listOfAssignedDefectItems.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }

    inner class AssignedDefectViewHolder(val binding: ItemAssignedDefectBinding) :
        RecyclerView.ViewHolder(binding.root)

    class AssignedDefectDiffCallback(
        private val oldList: List<AssignedDefectResponseItem>,
        private val newList: List<AssignedDefectResponseItem>
    ) : DiffUtil.Callback() {

        override fun getOldListSize() = oldList.size

        override fun getNewListSize() = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].defectId == newList[newItemPosition].defectId
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }

    interface OnAssignedDefectClickListener {
        fun onAssignedDefectClick(assignedDefectResponseItem: AssignedDefectResponseItem)
    }


}

