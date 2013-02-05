package com.codeforyou.shoppinglist;

import android.app.Activity;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SettingsActivity extends Activity {

	public static final String APPLICATION_SETTINGS_FILE = "SHOPPING_APP_SETTINGS";
	public static final String KEY_SETTING_BUDGET = "BUDGET_SETTING";
	
	private EditText textBudget;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_settings);
	    
	    textBudget = (EditText)findViewById(R.id.textBudget);
	    textBudget.setText(String.format("%.2f", getBudget()));
	}
	
	public void buttonDoneClicked(View v) {
		setBudget(Float.parseFloat(textBudget.getText().toString()));
    	finish();
	}
	
	private float getBudget() {
		return getSharedPreferences(APPLICATION_SETTINGS_FILE, MODE_PRIVATE).getFloat(KEY_SETTING_BUDGET, 65f);
	}
	
	private void setBudget(float budget) {
		Editor editor = getSharedPreferences(APPLICATION_SETTINGS_FILE, MODE_PRIVATE).edit();
		editor.putFloat(KEY_SETTING_BUDGET, budget);
		editor.commit();
	}

}
