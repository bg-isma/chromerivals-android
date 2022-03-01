package com.ismita.chromerivals.presenntation.adapters

import android.annotation.SuppressLint
import android.os.Build
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ismita.chromerivals.R
import com.ismita.chromerivals.databinding.EventCardBinding
import com.ismita.chromerivals.data.model.event.Event
import com.ismita.chromerivals.data.model.event.CurrentEvent
import com.ismita.chromerivals.data.model.event.UpcomingEvent
import com.ismita.chromerivals.utils.extensions.CurrentEventExtension.getEventMillisToFinish
import com.ismita.chromerivals.utils.extensions.StringExtension.deleteStrangeCharacters
import com.ismita.chromerivals.utils.extensions.StringExtension.toLocalZoneDateFormatted
import com.ismita.chromerivals.presenntation.ui.home.HomeViewModel

class EventsAdapter(private var eventViewModel: HomeViewModel?) : RecyclerView.Adapter<EventsViewHolder>() {

    private var events: List<Event> = emptyList()

    fun setData(newEvents: List<Event>) {
        events = newEvents
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return events.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.event_card, parent, false)
        return EventsViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        return holder.bind(events[position], position, eventViewModel)
    }

}

class EventsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private lateinit var eventViewModel: HomeViewModel
    private lateinit var binding: EventCardBinding
    private var eventIndex: Int = 0

    @RequiresApi(Build.VERSION_CODES.O)
    fun bind(event: Event, index: Int, homeViewModel: HomeViewModel?) = with(itemView) {

        binding = EventCardBinding.bind(itemView)
        eventIndex = index
        if (homeViewModel != null) eventViewModel = homeViewModel

        when (event) {
            is UpcomingEvent -> {
                binding.eventCardContent.layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
                binding.timer.visibility = View.GONE
                when {
                    event.ani != null -> {
                        setMothershipCardData(resources.getString(R.string.ani_mothership), R.drawable.ic_ani_logo, event.ani!!, R.color.ani)
                    }
                    event.bcu != null -> {
                        setMothershipCardData(resources.getString(R.string.bcu_mothership), R.drawable.ic_bcu_logo, event.bcu!!, R.color.bcu)
                    }
                    else -> setOutpostCardData(event)
                }
            }
            is CurrentEvent -> {
                if (event.endTime != null) {
                    binding.textView6.text = event.mapsName?.deleteStrangeCharacters() ?: ""
                    binding.textView8.text = event.getTypeName()
                    binding.eventDateText.visibility = View.GONE
                    val millisUntil = event.getEventMillisToFinish()
                    val timer = object : CountDownTimer(millisUntil, 1000) {
                        override fun onFinish() {
                            deleteEventFinished()
                        }
                        override fun onTick(millisUntilFinished: Long) {
                            drawTimerCount(millisUntilFinished)
                        }
                    }
                    timer.start()
                }
            }
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setMothershipCardData(name: String, nationLogoDrawable: Int, date: String, color: Int) {
        binding.textView6.text = name
        binding.eventDateText.text = date.toLocalZoneDateFormatted()
        binding.textView8.text = this.itemView.resources.getString(R.string.mothership)
        binding.itemContent.setCardBackgroundColor(ContextCompat.getColor(itemView.context, color))
        binding.imageView9.setImageResource(nationLogoDrawable)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setOutpostCardData(event: UpcomingEvent) {
        binding.textView6.text = event.outpostName?.deleteStrangeCharacters() ?: ""
        binding.textView8.text = this.itemView.resources.getString(R.string.outpost)
        binding.eventDateText.text = event.deployTime?.toLocalZoneDateFormatted()
        when (event.influence?.toInt()) {
            2 -> {
                binding.itemContent.setCardBackgroundColor(ContextCompat.getColor(itemView.context, R.color.bcu))
                binding.imageView9.setImageResource(R.drawable.ic_bcu_logo)
            }
            4 -> {
                binding.itemContent.setCardBackgroundColor(ContextCompat.getColor(itemView.context, R.color.ani))
                binding.imageView9.setImageResource(R.drawable.ic_ani_logo)
            }
        }
    }

    private fun deleteEventFinished() {
        eventViewModel.deleteCurrentEventByIndex(eventIndex)
    }

    @SuppressLint("SetTextI18n")
    private fun drawTimerCount(millisUntilFinished: Long) {
        val seconds = millisUntilFinished / 1000
        val minutes = seconds / 60
        val secondsText = (seconds - minutes * 60).toString()
        binding.timer.text =  "${minutes}:${if (secondsText.length == 2) secondsText else "0$secondsText"}"
    }

}