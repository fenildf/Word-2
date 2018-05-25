package com.example.fragmentbestpractice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.litepal.LitePal;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zz);
        Button button = (Button) findViewById(R.id.zzzz);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                List<word> words = new ArrayList<>();

                InputStream is = getAssetsSteam("words.xml");//读取文档
                try {
                    words = new Pullwords().parse(is);
                    for (word word : words) {
                        word wordlist =new word();
                        wordlist.setTrans(word.getTrans());
                        wordlist.setWord(word.getWord());
                        wordlist.save();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            //读入方法
            private InputStream getAssetsSteam(String s) {
                InputStream is = null;
                try {
                    is = getAssets().open(s);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return is;
            }
        });
    }



    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.chaxun:
                Intent intent =new Intent(this,Second.class);
                startActivity(intent);
                break;
            case R.id.add:
                Intent intent2 =new Intent(this,add.class);
                startActivity(intent2);
                break;
        }
        return true;
    }
}
