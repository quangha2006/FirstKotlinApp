package com.example.myfirstkotlinapplication

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import kotlinx.android.synthetic.main.walkonlan_card.view.*

class WalkOnLanDataAdapter(arrayList: ArrayList<Computer>) : RecyclerView.Adapter<WalkOnLanDataAdapter.ViewHolder>(), Filterable {

    private var mArrayList: ArrayList<Computer>? = arrayList
    private var mFilteredList: ArrayList<Computer>? = arrayList
    private val LOGTAG = "QUANGHA"

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): WalkOnLanDataAdapter.ViewHolder {
        val view =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.walkonlan_card, viewGroup, false)
        view.btn_start.setOnClickListener()
        {
            val ipAddressWOL:String = view.tve_ip.text.toString()

            val macAddressWOL:String = view.tve_mac.text.toString()

            Log.i(LOGTAG, "Call Walk On Lan IP: $ipAddressWOL macAddress: $macAddressWOL")

            FragmentWalkOnLan.SendMagicPacket(ipAddressWOL, macAddressWOL).execute()
        }
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: WalkOnLanDataAdapter.ViewHolder, i: Int) {
        viewHolder.pcName.text = mFilteredList!![i].getPCName()
        viewHolder.pcIp.text = mFilteredList!![i].getIPAddress()
        viewHolder.pcMac.text = mFilteredList!![i].getMACAddress()
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

    class JSONComputerList(){

        private var PCList:ArrayList<Computer>?=null

        fun getComputerList(): ArrayList<Computer>? {
            return PCList
        }
    }
    class Computer {
        private val mPCName: String? = null
        private val mIP: String? = null
        private val mMac: String? = null

        fun getPCName(): String? {
            return mPCName
        }

        fun getIPAddress(): String? {
            return mIP
        }

        fun getMACAddress(): String? {
            return mMac
        }
    }

}