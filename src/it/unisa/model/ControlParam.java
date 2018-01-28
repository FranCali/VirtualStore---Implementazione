package it.unisa.model;

public class ControlParam {
	
	public static boolean getControl(String param) {
		
		for(int i = 0; i< param.length(); i++) {
			if(param.charAt(i) == '!' || param.charAt(i) == '?' ||  param.charAt(i) == '|' || param.charAt(i) == '"'
				|| param.charAt(i) == '£' || param.charAt(i) == '$' || param.charAt(i) == '%' || param.charAt(i) == '&'
				|| param.charAt(i) == '/' || param.charAt(i) == '(' || param.charAt(i) == ')' || param.charAt(i) == '='
				|| param.charAt(i) == '^' || param.charAt(i) == '+' || param.charAt(i) == '*' || param.charAt(i) == ']'
				|| param.charAt(i) == '[' || param.charAt(i) == '@' || param.charAt(i) == '#' || param.charAt(i) == '§'
				|| param.charAt(i) == '-' || param.charAt(i) == '°' || param.charAt(i) == '_' || param.charAt(i) == ':'
				|| param.charAt(i) == '.' || param.charAt(i) == ';' || param.charAt(i) == ',' || param.charAt(i) == '<'
				|| param.charAt(i) == '>' || param.charAt(i) == '1' || param.charAt(i) == '2' || param.charAt(i) == '3'
				|| param.charAt(i) == '4' || param.charAt(i) == '5' || param.charAt(i) == '6' || param.charAt(i) == '7'
				|| param.charAt(i) == '8' || param.charAt(i) == '9' )
				return false;
		}
		return true;
	}
}
