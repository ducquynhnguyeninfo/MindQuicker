package vn.game.speedorder.utils;

import android.util.Log;

/**
 * see {@android.util.Log}
 * @author DUC QUYNH
 *
 */
public class _Log {
	public static final String TAG_WARNING = "log_warning";
	public static final String TAG_VERBOSE = "log_verborse";
	public static final String TAG_DEBUG = "log_debug";
	public static final String TAG_INFO = "log_info";
	public static final String TAG_ERROR = "log_error";

	static boolean isLog = true;

	/**
	 * Send an INFO log message.
	 * @param where
	 * @param text
	 */
	public static void i(String where, String text) {
		if (isLog)
			Log.i(where, text);
	}

	public static void d(String where, String text) {
		if (isLog)
			Log.d(where, text);
	}

	public static void v(String where, String text) {
		if (isLog)
			Log.v(where, text);
	}

	public static void w(String where, String text) {
		if (isLog)
			Log.w(where, text);
	}

	public static void e(String where, String text) {
		if (isLog)
			Log.i(where, text);
	}

}
