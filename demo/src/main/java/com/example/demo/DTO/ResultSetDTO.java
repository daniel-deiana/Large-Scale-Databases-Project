package com.example.demo.DTO;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ResultSetDTO {
    String field1;
    String field2;
    String field3;

    public ResultSetDTO(String field1, String field2, String field3) {
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

    @Override
    public String toString() {
        return "AnalyticDTO{" +
                "field1='" + field1 + '\'' +
                ", field2='" + field2 + '\'' +
                ", field3='" + field3 + '\'' +
                '}';
    }
}
