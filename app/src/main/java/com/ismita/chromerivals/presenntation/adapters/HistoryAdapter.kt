package com.ismita.chromerivals.presenntation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ismita.chromerivals.R
import com.ismita.chromerivals.databinding.HistoryCardBinding
import com.ismita.chromerivals.databinding.SearchFragmentBinding
import com.ismita.chromerivals.data.model.pedia.HistoryElementDB
import com.ismita.chromerivals.presenntation.ui.pedia.SearchViewModel
import com.ismita.chromerivals.presenntation.ui.pedia.SearchFragment

class HistoryAdapter(private val searchViewModel: SearchViewModel, private val searchFragment: SearchFragment) : RecyclerView.Adapter<HistoryViewHolder>() {

    private var historyElements = emptyList<HistoryElementDB>()

    fun setData(newHistoryElementDBS: List<HistoryElementDB>) {
        historyElements = newHistoryElementDBS
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return historyElements.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.history_card, parent, false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        return holder.bind(historyElements[position], searchViewModel, position, searchFragment)
    }
}

class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(historyElementDB: HistoryElementDB, searchViewModel: SearchViewModel, index: Int, searchFragment: SearchFragment) = with(itemView) {
        val binding = HistoryCardBinding.bind(itemView)

        binding.historyItemName.text = historyElementDB.query
        binding.historyRemoveIcon.setOnClickListener {
            searchViewModel.deleteHistoryElementAtIndex(historyElementDB)
        }

        this.setOnClickListener {
            searchFragment.view?.let { SearchView ->
                SearchFragmentBinding.bind(SearchView).apply {
                    searchInput.setQuery(historyElementDB.query, false)
                    searchFragment.closeHistoryAndClearFocus()
                }
            }
            searchViewModel.searchNewResults(query = historyElementDB.query)
        }

    }
}