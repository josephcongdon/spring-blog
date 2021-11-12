package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Counter;
import com.codeup.springblog.repositories.CounterRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
public class CounterController {
    private final CounterRepository counterDao;

    public CounterController(CounterRepository counterDao){
        this.counterDao = counterDao;
    }

    @GetMapping("/counter")
    public String getCounter(Model model){
        model.addAttribute("counterObject", counterDao.findById(1L));
        return"counter/counter";
    }

//    @GetMapping("/counter/{id}")

    @PostMapping("/counter/{id}")
    public String postCounter
      (@PathVariable Long id, @RequestParam(name= "increment", required = false) String increment,
       @RequestParam(name= "decrement", required = false) String decrement)
    {
        System.out.println(increment);
        System.out.println(decrement);
        //ads new counter object, can't use
        Counter editCounter  = counterDao.findById(id).orElse(new Counter());
//        editCounter.get().setName("user3");
        editCounter.setName("user3");
        if(increment == null){
            editCounter.decrement();
            counterDao.save(editCounter);
        } else if(decrement == null){
            editCounter.increment();
            counterDao.save(editCounter);
        }
        return "redirect:/counter";
    }


}
