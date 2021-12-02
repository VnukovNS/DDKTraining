package com.example.ddktraining;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {
    String [] addresses = {"t89161465577@gmail.com"};
    String subject = "Order from DDK Training";
    String emailText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        setTitle("Your Order");

        Intent receivedOrderIntent = getIntent();
        String userName = receivedOrderIntent.getStringExtra("userNameForIntent");
        String goodsName = receivedOrderIntent.getStringExtra("trainingNameForIntent");
        int quantity = receivedOrderIntent.getIntExtra("quantityForIntent", 0);
        double price = receivedOrderIntent.getDoubleExtra("priceForIntent", 0);
        double orderPrice = receivedOrderIntent.getDoubleExtra("orderPriceForIntent", 0);


        TextView orderCustomerNameTextView = findViewById(R.id.orderCustomerNameTextView);
        orderCustomerNameTextView.setText("Customer name: " + userName);

        TextView orderGoodsNameTextView = findViewById(R.id.orderGoodsNameTextView);
        orderGoodsNameTextView.setText("Training name: " + goodsName);

        TextView orderQuantityTextView = findViewById(R.id.orderQuantityTextView);
        orderQuantityTextView.setText("Quantity: " + quantity);

        TextView orderPriceTextView = findViewById(R.id.orderPriceTextView);
        orderPriceTextView.setText("Price: " + price);

        TextView orderCartPriceNameTextView = findViewById(R.id.orderCartPriceNameTextView);
        orderCartPriceNameTextView.setText("Order price: " + orderPrice);

        emailText = "Customer name:" + userName + "\n" + "Training name: " + goodsName + "\n" +
                "Quantity: " + quantity + "\n" + "Price: " + price + "\n" + "Order price: " + orderPrice;

    }

    public void submitOrder(View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, emailText);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

    public void composeEmail(String[] addresses, String subject) {

    }

}