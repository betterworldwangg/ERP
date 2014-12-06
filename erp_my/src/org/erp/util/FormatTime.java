package org.erp.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatTime {
	
	public static String formatTime(Long time)
	{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(new Date(time));
	}

}
