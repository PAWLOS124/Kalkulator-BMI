package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BmiController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/calculate")
    public String calculate(@RequestParam("weight") double weight,
                            @RequestParam("height") double height,
                            Model model) {
        double bmi = weight / (height * height);
        double scaledBmi = bmi * 10000; // Pomnożenie wyniku BMI razy 10000

        String category;
        if (scaledBmi < 18.5) {
            category = "niedowaga";
        } else if (scaledBmi >= 18.5 && scaledBmi <= 24.9) {
            category = "waga normalna";
        } else if (scaledBmi >= 25 && scaledBmi <= 29.9) {
            category = "nadwaga";
        } else if (scaledBmi >= 30 && scaledBmi <= 34.9) {
            category = "otyłość";
        } else {
            category = "ekstremalna otyłość";
        }

        model.addAttribute("bmi", scaledBmi);
        model.addAttribute("category", category);
        return "result";
    }
}
