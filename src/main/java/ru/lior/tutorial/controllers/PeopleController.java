package ru.lior.tutorial.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.lior.tutorial.dao.PersonDAO;
import ru.lior.tutorial.models.Person;

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

    @GetMapping("/new")
    public String newPerson(Model model){
        model.addAttribute("person", new Person());
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") Person person){
        dao.save(person);
        return "redirect:/people";
    }
}
