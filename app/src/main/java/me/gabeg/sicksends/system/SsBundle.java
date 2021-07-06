package me.gabeg.sicksends.system;

import android.os.Bundle;
import android.os.BadParcelableException;

/**
 * Bundle helper.
 */
public class SsBundle
{

	/**
	 * Key for the source ID when starting the Add Climb fragment.
	 */
	public static final String ADD_CLIMB_SRC_KEY = "SsAddClimbSrcKey";

	/**
	 * @return The source ID that started the Add Climb fragment.
	 */
	public static int getAddClimbSrcId(Bundle bundle)
	{
		return (bundle != null) ? bundle.getInt(ADD_CLIMB_SRC_KEY) : -1;
	}

	/**
	 * Convert data for the Add Climb fragment to a bundle.
	 */
	public static Bundle toAddClimbBundle(int srcId)
	{
		Bundle args = new Bundle();

		args.putInt(ADD_CLIMB_SRC_KEY, srcId);
		return args;
	}

}
