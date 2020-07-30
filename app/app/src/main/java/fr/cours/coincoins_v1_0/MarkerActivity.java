package fr.cours.coincoins_v1_0;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.content.FileProvider;
//import androidx.core.content.FileProvider;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.owlike.genson.Genson;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import fr.cours.coincoins_v1_0.entities.Corner;
import fr.cours.coincoins_v1_0.ws.MarkerWs;
import fr.cours.coincoins_v1_0.ws.RetrofitSingleton;
import fr.cours.coincoins_v1_0.ws.WSInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MarkerActivity extends Activity implements LocationListener {

    private static final int PERMISSION_CODE = 1000;
    private static final int IMAGE_CAPTURE_CODE = 1001;
    private static final String MYTAG = "TEST";

    private ImageView photoview;
    private EditText textTitle;
    private EditText textResume;
    private EditText textImg;
    private EditText textLat;
    private EditText textLng;
    private EditText textUserId;

    Uri imageUri;
    String currentPhotoPath;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_marker);
        init();
        showMyLocation();
        openCamera();

    }

    private void init(){
        photoview = findViewById(R.id.imageView);
        textTitle = findViewById(R.id.textTitle);
        textResume = findViewById(R.id.textResume);
        textImg = findViewById(R.id.textImg);
        textLat = findViewById(R.id.textLat);
        textLng = findViewById(R.id.textLng);
        textUserId = findViewById(R.id.textUserId);
        Button btnPost = findViewById(R.id.button_post);

        textUserId.setText("1");

        btnPost.setOnClickListener(v -> {
            try{
//                        postImage();
                textImg.setText(imageUri.getPath());
                postCorner();

            } catch (Exception e ) {
                Log.i(MYTAG + " ONCLICK", "erreur", e);
            }
        });
    }
//    private boolean postImage() {
//        Log.i(MYTAG + " UPLOAD IMG","Start");
//        final boolean[] result = {false};
//        new Thread(new Runnable() {
//            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//            @Override
//            public void run() {
//                HttpURLConnection urlConnection = null;
//                String fileName = image.toString();
//                try {
//
//                    String message = new Genson().serialize(fileToUpload);
//                    Log.i(MYTAG + " UPLOAD IMG","message == " + message);
//
//                    URL url = new URL("http://192.168.1.18:8080/api/uploadimg");
//                    urlConnection = (HttpURLConnection) url.openConnection();
//                    urlConnection.setDoOutput( true );
//                    urlConnection.setRequestMethod("POST");
//                    urlConnection.setRequestProperty("Content-Type", "multipart/form-data");
//                    urlConnection.setRequestProperty("ENCTYPE", "multipart/form-data");
//                    urlConnection.setRequestProperty("uploaded_file", fileName);
//
//                    OutputStream out = urlConnection.getOutputStream();
//                    out.write(message.getBytes());
//                    out.close();
//
//                    if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
//                        result[0] = true;
//                        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))) {
//                            String line;
//                            while ((line = bufferedReader.readLine()) != null) {
//                                Log.i(MYTAG + " UPLOAD IMG", "Line == " + line);
//                            }
//                        }
//                    } else {
//                        Log.i(MYTAG + " UPLOAD IMG","urlConnection.getResponseCode == BAD_REQUEST"+ urlConnection.getResponseCode());
//                    }
//
//                }catch (Exception e) {
//                    Log.e(MYTAG + " UPLOAD IMG", "Cannot found http server", e);
//                }finally {
//                    if ( urlConnection != null ) urlConnection.disconnect();
//                }
//            }
//        }).start();
//        return result[0];
//    }

    void postCorner() {
        Log.i(MYTAG + " postCorner","POST NEW MARKER" );
        textImg.setText(imageUri.getPath());

        WSInterface service = RetrofitSingleton.getRetrofitInstance().create(WSInterface.class);
        MarkerWs marker = new MarkerWs(textTitle.getText().toString(),
                textResume.getText().toString(),
                textImg.getText().toString(),
                Double.parseDouble(textLat.getText().toString()),
                Double.parseDouble(textLng.getText().toString()),
                Integer.parseInt(textUserId.getText().toString())
            );
        Call<MarkerWs> call = service.postMaker(marker);

        call.enqueue(new Callback<MarkerWs>() {
             @Override
             public void onResponse(Call<MarkerWs> call, Response<MarkerWs> response) {
                 Log.d(MYTAG + " postCorner","post");
                 if (response.isSuccessful()) {
                     Toast.makeText(MarkerActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                 } else {
                     Toast.makeText(MarkerActivity.this, "OK", Toast.LENGTH_SHORT).show();
                 }
                 Intent i = new Intent(MarkerActivity.this, MainActivity.class);
                 startActivity(i);
                 finish();
             }

             @Override
             public void onFailure(Call<MarkerWs> call, Throwable t) {

                 Toast.makeText(MarkerActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                 Intent i = new Intent(MarkerActivity.this, MainActivity.class);
                 startActivity(i);
                 finish();
             }
        });
//            new Thread(new Runnable() {
//            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//            @Override
//            public void run() {
//                Corner mark = new Corner(
//                    textTitle.getText().toString(),
//                    textResume.getText().toString(),
//                    textImg.getText().toString(),
//                    Double.parseDouble(textLat.getText().toString()),
//                    Double.parseDouble(textLng.getText().toString()),
//                    Integer.parseInt(textUserId.getText().toString())
//                );
//
//                String message = new Genson().serialize(mark);
//
//                HttpURLConnection urlConnection = null;
//                try {
//                    URL url = new URL("http://192.168.1.18:8080/api/marker/add");
//                    urlConnection = (HttpURLConnection) url.openConnection();
//                    urlConnection.setDoOutput( true );
//                    urlConnection.setRequestMethod("POST");
//                    urlConnection.setRequestProperty("Content-Type", "application/json; UTF-8");
//                    urlConnection.setRequestProperty("Accept", "application/json");
//
//                    OutputStream out = urlConnection.getOutputStream();
//                    out.write(message.getBytes(StandardCharsets.UTF_8));
//                    out.close();
//
//                    if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
//                        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))) {
//                            String line;
//                            while ((line = bufferedReader.readLine()) != null) {
//                                Log.i(MYTAG + " ONCLICK","Line == " + line);
//                            }
//                        }
//                    } else {
//                        Log.i(MYTAG + " postCorner","urlConnection.getResponseCode == BAD_REQUEST"+ urlConnection.getResponseCode());
//
//                    }
//
//                    Intent i = new Intent(MarkerActivity.this, MainActivity.class);
//                    startActivity(i);
//                    finish();
//
//                }catch (Exception e) {
//                    Log.e(MYTAG + " postCorner", "Cannot found http server", e);
//                }finally {
//                    if ( urlConnection != null ) urlConnection.disconnect();
//                }
//            }
//        }).start();

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
        /*Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
//            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "fr.cours.coincoins_v1_0.provider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, IMAGE_CAPTURE_CODE);
            }

        }*/

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.TITLE, "New Picture");
            values.put(MediaStore.Images.Media.DESCRIPTION, "From the Camera");

            imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            startActivityForResult(cameraIntent, IMAGE_CAPTURE_CODE);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "PNG_" + timeStamp + "";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".png",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
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
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGE_CAPTURE_CODE && resultCode == RESULT_OK) {
//            Bundle extras = data.getExtras();
 //           Bitmap imageBitmap = (Bitmap) extras.get("data");
//            photoview.setImageBitmap(imageBitmap);
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
