package andoird.speedometer.speedometer;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    LocationService myService;
    static boolean status;
    LocationManager locationManager;
    static TextView dist, time, speed, timetext, disttext;
    static long startTime, endTime;
    ImageView image;
    static ProgressDialog locate;
    static int p = 0;
    public SharedPreferences preferences;
    public SharedPreferences preferences2;

    private ServiceConnection sc = new ServiceConnection() {
        public void onServiceConnected(ComponentName name, IBinder service) {
            LocationService.LocalBinder binder = (LocationService.LocalBinder) service;
            myService = binder.getService();
            status = true;
        }

        public void onServiceDisconnected(ComponentName name) {
            status = false;
        }
    };

    public ImageButton androidImmageButton;
    public void init() {
        androidImmageButton = findViewById(R.id.button);
        androidImmageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent toy = new Intent(MainActivity.this, Einstellungen.class);
                startActivity(toy);
            }
        });
    }

    void bindService() {
        if (status == true)
            return;
        Intent i = new Intent(getApplicationContext(), LocationService.class);
        bindService(i, sc, BIND_AUTO_CREATE);
        status = true;
        startTime = System.currentTimeMillis();
    }

    void unbindService() {
        if (status == false)
            return;
        Intent i = new Intent(getApplicationContext(), LocationService.class);
        unbindService(sc);
        status = false;
    }

    protected void onResume() {
        super.onResume();

    }

    protected void onStart() {
        super.onStart();

    }

    protected void onDestroy() {
        super.onDestroy();
        if (status == true)
            unbindService();
    }

    public void onBackPressed() {
        if (status == false)
            super.onBackPressed();
        else
            moveTaskToBack(true);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dist = findViewById(R.id.distancetext);
        time = findViewById(R.id.timetext);
        speed = findViewById(R.id.speedtext);
        disttext = findViewById(R.id.textView3);
        timetext = findViewById(R.id.textView4);

        image = findViewById(R.id.image);

        preferences2 = getSharedPreferences(Einstellungen.SHARED_PREFERENCES2, MODE_PRIVATE);
        int size = preferences2.getInt(Einstellungen.SELECTED_COLOR2, 0);
        speed.setTextSize(size);

        preferences = getSharedPreferences(Einstellungen.SHARED_PREFERENCES, MODE_PRIVATE);
        int color = preferences.getInt(Einstellungen.SELECTED_COLOR, Color.WHITE);
        speed.setTextColor(color);
        time.setTextColor(color);
        dist.setTextColor(color);
        timetext.setTextColor(color);
        disttext.setTextColor(color);
        start();
        init();
    }

            public void start() {
                checkGps();
                locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

                if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

                    return;
                }


                if (status == false)
                    bindService();
                locate = new ProgressDialog(MainActivity.this);
                locate.setIndeterminate(true);
                locate.setCancelable(false);
                locate.setMessage("Suche GPS-Signal");
                locate.show();
            }

    void checkGps() {
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {


            showGPSDisabledAlertToUser();
        }
    }

    private void showGPSDisabledAlertToUser() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Bitte GPS aktivieren")
                .setCancelable(false)
                .setPositiveButton("GPS aktivieren",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent callGPSSettingIntent = new Intent(
                                        android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                startActivity(callGPSSettingIntent);
                            }
                        });
        alertDialogBuilder.setNegativeButton("Abbrechen",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }
}
