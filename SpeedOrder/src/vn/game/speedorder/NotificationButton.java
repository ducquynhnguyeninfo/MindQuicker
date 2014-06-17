package vn.game.speedorder;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class NotificationButton extends RelativeLayout {

	private Button button;
	private ImageView imgView;
	private boolean orderTagChangable = true;
	private boolean wasClicked = false;
	String number;
	int drawable;
	int color;

	public NotificationButton(Context context) {
		super(context);
	}

	public NotificationButton(Context context, AttributeSet attrs) {
		super(context, attrs);

		TypedArray typedArray = context.obtainStyledAttributes(attrs,
				R.styleable.NotificationButtonStyleable, 0, 0);
		number = typedArray
				.getString(R.styleable.NotificationButtonStyleable_buttonText);
		drawable = typedArray.getInteger(
				R.styleable.NotificationButtonStyleable_setImgSource,
				R.drawable.o1);
		color = typedArray.getColor(
				R.styleable.NotificationButtonStyleable_texColor,
				android.R.color.holo_orange_light);
		typedArray.recycle();

		setGravity(Gravity.CENTER_VERTICAL);
		RelativeLayout.LayoutParams reParams = new LayoutParams(
				new MarginLayoutParams(LayoutParams.WRAP_CONTENT,
						LayoutParams.WRAP_CONTENT));
		reParams.height = LayoutParams.WRAP_CONTENT;
		reParams.width = LayoutParams.WRAP_CONTENT;
		reParams.setMargins(10, 10, 10, 10);

		setLayoutParams(reParams);

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.notification_button, this, true);
		button = (Button) findViewById(R.id.button);
		imgView = (ImageView) findViewById(R.id.imgView);
	}

	public NotificationButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public NotificationButton setText(String text) {
		this.button.setText(text);
		return this;
	}

	public NotificationButton setOrderTagSource(int imgResId) {
		if (this.orderTagChangable)
			this.imgView.setImageResource(imgResId);
		return this;
	}

	@Override
	public void setOnClickListener(OnClickListener l) {
		button.setOnClickListener(l);
		super.setOnClickListener(l);
	}

	@Override
	public int getId() {
		return super.getId();
	}

	public CharSequence getText() {
		return this.button.getText();
	}

	public NotificationButton setOrderTagChangable(boolean b) {
		orderTagChangable = b;
		return this;
	}

	public boolean wasClicked() {

		return wasClicked;
	}

	public NotificationButton setWasClicked(boolean b) {
		wasClicked = b;
		return this;
	}

	public void buttonResetState() {
		setWasClicked(false).setOrderTagChangable(true).setOrderTagSource(
				R.drawable.empty_bg);
	}
}
