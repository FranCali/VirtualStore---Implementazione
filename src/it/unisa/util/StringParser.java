package it.unisa.util;

public class StringParser {
	

	public static String parse(String text) {
		System.out.println("input string: " + text);
		
		String parsedtext = null;
		
		
		parsedtext = text.replaceAll("/[�]/", "&agrave;");
		parsedtext = parsedtext.replaceAll("/[è]/", "&egrave;");
		parsedtext = parsedtext.replaceAll("/[ì]/", "&igrave;");
		parsedtext = parsedtext.replaceAll("/[ò]/", "&ograve;");
		parsedtext = parsedtext.replaceAll("/[ù]/", "&ograve;");

		System.out.println("output string: " + parsedtext);
		return parsedtext;
	}
	
	
	
}

