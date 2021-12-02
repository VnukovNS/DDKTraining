package com.example.ddktraining;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    int Quantity = 0;
    Spinner spinner;
    ArrayList spinnerArrayList;
    ArrayAdapter spinnerAdapter;
    HashMap trainingMap;
    String trainingName;
    double price;
    EditText userNameEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userNameEditText = findViewById(R.id.editTextTextPersonName);

        createSpinner();

        createMap();

    }
    void createSpinner() {
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        spinnerArrayList = new ArrayList();

        spinnerArrayList.add("Individual");
        spinnerArrayList.add("Group");
        spinnerArrayList.add("Gameplay view");
        spinnerArrayList.add("Tournament");
        spinnerArrayList.add("Strats for two");

        spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerArrayList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
    }
    void createMap() {
        trainingMap = new HashMap();
        trainingMap.put("Individual", 50.0);
        trainingMap.put("Group", 100.0);
        trainingMap.put("Gameplay view", 10.0);
        trainingMap.put("Tournament", 150.0);
        trainingMap.put("Strats for two", 20.0);
    }

    public void increaseQuantity(View view) {
        Quantity = Quantity + 1;
        TextView increaseButton = findViewById(R.id.QuantityTextView);
        increaseButton.setText("" + Quantity);

        TextView priceTextView = findViewById(R.id.PriceTextView);
        priceTextView.setText("" + Quantity * price);
    }

    public void decreaseQuantity(View view) {
        Quantity = Quantity - 1;
        if (Quantity < 0) {
            Quantity = 0;
        }
        TextView increaseButton = findViewById(R.id.QuantityTextView);
        increaseButton.setText("" + Quantity);

        TextView priceTextView = findViewById(R.id.PriceTextView);
        priceTextView.setText("" + Quantity * price);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        trainingName = spinner.getSelectedItem().toString();
        price = (double)trainingMap.get(trainingName);
        TextView priceTextView = findViewById(R.id.PriceTextView);
        priceTextView.setText("" + Quantity * price);

        ImageView trainingImageView = findViewById(R.id.trainingImageView);

        switch(trainingName) {
            case "Individual":
                trainingImageView.setImageResource(R.drawable.individual);
                break;
            case "Group":
                trainingImageView.setImageResource(R.drawable.group);
                break;
            case "Gameplay view":
                trainingImageView.setImageResource(R.drawable.gameplay);
                break;
            case "Strats for two":
                trainingImageView.setImageResource(R.drawable.strats);
                break;
            case "Tournament":
                trainingImageView.setImageResource(R.drawable.invi1);
                break;
            default:
                trainingImageView.setImageResource(R.drawable.ddk_gaming_help_low);
                break;
        }



    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void addToCart(View view) {
        Order order = new Order();

        order.userName = userNameEditText.getText().toString();


        order.goodsName = trainingName;

        order.quantity = Quantity;

        order.price = price;

        order.orderPrice = Quantity * price;
        Intent orderIntent = new Intent(MainActivity.this, OrderActivity.class);
        orderIntent.putExtra("userNameForIntent", order.userName);
        orderIntent.putExtra("trainingNameForIntent", order.goodsName);
        orderIntent.putExtra("quantityForIntent", order.quantity);
        orderIntent.putExtra("priceForIntent", order.price);
        orderIntent.putExtra("orderPriceForIntent", order.orderPrice);

        startActivity(orderIntent);



    }
}