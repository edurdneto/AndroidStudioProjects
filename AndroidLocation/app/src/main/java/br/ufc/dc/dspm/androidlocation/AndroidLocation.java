package br.ufc.dc.dspm.androidlocation;

import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AndroidLocation extends Activity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private TextView addressLabel;
    private TextView locationLabel;
    private TextView pos1;
    private TextView pos2;
    private TextView pos3;
    private TextView pos4;
    private GoogleApiClient googleApiClient;
    private LatLng vertice1;
    private LatLng vertice2;
    private LatLng vertice3;
    private LatLng vertice4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_location);
        this.addressLabel = (TextView) findViewById(R.id.addressTextView);
        this.locationLabel = (TextView) findViewById(R.id.locationTextView);
        this.pos1 = (TextView) findViewById(R.id.textView);
        this.pos2 = (TextView) findViewById(R.id.textView2);
        this.pos3 = (TextView) findViewById(R.id.textView3);
        this.pos4 = (TextView) findViewById(R.id.textView4);

        vertice1 = new LatLng(0,0);
        vertice2 = new LatLng(0,0);
        vertice3 = new LatLng(0,0);
        vertice4 = new LatLng(0,0);

        GoogleApiClient.Builder builder = new GoogleApiClient.Builder(this);
        builder.addApi(LocationServices.API);
        builder.addConnectionCallbacks(this);
        builder.addOnConnectionFailedListener(this);
        googleApiClient = builder.build();
    }

    @Override
    protected void onStart() {
        super.onStart();
        googleApiClient.connect();
        locationLabel.setText("Got connected...");
    }

    @Override
    protected void onStop() {
        googleApiClient.disconnect();
        locationLabel.setText("Got disconnected...");
        super.onStop();

    }

    public void doConnect(View view) {
        googleApiClient.connect();
        locationLabel.setText("Got connected...");
    }

    public void doDisconnect(View view) {
        googleApiClient.disconnect();
        locationLabel.setText("Got disconnected...");
    }

    public void getLocation1(View view) {
        Location location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        String text = "Location = <" + location.getLatitude() + "," + location.getLongitude() + ">";
        this.pos1.setText(text);
        vertice1.setLatitude(location.getLatitude());
        vertice1.setLongitude(location.getLongitude());
        System.out.println("1:" + text);
    }

    public void getLocation2(View view) {
        Location location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        String text = "Location = <" + location.getLatitude() + "," + location.getLongitude() + ">";
        this.pos2.setText(text);
        vertice2.setLatitude(location.getLatitude());
        vertice2.setLongitude(location.getLongitude());
        System.out.println("2:" + text);
    }

    public void getLocation3(View view) {
        Location location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        String text = "Location = <" + location.getLatitude() + "," + location.getLongitude() + ">";
        this.pos3.setText(text);
        vertice3.setLatitude(location.getLatitude());
        vertice3.setLongitude(location.getLongitude());
        System.out.println("3:" + text);
    }

    public void getLocation4(View view) {
        Location location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        String text = "Location = <" + location.getLatitude() + "," + location.getLongitude() + ">";
        this.pos4.setText(text);
        vertice4.setLatitude(location.getLatitude());
        vertice4.setLongitude(location.getLongitude());
        System.out.println("4:"+text);
    }

    public void getLocation(View view) {
        Location location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        String text = "Location = <" + location.getLatitude() + "," + location.getLongitude() + ">";
        this.locationLabel.setText(text);
        GetAddressTask task = new GetAddressTask(this);
        task.execute(location);
        LatLng vertice = new LatLng(location.getLatitude(),location.getLongitude());
        ArrayList<LatLng>vertices=new ArrayList<>();
        vertices.add(vertice1);
        vertices.add(vertice2);
        vertices.add(vertice3);
        vertices.add(vertice4);
        if(isPointInPolygon(vertice,vertices)){
            Toast.makeText(getApplicationContext(),"Dentro da Area",Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(),"Fora da Area",Toast.LENGTH_LONG).show();
        }
    }

    public void doSubscribe(View view) {
        if (googleApiClient.isConnected()) {
            LocationRequest request = new LocationRequest();
            request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            request.setInterval(5000);
            request.setSmallestDisplacement(2);

            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, request, this);
        }
    }

    public void doUnsubscribe(View view) {
        if (googleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        Toast.makeText(this, "Connected!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onConnectionSuspended(int value) {
        Toast.makeText(this, "Disconnected!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        Toast.makeText(this, "Connection failed...", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLocationChanged(Location location) {
        String text = "Updated Location = <" + location.getLatitude() + "," + location.getLongitude() + ">";
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    private class GetAddressTask extends AsyncTask<Location, Void, String> {
        private Context context;

        public GetAddressTask(Context context) {
            super();
            this.context = context;
        }

        @Override
        protected void onPostExecute(String address) {
            addressLabel.setText(address);
        }

        @Override
        protected String doInBackground(Location... params) {
            Geocoder geocoder = new Geocoder(context, Locale.getDefault());
            Location location = params[0];
            List<Address> addresses;
            try {
                addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            } catch (IOException ioe) {
                Log.e("GetAddressTask", "IO Exception in getFromLocation()");
                ioe.printStackTrace();
                return "IO Exception trying to get address";
            } catch (IllegalArgumentException iae) {
                String errorString = "Illegal arguments " + Double.toString(location.getLatitude()) + " , " + Double.toString(location.getLongitude()) + " passed to address service";
                Log.e("GetAddressTask", errorString);
                iae.printStackTrace();
                return errorString;
            }
            if (addresses != null && addresses.size() > 0) {
                Address address = addresses.get(0);
                String addressText = address.getAddressLine(0) + ", " + address.getAdminArea() + ", " + address.getCountryCode();
                return addressText;
            } else {
                return "No address found!";
            }
        }
    }

    private boolean isPointInPolygon(LatLng tap, ArrayList<LatLng> vertices) {
        int intersectCount = 0;
        for (int j = 0; j < vertices.size() - 1; j++) {
            if (rayCastIntersect(tap, vertices.get(j), vertices.get(j + 1))) {
                intersectCount++;
            }
        }

        return ((intersectCount % 2) == 1); // odd = inside, even = outside;
    }

    private boolean rayCastIntersect(LatLng tap, LatLng vertA, LatLng vertB) {

        double aY = vertA.latitude;
        double bY = vertB.latitude;
        double aX = vertA.longitude;
        double bX = vertB.longitude;
        double pY = tap.latitude;
        double pX = tap.longitude;

        if ((aY > pY && bY > pY) || (aY < pY && bY < pY)
                || (aX < pX && bX < pX)) {
            return false; // a and b can't both be above or below pt.y, and a or
            // b must be east of pt.x
        }

        double m = (aY - bY) / (aX - bX); // Rise over run
        double bee = (-aX) * m + aY; // y = mx + b
        double x = (pY - bee) / m; // algebra is neat!

        return x > pX;
    }
}
