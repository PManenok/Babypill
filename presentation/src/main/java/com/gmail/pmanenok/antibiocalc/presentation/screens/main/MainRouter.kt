package com.gmail.pmanenok.antibiocalc.presentation.screens.main

import android.content.res.Resources
import com.gmail.pmanenok.antibiocalc.R
import com.gmail.pmanenok.antibiocalc.presentation.base.BaseRouter
import com.gmail.pmanenok.antibiocalc.presentation.screens.main.calc.CalcFragment
import com.gmail.pmanenok.antibiocalc.presentation.screens.main.menu.MenuFragment
import com.gmail.pmanenok.antibiocalc.presentation.screens.main.result.ResultFragment
import com.gmail.pmanenok.domain.entity.types.MenuType
import com.gmail.pmanenok.domain.entity.types.TypedEnum

class MainRouter(activity: MainActivity) : BaseRouter<MainActivity>(activity) {

    fun goToMainMenu() {
        replaceFragment(activity.supportFragmentManager, MenuFragment.getInstance(), R.id.main_frame_layout, false)
    }

    fun goToCalcFragment(drugType: MenuType) {
        replaceFragment(activity.supportFragmentManager, CalcFragment.getInstance(drugType), R.id.main_frame_layout, true)
    }

    fun goToResultFragment(drugType: MenuType, drugSubtype: TypedEnum, weight: Int, dosageInd: Int) {
        replaceFragment(
            activity.supportFragmentManager,
            ResultFragment.getInstance(drugType, drugSubtype, weight, dosageInd),
            R.id.main_frame_layout,
            true
        )
    }

    fun getResources(): Resources {
        return activity.resources
    }

    fun getResourceString(id: Int): String {
        return activity.resources.getString(id)
    }

    fun getDayDosageString(dose: String): String {
        return activity.resources.getString(
            R.string.dosage_mg_kg_template, dose
        )
    }
    fun getDosageString(dose: String): String {
        return activity.resources.getString(
            R.string.dosage_mg_kg_day_template, dose
        )
    }

    fun getWeightString(weight: String): String {
        return activity.resources.getString(
            R.string.weight_template, weight
        )
    }
}