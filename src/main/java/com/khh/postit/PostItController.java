package com.khh.postit;

import com.khh.domain.PostIt;
import com.khh.service.PostItService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    private static final String root = "postit";

    @Autowired
    private PostItService postItService;

    @RequestMapping("/")
    public String test(Model model) {

        model.addAttribute("root", root);

        return "postit/postit";
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
