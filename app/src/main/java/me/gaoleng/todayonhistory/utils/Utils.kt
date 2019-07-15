package me.gaoleng.todayonhistory.utils

import android.content.Context
import android.text.format.DateFormat
import android.util.Log
import java.util.*

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

    fun formatDate(year: Int, month: Int, day: Int): String {
        val calendar = Calendar.getInstance()
        calendar.set(year, month - 1, day)
        return DateFormat.format("yyyy-MM-dd", calendar).toString()
    }

    fun dp2px(context: Context, dpValue: Int): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }
}
