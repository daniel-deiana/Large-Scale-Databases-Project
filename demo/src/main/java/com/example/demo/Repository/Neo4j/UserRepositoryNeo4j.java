package com.example.demo.Repository.Neo4j;

import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;

public interface UserRepositoryNeo4j extends ReactiveNeo4jRepository<UserGraph, String> {

}
