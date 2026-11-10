package com.golnar.pawdiary;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CatController {

    private String catName = "Benitto";

    @GetMapping("/")
    public String showHomePage(Model model) {
        model.addAttribute("catName", catName);
        return "index";
    }

    @PostMapping("/cat/name")
    public String updateCatName(@RequestParam String catName) {
        String cleanedName = catName.trim();

        if (!cleanedName.isEmpty() && cleanedName.length() <= 50) {
            this.catName = cleanedName;
        }

        return "redirect:/";
    }
}