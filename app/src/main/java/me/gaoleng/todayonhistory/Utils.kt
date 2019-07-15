package me.gaoleng.todayonhistory

import android.content.Context
import android.text.format.DateFormat
import android.text.format.DateUtils
import android.util.Log

object Utils {
    fun getTodayForDisplay(context: Context): String {
        val date = DateFormat.format("M月dd日", System.currentTimeMillis()).toString()
        Log.e("TAG", date)
        return date;
    }

    fun getTodayForQuery(context: Context): String {
        val date = DateFormat.format("M/dd", System.currentTimeMillis()).toString()
        Log.e("TAG", date)
        return date;
    }

    fun dp2px(context: Context, dpValue: Int): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }
}
