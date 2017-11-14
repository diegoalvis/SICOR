package com.diegoalvis.android.sicor

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_notifications.view.*
import kotlinx.android.synthetic.main.item_notification.view.*
import java.util.*

class NotificationsFragment : Fragment() {


    val articlesAdapter = NotificationAdapter()

    lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val v = inflater!!.inflate(R.layout.fragment_notifications, container, false)
        recyclerView = v.recycler
        recyclerView.adapter = articlesAdapter
        return v
    }


    fun setData() {
        val prefs = getContext().getSharedPreferences(NOTIFCATIONS, 0)

        articlesAdapter.items.clear()

        var notificationListContent = prefs.getString(CONTENT, "")
        var notificationListDate = prefs.getString(DATE, "")

        var contents = notificationListContent.split("#")
        var dates = notificationListDate.split("#")


        contents.forEach {
            contents.indexOf(it)
            articlesAdapter.items.add(NotificationAdapter.Notification(it, dates[contents.indexOf(it)]))
        }

        articlesAdapter.notifyDataSetChanged()
    }


    class NotificationAdapter : RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {

        var items: ArrayList<Notification> = ArrayList()
            set(value) {
                field = value
                notifyDataSetChanged()
            }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder
                = NotificationViewHolder(parent.inflate(R.layout.item_notification))

        override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
            holder.content.text = items[position].content
            holder.date.text = items[position].date
        }

        override fun getItemCount() = items.size


        class NotificationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val content: TextView = itemView.content
            val date: TextView = itemView.date
        }

        data class Notification(val content: String, val date: String)

    }

}