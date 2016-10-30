package com.jstudy.anno;

@Table(value = "tbl_person")
public class Person {

	@Column(value = "age", description = "it is unique")
	int age;

	@Column(value = "name")
	String name;

	@Column(value = "addr")
	String addr;

	@Column(value = "departMent")
	String departMent;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getDepartMent() {
		return departMent;
	}

	public void setDepartMent(String departMent) {
		this.departMent = departMent;
	}

}
