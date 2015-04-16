package com.bilgin.labyrinthes;

import android.graphics.RectF;

public class Case extends RectF{
	
	public enum type {MUR,TROU,DEPART,ARRIVE};
	
	private type caseType=null;
	private double X;
	private double Y;
	private double taille=2*Boule.RAYON;
	
	public Case(type Type , int X, int Y)
	{
		super();
		this.caseType=Type;
		this.X=X;
		this.Y=Y;
		this.left=(float) (X*taille);
		this.right=(float) ((X+1)*taille);
		this.top=(float) ((Y)*taille);
		this.bottom=(float) ((Y+1)*taille);
	}
	
	public type getCaseType() {
		return caseType;
	}
	public void setCaseType(type caseType) {
		this.caseType = caseType;
	}
	public double getX() {
		return X;
	}
	public void setX(double x) {
		X = x;
	}
	public double getY() {
		return Y;
	}
	public void setY(double y) {
		Y = y;
	}
	public double getTaille() {
		return taille;
	}
	public void setTaille(double taille) {
		this.taille = taille;
	}


	

}
