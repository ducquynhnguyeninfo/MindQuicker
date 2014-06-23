package vn.game.speedorder.models;

import vn.game.speedorder.R;
import vn.game.speedorder.R.drawable;
import vn.game.speedorder.R.id;
import vn.game.speedorder.R.layout;
import vn.game.speedorder.R.styleable;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * {@link NotificationButton} is a view that compounded from {@link Button} and
 * an {@link ImageView}.
 * 
 * @author DUC QUYNH
 * 
 */
public class NotificationButton extends RelativeLayout {

	private Button button;
	private ImageView imgView;
	/**
	 * Whether order-tag's resource can be barter or not.
	 */
	private boolean orderTagChangable = true;
	/**
	 * Mark this {@link NotificationButton} was clicked or not.
	 */
	private boolean wasClicked = false;
	/**
	 * The number that is showed as a  Button's text.
	 */
	private String number;
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

	@Override
	public void setOnClickListener(OnClickListener l) {
		button.setOnClickListener(l);
		super.setOnClickListener(l);
	}

	@Override
	public int getId() {
		return super.getId();
	}

	/**
	 * Set the order tag's image resource.
	 * 
	 * @param imgResId
	 *            Id of image resource.
	 * @return
	 */
	public NotificationButton setOrderTagSource(int imgResId) {
		if (this.orderTagChangable)
			this.imgView.setImageResource(imgResId);
		return this;
	}

	/**
	 * Set text to this {@link NotificationButton}.
	 * 
	 * @param text
	 * @return
	 */
	public NotificationButton setText(String text) {
		this.button.setText(text);
		return this;
	}

	/**
	 * Get text of this {@link NotificationButton}.
	 * 
	 * @return
	 */
	public CharSequence getText() {
		return this.button.getText();
	}

	/**
	 * To flag that this {@link NotificationButton} can barter the order tag's
	 * image source or not.
	 * 
	 * @param b
	 * @return
	 */
	public NotificationButton setOrderTagChangeable(boolean b) {
		orderTagChangable = b;
		return this;
	}

	/**
	 * 
	 * @return This {@link NotificationButton} was clicked or not.
	 */
	public boolean wasClicked() {
		return wasClicked;
	}

	/**
	 * Set the flag to mark that this button was clicked or not.
	 * 
	 * @param b
	 * @return
	 */
	public NotificationButton setWasClicked(boolean b) {
		wasClicked = b;
		return this;
	}

	/**
	 * Reset all states of a {@link NotificationButton} to the default states.
	 * clicked : false. ordering tag changeable : true. set the default order
	 * tag image resource.
	 */
	public void buttonResetState() {
		setWasClicked(false).setOrderTagChangeable(true).setOrderTagSource(
				R.drawable.empty_bg);
	}
}
