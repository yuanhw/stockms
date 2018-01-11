package stock.test;

import java.sql.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class Test {
	private String name;
	private int age;
	@JSONField (format="yyyy-MM-dd") 
	private Date birthdate;
	
	public Test() {
		super();
	}
	public Test(String name, int age, Date birthdate) {
		super();
		this.name = name;
		this.age = age;
		this.birthdate = birthdate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	
}
