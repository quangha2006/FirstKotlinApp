package com.example.myfirstkotlinapplication.ui.home.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import com.example.myfirstkotlinapplication.databinding.CardComputerBinding
import com.example.myfirstkotlinapplication.ui.home.MainActivity.Companion.LogTag
import com.example.myfirstkotlinapplication.ui.home.fragments.FragmentWalkOnLan
import kotlinx.android.synthetic.main.card_computer.view.*

class DataAdapterWalkOnLan(arrayList: ArrayList<Computer>) :
    androidx.recyclerview.widget.RecyclerView.Adapter<DataAdapterWalkOnLan.ViewHolder>(),
    Filterable {

    private var mArrayList: ArrayList<Computer> = arrayList
    private var mFilteredList: ArrayList<Computer> = arrayList

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        /*val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.card_computer, viewGroup, false)
        view.btn_start.setOnClickListener()
        {
            val ipAddressWOL:String = view.tve_ip.text.toString()
            val macAddressWOL:String = view.tve_mac.text.toString()
            Log.i(LogTag, "Call Walk On Lan IP: $ipAddressWOL macAddress: $macAddressWOL")
            FragmentWalkOnLan.SendMagicPacket(ipAddressWOL, macAddressWOL).execute()
        }
        return ViewHolder(view)
        */
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        val binding = CardComputerBinding.inflate(layoutInflater, viewGroup, false)
        binding.btnStart.setOnClickListener()
        {
            val ipAddressWOL: String = binding.tveIp.getString()
            val macAddressWOL: String = binding.tveMac.getString()
            Log.i(LogTag, "Call Walk On Lan IP: $ipAddressWOL macAddress: $macAddressWOL")
            FragmentWalkOnLan.SendMagicPacket(
                ipAddressWOL,
                macAddressWOL
            ).execute()
        }
        binding.btnStart.visibility = View.INVISIBLE

        binding.btnDelete.visibility = View.VISIBLE
        binding.btnEdit.visibility = View.VISIBLE
        return ViewHolder(binding.root)
    }

    private fun TextView?.getString(): String {
        return this?.text.toString()
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.bind(mFilteredList[i])
    }

    inner class ViewHolder(itemView: View) :
        androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
        fun bind(computer: Computer) {
            itemView.tve_pc.text = computer.PCName
            itemView.tve_ip.text = computer.IP
            itemView.tve_mac.text = computer.Mac
        }
    }

    override fun getFilter(): Filter? {
        return null
    }

    override fun getItemCount(): Int {
        return mFilteredList.size
    }

    class JSONComputerList(var PCList: ArrayList<Computer>? = null) {
        fun add(computer: Computer) {
            PCList!!.add(computer)
        }

    }

    data class Computer(var PCName: String, var IP: String, var Mac: String) {
    }

}