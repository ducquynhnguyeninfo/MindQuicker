package vn.game.speedorder;

import android.view.View;
public interface OnNotifyChangedListener extends View.OnClickListener {
	
	public void setNumer(int number);
	public int getNumber();
	public View viewParentClicked(View view);
}
