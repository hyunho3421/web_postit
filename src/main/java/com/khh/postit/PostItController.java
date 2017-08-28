package com.khh.postit;

import com.khh.domain.PostIt;
import com.khh.service.PostItService;
import com.khh.util.commonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hyunhokim on 2017. 8. 27..
 */
@Controller
@RequestMapping("/postit")
public class PostItController {

    private static final Logger logger = LoggerFactory.getLogger(PostItController.class);

    @Autowired
    private PostItService postItService;

    @RequestMapping("/test")
    public String test() {
        return "/postit/test";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<String> save(@RequestBody PostIt postIt) {
        ResponseEntity<String> entity;

        try {
            postItService.update(postIt);
            entity = new ResponseEntity<>("SUCCESS", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return entity;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<String> create(@RequestBody PostIt postIt) {
        ResponseEntity<String> entity;

        try {
            int postit_id = postItService.create(postIt);
            entity = new ResponseEntity<>(String.valueOf(postit_id), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return entity;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> list() {
        logger.info("postItList");
        ResponseEntity<Map<String, Object>> entity;

        try {

            List<PostIt> list = postItService.findAll();

            Map<String, Object> map = new HashMap<>();
            map.put("list", list);

            entity = new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return entity;
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.POST)
    public ResponseEntity<String> remove(@PathVariable("id") int id) {
        ResponseEntity<String> entity;

        try {
            postItService.deleteById(id);
            entity = new ResponseEntity<>("SUCCESS", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return entity;
    }

}
