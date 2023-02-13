package com.example.demo.Repository;
import com.example.demo.DTO.ResultSetDTO;
import org.neo4j.driver.Record;
import java.util.ArrayList;
import java.util.List;

public class Utilities {
    public static List<ResultSetDTO> RecordToResultSet(List<Record> records) {
        List<ResultSetDTO> result = new ArrayList<>();
        for (Record r : records) {
            String field1 = r.values().get(0).toString();
            String field2 = r.values().get(1).toString();
            ResultSetDTO aux = new ResultSetDTO(field1, field2, null);
            result.add(aux);
        }
        return result;
    }
}
