package ru.lior.tutorial.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.lior.tutorial.dao.PersonDAO;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO dao;

    public PeopleController(PersonDAO dao) {
        this.dao = dao;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("people",dao.index());
        //получает  список всех людей и передает их в отображение
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("person", dao.show(id));
        return "people/show";
    }

}
