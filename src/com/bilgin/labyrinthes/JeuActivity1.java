package com.bilgin.labyrinthes;

import java.util.ArrayList;

import com.bilgin.labyrinthes.Case.type;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.FragmentActivity;
import android.widget.Chronometer;
import android.widget.Toast;

public class JeuActivity1   extends FragmentActivity implements GamePopup.Communicator{

	private static final int VICTORY_DIALOG = 1;

	private static final int DEFEAT_DIALOG = 0;

	public ExampleSurfaceView testView;

    public Boule boule;
    public ArrayList<Case> laby;
    public Mouvement engine;
    public int score=0;
    public Boolean won=false;
    public Chronometer timer;
    

 @Override
protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState); 
	        ArrayList<Case> laby;
	     
	        
	        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
	        setTheme(android.R.style.Theme_Light_NoTitleBar_Fullscreen);
	        String dif= (String) getIntent().getExtras().get("difficulty");
	        
	        this.boule=new Boule();
	        boule.setInitialRectangle(new Case(type.DEPART, 2, 5));
	       
	        this.timer= new Chronometer(getBaseContext());
	        
	        Toast.makeText(getApplicationContext()  .getApplicationContext(), "difficulty "+dif, Toast.LENGTH_SHORT).show(); 
	        Labyrinthes l= new Labyrinthes(dif,this);
	        this.laby=l.laby;
	        laby=this.laby;
	        testView = new ExampleSurfaceView(this,this.laby,this.boule, this.timer);
	        setContentView(testView);
	        this.engine= new Mouvement(this.boule, this.laby, this);
	        engine.onCreate();

	        
        
	    }
 
	public void menu(){
		
		
		GamePopup gp = new GamePopup();
		Bundle args = new Bundle();
		
		args.putBoolean("win", this.won);
		args.putInt("score", this.score);
	
		gp.setArguments(args);
		
		gp.show(getSupportFragmentManager(), "MyDialog");
	}
	    
	    @Override
	    protected void onResume()
	    {
	    	super.onResume();
	    	engine.onResume();
	    	
	    }
	    
	    @Override
	    protected void onPause()
	    {
	    	super.onPause();
	    
	    	engine.onPause();
	    	
	    }
	    public void  actionTimer(int action)
	    {

	    	switch(action){
		    	
		    	case 0:	
			    	//restart timer
		        	this.timer.setText("00:00");
		        	this.timer.setBase(SystemClock.elapsedRealtime());
		    		this.timer.start();
		    		break;
			    case 1://stop timer
			    		this.timer.stop();
			    		break;
			    		
			    case 2:;
			    		break;
		    }
	    	
	    }

	    @Override
	    public Dialog onCreateDialog (int id) {
	        AlertDialog.Builder builder = new AlertDialog.Builder(this);
	        switch(id) {
	        case VICTORY_DIALOG:
	        	
	            builder.setCancelable(false)
	            .setMessage("Bravo, vous avez gagné !")
	            .setTitle("Champion !  Score :"+this.score)
	            .setNeutralButton("Recommencer", new DialogInterface.OnClickListener() {
	                @Override
	                public void onClick(DialogInterface dialog, int which) {
	                    // L'utilisateur peut recommencer s'il le veut
	                    engine.reset();
	                    engine.onResume();
	                }
	            });
	            break;

	        case DEFEAT_DIALOG:
	        	
	            builder.setCancelable(false)
	            .setMessage("Perduuuu.")
	            .setTitle("Bah bravo ! Score :"+this.score)
	            .setNeutralButton("Recommencer", new DialogInterface.OnClickListener() {
	                @Override
	                public void onClick(DialogInterface dialog, int which) {
	                	engine.reset();
	                    engine.onResume();
	                }
	            });
	        }
	        return builder.create();
	    }

	    @Override
	    public void onPrepareDialog (int id, Dialog box) {
	        // A chaque fois qu'une boîte de dialogue est lancée, on arrête le moteur physique
	        engine.stop();
	    }

		@Override
		public void onDialogMessage(int resId) {
			// TODO Auto-generated method stub
			
		}


	   
	    
	} 

