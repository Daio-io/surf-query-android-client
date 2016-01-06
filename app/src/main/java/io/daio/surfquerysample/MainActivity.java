package io.daio.surfquerysample;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import io.daio.surfqueryclient.OnFailureListener;
import io.daio.surfqueryclient.OnSuccessListener;
import io.daio.surfqueryclient.SurfQueryClient;
import io.daio.surfqueryclient.SurfQueryException;
import io.daio.surfqueryclient.SurfQueryRequest;
import io.daio.surfqueryclient.SurfQueryResult;
import io.daio.surfquerysample.network.SurfHttpClient;

public class MainActivity extends AppCompatActivity {

    private SurfHttpClient surfClient;

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
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        surfClient = new SurfHttpClient(App.getAppContext());

        SurfQueryClient surfQueryClient = new SurfQueryClient("API_KEY", surfClient);

        final SurfQueryRequest request = new SurfQueryRequest();

        // Surf spot 1449 - Rest Bay
        request.withParam(SurfQueryRequest.SPOT_ID_PARAM, "1449")
                .withParam(SurfQueryRequest.START_TIME_PARAM, "8")
                .withParam(SurfQueryRequest.END_TIME_PARAM, "22");

        surfQueryClient.makeRequest(request, new OnSuccessListener() {
            @Override
            public void onSuccess(String url, List<SurfQueryResult> results) {
                System.out.println(results);
                for (int i = 0, len = results.size(); i < len; i++) {
                    SurfQueryResult result = results.get(i);
                    System.out.println(result.getDate());
                    System.out.println(result.getTime());
                    System.out.println(result.getMaxSwell());
                    System.out.println(result.getMinSwell());
                    System.out.println(result.getSolidStar());
                    System.out.println(result.getFadedStar());
                    System.out.println(result.getWind());
                    System.out.println(result.getTimestamp());
                }
            }
        }, new OnFailureListener() {
            @Override
            public void onFailure(String s, SurfQueryException e) {

                System.out.println(e.getMessage());
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
