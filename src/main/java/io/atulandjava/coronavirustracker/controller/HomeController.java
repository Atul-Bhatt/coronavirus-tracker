package io.atulandjava.coronavirustracker.controller;

import io.atulandjava.coronavirustracker.models.LocationStats;
import io.atulandjava.coronavirustracker.services.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.text.DecimalFormat;

@Controller
public class HomeController {

    @Autowired
    CoronaVirusDataService coronaVirusDataService;

    @GetMapping("/")
    public String Home(Model model){
        DecimalFormat df=new DecimalFormat("#,###");
        List<LocationStats> allStats = coronaVirusDataService.getAllStats();
        String totalRepostedCases = df.format(allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum());
        model.addAttribute("locationStats", allStats);
        model.addAttribute("totalReportedCases", totalRepostedCases);
        return "home";
    }
}
