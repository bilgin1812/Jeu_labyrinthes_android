package com.bilgin.labyrinthes;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.app.Activity;
import android.net.Uri;
import android.widget.Toast;

import com.bilgin.labyrinthes.Case.type;

public class Labyrinthes {
	private static final int NOMBRE_LIGNE = 40;
	private static final int NOMBRE_COLONNE = 24;
	public ArrayList<Case> laby;
	public Activity jeu;

	public  Labyrinthes(String dif,Activity jeu)
	{
		this.laby=new ArrayList<Case>();
		this.jeu=jeu;
		
		//Toast.makeText(this.jeu.getApplicationContext(), dif, 2).show();
		switch(dif)
		{
		case "1":buildLab2("laby1");
					break;
		case "2":buildLab2("laby2");
				break;		
		case "3":buildLab2("laby3");
				break;

		}
		
	}
	
	public void buildLab2(String file) 
	{
		int i = 0;
	
	
		try {	
			
			InputStreamReader rd= new InputStreamReader(this.jeu.getAssets().open(file));
			
			BufferedReader reader= new BufferedReader(rd);
			String line;
			while( (i< NOMBRE_LIGNE ) && (line=reader.readLine()) != null)
			{
				
				for(int index=0;index<NOMBRE_COLONNE;index++){
					char c;
					try {
						c=line.charAt(index);
					}
					catch (Exception e)
					{
						c=' ';
					}
					switch(c)
					{
					case 'x':laby.add(new Case(type.MUR, i, index));
					//Toast.makeText(this.jeu.getApplicationContext(),"mur "+i+" "+index, 2).show();
							break;
							
					case 'h':laby.add(new Case(type.TROU, i, index));
						break;
						
					case 'd':laby.add(new Case(type.DEPART, i, index));
						break;
						
					case 'a':laby.add(new Case(type.ARRIVE, i, index));
						break;
	
					}				
				
				}
				i++;
				
			}
			
			
		}
		catch (FileNotFoundException e) {
			Toast.makeText(this.jeu.getApplicationContext(), e.getMessage(), 2).show();
			e.printStackTrace();
		}
		catch (IOException e) {
			Toast.makeText(this.jeu.getApplicationContext(), e.getMessage(), 2).show();
			e.printStackTrace();
		}
		
	}
		
	
	public void buildLab3()
	{
		
	}
	
