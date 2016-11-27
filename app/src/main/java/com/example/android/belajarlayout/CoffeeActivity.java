package com.example.android.belajarlayout;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CoffeeActivity extends AppCompatActivity {
    //Variabel untuk Edit Text
    EditText mTextNama;

    //Variabel untuk Button
    Button mButtonOrder, mButtonPlus, mButtonMin;

    //Variabel untuk TextView
    TextView mTextHarga, mTextQty;

    //Variabel untuk Spinner
    Spinner mSpinner;

    //Variabel untuk Radio Group dan Radio Button
    RadioGroup mRadioGroup;
    RadioButton mRadioItem1,mRadioItem2;

    //Variabel untuk Context atau bisa pakai this
    Context mContext;

    //Variabel untuk list menu
    List<String>mListMenu = new ArrayList<>();

    //Variabel untuk harga dan quantity
    int harga = 0;
    int qty = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.order);
        setContentView(R.layout.activity_coffee);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        /*
        * Variabelnya dimasukkan ke view
        * */
        mContext = getApplicationContext();
        mTextHarga = (TextView) findViewById(R.id.mTextHarga);
        mTextNama = (EditText) findViewById(R.id.mTextNama);
        mButtonOrder = (Button) findViewById(R.id.mButtonOrder);
        mTextQty = (TextView) findViewById(R.id.mTextQty);
        mButtonPlus = (Button) findViewById(R.id.mButtonPlus);
        mButtonMin = (Button) findViewById(R.id.mButtonMin);

        //Radio
        mRadioGroup = (RadioGroup) findViewById(R.id.mRadioGroup);
        mRadioItem1 = (RadioButton) findViewById(R.id.mRadioItem1);
        mRadioItem2 = (RadioButton) findViewById(R.id.mRadioItem2);

        /*
        * Shortcut untuk mengetahui parameter yaitu ctrl+p
        * Cara penulisan cepat yaitu setelah nulis new langsung tekan ctrl+space
        * */
        mRadioItem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext,"Susu",Toast.LENGTH_SHORT).show();
            }
        });

        mRadioItem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext,"Coklat",Toast.LENGTH_SHORT).show();
            }
        });

        // Spinner
        mSpinner = (Spinner) findViewById(R.id.mSpinner);
        mListMenu.add("");
        mListMenu.add("Martabak");
        mListMenu.add("Piscok");
        mListMenu.add("Ice Cream Sandwich");
        mListMenu.add("Lumpia Basah");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,mListMenu);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(dataAdapter);

        //Contoh buat toast
        /*mButtonOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Hi, nama saya "+mTextNama.getText(),Toast.LENGTH_LONG).show();
                mTextHarga.setText(mTextNama.getText());
            }
        });*/
    }

    public void onClickOrder(View v) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, "arieridwansyah@gmail.com");
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, mTextNama.getText());
        emailIntent.putExtra(Intent.EXTRA_TEXT,
                "Saya Pesan "
                +  mSpinner.getSelectedItem()
                + " sebanyak "
                + mTextQty.getText()
                + " seharga "
                + mTextHarga.getText()
        );
        try{
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
        }catch (android.content.ActivityNotFoundException ex){
            Toast.makeText(this,"There is no email client installed.",Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickPlus(View v){
        harga += 5;
        qty ++;
        mTextQty.setText(qty+"");
        mTextHarga.setText("$"+harga);
    }

    public void onClickMin(View v){
        if(harga != 0){
            harga -= 5;
            qty --;
            mTextQty.setText(qty+"");
            mTextHarga.setText("$"+harga);
        }else{
            harga = 0;
            qty = 0;
            mTextQty.setText(""+qty);
            mTextHarga.setText("$"+harga);
        }

    }

    public void onClickReset(View v){
        harga = 0;
        qty = 0;
        mTextNama.setText("");
        mTextQty.setText(qty+"");
        mTextHarga.setText("$"+harga);
    }

    public void onClickBack(View v){
        Intent i = new Intent(CoffeeActivity.this,MainActivity.class);
        //TODO flag_activity_clear_top untuk menghapus recent activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
