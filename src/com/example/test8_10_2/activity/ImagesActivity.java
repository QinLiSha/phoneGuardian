package com.example.test8_10_2.activity;

import com.example.test8_10_2.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

public class ImagesActivity extends Activity {
	private ImageView imageview;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_images);
		imageview = (ImageView) findViewById(R.id.imageView1);

		RotateAnimation rat = new RotateAnimation(360, 0,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);

		rat.setDuration(3000);
		imageview.setAnimation(rat);

		rat.setAnimationListener(new AnimationListener() {

			public void onAnimationStart(Animation animation) {

			}

			public void onAnimationRepeat(Animation animation) {

			}

			public void onAnimationEnd(Animation animation) {
				Intent intent = new Intent(ImagesActivity.this,
						HomeActivity.class);
				startActivity(intent);
			}
		});
	}

}
