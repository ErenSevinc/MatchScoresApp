package com.example.matchscoresapp.core

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