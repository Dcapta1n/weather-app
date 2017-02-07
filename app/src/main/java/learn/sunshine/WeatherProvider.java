package learn.sunshine;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

/**
 * Created by sonu on 20-01-2016.
 */
public class WeatherProvider extends ContentProvider {
    public static final int weather =100;
    public static final int weather_with_location =101;
    public static final int Weather_with_location_and_date =102;
    public static final int location =300;
    public static final int location_id =301;
    private  static final UriMatcher sUriMatcher =buildUriMatcher();
    public WeatherDbHelper mOpenHelper;


    private static UriMatcher buildUriMatcher(){
        final UriMatcher matcher =  new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = WeatherContract.CONTENT_AUTHORITY;
        matcher.addURI(authority,WeatherContract.PATH_WEATHER,weather);
        matcher.addURI(authority,WeatherContract.PATH_WEATHER+"/*",weather_with_location);
        matcher.addURI(authority,WeatherContract.PATH_WEATHER+"/*/*",Weather_with_location_and_date);

        matcher.addURI(authority,WeatherContract.PATH_LOCATION,location);
        matcher.addURI(authority,WeatherContract.PATH_LOCATION+"/#",location_id);
        return  matcher;
    }
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match){
            case  Weather_with_location_and_date:
                return WeatherContract.WeatherEntry.CONTENT_ITEM_TYPE;
            case weather_with_location:
                return WeatherContract.WeatherEntry.CONTENT_TYPE;
            case weather:
                return WeatherContract.WeatherEntry.CONTENT_TYPE;
            default:
                throw new UnsupportedOperationException("unknown uri"+ uri);
        }

    }
    public boolean onCreate() {
        mOpenHelper = new WeatherDbHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor retCursor;

        final int match = sUriMatcher.match(uri);
        switch (match){
            case  Weather_with_location_and_date:
                retCursor =null;
                break;
            case weather_with_location:
                retCursor =null;
                break;
            case weather:
                retCursor =mOpenHelper.getReadableDatabase().query(
                        WeatherContract.WeatherEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            case location:
                retCursor =mOpenHelper.getReadableDatabase().query(
                        WeatherContract.LocationEntry.TABLE_NAME,
                        projection,
                        WeatherContract.LocationEntry._ID+" = '"+ ContentUris.parseId(uri),
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
            default:
                throw new UnsupportedOperationException("unknown uri"+ uri);
        }
        retCursor.setNotificationUri(getContext().getContentResolver(),uri);
        return retCursor;
    }


    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
