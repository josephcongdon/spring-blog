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
        Optional<Counter> editCounter = counterDao.findById(id);
        editCounter.get().setName("user2");
        if(increment == null){
            editCounter.get().decrement();
            counterDao.save(editCounter.get());
        } else if(decrement == null ){
            editCounter.get().increment();
            counterDao.save(editCounter.get());
        }
        return "redirect:/counter";
    }


}
