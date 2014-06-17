package vn.game.speedorder;

import android.os.CountDownTimer;
import android.os.Handler;

public class Timer {

	private Handler handler;
	private CountDownTimer downTimer;
	
	public Timer(Handler handler) {
		this.handler = handler;
	}

	void setHandler(Handler handler) {
		this.handler = handler;
	}

	public void doStart() {
		if(downTimer != null){
			downTimer.cancel();
			downTimer = null;
		}
			
		downTimer = new CountDownTimer(1800, 1000) {

			@Override
			public void onTick(long millisUntilFinished) {
			}

			@Override
			public void onFinish() {
				handler.sendEmptyMessage(0);
			}
		};
		
		downTimer.start();
	}
	
	public void destroyTimer() {
		if(downTimer != null){
			downTimer.cancel();
			downTimer = null;
		}
	}
}
