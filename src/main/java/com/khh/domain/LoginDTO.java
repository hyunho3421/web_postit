package com.khh.domain;

import lombok.Data;

/**
 * Created by hyunhokim on 2017. 8. 27..
 */
@Data
public class LoginDTO {
    private String id;
    private String password;
    private boolean useCookie;
}
