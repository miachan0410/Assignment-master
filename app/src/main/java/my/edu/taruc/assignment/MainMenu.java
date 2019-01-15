package my.edu.taruc.assignment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.android.volley.toolbox.JsonArrayRequest;


public class MainMenu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private String Name = "" ;
    private String ID = "" ;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        SharedPreferences prefs =   getSharedPreferences("PrefText", MODE_PRIVATE);
        Name = prefs.getString("StudentName", "No name defined");
        ID = prefs.getString("StudentID", "No ID defined");
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.StudentName);
        navUsername.setText(Name);
        TextView navUserID = (TextView) headerView.findViewById(R.id.StudentID);
        navUserID.setText(ID);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MainMenuFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()){
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new MainMenuFragment()).commit();
                break;
            case R.id.nav_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ProfileFragment()).commit();
                break;
            case R.id.nav_logout:
                SharedPreferences prefs =   getApplicationContext().getSharedPreferences("PrefText", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.clear();
                editor.commit();


                Intent i = new Intent(this, MainActivity.class);
                // Closing all the Activities
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                // Add new Flag to start new Activity
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                // Staring Login Activity
                this.startActivity(i);

                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void toDriver(View view){
        Intent goDriver = new Intent(this,DriverCreate.class);
        this.startActivity(goDriver);
    }

    public void toPassenger(View view){
        Intent goPassenger = new Intent(this,PassengerSearchPath.class);
        this.startActivity((goPassenger));
    }

    public void Logout(View view){
        SharedPreferences prefs =  getApplicationContext().getSharedPreferences("PrefText", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.commit();
        NavUtils.navigateUpFromSameTask(this);
    }

    public void goAbout(View view){
        Intent newIntent = new Intent(this,AboutPage.class);
        this.startActivity(newIntent);
    }

    public void goContactUs(View view){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] {"yaptw-wa16@student.tarc.edu.my"});
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finishAffinity();
                break;
        }
        return true;
    }

}
