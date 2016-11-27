package com.example.android.belajarlayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import static com.example.android.belajarlayout.R.id.quit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickMe(View v){
        //Artinya kita mindahin activity ini (MainActivity.this ke CoffeeActivity.class)
        Intent i = new Intent(MainActivity.this,CoffeeActivity.class);
        startActivity(i);
    }


    //TODO Membuat menu titik 3 diatas action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.about:
                //TODO pindah ke halaman profile activity
                Intent i = new Intent(MainActivity.this,ProfileActivity.class);
                startActivity(i);
                break;
            case R.id.order:
                //TODO pindah ke halaman order activity
                Intent j = new Intent(MainActivity.this,CoffeeActivity.class);
                startActivity(j);
                break;
            case quit:
                //TODO quit apps
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Anda yakin ingin keluar ?");
                builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog quit = builder.create();
                quit.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
