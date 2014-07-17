package com.mycompany.mbean.util;

import java.util.regex.Pattern;

public class ContactDetailValidator {

    public  Boolean shortTextValidator(String shortText) {
	try {
	    String pattern = "\\A[^\\x00-\\x1F\\x7F]*\\z";
	    if (Pattern.compile(pattern).matcher(shortText).matches()) {
		return true;
	    }
	    return false;
	} catch (Exception e) {
	    e.printStackTrace();
	    return false;
	}

    }

    public  Boolean abValidator(String ab) {
	try {
	    String pattern = "\\A[A-Za-z][A-Za-z\\-= ]*[A-Za-z]\\z";
	    if (Pattern.compile(pattern).matcher(ab).matches()) {
		return true;
	    }
	    return false;
	} catch (Exception e) {
	    e.printStackTrace();
	    return false;
	}
    }

    public  Boolean zipValidator(String zip) {
	try {
	    String pattern = "\\A^(?=.{0,10}$)([0-9]+)(-?[0-9]+)\\z";
	    if (Pattern.compile(pattern).matcher(zip).matches()) {
		return true;
	    }
	    return false;
	} catch (Exception e) {
	    e.printStackTrace();
	    return false;
	}
    }

    public  Boolean telValidator(String telephoneNumber) {
	try {
	    String pattern = "\\A^(?=.{8,13}$)([0-9]+)([\\-]?)([0-9]+)([\\-]?)([0-9]+)\\z";
	    if (Pattern.compile(pattern).matcher(telephoneNumber).matches()) {
		return true;
	    }
	    return false;
	} catch (Exception e) {
	    e.printStackTrace();
	    return false;
	}
    }

    public  Boolean emailValidator(String email) {
	try {
	    String pattern = "\\A^(?=.{0,64}$)([a-z0-9_\\.-]+)@([\\da-z\\.-]+)([\\da-z]+)\\.([a-z]+)\\z";
	    if (Pattern.compile(pattern).matcher(email).matches()) {
		return true;
	    }
	    return false;
	} catch (Exception e) {
	    e.printStackTrace();
	    return false;
	}
    }

    public  Boolean dateValidator(String dateValue) {
	try {
	    String pattern1 = "\\A^(?=.{0,10}$)((19|20)\\d\\d|\\d{2})[/-](0?[1-9]|1[012])[/-](0?[1-9]|[12][0-9]|3[01])\\z";
	    String pattern2 = "\\A^(?=.{0,8}$)((19|20)\\d\\d|\\d{2})(0?[1-9]|1[012])(0?[1-9]|[12][0-9]|3[01])\\z";
	    if (Pattern.compile(pattern1).matcher(dateValue).matches()
		    || Pattern.compile(pattern2).matcher(dateValue).matches()) {
		return true;
	    }
	    return false;
	} catch (Exception e) {
	    e.printStackTrace();
	    return false;
	}
    }

    public  Boolean numValidator(String number) {
	try {
	    String pattern = "\\A-?[0-9]*[\\.]?[0-9]*\\z";
	    if (Pattern.compile(pattern).matcher(number).matches()) {
		return true;
	    }
	    return false;
	} catch (Exception e) {
	    e.printStackTrace();
	    return false;
	}
    }

    public  Boolean longTextValidator(String longTextValue) {
	try {
	    String pattern = "\\A[^\\x00-\\x09\\x0B\\x0C\\x0E-\\x1F\\x7F]*\\z";
	    if (Pattern.compile(pattern).matcher(longTextValue).matches()) {
		return true;
	    }
	    return false;
	} catch (Exception e) {
	    e.printStackTrace();
	    return false;
	}
    }

    public  Boolean positiveIntValidator(String intValue) {
	try {
	    String pattern = "\\A[0-9]*\\z";
	    if (Pattern.compile(pattern).matcher(intValue).matches()) {
		return true;
	    }
	    return false;
	} catch (Exception e) {
	    e.printStackTrace();
	    return false;
	}
    }

    public  Boolean passwordValidator(String password) {
	try {
	    String pattern = "\\A^(?=.{8,255}$)([a-zA-Z\\d]*)([a-zA-Z]+)([a-zA-Z\\d]*)\\z";
	    if (Pattern.compile(pattern).matcher(password).matches()) {
		return true;
	    }
	    return false;
	} catch (Exception e) {
	    e.printStackTrace();
	    return false;
	}
    }

}
