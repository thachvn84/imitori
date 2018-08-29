package imitori.controller;

import java.util.ArrayList;
import java.util.Collection;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import imitori.entity.mongodb.JAENDicMonEntity;
import imitori.services.JaEnDicService;

@RestController
@RequestMapping("/dic/jaen")
public class JaEnDicController {
    private final JaEnDicService jaEnDicService;

    public JaEnDicController(JaEnDicService jaEnDicService) {
        this.jaEnDicService = jaEnDicService;
    }

    @PostMapping("/addOneWord")
    public Integer add(@RequestBody JAENDicMonEntity word) {
        Integer res = 0;
        res = jaEnDicService.addOneWord(word);
        return res;
    }

    @GetMapping("/getWordById")
    public String getWordById(@RequestParam Integer id) {
        System.out.println("getWordById");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String res = gson.toJson(jaEnDicService.getWordById(id));
        System.out.println(res);
        return res.toString();
    }

    @GetMapping("/getMaxId")
    public Integer getWordCount() {
        return jaEnDicService.getMaxId();
    }

    @GetMapping("/getAllMeansById")
    public  Collection<String> getAllMeansById(@RequestParam Integer id) {
        Collection<String> res = new ArrayList<>();
        res = jaEnDicService.getAllMeansById(id);
        return res;
    }

}