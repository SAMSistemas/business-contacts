package com.samsistemas.sample.utility;

import android.os.StrictMode;

/**
 * @author jonatan.salas
 */
public final class DeveloperUtility {

    private DeveloperUtility() { }

    /**
     * Method used to enable or disable the Strict Mode API, this API is really useful
     * for developers to find applications errors or memory leaks, or long time lose writing to disk, etc.
     *
     * @param enabled true or false, used to enable or disable the Strict Mode API
     */
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
