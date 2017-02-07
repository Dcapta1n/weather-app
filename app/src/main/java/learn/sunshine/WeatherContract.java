package learn.sunshine;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;
import android.text.format.Time;

/**
 * Created by sonu on 20-01-2016.
 */
public class WeatherContract {
    public static final String CONTENT_AUTHORITY ="learn.sunshine";
    public static  final Uri BASE_CONTENT_URI =Uri.parse("content://"+CONTENT_AUTHORITY);
    public static final String PATH_WEATHER = "weather";
    public static final String PATH_LOCATION = "location";
    public static long normalizeDate(long StartDate){
        Time time = new Time();
        time.setToNow();
        int julianDay = Time.getJulianDay(StartDate,time.gmtoff);
        return  time.setJulianDay(julianDay);
    }
public static final class LocationEntry implements BaseColumns{
    public static  final Uri CONTENT_URI =BASE_CONTENT_URI.buildUpon().appendPath(PATH_LOCATION).build();

    public static final String TABLE_NAME="Location";
    public static final String COLOUMN_LOCATION_SETTINGS="location_setting";
    public static final String COLOUMN_CITY_NAME="city_name";
    public static final String COLOUMN_COORD_LAT="latitude";
    public static final String COLOUMN_COORD_LONG="longitude";
    public static final String COLOUMN_LOCATION_SETTING="settings";

    public static Uri builLocationUri(long _id){
        return ContentUris.withAppendedId(CONTENT_URI,_id);
    }
}
    public static final class WeatherEntry implements BaseColumns {

        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/" + CONTENT_AUTHORITY + "/" + PATH_LOCATION;
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/" + CONTENT_AUTHORITY + "/" + PATH_LOCATION;
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_WEATHER).build();

        public static final String TABLE_NAME = "WEATHER";
        public static final String COLOUMN_LOC_KEY = "location_id";
        public static final String COLOUMN_DATE = "date";
        public static final String COLOUMN_WEATHER_ID = "weather_id";
        public static final String COLOUMN_SHORT_DESC = "short_desc";
        public static final String COLOUMN_HUMIDITY = "humidity";
        public static final String COLOUMN_MIN_TEMP = "minTemp";
        public static final String COLOUMN_MAX_TEMP = "maxTemp";
        public static final String COLOUMN_DEGREES = "degrees";
        public static final String COLOUMN_PRESSURE = "pressure";
        public static final String COLOUMN_WIND_SPEED = "windSpeed";

        public static Uri builWeatherUri(long id){
            return ContentUris.withAppendedId(CONTENT_URI,id);
        }
        public static Uri builWeatherLocation(String locationSettings){
            return CONTENT_URI.buildUpon().appendPath(locationSettings).build();
        }
        public static Uri builWeatherLocationWithStartDate(String locationSettings, String startDate){
            return CONTENT_URI.buildUpon().appendPath(locationSettings).appendQueryParameter(COLOUMN_DATE, startDate).build();
        }
        public static Uri builWeatherLocationWithDate(String locationSettings, String date){
            return CONTENT_URI.buildUpon().appendPath(locationSettings).appendPath(date).build();
        }
        public static String getLocationSettingFromUri(Uri uri){
            return uri.getPathSegments().get(1);
        }
        public static String getDateFromUri(Uri uri){

            return uri.getPathSegments().get(2);
        }

        public static String getStartDateFromUri(Uri uri){
            return uri.getQueryParameter(COLOUMN_DATE);
        }
    }
}


