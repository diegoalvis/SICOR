package com.diegoalvis.android.sicor

import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * Created by diegoalvis on 13/11/2017.
 */

val NOTIFCATIONS: String = "notifications"
val CONTENT: String = "content"
val DATE: String = "date"


fun ViewGroup.inflate(layout:Int) = LayoutInflater.from(context).inflate(layout, this, false)
