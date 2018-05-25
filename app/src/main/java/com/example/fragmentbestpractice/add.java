package com.example.fragmentbestpractice;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class add extends AppCompatActivity {
    private EditText edit_word;
    private TextView tv_word;
    private TextView tv_trans;
    private TextView tv_source;
    private ProgressDialog dialog;
    private String trans;
    private String word;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);
        edit_word=(EditText)findViewById(R.id.search);
        tv_word=(TextView)findViewById(R.id.word);
        tv_trans=(TextView)findViewById(R.id.trans);
        tv_source=(TextView)findViewById(R.id.source);
        dialog =new ProgressDialog(this);
        dialog.setTitle("ing");

        findViewById(R.id.chaxunfeomnet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(edit_word.getText().toString())){
                    Toast.makeText(add.this,"内容为空",Toast.LENGTH_SHORT).show();
                    return;
                }
               final String path ="http://dict-co.iciba.com/search.php?word=" +  edit_word.getText().toString() + "&submit=查询";
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            URL url = new URL(path);
                            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                            httpURLConnection.setRequestMethod("GET");//get请求
                            int code = httpURLConnection.getResponseCode();
                            if(code == 200){//请求码
                                InputStream in = httpURLConnection.getInputStream();
                                InputStreamReader isr = new InputStreamReader(in,"utf-8");
                                BufferedReader reader = new BufferedReader(isr);
                                String line = null;
                                int i = 0;
                                while((line = reader.readLine()) != null) {
                                    i++;
                                    if(i == 22) {
                                        trans += line;
                                        break;
                                    }
                                }
                                tv_trans.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        trans = trans.replaceAll("&nbsp;","\n");
                                        trans = trans.replaceAll("<br />","");
                                        tv_trans.setText(trans);
                                        tv_word.setText(edit_word.getText().toString());
                                        trans = "";
                                    }
                                });
                            }
                            else{
                                Toast.makeText(add.this, "连接网络失败,请重试！", Toast.LENGTH_SHORT);
                            }
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }).start();
            }
        });
    }
}

