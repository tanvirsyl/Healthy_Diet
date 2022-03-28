package com.example.healthy_diet;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
private TextView foodView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


    public void calculateClickHandler(View view) {
        if (view.getId() == R.id.calcBMI) {


              foodView =(TextView) findViewById(R.id.foodView);

            // get the references to the widgets


            EditText weightNum = (EditText)findViewById(R.id.weightNum);
            EditText heightNum = (EditText)findViewById(R.id.heightNum);
            TextView resultLabel = (TextView)findViewById(R.id.resultLabel);

            Spinner weightSpinner = (Spinner)findViewById(R.id.weightSpinner);
            Spinner heightSpinner = (Spinner)findViewById(R.id.heightSpinner);
            String weightSpinnerString = weightSpinner.getSelectedItem().toString();
            String heightSpinnerString = heightSpinner.getSelectedItem().toString();

            double weight;
            weight = 0;
            double height;
            height = 0;

            // get the users values from the widget references
            if (!(weightNum.getText().toString().equals(""))) {
                weight = Double.parseDouble(weightNum.getText().toString());
            }

            if (!(heightNum.getText().toString().equals(""))) {
                height = Double.parseDouble(heightNum.getText().toString());
            }

            double bmi;

            // calculate bmi value - pounds and inch
            if (weightSpinnerString.equals("Pounds") && heightSpinnerString.equals("Inches")) {
                bmi = calculateBMI(weight, height);
            } else if (weightSpinnerString.equals("Kilograms") &&
                    heightSpinnerString.equals("Inches")){
                weight = weight * 2.205;
                bmi = calculateBMI(weight, height);
            } else if (weightSpinnerString.equals("Pounds") && heightSpinnerString.equals("Centimetres")){
                height = height / 2.54;
                bmi = calculateBMI(weight, height);
            } else {
                weight = weight * 2.205;
                height = height / 2.54;
                bmi = calculateBMI(weight, height);
            }

            // round to 1 digit
            double newBMI = Math.round(bmi*10.0)/10.0;
            DecimalFormat f = new DecimalFormat("##.0");

            // interpret the meaning of the bmi value
            String bmiInterpretation = interpretBMI(newBMI);

            // now set the value in the results text
            resultLabel.setText("BMI Score = " + newBMI + "\n" + bmiInterpretation);
        }
    }

    // the formula to calculate the BMI index
    private double calculateBMI (double weight, double height) {
        // convert values to metric
        return (double) (((weight / 2.2046) / (height * 0.0254)) / (height * 0.0254));
    }

    // interpret what BMI means
    private String interpretBMI(double bmi) {

        if (bmi == 0) {
            return "Enter your details";

        } else if (bmi < 18.5) {

            String foodList="BreakFast:\n 1.half boiled egg \n 2.Green Tea \n 3.Green Salad \n \n " +
                            "Lunch: \n 1.Two cup Rice \n 2. one piece fish \n3.One piece Chicken\n\n" +
                            "Dinner:\n 1.Two cup rice \n2.chicken \n 3.beef";
            foodView.setText(foodList);

            return "You are underweight";

        } else if (bmi < 25) {
            String foodList="BreakFast:\n 1.half boiled egg \n 2.Green Tea \n 3.Green Salad \n \n " +
                    "Lunch \n 1. One cup Rice \n 2. one piece fish";
            foodView.setText(foodList);
            return "You are a healthy weight";
        } else if (bmi < 30) {
            String foodList="BreakFast:\n 1.half boiled egg \n 2.Green Tea \n 3.Green Salad \n \n " +
                    "Lunch \n 1. One cup Rice \n 2. one piece fish";
            foodView.setText(foodList);
            return "You are overweight";
        } else if (bmi < 40) {
            String foodList="BreakFast:\n 1.half boiled egg \n 2.Green Tea \n 3.Green Salad \n \n " +
                    "Lunch \n 1. One cup Rice \n 2. one piece fish";
            foodView.setText(foodList);
            return "You are obese";
        } else {
//
            String foodList="BreakFast:\n 1.half boiled egg \n 2.Green Tea \n 3.Green Salad \n \n " +
                    "Lunch \n 1. One cup Rice \n 2. one piece fish";
            foodView.setText(foodList);
            return "You are severely obese";
        }
    }



}





