package org.mm.balance.tools;

import java.text.DecimalFormat;
import java.util.Locale;

/**
 * Created by max on 17/11/2015.
 */
public abstract class CurrencyHelper {

    public static String formatIntoCurrencyString(long number) {
        return DecimalFormat.getCurrencyInstance(Locale.getDefault()).format(number);
    }

}
