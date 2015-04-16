package com.bilgin.labyrinthes;


import java.util.ArrayList;

import com.bilgin.labyrinthes.Case.type;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
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
import android.widget.Toast;

public class Mouvement{

	private ExampleSurfaceView testView;
    
    private SensorManager mSensorManager = null;
    private Sensor mAccelerometer = null;
    public Boule boule=null;
    public ArrayList<Case> laby=null;
    public static  Activity jeuActivity;
    public Editor editor;
    public SharedPreferences pref ;
    public Mouvement(Boule bl,ArrayList<Case> l,Activity jeu)
    {
    	this.boule=bl;
    	this.laby=l;
    	this.jeuActivity=jeu;
    	this.pref = ((JeuActivity1)jeuActivity).getApplicationContext().getSharedPreferences("Scores", 0); // 0 - for private mode
    	this.editor = pref.edit();
    	
    	
    }
    
   final SensorEventListener mSensorEventListener = new SensorEventListener() {
    	
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
    	// que faire si changement de précision
    	}
    	public void onSensorChanged(SensorEvent sensorEvent) {
    	// que faire en cas d'événement sur le capteur
    	// dans notre cas, récupérer les valeurs x et y sufit...
    	float x = sensorEvent.values[0];
    	float y = sensorEvent.values[1];

 
      RectF hitBox=boule.putXAndY(x, y);     
        
    //	  Toast.makeText(jeuActivity.getApplicationContext()  .getApplicationContext(), "v "+x+"_"+y, Toast.LENGTH_SHORT).show(); 
     // 	  Toast.makeText(jeuActivity.getApplicationContext()  .getApplicationContext(), "b "+boule.vitesseX+"_"+boule.vitesseY, Toast.LENGTH_SHORT).show(); 
        
 	
     // Pour tous les blocs du labyrinthe
        for(Case block : laby) {
            // On crée un nouveau rectangle pour ne pas modifier celui du bloc
            RectF inter = new RectF(block);
            if(inter.intersect(hitBox)) {
                // On agit différement en fonction du type de bloc
                switch(block.getCaseType()) {
                case TROU:
                	jeuActivity.showDialog(0);
                	((JeuActivity1)jeuActivity).actionTimer(1);
                	((JeuActivity1)jeuActivity).testView.resetTime();
                	((JeuActivity1)jeuActivity).score -=50;
                    break;

                case DEPART:
                    break;

                case MUR:
                	((JeuActivity1)jeuActivity).score -=5;
                	if(boule.getX() !=x)
                		boule.changeXSpeed();
                	if(boule.getY() !=y)
                		boule.changeYSpeed();
                    break;
                case ARRIVE:
                	((JeuActivity1)jeuActivity).score +=300;
                	jeuActivity.showDialog(1);
                	((JeuActivity1) jeuActivity).actionTimer(1);
                	((JeuActivity1)jeuActivity).testView.resetTime();
                    break;
                }
                break;
            }
        }
     
 }

 };
    
    
	    
	    public void onCreate() {
	        

	        
	        //mSensorManager =(SensorManager)getSystemService(Context.SENSOR_SERVICE);
	        mSensorManager = (SensorManager) this.jeuActivity.getBaseContext().getSystemService(Context.SENSOR_SERVICE);
	        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
	        mSensorManager.registerListener(mSensorEventListener, mAccelerometer,SensorManager.SENSOR_DELAY_UI);
	        
        
	    }
	    
	  
	    public void onResume()
	    {
	    
	    	mSensorManager.registerListener(mSensorEventListener, mAccelerometer,SensorManager.SENSOR_DELAY_NORMAL);
	    }
	    
	    public void onPause()
	    {
	    	
	    	mSensorManager.unregisterListener(mSensorEventListener,mAccelerometer);
	    }

	    // Remet à zéro l'emplacement de la boule
	    public void reset() {
	        boule.reset();
        	((JeuActivity1) jeuActivity).actionTimer(1);
        	((JeuActivity1)jeuActivity).testView.resetTime();
	    }

	    // Arrête le capteur
	    public void stop() {
	    	mSensorManager.unregisterListener(mSensorEventListener, mAccelerometer);
	    }
	    
	} 

