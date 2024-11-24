package Koodoapi.controller;

import Koodoapi.Service.KoodoScraperService;
import Koodoapi.model.KoodoPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/koodo")
public class KoodoController {

    private final KoodoScraperService koodoScraperService;

    @Autowired
    public KoodoController(KoodoScraperService koodoScraperService) {
        this.koodoScraperService = koodoScraperService;
    }

    @GetMapping("/plans")
    public List<KoodoPlan> getKoodoPlans() {
        return koodoScraperService.scrapePrepaidPlans();
    }
}
