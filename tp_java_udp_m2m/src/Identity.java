import java.util.ArrayList;
import java.util.List;

public class Identity {
	
	private String lastName, firstName, age, gender, ssn, phone;
	private List<String> listKey = new ArrayList<String>();
	private List<String> listValue = new ArrayList<String>();
	
	
	
	
	public Identity(String lastName, String firstName, String age, String gender) {		
		
		this.lastName = lastName;
		this.firstName = firstName;
		this.age = age;
		this.gender = gender;
	}
	
	
	

	public Identity(String lastName, String firstName, String age, String gender, String ssn) {
		
		this.lastName = lastName;
		this.firstName = firstName;
		this.age = age;
		this.gender = gender;
		this.ssn = ssn;
	}


	


	public Identity(String lastName, String firstName, String age, String gender, String ssn, String phone) {
		
		this.lastName = lastName;
		this.firstName = firstName;
		this.age = age;
		this.gender = gender;
		this.ssn = ssn;
		this.phone = phone;
	}




	@Override
	public String toString() {
		return "Identity [lastName=" + lastName + ", firstName=" + firstName + ", age=" + age + ", gender=" + gender
				+ ", ssn=" + ssn + ", phone=" + phone + "]";
	}




	public List<String> getListValue() {
		if(this.lastName != null)this.listValue.add(this.lastName);
		if(this.firstName != null)this.listValue.add(this.firstName);
		if(this.age != null)this.listValue.add(this.age);
		if(this.gender != null)this.listValue.add(this.gender);
		if(this.ssn != null)this.listValue.add(this.ssn);
		if(this.phone != null)this.listValue.add(this.phone);
		
		return this.listValue;
	}




	public List<String> getListKey() {
		
		if(this.lastName != null)this.listKey.add("lastName");
		if(this.firstName != null)this.listKey.add("firstName");
		if(this.age != null)this.listKey.add("age");
		if(this.gender != null)this.listKey.add("gender");
		if(this.ssn != null)this.listKey.add("ssn");
		if(this.phone != null)this.listKey.add("phone");
		
		
		return this.listKey;
	}
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	

}
