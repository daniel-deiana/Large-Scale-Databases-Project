package com.example.demo.Repository.Neo4j;

import java.util.List;

import org.neo4j.driver.*;
import org.neo4j.driver.Record;
import org.slf4j.*;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.neo4j.driver.Values.parameters;

public class Neo4jManager implements AutoCloseable{
    Logger logger = LoggerFactory.getLogger(Neo4jManager.class);

    private static Neo4jManager neo4j;

    private Driver driver = GraphDatabase.driver("neo4j+s://fc4c2952.databases.neo4j.io",
            AuthTokens.basic("neo4j","eIPWi6GQUfiWdorjA8-zM1fEh-RFdej-l2AplPIB8fA"));

    private Session session;

    @Override
    public void close() {
        driver.close();
    }
    public static Neo4jManager getIstance() {
        if(neo4j == null){
            neo4j = new Neo4jManager();
        }
        return neo4j;
    }

    public void write(final String query, final Value parameters) {

        try (Session session = driver.session()) {
            logger.warn(Long.toString(System.currentTimeMillis()));
            session.executeWrite(tx ->
            {
                tx.run(query, parameters).consume();
                return null;
            });
        }
    }
    public void write(String query) { write(query, parameters()); }
    public List<Record> read(final String query, final Value parameters) {
        List<Record> recordsList;
        try (Session session = driver.session()) {
            logger.warn("NELLA READ" + Long.toString(System.currentTimeMillis()));
            recordsList = session.executeRead(tx -> {
                Result result = tx.run( query, parameters );
                List<Record> records = new ArrayList<>();
                while (result.hasNext()) {
                    Record r = result.next();
                    records.add(r);
                }
                return records;
            });
        }
        return recordsList;
    }
    public List<Record> read(String query) {
        return read(query, parameters());
    }


    public Boolean connect (){

        try (Session session = driver.session()) {
            // è una prova
            session.run("CREATE (n:Person {name: 'Danilo', title: 'Developer'})");
        }
        return true;
    }


}