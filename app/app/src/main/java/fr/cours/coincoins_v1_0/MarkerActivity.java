package fr.cours.coincoins_v1_0;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.owlike.genson.Genson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import fr.cours.coincoins_v1_0.entities.Corner;


public class MarkerActivity extends Activity implements LocationListener {

    private static final int PERMISSION_CODE = 1000;
    private static final int IMAGE_CAPTURE_CODE = 1001;
    private static final String MYTAG = "TAG";

    private ImageView photoview;
    private EditText textTitle;
    private EditText textResume;
    private EditText textImg;
    private EditText textLat;
    private EditText textLng;
    private EditText textUserId;

    Uri imageUri;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_marker);
        photoview = findViewById(R.id.imageView);
        textTitle = findViewById(R.id.textTitle);
        textResume = findViewById(R.id.textResume);
        textImg = findViewById(R.id.textImg);
        textLat = findViewById(R.id.textLat);
        textLng = findViewById(R.id.textLng);
        textUserId = findViewById(R.id.textUserId);
        Button btnPost = findViewById(R.id.button_post);


        showMyLocation();
        openCamera();

        textImg.setText(imageUri.getPath());
        textUserId.setText("1");

        btnPost.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.i(MYTAG + " ONCLICK","Click button" );
                postCorner();
            }
        });
    }

    protected void postCorner() {
        Log.i(MYTAG + " MarkerActivity","POST NEW MARKER" );
        new Thread(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void run() {
                Corner mark = new Corner(
                    textTitle.getText().toString(),
                    textResume.getText().toString(),
                    textImg.getText().toString(),
                    Double.parseDouble(textLat.getText().toString()),
                    Double.parseDouble(textLng.getText().toString()),
                    Integer.parseInt(textUserId.getText().toString())
                );

                String message = new Genson().serialize(mark);

                HttpURLConnection urlConnection = null;
                try {
                    URL url = new URL("http://192.168.1.18:6253/api/marker/add");
                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setDoOutput( true );
                    urlConnection.setRequestMethod("POST");
                    urlConnection.setRequestProperty("Content-Type", "application/json; UTF-8");
                    urlConnection.setRequestProperty("Accept", "application/json");

                    OutputStream out = urlConnection.getOutputStream();
                    out.write(message.getBytes(StandardCharsets.UTF_8));
                    out.close();

                    if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))) {
                            String line;
                            while ((line = bufferedReader.readLine()) != null) {
                                Log.i(MYTAG + " ONCLICK","Line == " + line);
                            }
                        }
                    } else {
                        Log.i(MYTAG + " ONCLICK","urlConnection.getResponseCode == BAD_REQUEST"+ urlConnection.getResponseCode());

                    }

                    Intent i = new Intent(MarkerActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();

                }catch (Exception e) {
                    Log.e(MYTAG + " ONCLICK", "Cannot found http server", e);
                }finally {
                    if ( urlConnection != null ) urlConnection.disconnect();
                }
            }
        }).start();

    }

    // Récupération de la latitude et longitude et les inserent dans les input
    private void showMyLocation() {

        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        String locationProvider = this.getEnabledLocationProvider();

        if (locationProvider == null) {
            return;
        }

        // Millisecond
        final long MIN_TIME_BW_UPDATES = 1000;
        // Met
        final float MIN_DISTANCE_CHANGE_FOR_UPDATES = 1;

        Location myLocation = null;

        try {
            // This code need permissions (Asked above ***)
            locationManager.requestLocationUpdates(
                    locationProvider,
                    MIN_TIME_BW_UPDATES,
                    MIN_DISTANCE_CHANGE_FOR_UPDATES, (LocationListener) this);
            // Getting Location.
            myLocation = locationManager
                    .getLastKnownLocation(locationProvider);
        }
        // With Android API >= 23, need to catch SecurityException.
        catch (SecurityException e) {
            Toast.makeText(this, "Show My Location Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
            return;
        }
        Log.i("showMyLocation", "myLocation == " + myLocation );
        if (myLocation != null) {
            Log.i("showMyLocation", "textLat == " + textLat );
            Log.i("showMyLocation", "textLng == " + textLng );

            textLat.setText( "" + myLocation.getLatitude());
            textLng.setText( "" + myLocation.getLongitude());

        } else {
            Toast.makeText(this, "Location not found!", Toast.LENGTH_LONG).show();
        }
    }

    // Ouvre l'appareil photo
    private void openCamera() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "New Picture");
        values.put(MediaStore.Images.Media.DESCRIPTION, "From the Camera");

        imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(cameraIntent, IMAGE_CAPTURE_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //
        switch (requestCode) {

            case PERMISSION_CODE: {
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openCamera();
                } else {
                    Toast.makeText(this, "Permission denied...", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    // Après la photo on affiche l'image prise dans le imageView
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

//        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            photoview.setImageURI(imageUri);
        }
    }


    private String getEnabledLocationProvider() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        // Criteria to find location provider.
        Criteria criteria = new Criteria();

        // Returns the name of the provider that best meets the given criteria.
        // ==> "gps", "network",...
        String bestProvider = locationManager.getBestProvider(criteria, true);

        boolean enabled = locationManager.isProviderEnabled(bestProvider);

        if (!enabled) {
            Toast.makeText(this, "No location provider enabled!", Toast.LENGTH_LONG).show();
            return null;
        }
        return bestProvider;
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
