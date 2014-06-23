package vn.game.speedorder;

import vn.game.speedorder.models.NotificationButton;
import android.view.View;
/**
 * Public interface.
 * {@link OnNotifyChangedListener} extends {@link OnClickListener}.
 * 
 * Contributes a method to recognize clicked {@link NotificationButton} views.
 * @author DUC QUYNH
 *
 */
public interface OnNotifyChangedListener extends View.OnClickListener {
	public View viewParentClicked(View view);
}
