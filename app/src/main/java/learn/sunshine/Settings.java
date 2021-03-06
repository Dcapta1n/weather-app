package learn.sunshine;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.v4.content.SharedPreferencesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.security.spec.ECField;
import java.util.List;

public class Settings extends  PreferenceActivity implements Preference.OnPreferenceChangeListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // getPreferences(R.xml.pref_general);


        addPreferencesFromResource(R.xml.pref_general);
        bindPreferenceSummaryToValue(findPreference(getString(R.string.pref_location_key)));
        bindPreferenceSummaryToValue(findPreference(getString(R.string.pref_unit_key)));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void bindPreferenceSummaryToValue(Preference preference){
        preference.setOnPreferenceChangeListener(this);
        onPreferenceChange(preference, PreferenceManager
        .getDefaultSharedPreferences(preference.getContext())
        .getString(preference.getKey(),""));
    }
    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        String stringval=newValue.toString();
        if(preference instanceof ListPreference){
            ListPreference listPreference = (ListPreference)preference;
            int prefIndex = listPreference.findIndexOfValue(stringval);
            if(prefIndex>=0){
                preference.setSummary(listPreference.getEntries()[prefIndex]);

            }

        }
        else{
            preference.setSummary(stringval);
        }
        return true;
    }
}
