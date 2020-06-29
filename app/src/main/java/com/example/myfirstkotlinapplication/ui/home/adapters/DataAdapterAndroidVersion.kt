package com.example.myfirstkotlinapplication.ui.home.adapters

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstkotlinapplication.data.entity.response.Android
import com.example.myfirstkotlinapplication.databinding.CardAndroidversionBinding
import kotlinx.android.synthetic.main.card_androidversion.view.*
import java.io.IOException
import java.io.InputStream
import java.util.*
import kotlin.collections.ArrayList

class DataAdapterAndroidVersion(data: ArrayList<Android>) :
    RecyclerView.Adapter<DataAdapterAndroidVersion.ViewHolder>(),
    Filterable {

    private var mArrayList: ArrayList<Android> = data
    private var mFilteredList: ArrayList<Android> = data
    private lateinit var mContext: Context

    fun setNewData(data: ArrayList<Android>) {
        mArrayList = data
        mFilteredList = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {

        mContext = viewGroup.context

        val layoutInflater = LayoutInflater.from(viewGroup.context)
        val binding = CardAndroidversionBinding.inflate(layoutInflater, viewGroup, false)
        mContext = viewGroup.context
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {

        // This is cheat!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        var assertPath = "androidversion/android_o.jpg"
        if (mFilteredList[i].name.equals("Marshmallow"))
            assertPath = "androidversion/android_m.jpg"
        if (mFilteredList[i].name.equals("Nougat"))
            assertPath = "androidversion/android_n.jpg"
        if (mFilteredList[i].name.equals("Lollipop"))
            assertPath = "androidversion/android_l.jpg"
        if (mFilteredList[i].name.equals("Pie"))
            assertPath = "androidversion/android_p.jpg"
        if (mFilteredList[i].name.equals("Android 10"))
            assertPath = "androidversion/android_10.jpg"
        //bind data
        viewHolder.bind(mFilteredList[i], assertPath)
    }

    override fun getItemCount(): Int {
        return mFilteredList.size
    }

    override fun getFilter(): Filter {

        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {

                val charString = charSequence.toString()
                if (charString.isEmpty()) {
                    mFilteredList = mArrayList
                } else {
                    val filteredList = ArrayList<Android>()
                    for (androidVersion in mArrayList) {
                        if (androidVersion.api!!.toLowerCase(Locale.ROOT)
                                .contains(charString) || androidVersion.name!!.toLowerCase(
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
                val filterResults = FilterResults()
                filterResults.values = mFilteredList
                return filterResults
            }

            override fun publishResults(
                charSequence: CharSequence,
                filterResults: FilterResults
            ) {
                mFilteredList =
                    filterResults.values as ArrayList<Android>
                notifyDataSetChanged()
            }
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(android: Android, assertPath: String) {
            itemView.tv_name.text = android.name
            itemView.tv_version.text = android.ver
            itemView.tv_api_level.text = android.api
            itemView.tv_release_date.text = android.releasedate
            try {
                val ims: InputStream = mContext.assets.open(assertPath);
                val drawable = Drawable.createFromStream(ims, null);
                itemView.imageView.setImageDrawable(drawable)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

}
