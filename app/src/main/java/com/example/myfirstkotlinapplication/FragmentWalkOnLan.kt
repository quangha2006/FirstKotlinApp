package com.example.myfirstkotlinapplication

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.android.synthetic.main.fragment_walk_on_lan.view.*
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress


class FragmentWalkOnLan : Fragment() {

    private val LOGTAG = "QUANGHA"
    private var mRecyclerView: RecyclerView? = null
    private var mDataAdapter : WalkOnLanDataAdapter?=null
    private lateinit var mContext: Context
    private lateinit var mView: View
    private var JSONData: WalkOnLanDataAdapter.JSONComputerList? = null

    public val jsonTest:String = "{\n" +
            "    \"PCList\":[\n" +
            "        {\n" +
            "            \"mPCName\": \"QUANGHA-PC\",\n" +
            "            \"mIP\": \"192.168.0.255\",\n" +
            "            \"mMac\": \"A8-5E-45-69-9C-D5\"\n" +
            "        }\n" +
            "    ]\n" +
            "}"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View = inflater.inflate(R.layout.fragment_walk_on_lan, container, false)
        mView = view
        mContext = container?.context!!
        initViews()
        return view
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val jsonData = Gson().fromJson(jsonTest, WalkOnLanDataAdapter.JSONComputerList::class.java)
        mDataAdapter = WalkOnLanDataAdapter(ArrayList(jsonData.getComputerList()))
    }

    private fun initViews() {
        mRecyclerView = mView.card_recycler_view_wol
        mRecyclerView!!.setHasFixedSize(true)
        mRecyclerView!!.layoutManager = LinearLayoutManager(mContext)
        mRecyclerView!!.adapter = mDataAdapter
    }
    class SendMagicPacket(ipAddressWOL:String,macAddressWOL:String): AsyncTask<Void, Void, String>() {
        private val LOGTAG = "QUANGHA"
        val macAddress = macAddressWOL
        val ipAddressWOL = ipAddressWOL
        override fun doInBackground(vararg p0: Void?): String? {
        try {
            val port = 9
            val macBytes = getMacBytes(macAddress)
            val bytes = ByteArray(6 + 16 * macBytes!!.size)
            for (i in 0..5) {
                bytes[i] = 0xff.toByte()
            }
            var i = 6
            while (i < bytes.size) {
                System.arraycopy(macBytes, 0, bytes, i, macBytes!!.size)
                i += macBytes!!.size
            }
            val address: InetAddress = InetAddress.getByName(ipAddressWOL)
            val packet = DatagramPacket(bytes, bytes.size, address, port)
            val socket = DatagramSocket()
            socket.send(packet)
            socket.close()
            Log.i(LOGTAG, "Wake-on-LAN packet sent.")
        } catch (e: Exception) {
            Log.e(LOGTAG, "Failed to send Wake-on-LAN packet: $e")
        }
            return null
        }
        @Throws(IllegalArgumentException::class)
        private fun getMacBytes(macStr: String): ByteArray? {
            val bytes = ByteArray(6)
            val hex = macStr.split(":","-").toTypedArray()
            require(hex.size == 6) { "Invalid MAC address." }
            try {
                for (i in 0..5) {
                    bytes[i] = hex[i].toInt(16).toByte()
                }
            } catch (e: NumberFormatException) {
                throw IllegalArgumentException("Invalid hex digit in MAC address.")
            }
            return bytes
        }
    }
}