package kudo;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/scraper")
public class ScraperController {

    private final KoodoScraperService scraperService;

    public ScraperController(KoodoScraperService scraperService) {
        this.scraperService = scraperService;
    }

    @GetMapping("/kudo")
    public String scrapePrepaidPlans() {
        return scraperService.scrapePrepaidPlans();
    }
}
