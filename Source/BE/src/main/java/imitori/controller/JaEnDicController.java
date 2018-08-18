package imitori.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import imitori.mongodb.entity.ENVIWordEntity;
import imitori.mongodb.entity.JAENWordEntity;
import imitori.mongodb.repository.JAENWordRepository;
import imitori.mongodb.repository.ENVIWordCrudRepository;
import imitori.mongodb.repository.ENVIWordRepository;
import imitori.mongodb.repository.JAENWordCrudRepository;
import imitori.services.JaEnDicService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dic/jaen")
public class JaEnDicController {
    private final JaEnDicService jaEnDicService;

    public JaEnDicController(JaEnDicService jaEnDicService) {
        this.jaEnDicService = jaEnDicService;
    }

    @PostMapping("/addOneWord")
    public Long add(@RequestBody JAENWordEntity word) {
        Long res = 0L;
        res = jaEnDicService.addOneWord(word);
        return res;
    }

    @GetMapping("/getWordById")
    public String getWordById(@RequestParam Long id) {
        System.out.println("getWordById");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String res = gson.toJson(jaEnDicService.getWordById(id));
        System.out.println(res);
        return res.toString();
    }

    @GetMapping("/getMaxId")
    public Long getWordCount() {
        return jaEnDicService.getMaxId();
    }

    @GetMapping("/getAllMeansById")
    public Collection<Map<String, Long>> getAllMeansById(@RequestParam Long id) {
        Collection<Map<String, Long>> res = new ArrayList<>();
        //res = jaEnDicService.getAllMeansById(id);
        return res;
    }

}