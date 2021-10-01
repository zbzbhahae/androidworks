package com.zb.aidl2.utils;

import android.os.Process;

public class ProcessUtils {

    public static int pid() {
        return Process.myPid();
    }
}
