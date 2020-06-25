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
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.example.myfirstkotlinapplication.MainActivity.Companion.LogTag
import com.example.myfirstkotlinapplication.databinding.DialogWoladddeviceBinding
import com.example.myfirstkotlinapplication.databinding.FragmentWalkonlanBinding
import com.google.gson.Gson
import kotlinx.android.synthetic.main.dialog_woladddevice.*

import java.io.File
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress


class FragmentWalkOnLan : Fragment() {

    private var mRecyclerView: RecyclerView ?= null
    private var mDataAdapterWalkOnLan : DataAdapterWalkOnLan ?= null
    private lateinit var mContext: Context
    private var mJsonDataWalkOnLan : DataAdapterWalkOnLan.JSONComputerList ?= null
    private val mDataPath:String = "/PCList.json"
    private lateinit var mBinding : FragmentWalkonlanBinding
    private lateinit var mDialogBinding : DialogWoladddeviceBinding

    @SuppressLint("InflateParams", "StringFormatInvalid")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = FragmentWalkonlanBinding.inflate(inflater)

        mContext = container?.context!!
        initViews()
        mDialogBinding = DialogWoladddeviceBinding.inflate(inflater)
        //Inflate the dialog with custom view

        mBinding.floatingActionButton.setOnClickListener()
        {
            //AlertDialogBuilder
            val builder = AlertDialog.Builder(mContext)
                .setView(mDialogBinding.root)

            //Show dialog
            val mAlertDialog = builder!!.show()

            // Set button click of custom layout
            mDialogBinding.dialogOKBtn.setOnClickListener()
            {

                val ip = getString(
                    R.string.ip_value,
                    etvIP1?.getInput(),
                    etvIP2?.getInput(),
                    etvIP3?.getInput(),
                    etvIP4?.getInput()
                )
                val mac = getString(
                    R.string.mac_value,
                    etvMac1?.getInput(),
                    etvMac2?.getInput(),
                    etvMac3?.getInput(),
                    etvMac4?.getInput(),
                    etvMac5?.getInput(),
                    etvMac6?.getInput()
                )

                etvPcName?.getInput()?.let {pcName ->
                    val computer = DataAdapterWalkOnLan.Computer(pcName, ip,mac)
                    mAlertDialog.dismiss()
                    mJsonDataWalkOnLan?.add(computer)
                    val file = File(mContext.filesDir.absolutePath + mDataPath)
                    Gson().toJson(mJsonDataWalkOnLan)?.let { data->
                        file.writeText(data)
                    }
                }

            }
            mDialogBinding.dialogOKBtn.setOnClickListener(){
                mAlertDialog.dismiss()
            }
        }
        setUpTextChange()
        return mBinding.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataPath : String = activity?.applicationContext!!.filesDir.absolutePath + mDataPath
        Log.i(LogTag, "DataPath: $dataPath")
        val file = File(dataPath.toString())
        mJsonDataWalkOnLan = if (file.exists()) {
            val json : String = file.readText()
            Gson().fromJson(json, DataAdapterWalkOnLan.JSONComputerList::class.java)
        } else {
            DataAdapterWalkOnLan.JSONComputerList(arrayListOf<DataAdapterWalkOnLan.Computer>())
        }
        mDataAdapterWalkOnLan = mJsonDataWalkOnLan?.PCList?.let { DataAdapterWalkOnLan(it) }

    }

    private fun initViews() {
        mRecyclerView = mBinding.cardRecyclerViewWol
        mRecyclerView?.setHasFixedSize(true)
        mRecyclerView?.layoutManager = LinearLayoutManager(mContext)
        mRecyclerView?.adapter = mDataAdapterWalkOnLan
    }
    fun EditText?.getInput():String {
        return this?.text?.toString()?:""
    }
    private fun setUpTextChange(){
        etvIP1?.addTextChangedListener(object :TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (p0?.length == 3){
                    etvIP2?.requestFocus()
                }
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
        etvIP2?.addTextChangedListener(object :TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (p0?.length == 3){
                    etvIP3?.requestFocus()
                }

            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
        etvIP3?.addTextChangedListener(object :TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (p0?.length == 3){
                   etvIP4?.requestFocus()
                }
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
        etvIP4?.addTextChangedListener(object :TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (p0?.length == 3){
                    etvMac1?.requestFocus()
                }
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
        etvMac1?.addTextChangedListener(object :TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (p0?.length == 2){
                    etvMac2?.requestFocus()
                }
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
        etvMac2?.addTextChangedListener(object :TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (p0?.length == 2){
                    etvMac3?.requestFocus()
                }
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
        etvMac3?.addTextChangedListener(object :TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (p0?.length == 2){
                    etvMac4?.requestFocus()
                }
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
        etvMac4?.addTextChangedListener(object :TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (p0?.length == 2){
                    etvMac5?.requestFocus()
                }
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
        etvMac5?.addTextChangedListener(object :TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (p0?.length == 2){
                   etvMac6?.requestFocus()
                }
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
        /*etvIP2?.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_DOWN && etvIP2?.text.isEmpty()) {
                etvIP1?.requestFocus()
                return@OnKeyListener true
            }
            false
        })
        etvIP3?.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_DOWN && etvIP3?.getInput().isEmpty()) {
                etvIP2?.requestFocus()
                return@OnKeyListener true
            }
            false
        })
        etvIP4?.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_DOWN && etvIP4?.text.isEmpty()) {
                etvIP3?.requestFocus()
                return@OnKeyListener true
            }
            false
        })
        etvMac2?.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_DOWN && etvMac2?.text.isEmpty()) {
                etvMac1?.requestFocus()
                return@OnKeyListener true
            }
            false
        })
        etvMac3?.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_DOWN && etvMac3?.text.isEmpty()) {
                etvMac2?.requestFocus()
                return@OnKeyListener true
            }
            false
        })
        etvMac4?.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_DOWN && etvMac4?.text.isEmpty()) {
                etvMac3?.requestFocus()
                return@OnKeyListener true
            }
            false
        })
        etvMac5?.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_DOWN && etvMac5?.text.isEmpty()) {
                etvMac4?.requestFocus()
                return@OnKeyListener true
            }
            false
        })
        etvMac6?.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_DOWN && etvMac6?.text.isEmpty()) {
                etvMac5?.requestFocus()
                return@OnKeyListener true
            }
            false
        })*/

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