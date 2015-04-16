package com.bilgin.labyrinthes;
import com.bilgin.labyrinthes.R;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
	
	
	  //Notez qu'on utilise Menu.FIRST pour indiquer le premier élément d'un menu
   private final static int  MENU_DESACTIVER = Menu.FIRST;
   private final static int  MENU_RETOUR = Menu.FIRST + 1;
   public String dif="1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      
        
        Button btnStart= (Button)findViewById(R.id.button1);
        Button btnScore= (Button)findViewById(R.id.button2);
        final TextView difficulty = (TextView)findViewById(R.id.difficulty);
        TextView about = (TextView)findViewById(R.id.textView2);
        
        
        difficulty.setOnClickListener(new  OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 
				 openContextMenu(v);
 																				

				
			}
		});
        registerForContextMenu(difficulty);
        
        about.setOnClickListener(new  OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, AboutActivity.class);
				startActivity(intent);
				

				
			}
		});
        
        
        btnStart.setOnClickListener(new  OnClickListener() {

			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent(MainActivity.this, JeuActivity1.class);
				intent.putExtra("difficulty", dif);
				
				startActivity(intent);
			
			}
		});
        
        btnScore.setOnClickListener(new  OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, ScoreActivity.class);
				startActivity(intent);
				

				
			}
		});
        
    }

 


    @Override

    public void onCreateContextMenu(ContextMenu menu, View vue, ContextMenuInfo menuInfo) {

      super.onCreateContextMenu(menu, vue, menuInfo);

      getMenuInflater().inflate(R.layout.menu_niveau, menu);
      menu.setHeaderTitle("difficulty");
      menu.add(Menu.NONE, MENU_RETOUR, Menu.NONE, "Retour");

    } 
    
  
    public boolean onContextItemSelected(MenuItem item)
    {
    	switch(item.getItemId())
    	{
    	case R.id.item1:this.dif="1";
    					break;
    	case R.id.item2:this.dif="2";
						break;
    	case R.id.item3:this.dif="3";
						break;
    					
    	
    	}
		return false;
    	
    }
}
