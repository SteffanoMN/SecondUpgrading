package com.example.secondupgrading;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String BASE_URL = "https://www.thesportsdb.com/api/v1/json/1/search_all_teams.php?s=Soccer&c=Spain";
    private TeamAdapter adapter;
    private ArrayList<TeamModel> arrayList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recview);
    }

        private void addData() {
            AndroidNetworking.get(BASE_URL)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                arrayList = new ArrayList<>();
                                JSONArray teamsArray = response.getJSONArray("teams");
                                for (int i = 0; i < teamsArray.length(); i++) {
                                    JSONObject teamObject = teamsArray.getJSONObject(i);
                                    String name = teamObject.getString("strTeam");
                                    String description = teamObject.getString("strDescriptionEN");
                                    String image = teamObject.getString("strTeamBadge");
                                    arrayList.add(new TeamModel(image, name, description));
                                }
                                adapter = new TeamAdapter(getApplicationContext(), arrayList);
                                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                                recyclerView.setLayoutManager(layoutManager);
                                recyclerView.setAdapter(adapter);
                            } catch (JSONException e) {
                                Log.d("error", e.toString());
                            }
                        }

                        @Override
                        public void onError(ANError anError) {
                            Log.d("error", anError.toString());
                        }
                    });

        }
}