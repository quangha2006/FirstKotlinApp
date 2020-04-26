package com.example.myfirstkotlinapplication
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import java.util.*
import kotlin.collections.ArrayList


class DataAdapter(arrayList: ArrayList<FragmentAndroidVersionInfo.AndroidVersion>) : RecyclerView.Adapter<DataAdapter.ViewHolder>(), Filterable {
    private var mArrayList: ArrayList<FragmentAndroidVersionInfo.AndroidVersion> = arrayList
    private var mFilteredList: ArrayList<FragmentAndroidVersionInfo.AndroidVersion> = arrayList


    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): DataAdapter.ViewHolder {
        val view =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.card_row, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: DataAdapter.ViewHolder, i: Int) {

        viewHolder.tvname.text = (mFilteredList[i].name)
        viewHolder.tvversion.text = (mFilteredList[i].ver)
        viewHolder.tvapilevel.text = (mFilteredList[i].api)
        viewHolder.tvreleasedate.text = (mFilteredList[i].releasedate)
    }

    override fun getItemCount(): Int {
        return mFilteredList.size
    }

    override fun getFilter(): Filter {

        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): Filter.FilterResults {

                val charString = charSequence.toString()

                if (charString.isEmpty()) {

                    mFilteredList = mArrayList
                } else {

                    val filteredList = ArrayList<FragmentAndroidVersionInfo.AndroidVersion>()

                    for (androidVersion in mArrayList) {

                        if (androidVersion.api!!.toLowerCase(Locale.ROOT).contains(charString) || androidVersion.name!!.toLowerCase(
                                Locale.ROOT
                            ).contains(
                                charString
                            ) || androidVersion.ver!!.toLowerCase(Locale.ROOT).contains(charString)
                        ) {

                            filteredList.add(androidVersion)
                        }
                    }

                    mFilteredList = filteredList
                }

                val filterResults = Filter.FilterResults()
                filterResults.values = mFilteredList
                return filterResults
            }

            override fun publishResults(
                charSequence: CharSequence,
                filterResults: Filter.FilterResults
            ) {
                mFilteredList = filterResults.values as ArrayList<FragmentAndroidVersionInfo.AndroidVersion>
                notifyDataSetChanged()
            }
        }
    }
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvname: TextView = view.findViewById(R.id.tv_name)
        val tvversion: TextView = view.findViewById(R.id.tv_version)
        val tvapilevel: TextView = view.findViewById(R.id.tv_api_level)
        val tvreleasedate : TextView = view.findViewById(R.id.tv_release_date)
    }

}
