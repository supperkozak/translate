package com.example.translate;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private TextView textView;
    private Button button;

    private final String URL = "https://translate.yandex.net";
    private final String KEY = "trnsl.1.1.20170215T104322Z.e000ac3f7c0a38dd.5aa7da279ba1347e883803c7f26203d69a584894";

    private Gson gson = new GsonBuilder().create();

    private Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(URL)
            .build();

    private Link inter = retrofit.create(Link.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initEditText();
        initTextView();
        initButton();
    }

    private void initEditText() {

        editText = (EditText)findViewById(R.id.editText);
    }

    private void initTextView() {

        textView =(TextView)findViewById(R.id.textView);
    }

    private void initButton() {

        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map<String, String> mapJson = new HashMap<>();
                mapJson.put("key", KEY);
                mapJson.put("text", editText.getText().toString());
                mapJson.put("lang", "en-uk");

                Call<Object> call = inter.translate(mapJson);

                try {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);

                    Response<Object> response = call.execute();

                    Map<String, String> mapResponse = gson.fromJson(response.body().toString(), Map.class);

                   // String jsonStr = response.body().toString();
                   // Map<String, String> answer = gson.fromJson(jsonStr, Map.class);

                    for (Map.Entry e : mapResponse.entrySet()){
                        System.out.print(e.getKey()+" "+e.getValue()+"\n");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //Toast.makeText(button, "Hello world", LENGTH_LONG).show();
            }
        });
    }

}
