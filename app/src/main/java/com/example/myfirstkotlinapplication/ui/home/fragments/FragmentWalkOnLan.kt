package com.example.myfirstkotlinapplication.ui.home.fragments

import android.app.AlertDialog
import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myfirstkotlinapplication.R
import com.example.myfirstkotlinapplication.base.BaseFragment
import com.example.myfirstkotlinapplication.databinding.DialogWoladddeviceBinding
import com.example.myfirstkotlinapplication.databinding.FragmentWalkonlanBinding
import com.example.myfirstkotlinapplication.ui.home.MainActivity.Companion.LogTag
import com.example.myfirstkotlinapplication.ui.home.adapters.DataAdapterWalkOnLan
import com.example.myfirstkotlinapplication.utils.extensions.click
import com.example.myfirstkotlinapplication.utils.extensions.getInput
import com.google.gson.Gson
import java.io.File
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress


class FragmentWalkOnLan : BaseFragment<FragmentWalkonlanBinding>() {

    private val gson = Gson()
    private val mDataPath: String = "/PCList.json"

    private var mDataAdapterWalkOnLan: DataAdapterWalkOnLan? = null
    private var mJsonDataWalkOnLan: DataAdapterWalkOnLan.JSONComputerList? = null

    private lateinit var mDialogBinding: DialogWoladddeviceBinding
    private lateinit var mContext: Context


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mContext = container?.context!!
        mDialogBinding = DialogWoladddeviceBinding.inflate(inflater, container, false)

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun bindingView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentWalkonlanBinding.inflate(inflater, container, false)

    override fun initUIComponent() {

        binding.floatingActionButton.click {
            onFloatingAction()
        }
        initAdapters()
        initRecyclerViews()
        setUpTextChange()
    }

    override fun observerViewModel() {

    }

