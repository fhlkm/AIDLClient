package com.client

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.loop.ILoopService
import com.loop.data.DeviceResult
import com.loop.data.IBindDeviceCallback
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var TAG: String? = "Client MainActivity";
    var mILoopService: ILoopService?=null
    var mIBindCallBack: IBindDeviceCallback? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        R.layout.activity_main


        bind.setOnClickListener{
            attemptToBindService()
        }


    }

    private fun attemptToBindService() {
        val intent = Intent()
        intent.action = "com.loop.aidl"
        intent.setPackage("com.loop");
        bindService(intent,mServiceConnection, Context.BIND_AUTO_CREATE)

    }



    var mServiceConnection: ServiceConnection = object: ServiceConnection{
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            mILoopService = ILoopService.Stub.asInterface(service)
            if(null == mILoopService) {
                Log.e(TAG, "mILoopService is null,Service is cann't bind")

            }else {
                Log.i(TAG, "mILoopService is not null")
                mILoopService!!.bindDevice("afdsafsafsadfsadf", null, object : IBindDeviceCallback.Stub() {
                    override fun onSuccess(result: DeviceResult?) {
                        if (null != result)
                            Log.i(TAG, "IBindDeviceCallback: " + "deviceName: " + result.name + "  deviceBrand: " + result.brand)
                    }

                    override fun onFail(errorCode: Int, result: DeviceResult?) {
                        Log.i(TAG, "IBindDeviceCallback call back failed")

                    }
                })
            }
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            Log.i(TAG, "Service is unbinded")

        }
    }





}
