package com.example.reminiscence;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class ResourcesActivity extends AppCompatActivity {


    ListView list;

    String[] maintitle ={
            "Confederacion Española de Alzheimer","Asociacion Española de Alzheimer",
            "Fundacion Pasqual Maragall","FAFAL",
            "Alzheimer’s Association ESPAÑA",
    };

    String[] subtitle ={
            "www.ceafa.es (CLICK ME)","www.alzfae.es (CLICK ME)",
            "www.fpmaragall.org (CLICK ME)","www.fafal.org (CLICK ME)",
            "www.alz.org/es (CLICK ME)",
    };

    Integer[] imgid={
            R.drawable.logo_ceafa,R.drawable.logo_alzfae,
            R.drawable.logo_fpmaragall,R.drawable.logo_fafal,
            R.drawable.logo_alz,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);

        MyListAdapter adapter=new MyListAdapter(this, maintitle, subtitle,imgid);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                if(position == 0) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://www.ceafa.es"));
                    startActivity(intent);
                }

                else if(position == 1) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://www.alzfae.es"));
                    startActivity(intent);
                }

                else if(position == 2) {

                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://www.fpmaragall.org"));
                    startActivity(intent);
                }
                else if(position == 3) {

                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://www.fafal.org"));
                    startActivity(intent);
                }
                else if(position == 4) {

                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://www.alz.org/es"));
                    startActivity(intent);                }

            }
        });
    }
}