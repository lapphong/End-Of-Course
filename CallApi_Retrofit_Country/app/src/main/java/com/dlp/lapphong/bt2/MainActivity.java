package com.dlp.lapphong.bt2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.dlp.lapphong.bt2.Retrofit2.APIUtils;
import com.dlp.lapphong.bt2.Retrofit2.DataClient;

import java.util.ArrayList;
import java.util.List;

import it.sephiroth.android.library.tooltip.Tooltip;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ListView lvCountry;
    private ArrayList<Country> arrayCountry;
    private CountryAdapter adapter;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvCountry = findViewById(R.id.lvHinhAnhCountry);
        arrayCountry = new ArrayList<>();
        adapter = new CountryAdapter(arrayCountry,this);
        lvCountry.setAdapter(adapter);

        ColorDrawable sage = new ColorDrawable(Color.BLACK);
        lvCountry.setDivider(sage);
        lvCountry.setDividerHeight(1);

        callApi();
        adapter.runToolTip();

        lvCountry.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Toast.makeText(MainActivity.this, "Đợi 1 xíu để load cái", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,CountryInforActivity.class);
                //Country country = arrayCountry.get(i);
                Country country = adapter.countryList.get(i);
                Bundle bundle = new Bundle();
                bundle.putParcelable("countryDetail",country);
                intent.putExtra("dulieu",bundle);
                startActivity(intent);
            }
        });
    }

    private void callApi() {
        DataClient dataClient = APIUtils.getData();
        Call<CountryList> Callback = dataClient.getDATA();
        Callback.enqueue(new Callback<CountryList>() {
            @Override
            public void onResponse(Call<CountryList> call, Response<CountryList> response) {
                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                CountryList array = response.body();
                List<Country> ls = new ArrayList<>();
                ls = array.getGeonames();
                //Log.d("AAA",ls.size()+"");
                for(Country ds : ls){
                    arrayCountry.add(ds);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<CountryList> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search,menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    public void setTooltip(View view){
        Tooltip.make(this,new Tooltip.Builder (101)
                .anchor (view, Tooltip.Gravity.TOP)
                .closePolicy (new Tooltip.ClosePolicy()
                .insidePolicy(true,false)
                .outsidePolicy(true,false),3000)
                .activateDelay (800)
                .showDelay (300)
                .text ("Nhấp để xem chi tiết")
                .maxWidth (500)
                .withArrow(true)
                .withOverlay(true)
                .floatingAnimation (Tooltip.AnimationBuilder.DEFAULT)
                .build()).show();
    }

    @Override
    public void onBackPressed() {
        if(!searchView.isIconified()){
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }
}