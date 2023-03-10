/*
    NAME: ERTUGRUL SAHIN
    STUDENT NUMBER : A00270022
    DESCRIPTION : The application that converts units first receives a text field and numeric digits from the user.
    Then it chooses the value it wants to convert with the help of spinner. Finally, it prints the result to the screen.
    Units defined in Spinner are target units.
    For example, the user entered the number 10, this value is kept in the measurementValue. Then if spinner chooses km.
    The program calculates the value using the mile to km formula and displays the resultText textview to the user.

    Conversion units are :
    1. Mile to Km
    2. Inch to Cm
    3. Ounce to Gram
    4. Celsius to F
    5. Celsius to K
    6. Cup to L

 */

package com.example.conversion_app

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import java.math.RoundingMode
import java.text.DecimalFormat

class MainActivity : AppCompatActivity(){

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Call the ids of the views  created in the view with findViewById and assign them to new variables.
        val spinner: Spinner = findViewById(R.id.measurement_list)
        val resultText: TextView = findViewById(R.id.result_text)
        val measurementValue: EditText = findViewById(R.id.measurement_value)
        // Calling the array I created in string.xml and assigning it to the new list
        val unitOfMeasurementList = resources.getStringArray(R.array.unitOfMeasurementList)

        // Create an ArrayAdapter using a simple spinner layout and unitOfMeasurementList array
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, unitOfMeasurementList)
        // Set layout to use when the list of choices appear
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Set Adapter to Spinner
        spinner.adapter=spinnerAdapter

        // Method that works on every character the user writes in the EditText
        measurementValue.doAfterTextChanged {
            // it = Editable? . I run the result function if the last character typed by the user is not a "."
            // The result method takes 3 parameters. The first parameter spinner,
            // the second parameter is the number we get from the user,
            // the third parameter is the textview where the result is written
            if (it?.last()!='.')
                result(spinner,measurementValue,resultText)
        }
        // The method that runs when the spinner is first clicked
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            // This method works if the user chooses one of the options after the first collection.
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                result(spinner,measurementValue,resultText)
            }
            // If the user does not make a choice, this method works. This function cannot be used but cannot be deleted because override method
            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }
     }

    // The method in which the result method operations are performed
    @SuppressLint("SetTextI18n")
    fun result(spinner: Spinner, measurementValue: EditText, resultText: TextView) {
        // If the index of the spinner selected value is not 0 and the user has written a value, operations are performed.
        // Otherwise empty string result is assigned to textview
        if (spinner.selectedItemPosition!=0 && measurementValue.text.toString().isNotEmpty()){
            // A when statement was used according to the indexes selected in the spinner.
            when (spinner.selectedItemPosition) {
                // The measurementValue.text value is the value that the user enters into the editText.
                // the other unknown value, a new method is called for conversion to user selected target measurement type
                // The method converts the value we get from the user as a parameter to double and sends the parameter together with the method.
                // Select index 1 -> mile to km, 2 -> inch to cm, 3 -> ounce to G, 4 -> celsius to F, 5 -> celsius to K , 6 -> cup to L
                1 -> resultText.text ="${measurementValue.text} mile equals to ${miToKm(measurementValue.text.toString().toDouble())} km"
                2 -> resultText.text ="${measurementValue.text} inch equals to ${inToCm(measurementValue.text.toString().toDouble())} cm"
                3 ->resultText.text ="${measurementValue.text} ounce equals to ${ozToG(measurementValue.text.toString().toDouble())} G"
                4 -> resultText.text=  "${measurementValue.text} Celsius equals to ${cToF(measurementValue.text.toString().toDouble())} F"
                5 ->resultText.text=  "${measurementValue.text} Celsius equals to ${cToK(measurementValue.text.toString().toDouble())} K"
                6 -> resultText.text=   "${measurementValue.text} cup equals to ${cupToL(measurementValue.text.toString().toDouble())} L"
                else -> {
                }
            }
        }else{
            resultText.text=""
        }
    }
    // Convert the mile value we receive from the user to km.
    fun miToKm(toInt: Double):Double {
        return toInt*1.61;
    }
    // Convert the inch value we receive from the user to cm.
    fun inToCm(toInt: Double):Double {
        return toInt*2.54;
    }
    // Convert the inch value we receive from the user to gram.
    fun ozToG(toInt: Double):Double {
        return toInt*28.35;
    }
    // Convert the celsius value we receive from the user to fahrenheit.
    fun cToF(toInt: Double): Double {
        return (toInt*(9/5))+32;
    }
    // Convert the celsius value we receive from the user to kelvin.
    fun cToK(toInt: Double): Double {
        return toInt+273.15;
    }
    // Convert the cup value we receive from the user to liter.
    fun cupToL(toInt: Double): Double {
        return toInt*0.24;
    }
}




