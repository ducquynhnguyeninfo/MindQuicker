package vn.game.speedorder;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;

public class EndGameDialog {

	private Animation animShow, animHide;
	EndGameLayout popup;
	final ImageButton replay;

	private Activity activity;
	public EndGameDialog(Activity activity) {
		this.activity = activity;
		
		replay = (ImageButton) ((Activity) activity).findViewById(R.id.replay);
		popup = (EndGameLayout) ((Activity) activity)
				.findViewById(R.id.endGamePopup);
		while (popup == null) {
			_Log.d("popup", " is null");
			_Log.d("popup", " trying to cast value to popup");
			popup = (EndGameLayout) ((Activity) activity)    
					.findViewById(R.id.endGamePopup);
		}
		initPopup(activity);
//		((Activity)context).setContentView(R.layout.end_game_dialog);
	}

	public void showPopup() {
		
		popup.setVisibility(View.VISIBLE);
		popup.startAnimation(animShow);
	}

	private void initPopup(Context context) {

		popup.setVisibility(View.GONE);
		animShow = AnimationUtils.loadAnimation(context, R.anim.popup_show);
		animHide = AnimationUtils.loadAnimation(context, R.anim.popup_hide);

		replay.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
//				popup.startAnimation(animHide);
				popup.setVisibility(View.GONE);
				((GameActivity)activity).makeNewRoundGame();
			}
		});
	}

}
