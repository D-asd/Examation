package jsu.lnn.entity;

import java.io.Serializable;

//entity 实体，就是业务范畴中的具体名词
public class User implements Serializable{
	private static final long serialVersionUID = 447861509439121694L;
	
	private int id;
	private String name;
	private String passwd;
	private String phone;
	private String email;
	
	public User(){
		super();
	}
	public User(String name,int id,String passed){
		super();
		this.name = name;
		this.id = id;
		this.passwd = passed;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String toString(){
		return name;
	}
	
	public boolean equals(Object obj){
		if(obj == null)
			return false;
		if(this == obj)
			return true;
		if(obj instanceof User){
			User other = (User) obj;
			return this.id == other.id;
		}
		return false;
	}
	
	public int hashCode(){
		return id;
	}
	
}
