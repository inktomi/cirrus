package com.inktomi.cirrus.sample;

import android.app.Dialog;
import android.content.Intent;
import android.content.IntentSender;
import android.location.Location;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationClient;
import com.inktomi.cirrus.CirrusClient;
import com.inktomi.cirrus.R;
import com.inktomi.cirrus.WeatherUtils;
import com.inktomi.cirrus.forecast.WeatherResponse;
import com.inktomi.cirrus.forecast.Data;
import com.inktomi.cirrus.forecast.Parameters;
import com.inktomi.cirrus.forecast.TemperatureValue;

import java.util.Date;
import java.util.List;

/**
 * Retrieves the user's location, and then gets the current conditions and forecast for that location
 * from the Weather.gov National Digital Forecast service.
 *
 * For example:
 * http://graphical.weather.gov/xml/sample_products/browser_interface/ndfdXMLclient.php?lat=36.0397&lon=-114.9811&product=time-series&begin=2013-06-13T00:00:00&end=2013-06-14T10:00:00
 */
public class SampleActivity extends FragmentActivity implements GooglePlayServicesClient.ConnectionCallbacks,
        GooglePlayServicesClient.OnConnectionFailedListener {

    /*
     * Define a request code to send to Google Play services
     * This code is returned in Activity.onActivityResult
     */
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;

    /*
     * Define a location client that will get location updates for us to use.
     */
    private LocationClient mLocationClient;

    /*
     * Define the weather client which we'll use to talk to the National Digital Forecast Database
     */
    private CirrusClient mCirrusClient;

    private TextView mApparentTemperature;
    private TextView mHourlyMaxTemperature;
    private NetworkImageView mConditionsIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
         * Create a new location client, using the enclosing class to
         * handle callbacks.
         */
        mLocationClient = new LocationClient(this, this, this);

        /*
         * Wire up our weather client.
         */
        mCirrusClient = new CirrusClient(this);

        // Get handles to the views.
        mApparentTemperature = (TextView) findViewById(R.id.apparent_temp);
        mHourlyMaxTemperature = (TextView) findViewById(R.id.hourly_max_temp);
        mConditionsIcon = (NetworkImageView) findViewById(R.id.conditions_icon);
    }

    /*
     * Called when the Activity becomes visible.
     */
    @Override
    protected void onStart() {
        super.onStart();

        // Connect the client.
        mLocationClient.connect();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sample, menu);
        return true;
    }

    /*
     * Called when the Activity is no longer visible.
     */
    @Override
    protected void onStop() {
        // Disconnecting the client invalidates it.
        mLocationClient.disconnect();

        super.onStop();
    }

    /*
     * Handle results returned to the FragmentActivity
     * by Google Play services
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Decide what to do based on the original request code
        switch (requestCode) {
            case CONNECTION_FAILURE_RESOLUTION_REQUEST :
            /*
             * If the result code is Activity.RESULT_OK, try
             * to connect again
             */
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        mLocationClient.connect();
                        break;
                }
        }
    }

    public Location getLocation() {

        // If Google Play Services is available
        Location currentLocation = null;
        if (servicesConnected()) {

            // Get the current location
            currentLocation = mLocationClient.getLastLocation();
        }

        return currentLocation;
    }

    private boolean servicesConnected() {
        // Check that Google Play services is available
        final int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);

        // If Google Play services is available
        if (ConnectionResult.SUCCESS == resultCode) {
            // In debug mode, log the status
            Log.d("Location Updates", "Google Play services is available.");
            // Continue
            return true;
        } else {
            // Google Play services was not available for some reason
            // Get the error dialog from Google Play services
            Dialog errorDialog = GooglePlayServicesUtil.getErrorDialog(
                    resultCode,
                    this,
                    CONNECTION_FAILURE_RESOLUTION_REQUEST);

            // If Google Play services can provide an error dialog
            if (errorDialog != null) {
                // Create a new DialogFragment for the error dialog
                ErrorDialogFragment errorFragment = new ErrorDialogFragment();

                // Set the dialog in the DialogFragment
                errorFragment.setDialog(errorDialog);

                // Show the error dialog in the DialogFragment
                errorFragment.show(getSupportFragmentManager(), "Location Updates");
            }
        }

        return false;
    }

    @Override
    public void onConnected(Bundle bundle) {
        // Display the connection status
        Toast.makeText(this, "Connected to Location Service", Toast.LENGTH_SHORT).show();

        Toast.makeText(this, "Location is " + getLocation().getLatitude() + ", " + getLocation().getLongitude(), Toast.LENGTH_LONG).show();

        mCirrusClient.getWeatherForecast(getLocation().getLatitude(), getLocation().getLongitude(),
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SampleActivity.this, "Failed to get a forecast!", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.Listener<WeatherResponse>() {
                    @Override
                    public void onResponse(WeatherResponse response) {
                        Toast.makeText(SampleActivity.this, "Got a forecast!", Toast.LENGTH_SHORT).show();

                        setResponse(response);
                    }
                }
        );
    }

    private void setResponse(WeatherResponse response){
        Data responseData = null;
        if (null != response.data && !response.data.isEmpty()) {
            responseData = response.data.get(0);
        }

        Parameters parameters = null;
        if (null != responseData && null != responseData.parameters && !responseData.parameters.isEmpty() ) {
            parameters = responseData.parameters.get(0);
        }

        // Set the current temperature
        TemperatureValue hourlyTemp = WeatherUtils.getForecastMaximumTemperature(response, new Date());
        mHourlyMaxTemperature.setText(getString(R.string.hourly_max_temp, hourlyTemp.value));

        if( null != parameters ){
            setWeatherConditionIcon(parameters.conditionsIcon);
        } else {
            Toast.makeText(SampleActivity.this, "Got a forecast, but couldn't use it.", Toast.LENGTH_SHORT).show();
        }
    }

    private void setWeatherConditionIcon(List<Parameters.ConditionsIcon> conditionsIcon){
        if( null != conditionsIcon && !conditionsIcon.isEmpty() ){
            // Take the first icon, since our sample date range is small.
            ImageLoader imageLoader = new ImageLoader(Volley.newRequestQueue(this), new BitmapLruCache(10485760)); // 10 meg cache.

            if( !conditionsIcon.get(0).iconLink.isEmpty() ){
                mConditionsIcon.setImageUrl(conditionsIcon.get(0).iconLink.get(0), imageLoader);
            }
        }
    }

    @Override
    public void onDisconnected() {
        // Display the connection status
        Toast.makeText(this, "Disconnected from Location Service", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        /*
         * Google Play services can resolve some errors it detects.
         * If the error has a resolution, try sending an Intent to
         * start a Google Play services activity that can resolve
         * error.
         */
        if (connectionResult.hasResolution()) {
            try {
                // Start an Activity that tries to resolve the error
                connectionResult.startResolutionForResult(
                        this,
                        CONNECTION_FAILURE_RESOLUTION_REQUEST);
                /*
                 * Thrown if Google Play services canceled the original
                 * PendingIntent
                 */
            } catch (IntentSender.SendIntentException e) {
                // Log the error
                Log.e("SampleActivity", "Failed to start resolution activity for play services error", e);
            }
        } else {
            /*
             * If no resolution is available, display a dialog to the
             * user with the error.
             */

            // Google Play services was not available for some reason
            // Get the error dialog from Google Play services
            Dialog errorDialog = GooglePlayServicesUtil.getErrorDialog(
                    connectionResult.getErrorCode(),
                    this,
                    CONNECTION_FAILURE_RESOLUTION_REQUEST);

            // If Google Play services can provide an error dialog
            if (errorDialog != null) {
                // Create a new DialogFragment for the error dialog
                ErrorDialogFragment errorFragment = new ErrorDialogFragment();

                // Set the dialog in the DialogFragment
                errorFragment.setDialog(errorDialog);

                // Show the error dialog in the DialogFragment
                errorFragment.show(getSupportFragmentManager(), "Play Store Connection");
            }
        }
    }

    // Define a DialogFragment that displays the error dialog
    public static class ErrorDialogFragment extends DialogFragment {
        // Global field to contain the error dialog
        private Dialog mDialog;
        // Default constructor. Sets the dialog field to null
        public ErrorDialogFragment() {
            super();
            mDialog = null;
        }
        // Set the dialog to display
        public void setDialog(Dialog dialog) {
            mDialog = dialog;
        }
        // Return a Dialog to the DialogFragment.
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return mDialog;
        }
    }
    
}
