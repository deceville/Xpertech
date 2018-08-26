package com.company.xpertech.xpertech.Main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.company.xpertech.xpertech.Method.Packages;
import com.company.xpertech.xpertech.Method.Troubleshoot;
import com.company.xpertech.xpertech.Nav_Fragment.Channel_Packages_Fragment.ChannelFragment;
import com.company.xpertech.xpertech.Nav_Fragment.Channel_Packages_Fragment.PackagesFragment;
import com.company.xpertech.xpertech.Nav_Fragment.FeedbackFragment;
import com.company.xpertech.xpertech.Nav_Fragment.HomeFragment;
import com.company.xpertech.xpertech.Nav_Fragment.Manual_Fragment.Sub_Manual_Fragment;
import com.company.xpertech.xpertech.Nav_Fragment.Remote_Fragment.RemoteItemFragment;
import com.company.xpertech.xpertech.Nav_Fragment.Remote_Fragment.RemoteListFragment;
import com.company.xpertech.xpertech.Nav_Fragment.Self_Install_Fragment.SelfInstallFragment;
import com.company.xpertech.xpertech.Nav_Fragment.Self_Install_Fragment.Sub_Install_Fragment;
import com.company.xpertech.xpertech.Nav_Fragment.Troubleshoot_Fragment.IntroFragment;
import com.company.xpertech.xpertech.Nav_Fragment.Troubleshoot_Fragment.TroubleeshootItemFragment;
import com.company.xpertech.xpertech.Nav_Fragment.Troubleshoot_Fragment.TroubleshootConfirmationFragment;
import com.company.xpertech.xpertech.Nav_Fragment.Troubleshoot_Fragment.TroubleshootFragment;
import com.company.xpertech.xpertech.Nav_Fragment.Manual_Fragment.ManualListFragment;
import com.company.xpertech.xpertech.R;

import static android.Manifest.permission.CALL_PHONE;

public class MainActivity extends AppCompatActivity
        implements TroubleshootFragment.OnListFragmentInteractionListener,
        TroubleeshootItemFragment.OnFragmentInteractionListener,
        HomeFragment.OnFragmentInteractionListener,
        IntroFragment.OnFragmentInteractionListener,
        TroubleshootConfirmationFragment.OnFragmentInteractionListener,
        NavigationView.OnNavigationItemSelectedListener,
        PackagesFragment.OnListFragmentInteractionListener,
        ChannelFragment.OnFragmentInteractionListener,
        SelfInstallFragment.OnFragmentInteractionListener,
        Sub_Install_Fragment.OnFragmentInteractionListener,
        FeedbackFragment.OnFragmentInteractionListener,
        RemoteListFragment.OnFragmentInteractionListener,
        RemoteItemFragment.OnFragmentInteractionListener,
        ManualListFragment.OnFragmentInteractionListener,
        Sub_Manual_Fragment.OnFragmentInteractionListener{

    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FeedbackFragment feedbackFragment = new FeedbackFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.content_main, feedbackFragment).addToBackStack("main").commit();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //String boxNumber = getIntent().getStringExtra("boxNumber");
        SharedPreferences sharedPref = getSharedPreferences("values", Context.MODE_PRIVATE);
        String boxNumber = sharedPref.getString("def", "boxNumber");
        bundle = new Bundle();
        bundle.putString("boxNumber", boxNumber);
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    
    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.nav_troubleshoot:
                SharedPreferences sharedPref = getSharedPreferences("values", Context.MODE_PRIVATE);
                String boxNumber = sharedPref.getString("def", "boxNumber");
                bundle = new Bundle();
                bundle.putString("boxNumber", boxNumber);
                TroubleshootFragment fragment = new TroubleshootFragment();
                fragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).addToBackStack("tag").commit();
                break;
            case R.id.nav_selfInstall:
                getSupportFragmentManager().beginTransaction().replace(R.id.content_main, new SelfInstallFragment()).addToBackStack("tag").commit();
                break;
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.content_main, new HomeFragment()).addToBackStack("tag").commit();
                break;
            case R.id.nav_package:
                getSupportFragmentManager().beginTransaction().replace(R.id.content_main, new PackagesFragment()).addToBackStack("tag").commit();
                break;
            case R.id.nav_remoteControl:
                getSupportFragmentManager().beginTransaction().replace(R.id.content_main, new RemoteListFragment()).addToBackStack("tag").commit();
                break;
            case R.id.nav_send:
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:4458514"));

                if (ContextCompat.checkSelfPermission(this.getApplicationContext(), CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    startActivity(callIntent);
                } else {
                    requestPermissions(new String[]{CALL_PHONE}, 1);
                }
                break;
            case R.id.nav_userManual:
                getSupportFragmentManager().beginTransaction().replace(R.id.content_main, new ManualListFragment()).addToBackStack("tag").commit();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onListFragmentInteraction(Troubleshoot item) {

    }

    @Override
    public void onListFragmentInteraction(Packages item) {

    }
}
