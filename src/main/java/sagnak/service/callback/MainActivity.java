package sagnak.service.callback;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity {

    public static final String FILTER = "just.a.filter";
    public static final String KEY = "key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // service'ten yayınlanacak olan broadcast'i dinliyoruz
        LocalBroadcastManager.getInstance(this).registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                // yanıt için geri bildirim
                Log.w("servisten gelen cevap", intent.getStringExtra(KEY));
                Toast.makeText(MainActivity.this, intent.getStringExtra(KEY), Toast.LENGTH_SHORT).show();
            }
        }, new IntentFilter(FILTER));

        // işlem yapacak olan service'i başlatıyoruz
        startService(new Intent(this, DownloadService.class));
    }

}

