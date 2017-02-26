package com.xyang.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.junit.Test;
/**
 * 
 * @描述 serialVersionUID是 jvm根据类的内部细节自动生成的
 * @date 2017年2月10日-上午11:12:07
 * @author IBM
 *
 */
public class SerializationAndDeserializeTest {
	private static String serial_file_path = "D:/temp/";

	@Test
	public void serializeTest() throws IOException {
		Person person = new Person();
		person.setName("xyang");
		person.setAge(25);
		person.setSex("男");
		ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(new File(serial_file_path + "person.txt")));
		oo.writeObject(person);
		System.out.println("Person对象序列化成功！");
		oo.close();
	}

	@Test
	public void deserializeTest() throws IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(serial_file_path + "person.txt")));
		Person person = (Person) ois.readObject();
		System.out.println(person.toString());
		ois.close();
	}

	@Test
	public void serialversionUIDTest() throws Exception {
		serializeCustomer();// 序列化Customer对象
		Customer customer = deserializeCustomer();// 反序列Customer对象
		System.out.println(customer);
	}

	private static void serializeCustomer() throws FileNotFoundException, IOException {
		Customer customer = new Customer("gacl", 25);
		ObjectOutputStream oo = new ObjectOutputStream(
				new FileOutputStream(new File(serial_file_path + "customer.txt")));
		oo.writeObject(customer);
		System.out.println("Customer对象序列化成功！");
		oo.close();
	}

	/**
	 * MethodName: DeserializeCustomer Description: 反序列Customer对象
	 * 
	 * @author xudp
	 * @return
	 * @throws Exception
	 * @throws IOException
	 */
	private static Customer deserializeCustomer() throws Exception, IOException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(serial_file_path + "customer.txt")));
		Customer customer = (Customer) ois.readObject();
		System.out.println("Customer对象反序列化成功！");
		ois.close();
		return customer;
	}
}

class Person implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2644334872291084156L;
	private int age;
	private String name;
	private String sex;

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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		return "Person [age=" + age + ", name=" + name + ", sex=" + sex + "]";
	}
}

class Customer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5637628426921675282L;

	private String name;
	private int age;
	private String sex;

	public Customer(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public Customer(String name, int age, String sex) {
		this.name = name;
		this.age = age;
		this.sex = sex;
	}

	@Override
	public String toString() {
		return "name=" + name + ", age=" + age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

}
