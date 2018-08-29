package imitori.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import imitori.dto.KanjiDto;
import imitori.dto.ReturnValue;

@RestController
@RequestMapping("/kanji/")
public class KanjiManagerController {
    public KanjiManagerController() {

    }

    @GetMapping("/getById")
    public ReturnValue getById(@RequestParam Integer id) {
        ReturnValue res = new ReturnValue();

        return res;
    }

    @GetMapping("/searchOne")
    public ReturnValue searchOne(@RequestParam String w) {
        ReturnValue res = new ReturnValue();

        return res;
    }

    @PostMapping("/addOne")
    public ReturnValue addOne(@RequestBody KanjiDto w) {
        ReturnValue res = new ReturnValue();

        return res;
    }
}