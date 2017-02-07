package learn.sunshine;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ActionProvider;
import android.support.v4.view.MenuItemCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ShareActionProvider;
import android.widget.TextView;



/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {
    private static  final String LOG_TAG=DetailActivityFragment.class.getSimpleName();
    private static  final String FORECAST_SHARING_TAG=" #SunshineApp";
    public String mForecastStr;

    public DetailActivityFragment() {
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Intent intent= getActivity().getIntent();
        View rootView =inflater.inflate(R.layout.fragment_detail, container, false);
        if(intent!=null&& intent.hasExtra(Intent.EXTRA_TEXT) ){
            mForecastStr =intent.getStringExtra(Intent.EXTRA_TEXT);
            ((TextView)rootView.findViewById(R.id.detail_text)).setText(mForecastStr);

        }

        return rootView;
    }
    private Intent shareForecastIntetnt(){
        Intent shareIntetnt =  new Intent(Intent.ACTION_SEND);
        shareIntetnt.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        shareIntetnt.setType("text/plain");
        shareIntetnt.putExtra(Intent.EXTRA_TEXT,mForecastStr+FORECAST_SHARING_TAG);
        return shareIntetnt;
    }
 /*  public void onCreateOptionsMenu(Menu menu,MenuInflater inflater) {
        inflater.inflate(R.menu.detailfragment, menu);

        MenuItemCompat menuItem = (MenuItemCompat) menu.findItem(R.id.action_share);
        ShareActionProvider mShareActionProvider;
        mShareActionProvider = (ShareActionProvider)MenuItemCompat.getActionProvider((MenuItem) menuItem);
        mShareActionProvider.setShareIntent(shareForecastIntetnt());
        // Inflate the menu; this adds items to the action bar if it is present.


    }*/
}
