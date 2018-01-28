package it.unisa.beans;
import it.unisa.util.Data;

public class PaymentBean {
	
	private String email;
	private String identifier;
	private String securityCode;
	private String expireDate;
	
	public PaymentBean() {
		email = "";
		identifier = "";
		securityCode = "";
		expireDate = Data.creaData(1900, 01, 01);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

	public String getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(String date) {
		this.expireDate = date;
	}

	@Override
	public String toString() {
		return "PaymentBean [email=" + email + ", identifier=" + identifier + ", securityCode=" + securityCode
				+ ", expireDate=" + expireDate + "]";
	}

	


}