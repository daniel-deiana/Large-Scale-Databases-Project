package com.example.demo.Service;


import com.example.demo.DOT.UserDTO;
import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("mainUserService")
public class UserService {

		@Autowired
		UserRepository userRepos;
		public boolean addUser(User user){
				if (!userRepos.addUser(user))
					return false;
				return true;
				// aggiungi neo4j
		}


		public UserDTO getUser(String username) {
			Optional<User> result = userRepos.getUserByUsername(username);
			if (result.isEmpty())
				return null;
			return new UserDTO(result.get().getUsername(),result.get().getPassword());
			}

	public boolean isAdmin(String username) {
		return userRepos.checkAdmin(username);
	}
}
