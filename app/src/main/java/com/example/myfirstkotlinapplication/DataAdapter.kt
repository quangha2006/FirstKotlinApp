package com.example.myfirstkotlinapplication
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import java.util.ArrayList


class DataAdapter : RecyclerView.Adapter<DataAdapter.ViewHolder>() , Filterable{
    private var mArrayList: ArrayList<AndroidVersion>? = null
    private var mFilteredList: ArrayList<AndroidVersion>? = null

    fun DataAdapter(arrayList: ArrayList<AndroidVersion>) {
        mArrayList = arrayList
        mFilteredList = arrayList
    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): DataAdapter.ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.card_row, viewGroup, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(viewHolder: DataAdapter.ViewHolder, i: Int) {

        viewHolder.tv_name.setText(mFilteredList[i].getName())
        viewHolder.tv_version.setText(mFilteredList[i].getVer())
        viewHolder.tv_api_level.setText(mFilteredList[i].getApi())
    }
    override fun getItemCount(): Int {
        return mFilteredList.size()
    }
    override fun getFilter(): Filter {

        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): Filter.FilterResults {

                val charString = charSequence.toString()

                if (charString.isEmpty()) {

                    mFilteredList = mArrayList
                } else {

                    val filteredList = ArrayList<AndroidVersion>()

                    for (androidVersion in mArrayList) {

                        if (androidVersion.getApi()!!.toLowerCase().contains(charString) || androidVersion.getName()!!.toLowerCase().contains(
                                charString
                            ) || androidVersion.getVer()!!.toLowerCase().contains(charString)
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

            override fun publishResults(charSequence: CharSequence, filterResults: Filter.FilterResults) {
                mFilteredList = filterResults.values as ArrayList<AndroidVersion>
                notifyDataSetChanged()
            }
        }
    }
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tv_name: TextView
        val tv_version: TextView
        val tv_api_level: TextView

        init {
            tv_name = view.findViewById(R.id.tv_name)
            tv_version = view.findViewById(R.id.tv_version)
            tv_api_level = view.findViewById(R.id.tv_api_level)

        }
    }

}