package stock.vo;

import java.io.Serializable;
import java.sql.Date;

import com.alibaba.fastjson.annotation.JSONField;

@SuppressWarnings("serial")
public class User implements Serializable {
	private String uid;
	private String password;
	private String uname;
	private char gender;
	
	@JSONField (format="yyyy-MM-dd") 
	private Date birthdate;
	private String utype;
	private String phone;
	@Override
	public String toString(){
		return super.toString()+"@"+uid+"$"+password+"$"+
				uname+"$"+gender+"$"+birthdate+"$"+utype+"$"+phone+"@";
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	public String getUtype() {
		return utype;
	}
	public void setUtype(String utype) {
		this.utype = utype;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
