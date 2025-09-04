package com.example.springboot340test.object.vo;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import lombok.Data;

@Data
public class UserVO {
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