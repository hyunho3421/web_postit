package com.khh.domain;

import lombok.Data;

/**
 * Created by hyunhokim on 2017. 8. 27..
 */
@Data
public class PostIt {
    private int id;
    private String user_id;
    private String content;
    private String h_color;
    private String c_color;
    private int pos_x;
    private int pos_y;
    private int width;
    private int height;
}
