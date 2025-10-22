package com.dzo.announcerclock.utils

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.Locale
import androidx.core.net.toUri
import androidx.core.graphics.toColorInt

object Utils {

    fun toast(context: Context?, msg: String?) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    fun showSnackBar(view: View?, msg: String?){
        Snackbar.make(
            view!!,
            msg!!,
            Snackbar.LENGTH_SHORT
        ).show()
    }

    fun String.lighten(factor: Float = 0.2f): Int {
        val color = this.toColorInt()
        val r = ((Color.red(color) * (1 - factor) + 255 * factor).coerceAtMost(255f)).toInt()
        val g = ((Color.green(color) * (1 - factor) + 255 * factor).coerceAtMost(255f)).toInt()
        val b = ((Color.blue(color) * (1 - factor) + 255 * factor).coerceAtMost(255f)).toInt()
        return Color.rgb(r, g, b)
    }
    fun String.darken(factor: Float = 0.2f): Int {
        val color = Color.parseColor(this)
        val r = (Color.red(color) * (1 - factor)).toInt()
        val g = (Color.green(color) * (1 - factor)).toInt()
        val b = (Color.blue(color) * (1 - factor)).toInt()
        return Color.rgb(r, g, b)
    }

    fun getHadithName():List<String>{
        return listOf("")
    }
    fun changeDateFormat(date: String?, patternGiven: String,patternRequired:String): String? {
        var formattedDate = ""
        try {
            val inputFormat = SimpleDateFormat(patternGiven) //"EEE MMM dd HH:mm:ss z yyyy"
            val outputFormat = SimpleDateFormat(patternRequired)
            val date = inputFormat.parse(date)
            formattedDate = outputFormat.format(date)

        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return formattedDate
    }

    fun changeDateFormat(date: String?): String? {
        var formattedDate = ""
        try {
            val inputFormat = SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH)
            val outputFormat = SimpleDateFormat("dd-MM-yyyy")
            val date = inputFormat.parse(date)

            formattedDate = outputFormat.format(date)

        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return formattedDate
    }

    fun changeDateFormatForEvents(date: String?): String? {
        var formattedDate = ""
        try {
            val inputFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
            val outputFormat = SimpleDateFormat("EEE, d MMMM yyyy", Locale.getDefault())
            val date = inputFormat.parse(date)

            formattedDate = outputFormat.format(date)

        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return formattedDate
    }


    fun desiredDateFormat(input:String):String{
        val inputDate = "Sat Feb 01 14:31:14 GMT+05:30 2025"

        // Define input and output date formats
        val inputFormat = SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH)
        val outputFormat = SimpleDateFormat("EEE MMM dd yyyy", Locale.ENGLISH)

        // Parse the input date string
        val date: Date = inputFormat.parse(input)!!
        // Use Calendar to add one day
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.add(Calendar.DATE, 1) // Add one day

        // Format the date into the desired format
        val formattedDate = outputFormat.format(date)

        println(formattedDate)
        return formattedDate
    }

    fun timeToMillis(time: String): Long {
        val localTime = LocalTime.parse(time) // Parses "09:45" to LocalTime
        return localTime.toSecondOfDay() * 1000L // Converts to milliseconds
    }

    fun millisToTime(millis: Long): String {
        val format = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        return format.format(Date(millis))
    }

    fun openPlayStore(context: Context, packageName: String) {
        // Try open in Play Store app first
        try {
            val uri = "market://details?id=$packageName".toUri()
            val intent = Intent(Intent.ACTION_VIEW, uri).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            // If Play Store app not available, open in browser
            val webUri = "https://play.google.com/store/apps/details?id=$packageName".toUri()
            val webIntent = Intent(Intent.ACTION_VIEW, webUri).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(webIntent)
        }
    }

    /*
        MM/dd/yyyy hh:mm:ss tt	08/05/2006 03:05:15 PM
        M/d/yy h :m:s tt	8/5/06 3:5:15 PM
        ddd MMM dd yyyy	Sat Aug 05 2006
        "yyyy-MM-dd hh:mm:ss a"  2023-06-06 05:50:51 PM
        dddd, MMMM dd yyyy	Saturday, August 05 2006
    */

    /*fun changeTimeFormat(time:String?):String?{
        var formattedTime = ""
        try {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            val outputFormat = SimpleDateFormat("hh:mm a", Locale.US)
            val time = inputFormat.parse(time)
            formattedTime = outputFormat.format(time)
        }catch (e:ParseException){
            e.printStackTrace()
        }
        return formattedTime
    }*/

    /*fun changeDateFormat(time: String?): String? {
         var changedDate = ""
         try {
             val fmt = SimpleDateFormat("yyyy-MM-dd ")

             val date = fmt.parse(time)
             changedDate = fmt.format(date)
         } catch (e: ParseException) {
             e.printStackTrace()
         }
         return changedDate
     }*/

    fun changeTimeFormat(time: String?): String? {
        var changedTime = ""
        try {
            val display = SimpleDateFormat("hh:mm a", Locale.US)
            val fmt = SimpleDateFormat("yyyy-MM-dd 'T' HH:mm:ss.SSS'Z'")
            val date = fmt.parse(time)
            changedTime = display.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return changedTime
    }

    //Display Current date and time like "2023-08-25 11:06:54 PM"
    fun dateTime(calendar: Calendar): String {
        val dtForm: DateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss a", Locale.US)
        val date: String = dtForm.format(calendar.time)
        return date
    }

    //Display time (11:06:54 PM)  from Calender date  like "2023-08-25 11:06:54 PM"
    fun getTimeFromCalender(date: String?): String {
        val inputDateString = date
        val inputFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss a", Locale.US)
        val date = inputFormat.parse(inputDateString)
        val outputFormat = SimpleDateFormat("hh:mm a", Locale.US)
        val outputTimeString = outputFormat.format(date)
        println(outputTimeString)
        return outputTimeString
    }
    //Display date "2023-08-25 11:06:54 PM" to "2023-08-25
    fun getDateFromCalender(date: String?): String {
        val inputDateString = date
        val inputFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss a", Locale.US)
        val date = inputFormat.parse(inputDateString)
        val outputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val outputDateString = outputFormat.format(date)
        println(outputDateString)
        return outputDateString
    }

    fun sdm(date: String?): String {
        val gregorianFormatter = SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH)
        return gregorianFormatter.format(date)
    }

    fun getImageUriFromBitmap(context: Context?, bitmap: Bitmap?): Uri {
        val bytes = ByteArrayOutputStream()
        bitmap?.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path =
            MediaStore.Images.Media.insertImage(context?.contentResolver, bitmap, "Title", null)
        return Uri.parse(path)
    }

    fun getUriFromFile(context: Context?, bitmap: Bitmap): Uri {
        val tempFile = File.createTempFile("profile", ".png")
        val bytes = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bytes)
        val bitmapData = bytes.toByteArray()

        val fileOutPut = FileOutputStream(tempFile)
        fileOutPut.write(bitmapData)
        fileOutPut.flush()
        fileOutPut.close()
        return Uri.fromFile(tempFile)
    }

