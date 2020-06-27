package com.example.myfirstkotlinapplication

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import com.example.myfirstkotlinapplication.databinding.CardAndroidversionBinding
import kotlinx.android.synthetic.main.card_androidversion.view.*
import java.util.*
import kotlin.collections.ArrayList
import java.io.IOException;
import java.io.InputStream;

class DataAdapterAndroidVersion(arrayList: ArrayList<FragmentAndroidVersionInfo.AndroidVersion>) : RecyclerView.Adapter<DataAdapterAndroidVersion.ViewHolder>(), Filterable {
    private var mArrayList: ArrayList<FragmentAndroidVersionInfo.AndroidVersion> = arrayList
    private var mFilteredList: ArrayList<FragmentAndroidVersionInfo.AndroidVersion> = arrayList
    private lateinit var mContext: Context

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): DataAdapterAndroidVersion.ViewHolder {
        //val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.card_androidversion, viewGroup, false)
        //return ViewHolder(view)

        mContext = viewGroup.context

        val layoutInflater = LayoutInflater.from(viewGroup.context)
        val binding = CardAndroidversionBinding.inflate(layoutInflater, viewGroup, false)
        mContext = viewGroup.context
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(viewHolder: DataAdapterAndroidVersion.ViewHolder, i: Int) {

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
        viewHolder.bind(mFilteredList[i],assertPath)
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
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind (android:FragmentAndroidVersionInfo.AndroidVersion, assertPath : String){
            itemView.tv_name.text = android.name
            itemView.tv_version.text = android.ver
            itemView.tv_api_level.text = android.api
            itemView.tv_release_date.text = android.releasedate
            try {
                val ims : InputStream = mContext.assets.open(assertPath);
                val drawable =  Drawable.createFromStream(ims, null);
                itemView.imageView.setImageDrawable(drawable)
            }catch(e:IOException){
                e.printStackTrace()
            }
        }
    }

}
