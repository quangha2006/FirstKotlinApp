package com.example.myfirstkotlinapplication

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import com.example.myfirstkotlinapplication.MainActivity.Companion.LogTag
import com.google.gson.Gson
import kotlinx.android.synthetic.main.walkonlan_card.view.*
import kotlin.collections.ArrayList

class WalkOnLanDataAdapter(arrayList: ArrayList<Computer>) : RecyclerView.Adapter<WalkOnLanDataAdapter.ViewHolder>(), Filterable {

    private var mArrayList: ArrayList<Computer>? = arrayList
    private var mFilteredList: ArrayList<Computer>? = arrayList

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): WalkOnLanDataAdapter.ViewHolder {
        val view =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.walkonlan_card, viewGroup, false)
        view.btn_start.setOnClickListener()
        {
            val ipAddressWOL:String = view.tve_ip.text.toString()

            val macAddressWOL:String = view.tve_mac.text.toString()

            Log.i(LogTag, "Call Walk On Lan IP: $ipAddressWOL macAddress: $macAddressWOL")

            FragmentWalkOnLan.SendMagicPacket(ipAddressWOL, macAddressWOL).execute()
        }
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: WalkOnLanDataAdapter.ViewHolder, i: Int) {
        viewHolder.pcName.text = mFilteredList!![i].PCName
        viewHolder.pcIp.text = mFilteredList!![i].IP
        viewHolder.pcMac.text = mFilteredList!![i].Mac
    }
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var pcName: TextView = view.findViewById(R.id.tve_pc)
        var pcIp: TextView = view.findViewById(R.id.tve_ip)
        var pcMac: TextView = view.findViewById(R.id.tve_mac)
    }
    override fun getFilter(): Filter? {
        return null
    }
    override fun getItemCount(): Int {
        return mFilteredList!!.size
    }

    class JSONComputerList(var PCList:ArrayList<Computer>?=null){

        fun add(computer:Computer){
            PCList!!.add(computer)
        }

    }
    data class Computer(var PCName: String, var IP: String, var Mac: String) {
    }

}