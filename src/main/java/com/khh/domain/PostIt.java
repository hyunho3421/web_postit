package com.khh.domain;

import lombok.Data;

/**
 * Created by hyunhokim on 2017. 8. 27..
 */
@Data
public class PostIt {
    private int id;
    private String content;
    private int pos_x;
    private int pos_y;
}
