package com.golnar.pawdiary;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CatController {

    private String catName = "Benitto";
    private LocalDate catBirthday = LocalDate.of(2024, 11, 25);

    @GetMapping("/")
    public String showHomePage(Model model) {
        model.addAttribute("catName", catName);
        model.addAttribute("catBirthday", catBirthday);
        model.addAttribute("today", LocalDate.now());
        return "index";
    }

    @PostMapping("/cat/name")
    public String updateCatName(@RequestParam("catName") String catName) {
        String cleanedName = catName.trim();

        if (!cleanedName.isEmpty() && cleanedName.length() <= 50) {
            this.catName = cleanedName;
        }

        return "redirect:/";
    }

    @PostMapping("/cat/birthday")
    public String updateCatBirthday(
            @RequestParam("catBirthday") String catBirthday,
            RedirectAttributes redirectAttributes) {

        try {
            LocalDate birthday = LocalDate.parse(catBirthday);

            if (birthday.isAfter(LocalDate.now())) {
                redirectAttributes.addFlashAttribute(
                        "birthdayError",
                        "Birthday cannot be in the future."
                );
            } else {
                this.catBirthday = birthday;
            }
        } catch (DateTimeParseException exception) {
            redirectAttributes.addFlashAttribute(
                    "birthdayError",
                    "Please enter a valid birthday."
            );
        }

        return "redirect:/";
    }
}