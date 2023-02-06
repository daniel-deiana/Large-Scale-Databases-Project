package com.example.demo.Repository.Neo4j;


import com.example.demo.Model.User;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node("User")
public class UserGraph {
		@Id
		public String username;

		public UserGraph(String username){
				this.username = username;
		}
}
