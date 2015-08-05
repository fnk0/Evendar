package com.gabilheri.choresapp.sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 8/4/15.
 */
public class ChoresSyncService extends Service {

    private static final Object sSyncAdapterLock = new Object();
    private static ChoresSyncAdapter sChoresSyncAdapter = null;

    @Override
    public void onCreate() {
        synchronized (sSyncAdapterLock) {
            if(sChoresSyncAdapter == null) {
                sChoresSyncAdapter = new ChoresSyncAdapter(getApplicationContext(), true);
            }
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return sChoresSyncAdapter.getSyncAdapterBinder();
    }
}
