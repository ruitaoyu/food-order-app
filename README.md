# food-order-app
This is an Android app for ordering food created for a Software Development and Quality Assurance course (TCSS 360).

## To get this project to work

1. Clone this repository and open the root directory in Android Studio.
2. The restaurant selection part of this app uses the [Google Maps API](https://developers.google.com/maps/documentation/android-sdk/intro) and therefore you need an API key to use the app. Use [this link]( https://console.developers.google.com/flows/enableapi?apiid=maps_android_backend&keyType=CLIENT_SIDE_ANDROID&r=E6:06:0D:F9:74:E4:7A:23:45:45:CF:BB:7D:FB:85:2C:E7:C7:6B:6B%3Bcom.example.foodorderapp) to get an API key for this app then paste that key in `app/src/debug/res/values/google_maps_api.xml` where it says `ENTER_API_KEY_HERE`
3. Run the app on your device or a virtual device!
