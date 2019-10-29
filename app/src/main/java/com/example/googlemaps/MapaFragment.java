package com.example.googlemaps;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.DecimalFormat;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MapaFragment extends Fragment implements OnMapReadyCallback, LocationListener {
    GoogleMap gMap;
    Location location;
    Location location2;

    LocationManager lm;
    Spinner spinner1;
    Spinner spinner2;
    Spinner spinner3;
    ArrayAdapter adapter1;
    ArrayAdapter adapter2;
    ArrayAdapter adapter3;
    ArrayList<String> Tipo;
    ArrayList<String> Icono;
    ArrayList<String> Localizacion;


    Double japonLa,japonLo;
    Double   AlemaniaLa,AlemaniaLo;
    Double ItaliaLa,ItaliaLo;
    Double FranciaLa,FranciaLo;


    Button btn;
    TextView txtlatitud1,txtlngitud1;
    TextView txtlatitud2,txtlongitud2;
TextView txtdistancia;
    public MapaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_mapa, container, false);
        btn=(Button)v.findViewById(R.id.btnir);

        txtlatitud1=(TextView)v.findViewById(R.id.actuallatitud);
        txtlngitud1=(TextView)v.findViewById(R.id.actuallongitud);

        txtlatitud2=(TextView)v.findViewById(R.id.idlatitudotro);
        txtlongitud2=(TextView)v.findViewById(R.id.idlongitudotro);

