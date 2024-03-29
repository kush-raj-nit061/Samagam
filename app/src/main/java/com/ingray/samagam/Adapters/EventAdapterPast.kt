package com.ingray.samagam.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ingray.samagam.DataClass.Events
import com.ingray.samagam.R
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.FirebaseDatabase
import com.ingray.samagam.CustomDialog
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

class EventAdapterPast(options: FirebaseRecyclerOptions<Events?>) :
    FirebaseRecyclerAdapter<Events?, EventAdapterPast.userAdapterHolder?>(options) {
        val dbRef = FirebaseDatabase.getInstance().reference.child("PastEvents")
        val dbRefev = FirebaseDatabase.getInstance().reference.child("Events")

    override fun onBindViewHolder(
        holder: userAdapterHolder,
        position: Int,
        model: Events
    ) {
        holder.event_name.setText(model.event_name)
        Glide.with(holder.posterImage.context).load(model.purl).into(holder.posterImage)
        val resultInMillis = convertDateTimeToMillis(model.event_date, model.event_endtime)
        val currInMillis = Calendar.getInstance().timeInMillis
        val diff = currInMillis-resultInMillis
        val hoursDifference = TimeUnit.MILLISECONDS.toHours(diff)

        val map = HashMap<String,Long>()
        map.put("hrsAgo",hoursDifference)
        dbRef.child(model.key).updateChildren(map as Map<String, Any>)
        dbRefev.child(model.key).updateChildren(map as Map<String, Any>)
        val date = model.event_date
        val dateup = date.substring(8,10)+date.substring(4,8)+date.substring(0,4)
        holder.date.setText(dateup)
        holder.time.setText(model.event_starttime)
        holder.venue.setText(model.event_venue)
        holder.club_name.setText(model.club_name)
        holder.posterImage.setOnClickListener{
            val customDialog = CustomDialog(holder.itemView.context, model)
            customDialog.showDialog()
        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): userAdapterHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.past_item,parent,false)
        return userAdapterHolder(view)
    }

    inner class userAdapterHolder(innerView:View):RecyclerView.ViewHolder(innerView) {
        var posterImage:ImageView
        var event_name:TextView
        var club_name:TextView
        var date:TextView
        var time:TextView
        var venue:TextView

        init {
            posterImage =innerView.findViewById(R.id.postImage)
            event_name = innerView.findViewById(R.id.event_name)
            club_name = innerView.findViewById(R.id.club_name)
            date = innerView.findViewById(R.id.date)
            time = innerView.findViewById(R.id.time)
            venue = itemView.findViewById(R.id.venue)
        }
    }

    fun convertDateTimeToMillis(eventDate: String, eventTime: String): Long {
        val dateTimeString = "$eventDate $eventTime"
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())

        val calendar = Calendar.getInstance()
        calendar.time = dateFormat.parse(dateTimeString) ?: Date(0)

        // Set milliseconds to zero
        calendar.set(Calendar.MILLISECOND, 0)

        return calendar.timeInMillis
    }
}