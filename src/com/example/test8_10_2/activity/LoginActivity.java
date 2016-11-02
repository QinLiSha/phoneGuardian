package com.example.test8_10_2.activity;

import com.example.test8_10_2.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

public class LoginActivity extends Activity {

	private ImageView iv_login;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		iv_login = (ImageView) findViewById(R.id.iv_title);
		AlphaAnimation alp = new AlphaAnimation(0, 1);
		alp.setDuration(3*1000);
		iv_login.setAnimation(alp);
		alp.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
				startActivity(intent);
			}
		});
		
	}


}