txtdistancia=(TextView)v.findViewById(R.id.txtidscnacia);

        Tipo=new ArrayList<String>();
        Tipo.add("Normal");
        Tipo.add("Satelite");
        Tipo.add("Hibrido");
        Tipo.add("Terrian");

        Icono=new ArrayList<String>();
        Icono.add("Normal");
        Icono.add("Mundo");
        Icono.add("Satelite");
        Icono.add("Montaña");
        Icono.add("Planos");

        Localizacion=new ArrayList<String>();
        Localizacion.add("Japon");
        Localizacion.add("Alemania");
        Localizacion.add("Italia");
        Localizacion.add("Francia");
        spinner1 =(Spinner)v.findViewById(R.id.spiner1);
        spinner2 =(Spinner)v.findViewById(R.id.spiner2);
        spinner3 =(Spinner)v.findViewById(R.id.spiner3);

        adapter1= new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_item,Tipo);
        spinner1.setAdapter(adapter1);

        adapter2= new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_item,Icono);
        spinner2.setAdapter(adapter2);

        adapter3= new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_item,Localizacion);
        spinner3.setAdapter(adapter3);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Normal(gMap,spinner1.getSelectedItem().toString(),spinner2.getSelectedItem().toString(),spinner3.getSelectedItem().toString());
            }
        });
        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        return v;

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;
//LOCATION_SERVICE
        lm = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);

        if (getActivity().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && getActivity().checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {


            return;
        }
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0,  this);
        location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        double longitude = location.getLongitude();
        double latitude = location.getLatitude();

        txtlatitud1.setText(String.valueOf(latitude));
        txtlngitud1.setText(String.valueOf(longitude));

     //   gMap.setMyLocationEnabled(true);
       // gMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);


        LatLng aquitoy = new LatLng(latitude, longitude);

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(aquitoy)
                .zoom(14)//esto es el zoom
                .bearing(30)//esto es la inclonacion
                .build();


        gMap.addMarker(new MarkerOptions().position(aquitoy).title("Aqui estoy wey"));
        gMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


    }

    public void Normal(GoogleMap googleMap,String Tipo,String Icono,String Lugar) {

        double longitude=0;
        double latitude=0;
        gMap = googleMap;
        gMap.clear();
        //LOCATION_SERVICE
        lm = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);

        if (getActivity().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && getActivity().checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0,  this);
        location2 = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        //japonLa,japonLo;
       // AlemaniaLa,AlemaniaLo
       // ItaliaLa,ItaliaLo;
       // FranciaLa,FranciaLo;
             if (Lugar.equals("Japon"))
        {

             longitude = 120.2463432;
             latitude   =31.7114568;

          //  Location location2 = new Location("localizacion 2");
            location2.setLatitude(latitude);
            location2.setLongitude(longitude);

             txtlatitud2.setText(String.valueOf(latitude));
             txtlongitud2.setText(String.valueOf(longitude));

            double distance = location.distanceTo(location2);
         //   txtdistancia.setText(String.valueOf(distance));
            CalculationByDistance(Double.parseDouble(txtlatitud1.getText().toString()),Double.parseDouble(txtlngitud1.getText().toString()), latitude,longitude);

        }

        if (Lugar.equals("Alemania"))
        {


            longitude = 8.4964783;
            latitude = 50.8445634;
            txtlatitud2.setText(String.valueOf(latitude));
            txtlongitud2.setText(String.valueOf(longitude));
           CalculationByDistance(Double.parseDouble(txtlatitud1.getText().toString()),Double.parseDouble(txtlngitud1.getText().toString()), latitude,longitude);

        }


        if (Lugar.equals("Italia"))
        {

             longitude = 11.9819687;
             latitude = 43.2415807;
            txtlatitud2.setText(String.valueOf(latitude));
            txtlongitud2.setText(String.valueOf(longitude));
            CalculationByDistance(Double.parseDouble(txtlatitud1.getText().toString()),Double.parseDouble(txtlngitud1.getText().toString()), latitude,longitude);

        }

        if (Lugar.equals("Francia"))
        {


             longitude = 2.3067781;
             latitude = 46.934042;
            txtlatitud2.setText(String.valueOf(latitude));
            txtlongitud2.setText(String.valueOf(longitude));
            CalculationByDistance(Double.parseDouble(txtlatitud1.getText().toString()),Double.parseDouble(txtlngitud1.getText().toString()), latitude,longitude);

        }

        LatLng aquitoy = new LatLng(latitude, longitude);

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(aquitoy)
                .zoom(14)//esto es el zoom
                .bearing(30)//esto es la inclonacion
                .build();

        gMap.addMarker(new MarkerOptions().position(aquitoy).title("Aqui estoy wey"));
        gMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        if (Tipo.equals("Hibrido"))
                {
                    gMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            }

        if (Tipo.equals("Terrian"))
        {
            gMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        }
        if (Tipo.equals("Satelite"))
        {
            gMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        }

        if (Tipo.equals("Normal"))
        {
            gMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        }

        if (Icono.equals("Mundo"))
        {
               gMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.mundo)).anchor(0.0f,1.0f).position(aquitoy));
        }

        if (Icono.equals("Satelite"))
        {
           // gMap.clear();
            gMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.sa)).anchor(0.0f,1.0f).position(aquitoy));
        }

        if (Icono.equals("Montaña"))
        {
            // gMap.clear();
            gMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.monta)).anchor(0.0f,1.0f).position(aquitoy));
        }

    }


    public double CalculationByDistance(double StartP, double EndP,double latt2,double log2) {
        int Radius = 6371;// radio de la tierra en  kilómetros
        double lat1 = StartP;
        double lon1 = EndP;

        double lat2 = latt2;

        double lon2 = log2;


        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
                * Math.sin(dLon / 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double valueResult = Radius * c;
        double km = valueResult / 1;
        DecimalFormat newFormat = new DecimalFormat("####");
        int kmInDec = Integer.valueOf(newFormat.format(km));
        double meter = valueResult % 1000;
        int meterInDec = Integer.valueOf(newFormat.format(meter));
        Log.e("Radius Value", "" + valueResult + "   KM  " + kmInDec
                + " Meter   " + meterInDec);

        txtdistancia.setText(String.valueOf(kmInDec)+" KM");
        return Radius * c;
    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            Log.v("Localizacion", location.getLatitude() + " y " + location.getLongitude());
            lm.removeUpdates(this);
        }
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