    private fun setUpTextChange() {
        mDialogBinding.etvIP1.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (p0?.length == 3) {
                    mDialogBinding.etvIP2.requestFocus()
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
        mDialogBinding.etvIP2.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (p0?.length == 3) {
                    mDialogBinding.etvIP3.requestFocus()
                }

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
        mDialogBinding.etvIP3.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (p0?.length == 3) {
                    mDialogBinding.etvIP4.requestFocus()
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
        mDialogBinding.etvIP4.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (p0?.length == 3) {
                    mDialogBinding.etvMac1.requestFocus()
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
        mDialogBinding.etvMac1.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (p0?.length == 2) {
                    mDialogBinding.etvMac2.requestFocus()
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
        mDialogBinding.etvMac2.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (p0?.length == 2) {
                    mDialogBinding.etvMac3.requestFocus()
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
        mDialogBinding.etvMac3.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (p0?.length == 2) {
                    mDialogBinding.etvMac4.requestFocus()
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
        mDialogBinding.etvMac4.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (p0?.length == 2) {
                    mDialogBinding.etvMac5.requestFocus()
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
        mDialogBinding.etvMac5.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (p0?.length == 2) {
                    mDialogBinding.etvMac6.requestFocus()
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
        mDialogBinding.etvIP2.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_DOWN && mDialogBinding.etvIP2.text.isEmpty()) {
                mDialogBinding.etvIP1.requestFocus()
                return@OnKeyListener true
            }
            false
        })
        mDialogBinding.etvIP3.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_DOWN && mDialogBinding.etvIP3.text.isEmpty()) {
                mDialogBinding.etvIP2.requestFocus()
                return@OnKeyListener true
            }
            false
        })
        mDialogBinding.etvIP4.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_DOWN && mDialogBinding.etvIP4.text.isEmpty()) {
                mDialogBinding.etvIP3.requestFocus()
                return@OnKeyListener true
            }
            false
        })
        mDialogBinding.etvMac2.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_DOWN && mDialogBinding.etvMac2.text.isEmpty()) {
                mDialogBinding.etvMac1.requestFocus()
                return@OnKeyListener true
            }
            false
        })
        mDialogBinding.etvMac3.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_DOWN && mDialogBinding.etvMac3.text.isEmpty()) {
                mDialogBinding.etvMac2.requestFocus()
                return@OnKeyListener true
            }
            false
        })
        mDialogBinding.etvMac4.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_DOWN && mDialogBinding.etvMac4.text.isEmpty()) {
                mDialogBinding.etvMac3.requestFocus()
                return@OnKeyListener true
            }
            false
        })
        mDialogBinding.etvMac5.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_DOWN && mDialogBinding.etvMac5.text.isEmpty()) {
                mDialogBinding.etvMac4.requestFocus()
                return@OnKeyListener true
            }
            false
        })
        mDialogBinding.etvMac6.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_DOWN && mDialogBinding.etvMac6.text.isEmpty()) {
                mDialogBinding.etvMac5.requestFocus()
                return@OnKeyListener true
            }
            false
        })

    }

    class SendMagicPacket(private val ipAddressWOL: String, private val macAddressWOL: String) :
        AsyncTask<Void, Void, String>() {

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
            val hex = macStr.split(":", "-").toTypedArray()
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

    private fun initAdapters() {
        val dataPath = activity?.applicationContext!!.filesDir.absolutePath + mDataPath
        Log.i(LogTag, "DataPath: $dataPath")
        val file = File(dataPath)
        mJsonDataWalkOnLan = if (file.exists()) {
            val json: String = file.readText()
            gson.fromJson(json, DataAdapterWalkOnLan.JSONComputerList::class.java)
        } else {
            DataAdapterWalkOnLan.JSONComputerList(
                arrayListOf()
            )
        }
        mDataAdapterWalkOnLan = mJsonDataWalkOnLan?.PCList?.let {
            DataAdapterWalkOnLan(
                it
            )
        }
    }

    private fun initRecyclerViews() {
        binding.cardRecyclerViewWol.apply {
            setHasFixedSize(true)
            layoutManager =
                androidx.recyclerview.widget.LinearLayoutManager(mContext)
            adapter = mDataAdapterWalkOnLan
        }
    }

    private fun onFloatingAction() {
        if (mDialogBinding.root.parent != null) {
            //Remove self from parent view!
            val parentView = mDialogBinding.root.parent as ViewGroup
            parentView.removeView(mDialogBinding.root)
        }

        val builder = AlertDialog.Builder(mContext)
            .setView(mDialogBinding.root)

        val mAlertDialog = builder.show()

        mAlertDialog.setOnDismissListener {
            mDialogBinding.etvIP1.text.clear()
            mDialogBinding.etvIP2.text.clear()
            mDialogBinding.etvIP3.text.clear()
            mDialogBinding.etvIP4.text.clear()
            mDialogBinding.etvMac1.text.clear()
            mDialogBinding.etvMac2.text.clear()
            mDialogBinding.etvMac3.text.clear()
            mDialogBinding.etvMac4.text.clear()
            mDialogBinding.etvMac5.text.clear()
            mDialogBinding.etvMac6.text.clear()
            mDialogBinding.etvPcName.text.clear()
        }

        // Set button click of custom layout
        mDialogBinding.dialogOKBtn.click {
            val ip = getString(
                R.string.ip_value,
                mDialogBinding.etvIP1.getInput(),
                mDialogBinding.etvIP2.getInput(),
                mDialogBinding.etvIP3.getInput(),
                mDialogBinding.etvIP4.getInput()
            )
            val mac = getString(
                R.string.mac_value,
                mDialogBinding.etvMac1.getInput(),
                mDialogBinding.etvMac2.getInput(),
                mDialogBinding.etvMac3.getInput(),
                mDialogBinding.etvMac4.getInput(),
                mDialogBinding.etvMac5.getInput(),
                mDialogBinding.etvMac6.getInput()
            )

            mDialogBinding.etvPcName.getInput()?.let { pcName ->
                val computer =
                    DataAdapterWalkOnLan.Computer(
                        pcName,
                        ip,
                        mac
                    )
                mAlertDialog.dismiss()

                mJsonDataWalkOnLan?.add(computer)
                val file = File(mContext.filesDir.absolutePath + mDataPath)
                Gson().toJson(mJsonDataWalkOnLan)?.let { data ->
                    file.writeText(data)
                }
            }
        }
        mDialogBinding.dialogCancelBtn.click {
            mAlertDialog.dismiss()
        }
    }
}