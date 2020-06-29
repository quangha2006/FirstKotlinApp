package com.example.myfirstkotlinapplication.utils

import android.app.ActivityManager
import android.content.Context
import android.os.Build
import android.text.Editable
import android.view.View
import java.util.*
import kotlin.math.abs
import kotlin.math.log10
import kotlin.math.pow


object AppUtils {

    @JvmStatic
    fun getVersionName(context: Context): String {
        var versionName = ""
        try {
            val pInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            versionName = pInfo.versionName
            return versionName
        } catch (e: Exception) {
        }

        return versionName
    }

    @JvmStatic
    private fun isNotEmpty(text: Editable?): Boolean {
        return text != null && text.isNotEmpty()
    }

    @JvmStatic
    fun isActivityRunning(context: Context, activityName: String): Boolean {
        val am = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            val tasks = am.getRunningTasks(Int.MAX_VALUE)
            for (task in tasks) {
                val name = task.baseActivity.className.toString()


                if (name.contains(activityName)) {
                    return true
                }
            }
        } else {
            val tasks = am.appTasks
            for (task in tasks) {
                val name = task?.taskInfo?.origActivity?.className?.toString() ?: ""


                if (name.contains(activityName)) {
                    return true
                }
            }
        }
        return false

    }

    private val UNITS = arrayOf("K", "M", "B", "T", "P", "E")

    @JvmStatic
    fun suffixNumber(number: Long): String {
        var num = number
        val s = num.compareTo(0)
        val sign = if (s == -1) "-" else ""
        num = abs(num)
        if (num < 1000) {
            return String.format(Locale.getDefault(), "%s%d", sign, num)
        }
        val exp = (log10(num.toDouble()) / 3.0f).toInt()

        val formatter = String.format(
            Locale.getDefault(), "%s%.1f%s",
            sign, num / 1000.0.pow(exp.toDouble()), UNITS[exp - 1]
        )
        return formatter.format(number).replace(",".toRegex(), ".")
    }

    @JvmStatic
    fun hideSystemUI(view: View?) {
        if (view == null) return
        var flags = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            flags = flags or
                    View.SYSTEM_UI_FLAG_IMMERSIVE

        }

        view.systemUiVisibility = flags// hide status bar
    }

    @JvmStatic
    fun isDependencyAvailable(dependencyClass: String): Boolean {
        try {
            Class.forName(dependencyClass)
            return true
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        }

        return false
    }

    @JvmStatic
    fun decode(s: String): String {
        var working = s
        var index = working.indexOf("\\u")
        while (index > -1) {
            val len = working.length
            if (index > (len - 6)) break
            val numStart = index + 2
            val numFinish = numStart + 4
            val subString = working.substring(numStart, numFinish)
            val num = Integer.parseInt(subString, 16)
            val stringStart = working.substring(0, index)
            val stringEnd = working.substring(numFinish)
            working = stringStart + num.toChar() + stringEnd
            index = working.indexOf("\\u")
        }
        return working
    }
}
