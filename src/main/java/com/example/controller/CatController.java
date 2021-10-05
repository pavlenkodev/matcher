package com.example.controller;

import com.example.model.Cat;
import com.example.service.CatServiceImpl;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Data
@Controller
public class CatController {
    private final CatServiceImpl catService;

    @GetMapping("/all-cats")
    public String getAllCats(Model model
    ) {
        model.addAttribute("cats", catService.findAll());
        return "cat/all-cats";
    }

    @GetMapping("/all-deleted-cats")
    public String getAllDeletedCats(Model model) {
        model.addAttribute("cats", catService.findAllByIsDeletedTrue());
        return "cat/all-deleted-cats";
    }

    @GetMapping("/create-cat")
    public String createCat(Cat cat) {
        return "cat/create-cat";
    }

    @PostMapping("/create-cat")
    public String createCat(Cat cat, Model model) {
        model.addAttribute("save-cat", catService.save(cat));
        return "redirect:/all-cats";
    }

    @GetMapping("/edit-cat/{id}")
    public String editCat(@PathVariable("id") Long id, Model model) {
        model.addAttribute("cat", catService.findById(id));
        return "cat/edit-cat";
    }

    @PostMapping("/edit-cat")
    public String editCat(Cat cat, Model model) {
        model.addAttribute("edit-cat", catService.save(cat));
        return "redirect:/all-cats";
    }

    @GetMapping("/like/{id}")
    public String choice(@PathVariable Long id) {
        catService.likeCat(id);
        return "redirect:/all-cats";
    }


    @GetMapping("/remove/{id}")
    public String removeCat(@PathVariable long id) {
        catService.removeById(id);
        return "redirect:/all-cats";
    }


    @GetMapping("/reset/{id}")
    public String resetCat(@PathVariable long id) {
        catService.resetById(id);
        return "redirect:/all-cats";
    }

    @GetMapping("/top10")
    public String top10cats(Model model) {
        model.addAttribute("top", catService.getTop10());
        return "cat/top-view";
    }
}
