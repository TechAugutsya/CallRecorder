package com.example.callrecorder

import android.content.Context

import com.example.callrecord.CallRecord
import com.example.callrecord.receiver.CallRecordReceiver

import java.util.Date

class MyCallRecordReceiver(callRecord: CallRecord) : CallRecordReceiver(callRecord) {


    override fun onIncomingCallReceived(context: Context, number: String?, start: Date) {
        super.onIncomingCallReceived(context, number, start)
    }
}