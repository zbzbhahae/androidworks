package com.zb.jetpack.mvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zb.jetpack.utils.P

class ScoreViewModel : ViewModel() {

    private var scoreA = MutableLiveData<Int>()
    private var scoreB= MutableLiveData<Int>()

    init {
        scoreA.value = 0
        scoreB.value = 0
    }

    fun getScoreA() : MutableLiveData<Int> {
        P.p("getScoreA ${scoreA.value}")
        return scoreA
    }
    fun getScoreB() : MutableLiveData<Int> {
        P.p("getScoreB ${scoreB.value}")
        return scoreB
    }

    fun addScoreA(plused: Int) {
        P.p("addScoreA->$plused  & score=${scoreA.value}")
        saveLastScore()
        scoreA.value = scoreA.value!! + plused
    }

    fun addScoreB(plused: Int) {

        saveLastScore()
        scoreB.value = scoreB.value!! + plused
        P.p("addScoreB->$plused  & score=${scoreB.value}")
    }

    fun reset() {
        scoreA.value = 0
        scoreB.value = 0
    }

    fun goBack() {
        scoreA.value = lastScoreA
        scoreB.value = lastScoreB
    }

    private var lastScoreA = 0
    private var lastScoreB = 0

    private fun saveLastScore() {
        lastScoreA = scoreA.value!!
        lastScoreB = scoreB.value!!
    }

}