	public void buildLab1()
	{
		/*****************************/
		
		laby.add(new Case(type.MUR, 0, 0));

        laby.add(new Case(type.MUR, 0, 1));

        laby.add(new Case(type.MUR, 0, 2));

        laby.add(new Case(type.MUR, 0, 3));

        laby.add(new Case(type.MUR, 0, 4));

        laby.add(new Case(type.MUR, 0, 5));

        laby.add(new Case(type.MUR, 0, 6));

        laby.add(new Case(type.MUR, 0, 7));

        laby.add(new Case(type.MUR, 0, 8));

        laby.add(new Case(type.MUR, 0, 9));

        laby.add(new Case(type.MUR, 0, 10));

        laby.add(new Case(type.MUR, 0, 11));

        laby.add(new Case(type.MUR, 0, 12));

        laby.add(new Case(type.MUR, 0, 13));

        laby.add(new Case(type.MUR, 1, 0));

        laby.add(new Case(type.MUR, 1, 13));

        laby.add(new Case(type.MUR, 2, 0));

        laby.add(new Case(type.MUR, 2, 13));

        laby.add(new Case(type.MUR, 3, 0));

        laby.add(new Case(type.MUR, 3, 13));

        laby.add(new Case(type.MUR, 4, 0));

        laby.add(new Case(type.MUR, 4, 1));

        laby.add(new Case(type.MUR, 4, 2));

        laby.add(new Case(type.MUR, 4, 3));

        laby.add(new Case(type.MUR, 4, 4));

        laby.add(new Case(type.MUR, 4, 5));

        laby.add(new Case(type.MUR, 4, 6));

        laby.add(new Case(type.MUR, 4, 7));

        laby.add(new Case(type.MUR, 4, 8));

        laby.add(new Case(type.MUR, 4, 9));

        laby.add(new Case(type.MUR, 4, 10));

        laby.add(new Case(type.MUR, 4, 13));

        laby.add(new Case(type.MUR, 5, 0));

        laby.add(new Case(type.MUR, 5, 13));

        laby.add(new Case(type.MUR, 6, 0));

        laby.add(new Case(type.MUR, 6, 13));

        laby.add(new Case(type.MUR, 7, 0));

        laby.add(new Case(type.MUR, 7, 1));

        laby.add(new Case(type.MUR, 7, 2));

        laby.add(new Case(type.MUR, 7, 5));

        laby.add(new Case(type.MUR, 7, 6));

        laby.add(new Case(type.MUR, 7, 9));

        laby.add(new Case(type.MUR, 7, 10));

        laby.add(new Case(type.MUR, 7, 11));

        laby.add(new Case(type.MUR, 7, 12));

        laby.add(new Case(type.MUR, 7, 13));

        laby.add(new Case(type.MUR, 8, 0));

        laby.add(new Case(type.MUR, 8, 5));

        laby.add(new Case(type.MUR, 8, 9));

        laby.add(new Case(type.MUR, 8, 13));

        laby.add(new Case(type.MUR, 9, 0));

        laby.add(new Case(type.MUR, 9, 5));

        laby.add(new Case(type.MUR, 9, 9));

        laby.add(new Case(type.MUR, 9, 13));

        laby.add(new Case(type.MUR, 10, 0));

        laby.add(new Case(type.MUR, 10, 5));

        laby.add(new Case(type.MUR, 10, 9));

        laby.add(new Case(type.MUR, 10, 13));

        laby.add(new Case(type.MUR, 11, 0));

        laby.add(new Case(type.MUR, 11, 5));

        laby.add(new Case(type.MUR, 11, 9));

        laby.add(new Case(type.MUR, 11, 13));

        laby.add(new Case(type.MUR, 12, 0));

        laby.add(new Case(type.MUR, 12, 1));

        laby.add(new Case(type.MUR, 12, 2));

        laby.add(new Case(type.MUR, 12, 3));

        laby.add(new Case(type.MUR, 12, 4));

        laby.add(new Case(type.MUR, 12, 5));

        laby.add(new Case(type.MUR, 12, 9));

        laby.add(new Case(type.MUR, 12, 8));

        laby.add(new Case(type.MUR, 12, 13));

        laby.add(new Case(type.MUR, 13, 0));

        laby.add(new Case(type.MUR, 13, 8));

        laby.add(new Case(type.MUR, 13, 13));

        laby.add(new Case(type.MUR, 14, 0));

        laby.add(new Case(type.MUR, 14, 8));

        laby.add(new Case(type.MUR, 14, 13));

        laby.add(new Case(type.MUR, 15, 0));

        laby.add(new Case(type.MUR, 15, 8));

        laby.add(new Case(type.MUR, 15, 13));

        laby.add(new Case(type.MUR, 16, 0));

        laby.add(new Case(type.MUR, 16, 4));

        laby.add(new Case(type.MUR, 16, 5));

        laby.add(new Case(type.MUR, 16, 6));

        laby.add(new Case(type.MUR, 16, 7));

        laby.add(new Case(type.MUR, 16, 8));

        laby.add(new Case(type.MUR, 16, 9));

        laby.add(new Case(type.MUR, 16, 13));

        laby.add(new Case(type.MUR, 17, 0));

        laby.add(new Case(type.MUR, 17, 13));

        laby.add(new Case(type.MUR, 18, 0));

        laby.add(new Case(type.MUR, 18, 13));
        
        laby.add(new Case(type.MUR, 19, 0));

        laby.add(new Case(type.MUR, 19, 13));
        
        laby.add(new Case(type.MUR, 20, 0));

        laby.add(new Case(type.MUR, 20, 13));
        
        laby.add(new Case(type.MUR, 21, 0));

        laby.add(new Case(type.MUR, 21, 13));

        laby.add(new Case(type.MUR, 22, 0));

        laby.add(new Case(type.MUR, 22, 1));

        laby.add(new Case(type.MUR, 22, 2));

        laby.add(new Case(type.MUR, 22, 3));

        laby.add(new Case(type.MUR, 22, 4));

        laby.add(new Case(type.MUR, 22, 5));

        laby.add(new Case(type.MUR, 22, 6));

        laby.add(new Case(type.MUR, 22, 7));

        laby.add(new Case(type.MUR, 22, 8));

        laby.add(new Case(type.MUR, 22, 9));

        laby.add(new Case(type.MUR, 22, 10));

        laby.add(new Case(type.MUR, 22, 11));

        laby.add(new Case(type.MUR, 22, 12));

        laby.add(new Case(type.MUR, 22, 13));

        laby.add(new Case(type.DEPART, 2, 2));

        laby.add(new Case(type.ARRIVE, 8, 11));


	/*******************************/
	}

}
