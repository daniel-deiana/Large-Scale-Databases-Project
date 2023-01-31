package com.example.demo.Service;


import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("mainUserSre")
public class UserService {

@Autowired
		UserRepository userRepos;

public boolean addUser(String user){
		if (!userRepos.addUser(user))
			return false;

		return true;
		// aggiungi neo4j
}


}
