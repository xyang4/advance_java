package com.xyang.proxy;

public interface IUserManager {
	public void addUser(String id, String name);

	public void delUser(String id);

	public String findUser(String id);

	public void modifyUser(String id, String name);

}
