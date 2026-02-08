package com.sms.model;
public interface Student {
	String getStudentId();
	void setStudentId(String id);

	String getStudentName();
	void setStudentName(String name);

	class ContactInfo{
		String phoneNumber;
		String email;
	}
	
	String getContactInfo();
	void setContactInfo(String phn, String email);
	
	class AddressInfo{
		String city;
		String country;
		Long pincode;
	}

	String getAddressInfo();
	void setAddressInfo(String city, String country, Long pincode);
	
}
