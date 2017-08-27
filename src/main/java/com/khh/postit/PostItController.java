package com.khh.postit;

import com.khh.domain.PostIt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by hyunhokim on 2017. 8. 27..
 */
@Controller
@RequestMapping("/postit")
public class PostItController {

    private static final Logger logger = LoggerFactory.getLogger(PostItController.class);

    @RequestMapping("/test")
    public String test() {
        return "/postit/test";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<String> savePostIt(@RequestBody PostIt postIt) {
        ResponseEntity<String> entity;

        logger.info(postIt.toString());
//        try {
//
//        } catch () {
//
//        }

        // 임시
        entity = new ResponseEntity<>("SUCCESS", HttpStatus.OK);

        return entity;
    }


}


//    @RequestMapping(value = "", method = RequestMethod.POST)
//    public ResponseEntity<String> register(@RequestBody Reply reply) {
//        logger.info("reply register .....");
//        ResponseEntity<String> entity;
//
//        try {
//            replyService.register(reply);
//            entity = new ResponseEntity<>("SUCCESS", HttpStatus.OK);
//        } catch (Exception e) {
//            e.printStackTrace();
//            entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//
//        return entity;
//    }