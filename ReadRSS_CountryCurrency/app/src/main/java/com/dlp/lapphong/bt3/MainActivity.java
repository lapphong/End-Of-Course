package com.dlp.lapphong.bt3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView txt_Result_Currency,txt_Currency_Country_THUAN;
    EditText edt_Currency_INPUT;
    Button btnConvert;
    Spinner spinnerFROM,spinnerTO;
    ArrayList<CountryModel> arrayList;
    //ArrayList<CountryModel> arrayList2;
    LinearLayout history;
    CountryAdapter adapter;
    public static Database database;

    private int vitriFROM = 0,vitriTO = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = new Database(this,"QuanLy.sqlite",null,1);
        database.queryData("CREATE TABLE IF NOT EXISTS History(Id INTEGER PRIMARY KEY AUTOINCREMENT, codeFROM VARCHAR(150),codeTO VARCHAR(150), input VARCHAR(150),result VARCHAR(150),date VARCHAR(150))");

        anhXa();
        arrayList = new ArrayList<>();
        //arrayList2 = new ArrayList<>();
//        arrayList.add(new CountryModel(R.drawable.ic_launcher_background,"USD"));
//        arrayList.add(new CountryModel(R.drawable.ic_launcher_background,"VNĐ"));
//        arrayList.add(new CountryModel(R.drawable.ic_launcher_background,"NZD"));
//        arrayList.add(new CountryModel(R.drawable.ic_launcher_background,"ISO"));
//        arrayList.add(new CountryModel(R.drawable.ic_launcher_background,"USD"));
//        arrayList.add(new CountryModel(R.drawable.ic_launcher_background,"VNĐ"));

        adapter = new CountryAdapter(arrayList,this);
        spinnerFROM.setAdapter(adapter);
        spinnerTO.setAdapter(adapter);

        loadCurrencyCountryName();
        getVitri();
        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getVitri();
                String codeFrom = arrayList.get(vitriFROM).getCodeName();
                String codeTo = arrayList.get(vitriTO).getCodeName();

                Log.d("AAAb", codeFrom + " - " + codeTo);
                String urlFrom = "https://" + codeFrom.toLowerCase(Locale.ROOT) + ".fxexchangerate.com/rss.xml";
                new ReadRSS().execute(urlFrom);

                String result = "";
                String input = edt_Currency_INPUT.getText().toString();
                for (int y = 0; y < arrayList.size(); y++) {
                    CountryModel countryModel = arrayList.get(y);
                    if (codeTo.equals(countryModel.getCodeName())) {
                        Float amount = Float.valueOf(input);
                            //Float amount = Float.parseFloat(edt_Currency_INPUT.getText().toString());
                        txt_Currency_Country_THUAN.setText("1 " + codeFrom + " = " + String.format("%.5f",countryModel.getCurrencyCountry())+" "+ countryModel.getCodeName());
                        Log.d("aaa",countryModel.getCodeName()+"-"+countryModel.getCurrencyCountry());
                        Float a= amount * countryModel.getCurrencyCountry();
                        result = a+"";
                        break;
                    }
                }
                txt_Result_Currency.setText(result);
                database.INSERT2(codeFrom,codeTo,input,result,new SimpleDateFormat("yyyy/MM/dd").format(new Date()));
            }
        });
    }

    private void getVitri(){
        spinnerFROM.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                vitriFROM = i;
                String codeFrom = arrayList.get(vitriFROM).getCodeName();
                String urlFrom = "https://" + codeFrom.toLowerCase(Locale.ROOT) + ".fxexchangerate.com/rss.xml";
                new ReadRSS().execute(urlFrom);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                vitriFROM = 0;
            }
        });

        spinnerTO.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                vitriTO = i;
                String codeTo = arrayList.get(vitriTO).getCodeName();
                for(int y = 0 ; y <arrayList.size();y++ ){
                    CountryModel countryModel = arrayList.get(y);
                    if(codeTo.equals(countryModel.getCodeName())){
                        txt_Currency_Country_THUAN.setText("1 " + arrayList.get(vitriFROM).getCodeName() + " = " + String.format("%.5f",countryModel.getCurrencyCountry())+" "+ countryModel.getCodeName());
                        Log.d("aaa",countryModel.getCodeName()+"-"+countryModel.getCurrencyCountry());
                        break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                vitriTO = 0;
            }
        });
    }

    private void loadCurrencyCountryName(){
        new ReadRSS().execute("https://aud.fxexchangerate.com/rss.xml");
    }

    private void anhXa() {
        txt_Result_Currency = findViewById(R.id.textView_SoTienQuyDoi);
        txt_Currency_Country_THUAN = findViewById(R.id.textView_Thuan);
        edt_Currency_INPUT = findViewById(R.id.edt_inputAmount);
        btnConvert = findViewById(R.id.btn_ConvertedCurrency);
        spinnerFROM = findViewById(R.id.spn_Currency_Country_FROM);
        spinnerTO = findViewById(R.id.spn_Currency_Country_TO);
        history = findViewById(R.id.linear_History);
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,HistoryActivity.class);
                startActivity(intent);
            }
        });
    }

