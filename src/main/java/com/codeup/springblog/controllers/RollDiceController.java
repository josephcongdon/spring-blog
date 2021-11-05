package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RollDiceController {


    @GetMapping("/roll-dice")
    @ResponseBody
    public String view(){
        return "Enter a number:" +
                "<ul>\n" +
                "  <li><a href=\"/roll-dice/1\">1</a></li>\n" +
                "  <li><a href=\"/roll-dice/2\">2</a></li>\n" +
                "  <li><a href=\"/roll-dice/3\">3</a></li>\n" +
                "  <li><a href=\"/roll-dice/4\">4</a></li>\n" +
                "  <li><a href=\"/roll-dice/5\">5</a></li>\n" +
                "  <li><a href=\"/roll-dice/6\">6</a></li>\n" +
                "</ul>";
    }

    @GetMapping("/roll-dice/{n}")
    @ResponseBody
    public int rollDice(@PathVariable int n){
        return n;
    }


}
