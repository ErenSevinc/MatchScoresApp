package com.example.matchscoresapp.core

import com.example.matchscoresapp.R
import com.example.matchscoresapp.domain.model.Match
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


fun Long.toDate(): String {
    val date = Date(this)
    val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
    return dateFormat.format(date)
}

fun Match.getHomeName() : String{
    return if (this.homeName.length >= 15) {
        this.homeSubName
    } else {
        this.homeName
    }
}
fun Match.getAwayName() : String{
    return if (this.homeName.length >= 15) {
        this.awaySubName
    } else {
        this.awayName
    }
}

fun List<Match>.setBackgroundColorAndDividerVisibility(position: Int): Pair<Int,Boolean> {
    return if (position == 0 && this.size == 1) {
        Pair(first = R.drawable.rounded_all_background_white, second = false)
    } else if (position == 0 && this.size > 1) {
        Pair(first = R.drawable.rounded_top_background_white, second = true)
    } else if(this.size -1 == position) {
        Pair(first = R.drawable.rounded_bottom_background_white, second = false)
    } else {
        Pair(first = R.drawable.background_white, second = true)
    }
}