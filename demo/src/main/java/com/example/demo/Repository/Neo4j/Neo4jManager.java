package com.example.demo.Repository.Neo4j;

import org.neo4j.driver.*;
import org.springframework.context.ConfigurableApplicationContext;

public class Neo4jManager {

		private Driver driver = GraphDatabase.driver("neo4j+s://fc4c2952.databases.neo4j.io",
						AuthTokens.basic("neo4j","eIPWi6GQUfiWdorjA8-zM1fEh-RFdej-l2AplPIB8fA"));

		private Session session;


		public Boolean connect (){

				try (Session session = driver.session()) {
						// è una prova
						session.run("CREATE (n:Person {name: 'Danilo', title: 'Developer'})");
				}
				return true;
		}


}
