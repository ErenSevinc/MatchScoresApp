package com.example.matchscoresapp.core

import com.example.matchscoresapp.R
import com.example.matchscoresapp.domain.model.Match
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/***
 * toDate() : Uses in MatchDetailFragment
 *      returns date string from API's millisecond value of a date parameter
 *
 * getHomeName() : Uses in MatchAdapter
 *      returns home team name according to character size
 *
 * getAwayName() : Uses in MatchAdapter
 *      returns away team name according to character size
 *
 * setBackgroundColorAndDividerVisibility() : Uses in MatchAdapter
 *      returns pair containing the drawableRes id and divider visibility according to list size and item position,
 *      pairs first value, provides background color for list item shape,
 *      pairs second value, provides divider visibility for list item position
 *
 * isFavMatch() : Uses in MatchAdapter
 *      returns drawableRes id for item_match's button_favourite according to click button state
 */

fun Long.toDate(): String {
    val date = Date(this)
    val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
    return dateFormat.format(date)
}

fun Match.getHomeName(): String {
    return if (this.homeName.length >= 15) {
        this.homeSubName
    } else {
        this.homeName
    }
}

fun Match.getAwayName(): String {
    return if (this.homeName.length >= 15) {
        this.awaySubName
    } else {
        this.awayName
    }
}

fun List<Match>.setBackgroundColorAndDividerVisibility(position: Int): Pair<Int, Boolean> {
    return if (position == 0 && this.size == 1) {
        Pair(first = R.drawable.rounded_all_background_white, second = false)
    } else if (position == 0 && this.size > 1) {
        Pair(first = R.drawable.rounded_top_background_white, second = true)
    } else if (this.size - 1 == position) {
        Pair(first = R.drawable.rounded_bottom_background_white, second = false)
    } else {
        Pair(first = R.drawable.background_white, second = true)
    }
}

fun Boolean.isFavMatch(): Int {
    return if (this) {
        R.drawable.ic_star
    } else {
        R.drawable.ic_star_border
    }
}