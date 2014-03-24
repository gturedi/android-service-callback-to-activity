package sagnak.service.callback;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

/**
 * Created by HP on 24.03.2014.
 */
public class DownloadService extends Service{

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(" ", "onStartCommand");

        // farklı thread'e çıkarak asenkron işlem başlatılır
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String result = null;
                    // uzun süreli işlem yapılır; dosya indirme, hesaplama vs.
                    // gecikmeyi simule edelim
                    Thread.sleep(3000);

                    // temsili islem sonucu
                    result = "tek gercek fenerbahce :]";

                    // işlem bitti sonucu takipçi activity'e bildirelim
                    LocalBroadcastManager.getInstance(getBaseContext()).sendBroadcast(
                            new Intent(MainActivity.FILTER).putExtra(MainActivity.KEY, result)
                    );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        return super.onStartCommand(intent, flags, startId);
    }

}
