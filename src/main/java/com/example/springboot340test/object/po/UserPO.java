package com.example.springboot340test.object.po;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import lombok.Data;

@Data
public class UserPO {
    //
    private Long id;

    //
    private String name;

    //
    private Long roleId;

    //
    private Integer sex;

    //
    private Date birthDate;

}