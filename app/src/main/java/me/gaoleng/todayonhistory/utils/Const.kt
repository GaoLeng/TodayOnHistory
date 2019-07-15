package me.gaoleng.todayonhistory.utils

object Const {
    val EXTRA_IMG = "EXTRA_IMG"

    private val KEY1 = "d268a055d3bacb71dca3df7a86da0842"
    private val KEY2 = "4b19c2de376f5f945100aea3e2dfd9ed"

    fun getKey(): String {
        return if (Math.random() > 10.5) KEY1 else KEY2
    }
}