package vn.game.speedorder.logic;

import vn.game.speedorder.GameActivity;
import vn.game.speedorder.utils._Log;
import android.os.CountDownTimer;
import android.os.Handler;

/**
 * The class in charge of handling game's runtime.
 * 
 * @author DUC QUYNH
 * 
 */
public class Timer {

	private Handler handler;
	/**
	 * CountDownTimer to count down the time we use for a cycle of game.
	 */
	private CountDownTimer downTimer;
	/**
	 * Time we use for a game's cycle.
	 */
	private long countingTime = 1800;

	public Timer(Handler handler) {
		this.handler = handler;
	}

	void setHandler(Handler handler) {
		this.handler = handler;
	}

	/**
	 * Start a new cycle of game's timing.
	 */
	public void doStart() {
		destroyTimer();
		downTimer = new CountDownTimer(countingTime, 1000) {
			@Override
			public void onTick(long millisUntilFinished) {
			}

			@Override
			public void onFinish() {
				if (!GameActivity.losed) {
					handler.sendEmptyMessage(0);
					_Log.i("fail reason", "the cycle time has elapsed out.");
				}
				GameActivity.losed = true;
			}
		};
		downTimer.start();
	}

	/**
	 * Destroys the recent CountDownTimer we used, if we have.
	 */
	public void destroyTimer() {
		if (downTimer != null) {
			downTimer.cancel();
			downTimer = null;
		}
	}
}
