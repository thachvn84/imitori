package imitori.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import imitori.dto.JAWordDto;
import imitori.dto.ReturnValue;

@RestController
@RequestMapping("/jawordmng/")
public class JAWordManagerController {
    public JAWordManagerController() {

    }

    @GetMapping("/searchone")
    public ReturnValue searchOne(@RequestParam String w) {
        ReturnValue res = new ReturnValue();

        return res;
    }

    @PostMapping("/addOne")
    public ReturnValue addOne(@RequestBody JAWordDto w) {
        ReturnValue res = new ReturnValue();
        
        return res;
    }
}