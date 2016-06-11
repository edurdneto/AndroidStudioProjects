package br.ufc.lsbd.eduardo.tp13_q2;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private GoogleMap mMap;
    private GoogleApiClient googleApiClient;
    private Location lastLocation;
    LatLng lastLatLng=new LatLng(0,0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        GoogleApiClient.Builder builder = new GoogleApiClient.Builder(this);
        builder.addApi(LocationServices.API);
        builder.addConnectionCallbacks(this);
        builder.addOnConnectionFailedListener(this);
        builder.addApi(LocationServices.API);
        googleApiClient = builder.build();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    protected void onStart() {
        super.onStart();
        googleApiClient.connect();

    }

    @Override
    protected void onStop() {
        googleApiClient.disconnect();
        super.onStop();

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

    }

    @Override
    public void onConnected(Bundle bundle) {
        lastLocation = LocationServices.FusedLocationApi.getLastLocation(
                googleApiClient);
        LatLng sydney = new LatLng(lastLocation.getLatitude(),lastLocation.getLongitude());
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        createLocationRequest();
       // mMap.addMarker(new MarkerOptions().position(sydney).title("You are here!"));
        //CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(16).build();
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        //mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        Toast.makeText(this, "Connected!", Toast.LENGTH_LONG).show();
        LatLng newPos = new LatLng(lastLocation.getLatitude(),lastLocation.getLongitude());
        String text = "Updated Location = <" + lastLocation.getLatitude() + "," + lastLocation.getLongitude() + ">";
        if(distFrom(lastLatLng,newPos)>=100){
            System.out.println("NAO ENTREI AQUI");
            lastLatLng=new LatLng(newPos.latitude,newPos.longitude);
            mMap.addMarker(new MarkerOptions().position(lastLatLng));
            CameraPosition cameraPosition = new CameraPosition.Builder().target(lastLatLng).zoom(16).build();
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            Toast.makeText(this, text, Toast.LENGTH_LONG).show();
        }


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
        LatLng newPos = new LatLng(location.getLatitude(),location.getLongitude());
        if(distFrom(lastLatLng,newPos)>=100){
            lastLatLng=new LatLng(newPos.latitude,newPos.longitude);
            mMap.addMarker(new MarkerOptions().position(lastLatLng));
        }
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
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

    protected void createLocationRequest() {
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(mLocationRequest);
        PendingResult<LocationSettingsResult> result =
                LocationServices.SettingsApi.checkLocationSettings(googleApiClient,
                        builder.build());

    }


    public static double distFrom(LatLng x, LatLng y) {
        double earthRadius = 6371000; //meters
        double dLat = Math.toRadians(x.latitude-y.latitude);
        double dLng = Math.toRadians(x.longitude-y.longitude);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(Math.toRadians(x.latitude)) * Math.cos(Math.toRadians(y.latitude)) *
                        Math.sin(dLng/2) * Math.sin(dLng/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double dist = (double) (earthRadius * c);

        return dist;
    }
}
