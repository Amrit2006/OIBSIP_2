package com.example.unitconverterapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.unitconverterapp.R;

public class MainActivity extends AppCompatActivity {

    private EditText inputValueEditText;
    private Spinner fromUnitSpinner, toUnitSpinner;
    private Button convertButton;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get references to UI elements
        inputValueEditText = findViewById(R.id.input_value);
        fromUnitSpinner = findViewById(R.id.from_unit);
        toUnitSpinner = findViewById(R.id.to_unit);
        convertButton = findViewById(R.id.convert_button);
        resultTextView = findViewById(R.id.result_text);

        // Create ArrayAdapter for spinners
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.length_units,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromUnitSpinner.setAdapter(adapter);
        toUnitSpinner.setAdapter(adapter);

        // Set OnClickListener for the button
        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get input value and selected units
                double inputValue = Double.parseDouble(inputValueEditText.getText().toString());
                String fromUnit = fromUnitSpinner.getSelectedItem().toString();
                String toUnit = toUnitSpinner.getSelectedItem().toString();

                // Perform conversion
                double result = convertLength(inputValue, fromUnit, toUnit);

                // Display result
                resultTextView.setText(String.format("%.2f %s", result,toUnit));
            }
        });
    }

    // Conversion logic (example for meters to feet)
    private double convertLength(double value, String fromUnit, String toUnit) {
        if (fromUnit.equals("Meter") && toUnit.equals("Feet")) {
            return value * 3.28084;
        } else if (fromUnit.equals("Feet") && toUnit.equals("Meter")) {
            return value / 3.28084;
        }
        // Add more conversion logic for other
        else if (fromUnit.equals("Feet") && toUnit.equals("Centimeter")) {
            return value * 30.48;
        }
        else if (fromUnit.equals("Centimeter") && toUnit.equals("Feet")) {
            return value / 30.48;
        }
        else if (fromUnit.equals("Kilogram") && toUnit.equals("Gram")) {
            return value * 1000;
        }
        else if (fromUnit.equals("Gram") && toUnit.equals("Kilogram")) {
            return value / 1000;
        }
        else if (fromUnit.equals("Centimeter") && toUnit.equals("Meter")) {
            return value / 100;
        }
        else if (fromUnit.equals("Meter") && toUnit.equals("Centimeter")) {
            return value * 100;
        }
        else if (fromUnit.equals("Kilogram") && toUnit.equals("Meter")) {
            return value;
        }
        else if (fromUnit.equals("Gram") && toUnit.equals("Meter")) {
            return value;
        }

        return value;
    }
}

