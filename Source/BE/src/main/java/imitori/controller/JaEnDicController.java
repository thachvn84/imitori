package imitori.controller;

import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import imitori.mongodb.entity.ENWordEntity;
import imitori.mongodb.entity.Employee;
import imitori.mongodb.entity.JAWordEntity;
import imitori.mongodb.repository.JAWordRepository;
import imitori.mongodb.repository.ENWordCrudRepository;
import imitori.mongodb.repository.ENWordRepository;
import imitori.mongodb.repository.EmployeeRepository;
import imitori.mongodb.repository.EmployeeRepositoryCustom;
import imitori.mongodb.repository.JAWordCrudRepository;
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
    public Long add(@RequestBody JAWordEntity word) {
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

}