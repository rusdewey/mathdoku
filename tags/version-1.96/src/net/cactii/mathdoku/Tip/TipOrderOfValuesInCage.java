package net.cactii.mathdoku.Tip;

import net.cactii.mathdoku.GridCage;
import net.cactii.mathdoku.MainActivity;
import net.cactii.mathdoku.R;
import android.content.SharedPreferences;

public class TipOrderOfValuesInCage extends TipDialog {

	private static String TIP_NAME = "Tip.OrderOfValuesInCage.DisplayAgain";
	private static TipCategory TIP_CATEGORY = TipCategory.GAME_RULES;

	/**
	 * Creates a new tip dialog which explains that the order of values in the
	 * cell of the cage is not relevant for solving the cage arithmetic. </br>
	 * 
	 * For performance reasons this method should only be called in case the
	 * static call to method {@link #toBeDisplayed} returned true.
	 * 
	 * @param mainActivity
	 *            The activity in which this tip has to be shown.
	 */
	public TipOrderOfValuesInCage(MainActivity mainActivity) {
		super(mainActivity, TIP_NAME, TIP_CATEGORY);

		build(
				mainActivity.getResources().getString(
						R.string.dialog_tip_order_of_values_in_cage_title),
				mainActivity.getResources().getString(
						R.string.dialog_tip_order_of_values_in_cage_text),
				mainActivity.getResources().getDrawable(
						R.drawable.tip_order_of_values_in_cage)).show();
	}

	/**
	 * Checks whether this tip has to be displayed. Should be called statically
	 * before creating this object.
	 * 
	 * @param preferences
	 *            Preferences of the activity for which has to be checked
	 *            whether this tip should be shown.
	 * @param cage
	 * @return
	 */
	public static boolean toBeDisplayed(SharedPreferences preferences,
			GridCage cage) {
		// No tip to be displayed for non existing cages or single cell cages
		if (cage == null || cage.mAction == GridCage.ACTION_NONE) {
			return false;
		}

		// No tip to be displayed in case operators are visible and values have
		// to be added or multiplied.
		if (!cage.isOperatorHidden()
				&& (cage.mAction == GridCage.ACTION_ADD || cage.mAction == GridCage.ACTION_MULTIPLY)) {
			return false;
		}

		// Determine on basis of preferences whether the tip should be shown.
		return displayTip(preferences, TIP_NAME, TIP_CATEGORY);
	}
}