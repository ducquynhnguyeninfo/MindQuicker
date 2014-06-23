package vn.game.speedorder;

import java.util.ArrayList;
import java.util.List;

import vn.game.speedorder.logic.ResultChecker;
import vn.game.speedorder.logic.Timer;
import vn.game.speedorder.models.ElementSupplier;
import vn.game.speedorder.models.NotificationButton;
import vn.game.speedorder.utils._Log;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

/**
 * {@link GameActivity} handles all main game's processes. 
 * @author DUC QUYNH
 *
 */
public class GameActivity extends Activity implements OnNotifyChangedListener {

	/**
	 * The flag indicates the game's state.
	 */
	public static boolean losed = false;
	/**
	 * Primitive list of number unarranged.
	 */
	private List<Integer> rawElements;
	private List<Integer> play = new ArrayList<Integer>();
	private byte countClick = 0;

	private NotificationButton butt1;
	private NotificationButton butt2;
	private NotificationButton butt3;
	private EndGameDialog endGameDialog;
	private Timer timer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play);
		initialize();
	}

	/**
	 * Initialize game's fundamental components.
	 */
	private void initialize() {
		timer = new Timer(handler);
		butt1 = (NotificationButton) findViewById(R.id.button1);
		butt2 = (NotificationButton) findViewById(R.id.button2);
		butt3 = (NotificationButton) findViewById(R.id.button3);
		butt1.setOnClickListener(this);
		butt2.setOnClickListener(this);
		butt3.setOnClickListener(this);
		endGameDialog = new EndGameDialog(this);
		makeNewCycle();
	}

	/**
	 * Handler handles game's announcements.
	 */
	Handler handler = new Handler(new Handler.Callback() {
		@Override
		public boolean handleMessage(Message msg) {
			getLose();
			return false;
		}
	});

	@Override
	public void onClick(View button) {
		countClick++;
		_Log.v("view clicked id -- number " + countClick, button.getId() + "");
		setButtonImgOrder(button);
		/*
		 * If clicked all the button, then we are going to check the result.
		 */
		if (countClick == rawElements.size()) {
			countClick = 0;
			ResultChecker resultChecker = new ResultChecker(getElements(), play);
			if (resultChecker.isFailed()) {
				if (!losed) {
					handler.sendEmptyMessage(0);
					_Log.i("fail reason", "the arrangement is not correct.");
				}
				losed = true;
			} else {
				play.removeAll(play);
				makeNewCycle();
			}
		}
	}

	private List<Integer> getElements() {
		return this.rawElements;
	}

	/**
	 * Set the image resource to the order tags of each
	 * {@link NotificationButton}.
	 * 
	 * @param button
	 *            The button has compounded in {@link NotificationButton}
	 * 
	 */
	private void setButtonImgOrder(View button) {
		switch (viewParentClicked(button).getId()) {
		case R.id.button1:
			/*
			 * Adding element to the position at (countClick - 1) with the value
			 * of button.getText().
			 */
			play.add(countClick - 1, Integer.valueOf((String) butt1.getText()));
			if (!butt1.wasClicked()) {
				// _Log.v("button1 id", R.id.button1 + "");
				butt1.setOrderTagSource(setOrder(countClick))
						.setOrderTagChangeable(false).setWasClicked(true);
			} else { // If the button has clicked twice, we failed.
				if (!losed) {
					handler.sendEmptyMessage(0);
					_Log.i("fail reason", "click button 1 twice");
				}
				losed = true;
			}
			break;
		case R.id.button2:
			play.add(countClick - 1, Integer.valueOf((String) butt2.getText()));
			if (!butt2.wasClicked()) {
				_Log.v("button2 id", R.id.button2 + "");
				butt2.setOrderTagSource(setOrder(countClick))
						.setOrderTagChangeable(false).setWasClicked(true);
			} else {
				if (!losed) {
					handler.sendEmptyMessage(0);
					_Log.i("fail reason", "click button 1 twice");
				}
				losed = true;
			}
			break;
		case R.id.button3:
			play.add(countClick - 1, Integer.valueOf((String) butt3.getText()));
			if (!butt3.wasClicked()) {
				_Log.v("button3 id", R.id.button3 + "");
				butt3.setOrderTagSource(setOrder(countClick))
						.setOrderTagChangeable(false).setWasClicked(true);
			} else {
				if (!losed) {
					handler.sendEmptyMessage(0);
					_Log.i("fail reason", "click button 1 twice");
				}
				losed = true;
			}
			break;
		}
	}

	/**
	 * Shows a {@link EndGameDialog} to show the game's ending.
	 */
	private void getLose() {
		endGameDialog.showPopup();
		// finish();
	}

	/**
	 * Spawns a new game cycle.
	 */
	public void makeNewCycle() {
		losed = false;
		rawElements = ElementSupplier.supply(3, 20);
		for (Integer in : rawElements) {
			_Log.v("element", in + "");
		}
		butt1.buttonResetState();
		butt2.buttonResetState();
		butt3.buttonResetState();
		butt1.setText(String.valueOf(rawElements.get(0)));
		butt2.setText(String.valueOf(rawElements.get(1)));
		butt3.setText(String.valueOf(rawElements.get(2)));
		timer.doStart();
	}
//	private void setElements(List<Integer> elements) {
//		this.rawElements = elements;
//	}

	/**
	 * Decides the {@link NotificationButton}'s order tag depend on values of
	 * countClick.
	 * 
	 * @param countClick
	 *            Number of clicking time on the {@link NotificationButton}s.
	 * @return Resource image that will be wielded as order tag by
	 *         {@link NotificationButton}.
	 */
	private int setOrder(int countClick) {
		switch (countClick) {
		case 1:
			return R.drawable.o1;
		case 2:
			return R.drawable.o2;
		case 3:
			return R.drawable.o3;
		default:
			return 0;
		}
	}

	/**
	 * Gets exactly view (compounded view {@link NotificationButton}) that
	 * contains the button (mentioned here is the button we clicked up there) to
	 * preparing up coming steps.
	 */
	@Override
	public View viewParentClicked(View normalButton) {
		/*
		 * The view contains the view normalButton input directly.
		 */
		ViewParent parent = normalButton.getParent();
		NotificationButton nB = null;
		if (parent == null) {
			// _Log.d("TEST",
			// "normalButton.getParent() is a NotificationButton");
		} else {
			if (parent instanceof ViewGroup) {
				if (parent instanceof NotificationButton) {
					nB = (NotificationButton) parent;
					// _Log.d("TEST",
					// "this.getParent() is a NotificationButton");
					return nB;
				}
				ViewParent grandparent = ((ViewGroup) parent).getParent();
				if (grandparent == null) {
					// _Log.d("TEST",
					// "((ViewGroup) this.getParent()).getParent() ==> grandparent is null");
				} else if (grandparent instanceof NotificationButton)
					nB = (NotificationButton) grandparent;
				else {

					// _Log.d("TEST",
					// "((ViewGroup) this.getParent()).getParent() is not a NotificationButton");
				}
			} else {
				// _Log.d("TEST", "this.getParent() is not a ViewGroup");
			}
		}
		return nB;
	}

	@Override
	protected void onStop() {
		timer.destroyTimer();
		super.onStop();
	}
}