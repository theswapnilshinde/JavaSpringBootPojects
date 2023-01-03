package com.rest.webservices.restfulwebservices.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDao {

	public static List<User> users = new ArrayList<>();

	static {

		users.add(new User(1,"swapnil",LocalDate.now().minusYears(30)));
		users.add(new User(2,"tom",LocalDate.now().minusYears(40)));
		users.add(new User(3,"jeff",LocalDate.now().minusYears(20)));
		//users.
	}


	public List<User> findAll(){
		return users;

	}

	public User findOne(int id) {


		return null;
		//Predicate<? super User> predicate = User User
				//user -> user.getId().equals(id);
	//	return users.stream().filter(predicate);



	}

	public User saveUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}






}
