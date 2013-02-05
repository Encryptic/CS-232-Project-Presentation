package com.example.shoppingexample2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

public class NewItemActivity extends Activity {
	
	// Constants to expose for accessing the activity fields.
	public static final String NAME_FIELD = "NAME";
	public static final String PRICE_FIELD = "PRICE";
	public static final String PRIORITY_FIELD = "PRIORITY";
	public static final String QUANTIY_FIELD = "QUANTITY";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newitem);
    }
    
    public void createButtonClicked(View v) {
    	Intent intentResult = new Intent();
    	
    	EditText itemNameText = (EditText)findViewById(R.id.textItemName);
    	EditText itemPriceText = (EditText)findViewById(R.id.textItemPrice);
    	RadioGroup itemRadioGroup = (RadioGroup)findViewById(R.id.radioPriorities);
    	 	
    	String itemName = itemNameText.getText().toString();
    	Double price = Double.parseDouble(itemPriceText.getText().toString());
    	
    	
    	intentResult.putExtra(NAME_FIELD, itemName);
    	intentResult.putExtra(PRICE_FIELD, price);


    	switch (itemRadioGroup.getCheckedRadioButtonId())
    	{
    	case R.id.radioHighPriority:
    		intentResult.putExtra(PRIORITY_FIELD, ShoppingPriority.High);
    		break;
    	case R.id.radioMediumPriority:
    		intentResult.putExtra(PRIORITY_FIELD, ShoppingPriority.Medium);
    		break;
    	case R.id.radioLowPriority:
    		intentResult.putExtra(PRIORITY_FIELD, ShoppingPriority.Low);
    		break;
    	}

    	setResult(RESULT_OK, intentResult);
    	finish();
    	
    }
    
    public void cancelButtonClicked(View v) {
    	setResult(RESULT_CANCELED);
    	finish();
    }
}
