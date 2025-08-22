package com.raju.mvvm_koin.api.response

import com.google.gson.annotations.SerializedName

data class AssignedDefectResponseItem(
    @SerializedName("vehicle_reg_no") var vehicleRegNo: String?,
    @SerializedName("total_service") var totalService: String?,
    @SerializedName("defect_id") var defectId: String?,
    @SerializedName("vehicle_type") var vehicleType: String?,
    @SerializedName("task_status") var taskStatus: String?,
)
