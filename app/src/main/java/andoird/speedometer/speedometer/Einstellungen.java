package andoird.speedometer.speedometer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.webkit.WebSettings;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

public class Einstellungen extends AppCompatActivity {

    String[] names2 = {"Small", "Normal", "Big"};
    String[] des2 = {"Small", "Normal", "Big"};
    ArrayAdapter<String> adapter2;
    Spinner spinner2;
    TextView description2;
    public static final String SHARED_PREFERENCES2 = "SHARED_PREDS2";
    public static final String SELECTED_COLOR2 = "SELECTED_COLOR2";
    public SharedPreferences preferences2;
    public static final String SELECTED_COLOR_POSITION2 = "SELECTED_COLOR_POSITION2";

    String[] names = {"White", "Blue", "Red", "Green", "Yellow", "Gray", "Cyan", "Magenta"};
    String[] des = {"White", "Blue", "Red", "Green", "Yellow", "Gray", "Cyan", "Magenta"};
    ArrayAdapter<String> adapter;
    Spinner spinner;
    TextView description;
    public static final String SHARED_PREFERENCES = "SHARED_PREFS";
    public static final String SELECTED_COLOR = "SELECTED_COLOR";
    public SharedPreferences preferences;
    public static final String SELECTED_COLOR_POSITION = "SELECTED_COLOR_POSITION";
    public ImageButton androidImmageButton;
    public ImageButton androidImmageButton2;
    public ImageButton androidImmageButton3;

    public Button button;

    public void init() {
        androidImmageButton = findViewById(R.id.button);
        androidImmageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent toy = new Intent(Einstellungen.this, MainActivity.class);
                startActivity(toy);
            }
        });
    }

    public void twitter() {
        androidImmageButton2 = findViewById(R.id.imageButton5);
        androidImmageButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent twitterintent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/Tailor1337"));
                startActivity(twitterintent);
            }
        });
    }

    public void paypal() {
        androidImmageButton3 = findViewById(R.id.imageButton6);
        androidImmageButton3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent paypalinten = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.paypal.me/lucasschneider"));
                startActivity(paypalinten);
            }
        });
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_einstellungen);

        preferences2 = getSharedPreferences(SHARED_PREFERENCES2, MODE_PRIVATE);
        spinner2 = findViewById(R.id.spinner2);
        description2 = findViewById(R.id.text);
        adapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, names2);
        spinner2.setAdapter(adapter2);
        int position2 = preferences2.getInt(SELECTED_COLOR_POSITION2, 0);
        spinner2.setSelection(position2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int d, long l) {
                preferences2.edit().putInt(SELECTED_COLOR_POSITION2, d).apply();
                switch (d) {
                    case 0:
                        description2.setText(des2[d]);
                        preferences2.edit().putInt(SELECTED_COLOR2, 140).apply();
                        break;
                    case 1:
                        description2.setText(des2[d]);
                        preferences2.edit().putInt(SELECTED_COLOR2, 190).apply();
                        break;
                    case 2:
                        description2.setText(des[d]);
                        preferences2.edit().putInt(SELECTED_COLOR2, 240).apply();
                        break;
                }
            }

            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        preferences = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
        spinner = findViewById(R.id.spinner);
        description = findViewById(R.id.text);
        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, names);
        spinner.setAdapter(adapter);
        int position = preferences.getInt(SELECTED_COLOR_POSITION, 0);
        spinner.setSelection(position);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                preferences.edit().putInt(SELECTED_COLOR_POSITION, i).apply();
                switch (i) {
                    case 0:
                        description.setText(des[i]);
                        preferences.edit().putInt(SELECTED_COLOR, Color.WHITE).apply();
                        break;
                    case 1:
                        description.setText(des[i]);
                        preferences.edit().putInt(SELECTED_COLOR, Color.BLUE).apply();
                        break;
                    case 2:
                        description.setText(des[i]);
                        preferences.edit().putInt(SELECTED_COLOR, Color.RED).apply();
                        break;
                    case 3:
                        description.setText(des[i]);
                        preferences.edit().putInt(SELECTED_COLOR, Color.GREEN).apply();
                        break;
                    case 4:
                        description.setText(des[i]);
                        preferences.edit().putInt(SELECTED_COLOR, Color.YELLOW).apply();
                        break;
                    case 5:
                        description.setText(des[i]);
                        preferences.edit().putInt(SELECTED_COLOR, Color.GRAY).apply();
                        break;
                    case 6:
                        description.setText(des[i]);
                        preferences.edit().putInt(SELECTED_COLOR, Color.CYAN).apply();
                        break;
                    case 7:
                        description.setText(des[i]);
                        preferences.edit().putInt(SELECTED_COLOR, Color.MAGENTA).apply();
                        break;
                }
            }

            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        init();
        twitter();
        paypal();
    }
}