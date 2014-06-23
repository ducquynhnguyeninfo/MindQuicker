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
import android.content.pm.ActivityInfo;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

public class GameActivity extends Activity implements OnNotifyChangedListener {

	private List<Integer> elements;
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
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		setContentView(R.layout.activity_play);
		initialize();
	}

	private void initialize() {
		timer = new Timer(handler);
		butt1 = (NotificationButton) findViewById(R.id.button1);
		butt2 = (NotificationButton) findViewById(R.id.button2);
		butt3 = (NotificationButton) findViewById(R.id.button3);
		butt1.setOnClickListener(this);
		butt2.setOnClickListener(this);
		butt3.setOnClickListener(this);
		endGameDialog = new EndGameDialog(this);
		makeNewRoundGame();
	}

	Handler handler = new Handler(new Handler.Callback() {

		@Override
		public boolean handleMessage(Message msg) {
			getLose();
			return false;
		}
	});

	@Override
	public void onClick(View button) {
		_Log.v("view clicked id -- " + countClick++, button.getId() + "");
		setButtonImgOrder(button);
		if (countClick == elements.size()) {
			ResultChecker resultChecker = new ResultChecker(getElements(), play);
			_Log.v("Káº¿t quáº£ mĂ¡y tráº£ vá»�",
					"Ket quáº£ cá»§a nguá»�i chÆ¡i");
			if (resultChecker.isFailed()) {
				handler.sendEmptyMessage(0);
			} else {
				play.removeAll(play);
				makeNewRoundGame();
				countClick = 0;
			}
		}
	}

	private List<Integer> getElements() {
		return this.elements;
	}

	private void setButtonImgOrder(View button) {
		switch (viewParentClicked(button).getId()) {
		case R.id.button1:
			play.add(countClick - 1, Integer.valueOf((String) butt1.getText()));
			if (!butt1.wasClicked()) {
				_Log.v("button1 id", R.id.button1 + "");
				butt1.setOrderTagSource(setOrder(countClick))
						.setOrderTagChangeable(false).setWasClicked(true);
			} else {
				handler.sendEmptyMessage(0);
			}
			break;
		case R.id.button2:
			play.add(countClick - 1, Integer.valueOf((String) butt2.getText()));
			if (!butt2.wasClicked()) {
				_Log.v("button2 id", R.id.button2 + "");
				butt2.setOrderTagSource(setOrder(countClick))
						.setOrderTagChangeable(false).setWasClicked(true);
			} else {
				handler.sendEmptyMessage(0);
			}
			break;
		case R.id.button3:
			play.add(countClick - 1, Integer.valueOf((String) butt3.getText()));
			if (!butt3.wasClicked()) {
				_Log.v("button3 id", R.id.button3 + "");
				butt3.setOrderTagSource(setOrder(countClick))
						.setOrderTagChangeable(false).setWasClicked(true);
			} else {
				handler.sendEmptyMessage(0);
			}
			break;
		}
	}

	private void getLose() {
		endGameDialog.showPopup();
//		finish();
	}

	public void makeNewRoundGame() {

		elements = ElementSupplier.elementSupplier(3, 20);
		setElements(elements);
		for (Integer in : elements) {
			_Log.v("element", in + "");
		}
		// if(elements.get(0) == elements.get(1) ||
		// elements.get(0) == elements.get(2) ||
		// elements.get(1) == elements.get(2))
		// break;
		// }
		butt1.setText(String.valueOf(elements.get(0)));
		butt2.setText(String.valueOf(elements.get(1)));
		butt3.setText(String.valueOf(elements.get(2)));
		butt1.buttonResetState();
		butt2.buttonResetState();
		butt3.buttonResetState();
		timer.doStart();
	}

	private void setElements(List<Integer> elements) {
		this.elements = elements;
	}

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

	private int numberOfImgViewSource;

	@Override
	public void setNumer(int number) {
		numberOfImgViewSource = number;
	}

	@Override
	public int getNumber() {
		return numberOfImgViewSource;
	}

	@Override
	public View viewParentClicked(View view) {
		ViewParent parent = view.getParent();
		NotificationButton nB = null;
		if (parent == null) {
			_Log.d("TEST", "this.getParent() is a NotificationButton");
		} else {
			if (parent instanceof ViewGroup) {
				if (parent instanceof NotificationButton) {
					nB = (NotificationButton) parent;
					_Log.d("TEST", "this.getParent() is a NotificationButton");
					return nB;
				}
				ViewParent grandparent = ((ViewGroup) parent).getParent();
				if (grandparent == null) {
					_Log.d("TEST",
							"((ViewGroup) this.getParent()).getParent() ==> grandparent is null");
				} else if (grandparent instanceof NotificationButton)
					nB = (NotificationButton) grandparent;
				else
					_Log.d("TEST",
							"((ViewGroup) this.getParent()).getParent() is not a NotificationButton");
			} else
				_Log.d("TEST", "this.getParent() is not a ViewGroup");
		}
		return nB;
	}
	
	@Override
	protected void onStop() {
		timer.destroyTimer();
		super.onStop();
	}
}