    fun ImageView.loadImage(imageUrl: String) {
        Glide.with(this)
            .load(imageUrl)
            .into(this)
    }

    fun ImageView.showImage(image: Int) {
        Glide.with(this)
            .load(image)
            .into(this)
    }

    fun View.show() {
        if (this.visibility != View.VISIBLE)
            this.visibility = View.VISIBLE
    }

    fun View.gone() {
        if (this.visibility != View.GONE)
            this.visibility = View.GONE
    }

    fun Context.getScreenWidth(): Int {
        val metrics = this.resources.displayMetrics
        return metrics.widthPixels
    }

    fun Context.convertDpToPixel(dp: Float): Float {
        val resources = this.resources
        val metrics = resources.displayMetrics
        return dp * (metrics.densityDpi / 160f)
    }

    private fun getURLForResource(resourceId: Int): String {
        return Uri.parse("android.resource://com.yourpackage.name/$resourceId").toString()
    }


    fun convertTime24HrTo12Hr(time: String):String {
        val time = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"))
            .format(DateTimeFormatter.ofPattern("hh:mm a"));
        return time
    }

    fun convertTo12HourFormat(time: String): String {
        val inputFormat = SimpleDateFormat("HH:mm", Locale.getDefault())  // 24-hour format
        val outputFormat =
            SimpleDateFormat("h:mm", Locale.getDefault()) // 12-hour format with AM/PM

        val date = inputFormat.parse(time)  // Parse input time
        return outputFormat.format(date!!)  // Convert and return formatted time
    }

    fun convertTime24HrTo12HrWithIST(time: String):String {
        //val inputTime = "15:35 (IST)"
        val timeOnly = time.substringBefore(" (IST)")
        val parsedTime = LocalTime.parse(timeOnly, DateTimeFormatter.ofPattern("HH:mm"))
        val formattedTime = parsedTime.format(DateTimeFormatter.ofPattern("hh:mm a"))

        return formattedTime
    }

    fun convertTime12HrTo24Hr(time: String):String {
        val time = LocalTime.parse(time, DateTimeFormatter.ofPattern("hh:mm a"))
            .format(DateTimeFormatter.ofPattern("HH:mm a"));
        return time
    }

    fun ViewPager2.setShowSideItems(pageMarginPx : Int, offsetPx : Int) {

        clipToPadding = false
        clipChildren = false
        offscreenPageLimit = 3

        setPageTransformer { page, position ->

            val offset = position * -(2 * offsetPx + pageMarginPx)
            if (this.orientation == ViewPager2.ORIENTATION_HORIZONTAL) {
                if (ViewCompat.getLayoutDirection(this) == ViewCompat.LAYOUT_DIRECTION_RTL) {
                    page.translationX = -offset
                } else {
                    page.translationX = offset
                }
            } else {
                page.translationY = offset
            }
        }

    }

    val Int.dp: Int get() = (this / Resources.getSystem().displayMetrics.density).toInt()

    val Int.px: Int get() = (this * Resources.getSystem().displayMetrics.density).toInt()


    fun loadJsonFromAssets(context: Context, fileName: String): String? {
        return try {
            context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }



}