package vn.game.speedorder;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class EndGameLayout extends LinearLayout {

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public EndGameLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	public EndGameLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public EndGameLayout(Context context) {
		super(context);       
	}
	
}
