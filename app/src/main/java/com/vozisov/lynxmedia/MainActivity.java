package com.vozisov.lynxmedia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.vozisov.lynxmedia.model.EventsList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    EventsList eventsList;
    String[] category = {"football", "hockey", "tennis", "basketball", "volleyball", "cybersport"};
    private ProgressBar progressBar;
    TextView cat_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        progressBar = findViewById(R.id.progressBar);

        //default value - football
        cat_name = findViewById(R.id.catname);
        cat_name.setText(R.string.football);

        progressBar.setVisibility(View.VISIBLE);
        getPosts(category[0]);
    }

    private void getPosts(String cat) {

        Call<EventsList> events;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        events = api.loadListArticle(cat);

        events.enqueue(new Callback<EventsList>() {
            @Override
            public void onResponse(Call<EventsList> call, Response<EventsList> response) {
                if (response.isSuccessful()) {
                    eventsList = response.body();
                    setData();
                }
            }

            @Override
            public void onFailure(Call<EventsList> call, Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG);
                toast.show();
            }
        });

    }

    private void setData() {

        ListView lv = (ListView) findViewById(R.id.lv);

        String[] title = new String[eventsList.events.size()];

        for (int i = 0; i < eventsList.events.size(); i++) {
            title[i] = eventsList.events.get(i).getTitle();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, R.layout.simple_list_item_1, title);

        lv.setAdapter(adapter);
        progressBar.setVisibility(View.INVISIBLE);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(MainActivity.this, ArticleActivity.class);
                intent.putExtra("url", eventsList.events.get(position).getArticle());
                startActivity(intent);
            }
        });

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


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        progressBar.setVisibility(View.VISIBLE);
        if (id == R.id.nav_football) {

            getPosts(category[0]);
            cat_name.setText(R.string.football);

        } else if (id == R.id.nav_hockey) {

            getPosts(category[1]);
            cat_name.setText(R.string.hockey);

        } else if (id == R.id.nav_tennis) {

            getPosts(category[2]);
            cat_name.setText(R.string.tennis);

        } else if (id == R.id.nav_basketball) {

            getPosts(category[3]);
            cat_name.setText(R.string.basketball);

        } else if (id == R.id.nav_volleyball) {

            getPosts(category[4]);
            cat_name.setText(R.string.volleyball);

        } else if (id == R.id.nav_cybersport) {

            getPosts(category[5]);
            cat_name.setText(R.string.cybersport);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
