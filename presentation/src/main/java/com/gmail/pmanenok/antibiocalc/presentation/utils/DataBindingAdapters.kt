package com.gmail.pmanenok.antibiocalc.presentation.utils

import android.databinding.BindingAdapter
import android.view.View
import android.widget.TextView
import com.gmail.pmanenok.antibiocalc.R
import com.gmail.pmanenok.domain.entity.types.MenuType
import com.gmail.pmanenok.domain.entity.types.SubstanceType

@BindingAdapter("android:visibility")
fun setVisibility(view: View, visibility: Boolean) {
    view.visibility = if (visibility) View.VISIBLE else View.INVISIBLE
}

@BindingAdapter(value = ["android:text", "drugType"])
fun setText(view: TextView, stringId: Int, drugType: Int) {
    val array: Array<String> = when (drugType) {
        MenuType.ANTIBIOTIC.getTypeId() -> view.resources.getStringArray(R.array.menu)
        MenuType.ANTIBIOTIC.getId() -> view.resources.getStringArray(R.array.antibiotics)
        MenuType.ANTIPYRETIC.getId() -> view.resources.getStringArray(R.array.antipyretics)
        else -> emptyArray()
    }
    if (array.size > stringId) view.text = array[stringId]
    else view.text = ""
}

@BindingAdapter(value = ["android:text", "substanceType", "concentration", "concentrationIn", "result", "times"])
fun setText(
    view: TextView, drugType: Int, substanceType: SubstanceType,
    concentration: String, concentrationIn: Int, result: String, times: Int
) {
    val substance: String = view.resources.getStringArray(R.array.suspension_type)[substanceType.getId()]
    val substanceMassUnit: String = view.resources.getStringArray(R.array.suspension_mass_unit)[substanceType.getId()]
    val template: String =
        if (drugType == MenuType.ANTIPYRETIC.getId()) view.resources.getString(R.string.item_result_till_template)
        else view.resources.getString(R.string.item_result_template)
    view.text = template.format(substance, concentration, concentrationIn, substanceMassUnit, result, times)
}

