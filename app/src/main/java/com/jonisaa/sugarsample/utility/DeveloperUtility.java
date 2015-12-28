package com.jonisaa.sugarsample.utility;

import android.os.StrictMode;

/**
 * @author jonatan.salas
 */
public final class DeveloperUtility {

    private DeveloperUtility() { }

    public static void enableStrictModeApi(boolean enabled) {
        if (enabled) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectDiskReads()
                    .detectDiskWrites()
                    .detectNetwork()  // or .detectAll() for all detectable problems
                    .penaltyLog()
                    .build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                    .detectLeakedSqlLiteObjects()
                    .penaltyLog()
                    .penaltyDeath()
                    .build());
        }
    }
}
