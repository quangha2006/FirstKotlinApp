package com.example.myfirstkotlinapplication

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myfirstkotlinapplication.MainActivity.Companion.LogTag
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_walk_on_lan.view.*
import kotlinx.android.synthetic.main.walkonlan_dialog_add_device.view.*
import java.io.File
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress


class FragmentWalkOnLan : Fragment() {

    private var mRecyclerView: RecyclerView ?= null
    private var mDataAdapter : WalkOnLanDataAdapter ?= null
    private lateinit var mContext: Context
    private lateinit var mView: View
    private var mJsonData : WalkOnLanDataAdapter.JSONComputerList ?= null
    private val mDataPath:String = "/PCList.json"
    private var mDialogView : View ?= null

    @SuppressLint("InflateParams")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //val view:View =

        mView = inflater.inflate(R.layout.fragment_walk_on_lan, container, false)
        mContext = container?.context!!
        initViews()
        //Inflate the dialog with custom view
        mDialogView = LayoutInflater.from(mContext).inflate(R.layout.walkonlan_dialog_add_device,null)

        mView.floatingActionButton.setOnClickListener()
        {

            //AlertDialogBuilder
            val mBuilder = AlertDialog.Builder(mContext)
                .setView(mDialogView)

            //Show dialog
            val mAlertDialog = mBuilder.show()
            // Set button click of custom layout
            mDialogView!!.dialogOKBtn.setOnClickListener(){

                val pcName = mDialogView!!.etvPcName.text.toString()
                val ip = mDialogView!!.etvIP1.text.toString() + '.' + mDialogView!!.etvIP2.text.toString() + '.' + mDialogView!!.etvIP3.text.toString() + '.' + mDialogView!!.etvIP4.text.toString()
                val mac = mDialogView!!.etvMac1.text.toString() + ':' + mDialogView!!.etvMac2.text.toString() + ':' + mDialogView!!.etvMac3.text.toString() + ':' + mDialogView!!.etvMac4.text.toString() + ':' + mDialogView!!.etvMac5.text.toString() + ':' + mDialogView!!.etvMac6.text.toString()
                val computer = WalkOnLanDataAdapter.Computer(pcName, ip,mac)
                // Need validate Data
                // dismiss dialog
                mAlertDialog.dismiss()

                mJsonData!!.add(computer)
                //Update Data
                val newData = Gson().toJson(mJsonData).toString()
                val file = File(mContext.filesDir.absolutePath + mDataPath)
                file.writeText(newData)
            }
            mDialogView!!.dialogCancelBtn.setOnClickListener(){
                mAlertDialog.dismiss()
            }
        }

        setUpTextChange()

        return mView
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataPath : String = activity?.applicationContext!!.filesDir.absolutePath + mDataPath
        Log.i(LogTag, "DataPath: $dataPath")
        val file = File(dataPath.toString())
        mJsonData = if (file.exists()) {
            val json : String = file.readText()
            Gson().fromJson(json, WalkOnLanDataAdapter.JSONComputerList::class.java)
        } else {
            WalkOnLanDataAdapter.JSONComputerList(arrayListOf<WalkOnLanDataAdapter.Computer>())
        }
        mDataAdapter = mJsonData?.PCList?.let { WalkOnLanDataAdapter(it) }
    }

    private fun initViews() {
        mRecyclerView = mView.card_recycler_view_wol
        mRecyclerView!!.setHasFixedSize(true)
        mRecyclerView!!.layoutManager = LinearLayoutManager(mContext)
        mRecyclerView!!.adapter = mDataAdapter
    }
    private fun setUpTextChange(){
        mDialogView!!.etvIP1.addTextChangedListener(object :TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (p0?.length == 3){
                    mDialogView!!.etvIP2.requestFocus()
                }

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        mDialogView!!.etvIP2.addTextChangedListener(object :TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (p0?.length == 3){
                    mDialogView!!.etvIP3.requestFocus()
                }

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        mDialogView!!.etvIP3.addTextChangedListener(object :TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (p0?.length == 3){
                    mDialogView!!.etvIP4.requestFocus()
                }

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        mDialogView!!.etvIP4.addTextChangedListener(object :TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (p0?.length == 3){
                    mDialogView!!.etvMac1.requestFocus()
                }

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        mDialogView!!.etvMac1.addTextChangedListener(object :TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (p0?.length == 2){
                    mDialogView!!.etvMac2.requestFocus()
                }

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        mDialogView!!.etvMac2.addTextChangedListener(object :TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (p0?.length == 2){
                    mDialogView!!.etvMac3.requestFocus()
                }

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        mDialogView!!.etvMac3.addTextChangedListener(object :TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (p0?.length == 2){
                    mDialogView!!.etvMac4.requestFocus()
                }

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        mDialogView!!.etvMac4.addTextChangedListener(object :TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (p0?.length == 2){
                    mDialogView!!.etvMac5.requestFocus()
                }

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        mDialogView!!.etvMac5.addTextChangedListener(object :TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (p0?.length == 2){
                    mDialogView!!.etvMac6.requestFocus()
                }

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

    }
    class SendMagicPacket(private val ipAddressWOL:String, private val macAddressWOL:String): AsyncTask<Void, Void, String>() {

        override fun doInBackground(vararg p0: Void?): String? {
        try {
            val port = 9
            val macBytes = getMacBytes(macAddressWOL)
            val bytes = ByteArray(6 + 16 * macBytes!!.size)
            for (i in 0..5) {
                bytes[i] = 0xff.toByte()
            }
            var i = 6
            while (i < bytes.size) {
                System.arraycopy(macBytes, 0, bytes, i, macBytes.size)
                i += macBytes.size
            }
            val address: InetAddress = InetAddress.getByName(ipAddressWOL)
            val packet = DatagramPacket(bytes, bytes.size, address, port)
            val socket = DatagramSocket()
            socket.send(packet)
            socket.close()
            Log.i(LogTag, "Wake-on-LAN packet sent.")
        } catch (e: Exception) {
            Log.e(LogTag, "Failed to send Wake-on-LAN packet: $e")
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