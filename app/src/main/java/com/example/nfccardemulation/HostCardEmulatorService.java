//package com.example.nfccardemulation;
//
//
//import android.nfc.cardemulation.HostApduService;
//import android.os.Bundle;
//import android.util.Log;
//
//public class HostCardEmulatorService extends HostApduService {
//
//    private static final String TAG = "Host Card Emulator";
//    private static final String STATUS_SUCCESS = "9000";
//    private static final String STATUS_FAILED = "6F00";
//    private static final String CLA_NOT_SUPPORTED = "6E00";
//    private static final String INS_NOT_SUPPORTED = "6D00";
//    private static final String AID = "A0000002471001";
//    private static final String SELECT_INS = "A4";
//    private static final String DEFAULT_CLA = "00";
//    private static final int MIN_APDU_LENGTH = 12;
//
//    @Override
//    public void onDeactivated(int reason) {
//        Log.d(TAG, "Deactivated: $reason");
//    }
//
//     @Override
//    public byte[] processCommandApdu(byte[] commandApdu, Bundle extras) {
//        if (commandApdu == null) {
//            return Utils.Companion.hexStringToByteArray(STATUS_FAILED);
//        }
//
//        String hexCommandApdu = Utils.Companion.toHex(commandApdu);
//        if (hexCommandApdu.length() < MIN_APDU_LENGTH) {
//            return Utils.Companion.hexStringToByteArray(STATUS_FAILED);
//        }
//
//        if (hexCommandApdu.substring(0, 2) != DEFAULT_CLA) {
//            return Utils.Companion.hexStringToByteArray(CLA_NOT_SUPPORTED);
//        }
//
//        if (hexCommandApdu.substring(2, 4) != SELECT_INS) {
//            return Utils.Companion.hexStringToByteArray(INS_NOT_SUPPORTED);
//        }
//
//        if (hexCommandApdu.substring(10, 24) == AID)  {
//            return Utils.Companion.hexStringToByteArray(STATUS_SUCCESS);
//        } else {
//            return Utils.Companion.hexStringToByteArray(STATUS_FAILED);
//        }
//    }
//}