package org.erp.util;

import java.text.DecimalFormat;

public class FormatNumber {
	public static String format(Double dou)
	{
		 DecimalFormat df = new DecimalFormat("#.00");
		 return df.format(dou);
	}
}
