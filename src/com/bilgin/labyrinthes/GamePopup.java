package com.bilgin.labyrinthes;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class GamePopup extends DialogFragment implements View.OnClickListener{
	
	private View view;
	private boolean win;
	private int score;
	private ImageView icon;
	private TextView title;
	private TextView content;
	private Button positive;
	private Button negative;
	private Communicator communicator;

	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		this.score = getArguments().getInt("score");
		this.win = getArguments().getBoolean("win");

	}

	@Override
	public void onAttach(Activity activity){
		super.onAttach(activity);
		this.communicator = (Communicator)activity;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceStats){
		this.view = inflater.inflate(R.layout.popup, null);
		
		this.positive = (Button)this.view.findViewById(R.id.positive);
		this.negative = (Button)this.view.findViewById(R.id.negative);
		this.negative.setOnClickListener(this);
		this.positive.setOnClickListener(this);
		
		this.title = (TextView)this.view.findViewById(R.id.title);
		this.icon = (ImageView)this.view.findViewById(R.id.image);		
		
		if(this.win){
			this.setIcon(R.drawable.win);
			this.setTitle(" Vous avez gagné!");
		}else{
			this.setIcon(R.drawable.win);
			this.setTitle(" Vous avez perdu!");
		}
		
		this.content = (TextView)this.view.findViewById(R.id.score);
		this.content.setGravity(Gravity.CENTER_HORIZONTAL);
		this.content.setText("Votre score " + this.score);

		this.setCancelable(false);
		this.getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		
		return view;
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
			case(R.id.positive):
				this.communicator.onDialogMessage(R.id.positive);
				break;
			case(R.id.negative):
				this.communicator.onDialogMessage(R.id.negative);
				break;
		}
		this.dismiss();
	}
	

	public void setIcon(int resid){
		this.icon.setBackgroundResource(R.drawable.win);
	}
	
	public void setTitle(CharSequence text){
		this.title.setText(text);
	}
	
	interface Communicator{
		public void onDialogMessage(int resId);
	}
	

}
