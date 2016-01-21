package com.example.getreadynow.conversionapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity {


    private Spinner unitTypeSpinner;
    private EditText amountTextView;
    private static final String TAG = "CONVERSIONAPP";

    TextView tspTextView, tbsTextView, cupTextView, ounceTextView,
             pintTextView, quartTextView, gallonTextView, poundTextView,
             mlTextView, literTextView, mgTextView, kgTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Fill the spinner with unit choices for the user
        addItemsToUnitTypeSpinner();

        //Add a On Click Listener for the spinner
        addListenerToUnitTypeSpinner();

        //Get a handle to the amount tect
        amountTextView = (EditText) findViewById(R.id.amount_text_view);

        //Get handle to textviews to edit later on
        initializeTextViews ();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void initializeTextViews() {

        //Get a handle to all text views to make them easier to change later
        tspTextView= (TextView) findViewById(R.id.tsp_text_view);
        tbsTextView = (TextView) findViewById(R.id.tbs_text_view);
        cupTextView = (TextView) findViewById(R.id.cup_text_view);
        ounceTextView = (TextView) findViewById(R.id.oz_text_view);
        pintTextView = (TextView) findViewById(R.id.pint_text_view);
        quartTextView = (TextView) findViewById(R.id.quart_text_view);
        gallonTextView = (TextView) findViewById(R.id.gallon_text_view);
        poundTextView = (TextView) findViewById(R.id.pound_text_view);
        mlTextView = (TextView) findViewById(R.id.ml_text_view);
        literTextView = (TextView) findViewById(R.id.liter_text_view);
        mgTextView = (TextView) findViewById(R.id.mg_text_view);
        kgTextView = (TextView) findViewById(R.id.kg_text_view);

    }

    public void addItemsToUnitTypeSpinner () {
        unitTypeSpinner = (Spinner) findViewById(R.id.unit_type_spinner);

        //Configure the adapter array with your own
        ArrayAdapter<CharSequence> unitTypeSpinnerAdapter =
                ArrayAdapter.createFromResource(this,
                        R.array.conversion_types,
                        android.R.layout.simple_spinner_item);

        //Set the adapter to the spinner
        unitTypeSpinner.setAdapter(unitTypeSpinnerAdapter);
    }


    public void addListenerToUnitTypeSpinner() {
        unitTypeSpinner = (Spinner) findViewById(R.id.unit_type_spinner);
        unitTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //Read the item that was selected from the listener
                String itemSelectedInListener = parent.getItemAtPosition(position).toString();

                Log.d(TAG, "Item Selected is: " + itemSelectedInListener);

                //Verify which conversion function to use
                CheckIfConvertingFromTsp(itemSelectedInListener);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Add something here later
            }
        });
    }

    public void CheckIfConvertingFromTsp(String currentUnit) {
        if (currentUnit.equals("teaspoon")){
            //If the unit selected is teaspoon, update all units based on their teaspoon equivalent
            UpdateUnitTypeUsingTsp(Quantity.Unit.tsp);
        } else {
            //If the unit selected is not teaspoon, convert first to tsp, then to the final unit
            if (currentUnit.equals("tablespoon")){
                updateUnitTypeUsingOther(Quantity.Unit.tbs);
            } else if (currentUnit.equals("cup")) {
                updateUnitTypeUsingOther(Quantity.Unit.cup);
            } else if (currentUnit.equals("ounce")) {
                updateUnitTypeUsingOther(Quantity.Unit.oz);
            } else if (currentUnit.equals("pint"))  {
                updateUnitTypeUsingOther(Quantity.Unit.pint);
            } else if (currentUnit.equals("quart")) {
                updateUnitTypeUsingOther(Quantity.Unit.quart);
            } else if (currentUnit.equals("gallon")) {
                updateUnitTypeUsingOther(Quantity.Unit.gallon);
            } else if (currentUnit.equals("pound")) {
                updateUnitTypeUsingOther(Quantity.Unit.pound);
            } else if (currentUnit.equals("milliliter")) {
                updateUnitTypeUsingOther(Quantity.Unit.ml);
            } else if (currentUnit.equals("liter")) {
                updateUnitTypeUsingOther(Quantity.Unit.liter);
            } else if (currentUnit.equals("kilogram")) {
                updateUnitTypeUsingOther(Quantity.Unit.kg);
            }
        }
    }

    public void UpdateUnitTypeUsingTsp(Quantity.Unit currentUnit) {

        //If we are converting from TSP, then read the value from the amount txt view
        //and paste it into the tsp text view, after all it remains the same
        double doubleToConvert = Double.parseDouble(amountTextView.getText().toString());
        String tspValueAndUnit = doubleToConvert + " tsp";
        tspTextView.setText(tspValueAndUnit);

        //Now take care of the rest


        UpdateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.tbs, tbsTextView);
        UpdateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.cup, cupTextView);
        UpdateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.oz, ounceTextView);
        UpdateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.pint, pintTextView);
        UpdateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.quart, quartTextView);
        UpdateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.gallon, gallonTextView);
        UpdateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.pound, poundTextView);
        UpdateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.ml, mlTextView);
        UpdateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.liter, literTextView);
        UpdateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.kg, kgTextView);

    }

    public void UpdateUnitTextFieldUsingTsp(double doubleToConvert, Quantity.Unit unitToConvertTo,
                                            TextView textViewToUpdate) {
        Quantity baseUnitQuantity = new Quantity(doubleToConvert, Quantity.Unit.tsp);
        String convertedStringValue = baseUnitQuantity.to(unitToConvertTo).toString();
        textViewToUpdate.setText(convertedStringValue);
    }


    public void updateUnitTypeUsingOther (Quantity.Unit currentUnit) {

        double doubleToConvert = Double.parseDouble(amountTextView.getText().toString());
        Quantity currentQuantitySelected = new Quantity(doubleToConvert, currentUnit);

        String valueAndStringInTsp = currentQuantitySelected.to(Quantity.Unit.tsp).toString();

        //Set the text for the teaspoon TextView
        tspTextView.setText(valueAndStringInTsp);

        //Now convert to the other units
        UpdateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quantity.Unit.tbs, tbsTextView);
        UpdateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quantity.Unit.cup, cupTextView);
        UpdateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quantity.Unit.oz, ounceTextView);
        UpdateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quantity.Unit.pint, pintTextView);
        UpdateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quantity.Unit.quart, quartTextView);
        UpdateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quantity.Unit.gallon, gallonTextView);
        UpdateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quantity.Unit.pound, poundTextView);
        UpdateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quantity.Unit.ml, mlTextView);
        UpdateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quantity.Unit.liter, literTextView);
        UpdateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quantity.Unit.kg, kgTextView);


        if (currentUnit.name().equals(currentQuantitySelected.unit.name())) {

            String currentUnitTextViewText =  doubleToConvert + " " + currentQuantitySelected.unit.name();

            //Create the text view unit name
            String textViewName = currentQuantitySelected.unit.name() + "_text_view";

            //get the resource ID
            int resourceID = getResources().getIdentifier(textViewName, "id", MainActivity.this.getPackageName());

            //get a handle to the textview
            TextView currentTextView = (TextView) findViewById(resourceID);

            //put the right data in the TextView
            currentTextView.setText(currentUnitTextViewText);
        }
    }

    public void UpdateUnitTextFieldUsingTsp(double doubleToConvert, Quantity.Unit currentUnit,
                                            Quantity.Unit preferredUnit, TextView textViewToEdit) {

        Quantity currentQuantitySelected = new Quantity(doubleToConvert, currentUnit);

        //Algorithm used quantityInTbs.to(Unit.tsp).to(Unit.ounce)

        String tempTextViewTxt = currentQuantitySelected.to(Quantity.Unit.tsp).
                to(preferredUnit).toString();

        textViewToEdit.setText(tempTextViewTxt);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
