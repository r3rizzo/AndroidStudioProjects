package br.edu.faculdade.exerc01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText edtDollar;
    TextView txtReal;
    Button btnCalcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtDollar = findViewById(R.id.edtDollar);
        txtReal = findViewById(R.id.txtReal);
        btnCalcular = findViewById(R.id.btnCalcular);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double dollar, real;
                dollar = Double.parseDouble(edtDollar.getText().toString());
                real = dollar * 4.42;
                txtReal.setText(String.valueOf(real));
            }
        });

    }
}
