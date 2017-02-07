package learn.sunshine;

import android.app.LoaderManager;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sonu on 20-01-2016.
 */
public class WeatherDbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION=1;
    public static  final String DATABASE_NAME="weather.db";

    public WeatherDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_DATABASE_LOCATION_TABLE="CREATE TABLE "+ WeatherContract.LocationEntry.TABLE_NAME+"("+
                WeatherContract.LocationEntry._ID+" INTEGER PRIMARY KEY,"+
                WeatherContract.LocationEntry.COLOUMN_LOCATION_SETTINGS+" TEXT UNIQUE NOT NULL,"+
                WeatherContract.LocationEntry.COLOUMN_CITY_NAME+" TEXT NOT NULL,"+
                WeatherContract.LocationEntry.COLOUMN_COORD_LAT+" REAL NOT NULL,"+
                WeatherContract.LocationEntry.COLOUMN_COORD_LONG+" REAL NOT NULL"+
                ");";




        final String SQL_CREATE_DATABASE_WEATHER_TABLE ="CREATE TABLE "+ WeatherContract.WeatherEntry.TABLE_NAME+"("
            + WeatherContract.WeatherEntry._ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            WeatherContract.WeatherEntry.COLOUMN_LOC_KEY+" INTEGER NOT NULL,"+
            WeatherContract.WeatherEntry.COLOUMN_DATE+" INTEGER NOT NULL,"+
            WeatherContract.WeatherEntry.COLOUMN_SHORT_DESC+" TEXT NOT NULL,"+
            WeatherContract.WeatherEntry.COLOUMN_WEATHER_ID+" INTEGER NOT NULL,"+

            WeatherContract.WeatherEntry.COLOUMN_MIN_TEMP+" REAL NOT NULL,"+
            WeatherContract.WeatherEntry.COLOUMN_MAX_TEMP+" REAL NOT NULL," +


            WeatherContract.WeatherEntry.COLOUMN_HUMIDITY+" REAL NOT NULL,"+
            WeatherContract.WeatherEntry.COLOUMN_PRESSURE+" REAL NOT NULL,"+
            WeatherContract.WeatherEntry.COLOUMN_WIND_SPEED+" REAL NOT NULL,"+
            WeatherContract.WeatherEntry.COLOUMN_DEGREES+" REAL NOT NULL,"+

            "FOREIGN KEY ("+ WeatherContract.WeatherEntry.COLOUMN_LOC_KEY +")REFERENCES "+
            WeatherContract.LocationEntry.TABLE_NAME +" (" + WeatherContract.LocationEntry._ID+"), "+


            "UNIQUE ("+ WeatherContract.WeatherEntry.COLOUMN_DATE+","+
            WeatherContract.WeatherEntry.COLOUMN_LOC_KEY +") ON CONFLICT REPLACE);";
        db.execSQL(SQL_CREATE_DATABASE_WEATHER_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXIST"+ WeatherContract.LocationEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXIST"+ WeatherContract.WeatherEntry.TABLE_NAME);
        onCreate(db);
    }
}