//    private class ReadRSS2 extends AsyncTask<String,Void,String> {
//        StringBuilder content = new StringBuilder();
//        @Override
//        protected String doInBackground(String... strings) {
//            try {
//                URL url = new URL(strings[0]);
//                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
//                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//
//                String line = "";
//                while((line = bufferedReader.readLine()) != null){
//                    content.append(line);
//                }
//                bufferedReader.close();
//            }catch (MalformedURLException e){
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return content.toString();
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
//
//            XMLDOMParser parser = new XMLDOMParser();
//            Document document = parser.getDocument(s);
//            NodeList nodeList = document.getElementsByTagName("item");
//
//            String tieude = "";
//            if(arrayList2 != null){
//                arrayList2.clear();
//            }
//            for(int i = 0 ; i < nodeList.getLength() ; i++){
//                Element element = (Element) nodeList.item(i);
//                tieude = parser.getValue(element,"title");
//
//                String name="";
//                Float currency;
//
//                String temp[] = tieude.split("/",2);
//                name = temp[1];
//
//                //Currency
//                String temp3 = parser.getValue(element,"description");
//                String temp4[] = temp3.split("= ",2);
//                currency = Float.valueOf(temp4[1].substring(0,temp4[1].indexOf(" ")));
//
//                String tenTienTeQuocGia = subCountryCurrencyName(tieude);
//                //Log.e("=>>", tenTienTeQuocGia +'\t'+currency +"\n\n");
//                CountryModel countryModel = new CountryModel(name,tenTienTeQuocGia,currency);
//                arrayList2.add(countryModel);
//            }
//        }
//    }

    private class ReadRSS extends AsyncTask<String,Void,String> {
        StringBuilder content = new StringBuilder();
        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String line = "";
                while((line = bufferedReader.readLine()) != null){
                    content.append(line);
                }
                bufferedReader.close();
            }catch (MalformedURLException e){
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return content.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            XMLDOMParser parser = new XMLDOMParser();
            Document document = parser.getDocument(s);
            NodeList nodeList = document.getElementsByTagName("item");

            String tieude = "";
            if(arrayList != null){
                arrayList.clear();
            }
            for(int i = 0 ; i < nodeList.getLength() ; i++){
                Element element = (Element) nodeList.item(i);
                tieude = parser.getValue(element,"title");

                String name="";
                Float currency;

                String temp[] = tieude.split("/",2);
                name = temp[1];

                //Currency
                String temp3 = parser.getValue(element,"description");
                String temp4[] = temp3.split("= ",2);
                currency = Float.valueOf(temp4[1].substring(0,temp4[1].indexOf(" ")));

                String tenTienTeQuocGia = subCountryCurrencyName(tieude);
                //Log.e("=>>", tenTienTeQuocGia +'\t'+currency +"\n\n");
                CountryModel countryModel = new CountryModel(name,tenTienTeQuocGia,currency);
                arrayList.add(countryModel);
            }
            adapter.notifyDataSetChanged();
        }
    }

    public String subCountryCurrencyName(String title){
        int i = title.lastIndexOf("(");
        int y = title.lastIndexOf(")");
        String ten = title.substring(i+1,y);
        return ten;
    }
}