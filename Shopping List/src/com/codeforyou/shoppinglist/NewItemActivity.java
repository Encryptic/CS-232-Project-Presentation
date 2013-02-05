package com.codeforyou.shoppinglist;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

public class NewItemActivity extends Activity {
	public static final String NAME_FIELD = "NAME";
	public static final String PRICE_FIELD = "PRICE";
	public static final String PRIORITY_FIELD = "PRIORITY";
	public static final String QUANTIY_FIELD = "QUANTITY";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newitem);
    }
    
    public void cancelButtonClicked(View v) {
    	setResult(RESULT_CANCELED);
    	finish();
    }
    
    public void createButtonClicked(View v) {
    	Intent intentResult = new Intent();
    	
    	EditText itemNameText = (EditText)findViewById(R.id.textItemName);
    	EditText itemPriceText = (EditText)findViewById(R.id.textItemPrice);
    	EditText itemQuantityText = (EditText)findViewById(R.id.textItemQuantity);
    	RadioGroup itemRadioGroup = (RadioGroup)findViewById(R.id.radioPriorities);
    	
    	// Precondition: All fields must be entered.
    	// TODO: Tell the user specifically which field is not set.
    	if (itemNameText.getText().length() == 0 || itemPriceText.getText().length() == 0 || itemQuantityText.getText().length() == 0 || itemRadioGroup.getCheckedRadioButtonId() == -1) {
    		showSimpleAlertDialog("All fields are required.  Please correct the problem and try again.");
    		return;
    	}
    	 	
    	try {
    		Integer quantity = Integer.parseInt(itemQuantityText.getText().toString());
        	Double price = Double.parseDouble(itemPriceText.getText().toString());
        	
        	if (price < 0.01) {
        		showSimpleAlertDialog("Price must be at least 1 cent.");
        		return;
        	}
        	
        	if (quantity < 1) {
        		showSimpleAlertDialog("Please enter a valid number of items to purchase (You must purchase at least one).");
        		return;
        	}
    		
        	intentResult.putExtra(NAME_FIELD, itemNameText.getText().toString());
        	intentResult.putExtra(PRICE_FIELD, price);
        	intentResult.putExtra(QUANTIY_FIELD, quantity);
	    	
	    	
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
    	catch (NumberFormatException exc)
    	{
    		showSimpleAlertDialog("Please input a valid number in the form of X.XX");
    	}
    }
    
	private void showSimpleAlertDialog(String message) {
		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setMessage(message);
		alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		alertDialog.show();
	}
}
