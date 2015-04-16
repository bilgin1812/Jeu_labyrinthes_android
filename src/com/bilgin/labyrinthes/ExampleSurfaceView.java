package com.bilgin.labyrinthes;

import java.util.ArrayList;

import com.bilgin.labyrinthes.Case.type;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Chronometer;
import android.widget.Toast;
import android.widget.Chronometer.OnChronometerTickListener;

 public class ExampleSurfaceView extends SurfaceView implements
		SurfaceHolder.Callback {
	// Le holder
	SurfaceHolder mSurfaceHolder;
	// Le thread dans lequel le dessin se fera DrawingThread mThread;
	DrawingThread mThread;
	Paint mPaint;
	public Boule boule;
	public static ArrayList<Case> laby;
	//RectF mRect = new RectF(20, 20, 780, 1140);
	//RectF mRect2 = new RectF(100, 100, 580, 800);
	Chronometer timer;
    private long elapsedTime=0;
    public int millisUntilFinished ;
	
	
    public int seconds ;
    public  int minutes ;
    public int hours ;
	


	public ExampleSurfaceView(Context context,ArrayList<Case> l,Boule bl, Chronometer timer) {
		super(context);
		mSurfaceHolder = getHolder();
		mSurfaceHolder.addCallback(this);
		mThread = new DrawingThread();
		mPaint = new Paint();
		mPaint.setStyle(Paint.Style.FILL);
		this.timer=timer;
	
		
	
		this.laby= l;
		this.boule=bl;
		 timer.setBase(SystemClock.elapsedRealtime());
		this.timer.start();
	
	
	}

	@Override
	protected void onDraw(Canvas pCanvas) {
		

	    
		// Dessinez ici !
		int x = 200;
		int y = 100;
		pCanvas.drawColor(Color.LTGRAY);
		mPaint.setColor(Color.WHITE);
		mPaint.setTextSize(30);
		//pCanvas.drawRect(mRect, mPaint);
	
		for(Case Case : this.laby)
		{
			switch(Case.getCaseType())
			{
			
			case TROU:  mPaint.setColor(Color.RED);
						break;
			case MUR:  mPaint.setColor(Color.BLACK);
						break;
			
			case DEPART:  mPaint.setColor(Color.CYAN);
						break;
				
			case ARRIVE:  mPaint.setColor(Color.GREEN);
						break;
			}
			pCanvas.drawRect(Case, mPaint);
			

			// rectangle de collision pour la boule
			//mPaint.setColor(Color.WHITE);
			//pCanvas.drawRect(this.boule.rectCollision, mPaint);

			mPaint.setColor(Color.YELLOW);
			if(this.boule != null)
				pCanvas.drawCircle(this.boule.getX(), this.boule.getY(), this.boule.RAYON , mPaint);
			 millisUntilFinished =(int) ((SystemClock.elapsedRealtime() - timer.getBase()) / 1000);
			 seconds = (int) ((millisUntilFinished)%60);
		     minutes = (int) ((millisUntilFinished)/60);
		     hours = (int) ((millisUntilFinished)/3600);
		        
			pCanvas.drawText(" "+minutes+":"+seconds, 20, 30, mPaint);

			
		}
		


	}
	public void resetTime()
	{
		this.hours=0;
		this.minutes=0;
		this.seconds=0;
	}

	private Context getApplicationContext() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// Que faire quand le surface change ? (Si l'utilisateur tourne son té
		// lé phone par exemple)
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		mThread.keepDrawing = true;
        // Quand on crée la boule, on lui indique les coordonnées de l'écran
        if(boule != null ) {
            this.boule.setHeight(getHeight());
            this.boule.setWidth(getWidth());
        }
   
		mThread.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		mThread.keepDrawing = false;
		boolean joined = false;
		while (!joined) {
			try {
				mThread.join();
				joined = true;
			} catch (InterruptedException e) {
			}
		}
	}

	private class DrawingThread extends Thread {
		// pour arreter le dessin quand il le faut
		boolean keepDrawing = true;

		 public void run() {
			while (keepDrawing) {
				Canvas canvas = null;
				try {
					// on chope le canvas pour dessiner dessus
					canvas = mSurfaceHolder.lockCanvas();
					// on s'assure qu'aucun autre thread n'y accède
					synchronized (mSurfaceHolder) {
						// et on dessine
						onDraw(canvas);
					}
				} finally {
					// le dessin terminé on relâche
					// le canvas pour que le dessin s'affiche
					if (canvas != null)
						mSurfaceHolder.unlockCanvasAndPost(canvas);
				}
				// pour dessiner a 50 fps;
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
				}
			}
		}

	}
	
	
}