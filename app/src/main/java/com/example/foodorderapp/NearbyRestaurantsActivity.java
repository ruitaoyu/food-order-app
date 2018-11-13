package com.example.foodorderapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class NearbyRestaurantsActivity extends AppCompatActivity {

    public static final String SELECTED_RESTAURANT = "com.example.foodorderapp.SELECTEDRESTUARANT";

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private Context myContext;

    private static final LatLng UWT = new LatLng(47.244663, -122.437646);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nearby_restaurants);

        myContext = this;
        mLinearLayoutManager = new LinearLayoutManager(this);

        // Set up the RecyclerView
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(new MapAdapter(LIST_LOCATIONS));
        mRecyclerView.setRecyclerListener(mRecycleListener);
    }


    /**
     * Adapter that displays a title and {@link com.google.android.gms.maps.MapView} for each item.
     * The layout is defined in <code>lite_list_demo_row.xml</code>. It contains a MapView
     * that is programatically initialised in
     * {@link #(int, android.view.View, android.view.ViewGroup)}
     */
    private class MapAdapter extends RecyclerView.Adapter<MapAdapter.ViewHolder> {

        private NamedLocation[] namedLocations;

        private MapAdapter(NamedLocation[] locations) {
            super();
            namedLocations = locations;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.nearby_restuarants_row, parent, false));
        }

        /**
         * This function is called when the user scrolls through the screen and a new item needs
         * to be shown. So we will need to bind the holder with the details of the next item.
         */
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            if (holder == null) {
                return;
            }
            holder.bindView(position);
        }

        @Override
        public int getItemCount() {
            return namedLocations.length;
        }

        /**
         * Holder for Views used in the {@link NearbyRestaurantsActivity.MapAdapter}.
         * Once the  the <code>map</code> field is set, otherwise it is null.
         * When the {@link #onMapReady(com.google.android.gms.maps.GoogleMap)} callback is received and
         * the {@link com.google.android.gms.maps.GoogleMap} is ready, it stored in the {@link #map}
         * field. The map is then initialised with the NamedLocation that is stored as the tag of the
         * MapView. This ensures that the map is initialised with the latest data that it should
         * display.
         */
        class ViewHolder extends RecyclerView.ViewHolder implements OnMapReadyCallback {

            MapView mapView;
            TextView title;
            GoogleMap map;
            View layout;

            private ViewHolder(View itemView) {
                super(itemView);
                layout = itemView;
                mapView = layout.findViewById(R.id.lite_listrow_map);
                title = layout.findViewById(R.id.lite_listrow_text);
                mapView.setClickable(false);
                if (mapView != null) {
                    // Initialise the MapView
                    mapView.onCreate(null);
                    // Set the map ready callback to receive the GoogleMap object
                    mapView.getMapAsync(this);
                }
            }

            @Override
            public void onMapReady(GoogleMap googleMap) {
                MapsInitializer.initialize(getApplicationContext());
                map = googleMap;
                setMapLocation();
                map.getUiSettings().setMapToolbarEnabled(false);
            }

            /**
             * Displays a {@link NearbyRestaurantsActivity.NamedLocation} on a
             * {@link com.google.android.gms.maps.GoogleMap}.
             * Adds a marker and centers the camera on the NamedLocation with the normal map type.
             */
            private void setMapLocation() {
                if (map == null) return;

                NamedLocation data = (NamedLocation) mapView.getTag();
                if (data == null) return;

                // In the future, have this be assigned using an intent from the user inputting where
                // they currently are.
                MarkerOptions current = new MarkerOptions().position(UWT).title("You");
                MarkerOptions target = new MarkerOptions().position(data.location).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
                map.addMarker(current);
                map.addMarker(target);

                // Determine bounds of map based on location of markers
                LatLngBounds.Builder builder = new LatLngBounds.Builder();
                builder.include(current.getPosition());
                builder.include(target.getPosition());
                LatLngBounds bounds = builder.build();

                map.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 0));
                map.moveCamera(CameraUpdateFactory.zoomOut());

                // Set the map type back to normal.
                map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            }

            private void bindView(int pos) {
                final NamedLocation item = namedLocations[pos];
                // Store a reference of the ViewHolder object in the layout.
                layout.setTag(this);
                // Store a reference to the item in the mapView's tag. We use it to get the
                // coordinate of a location, when setting the map location.
                mapView.setTag(item);
                setMapLocation();
                title.setText(item.name);

                layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(myContext, RestaurantHomepageActivity.class);
                        intent.putExtra(SELECTED_RESTAURANT, item.name);
                        startActivity(intent);
                    }
                });
            }
        }
    }

    /**
     * RecycleListener that completely clears the {@link com.google.android.gms.maps.GoogleMap}
     * attached to a row in the RecyclerView.
     * Sets the map type to {@link com.google.android.gms.maps.GoogleMap#MAP_TYPE_NONE} and clears
     * the map.
     */
    private RecyclerView.RecyclerListener mRecycleListener = new RecyclerView.RecyclerListener() {

        @Override
        public void onViewRecycled(RecyclerView.ViewHolder holder) {
            MapAdapter.ViewHolder mapHolder = (MapAdapter.ViewHolder) holder;
            if (mapHolder != null && mapHolder.map != null) {
                // Clear the map and free up resources by changing the map type to none.
                // Also reset the map when it gets reattached to layout, so the previous map would
                // not be displayed.
                mapHolder.map.clear();
                mapHolder.map.setMapType(GoogleMap.MAP_TYPE_NONE);
            }
        }
    };

    /**
     * Location represented by a position ({@link com.google.android.gms.maps.model.LatLng} and a
     * name ({@link java.lang.String}).
     */
    private static class NamedLocation {

        public final String name;
        public final LatLng location;

        NamedLocation(String name, LatLng location) {
            this.name = name;
            this.location = location;
        }
    }

    /**
     * A list of locations to show in this ListView.
     */
    private static final NamedLocation[] LIST_LOCATIONS = new NamedLocation[]{
            new NamedLocation("Jimmy Johns", new LatLng(47.246503, -122.437270)),
            new NamedLocation("The Rock", new LatLng(47.244207, -122.439232)),
            new NamedLocation("Jack in the Box", new LatLng(47.239257, -122.435733)),
            new NamedLocation("Burger Seoul", new LatLng(47.242893, -122.469049)),
            new NamedLocation("Edit me in NearbyRestaurantsActivity near the bottom!", new LatLng(0, 0)),

    };
}
