package au.edu.unimelb.cis.dragons.core;

import playn.core.Font;
import playn.core.PlayN;
import tripleplay.ui.Label;
import tripleplay.ui.Style;

public class GlobalConfig {
	
	private final static String FontName = "Times New Roman";
	private final static int FontSize = 20;
	
	public static Label makeBoldLabel(String text) {
		return new Label(text).addStyles(Style.FONT.is(PlayN.graphics().createFont(
				GlobalConfig.FontName, Font.Style.BOLD, GlobalConfig.FontSize)));
	}
	
	public static Label makeBoldLabel() {
		return makeBoldLabel("");
	}
	
	public static Label makePlainLabel(String text) {
		return new Label(text).addStyles(Style.FONT.is(PlayN.graphics().createFont(
				GlobalConfig.FontName, Font.Style.PLAIN, GlobalConfig.FontSize)));
	}
	
	public static Label makePlainLabel() {
		return makePlainLabel("");
	}

}
