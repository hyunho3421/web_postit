package com.khh.domain;

import java.util.Date;

import lombok.Data;

/**
 * Created by hyunhokim on 2017. 8. 25..
 */
@Data
public class User {
	String id;
	String password;
	String name;
	String email;
	Date join_date;
}
