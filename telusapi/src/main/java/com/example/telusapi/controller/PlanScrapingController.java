package com.example.telusapi.controller;

import com.example.telusapi.model.TelusPlan;
import com.example.telusapi.service.PlanScrapingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/plans")
public class PlanScrapingController {

    @Autowired
    private PlanScrapingService planScrapingService;

    @GetMapping
    public List<TelusPlan> getTelusPlans() {
        return planScrapingService.scrapeTelusPlans();
    }
}
