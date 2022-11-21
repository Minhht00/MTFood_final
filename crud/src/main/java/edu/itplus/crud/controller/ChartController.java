package edu.itplus.crud.controller;

import edu.itplus.crud.model.Amount;
import edu.itplus.crud.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Year;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Controller
public class ChartController {
    @Autowired
    OrderService orderService;

    @GetMapping("/admin")
    public String getPieChart(Model model, @RequestParam(value = "year", required = false) Long year) {
        int yearCurrent = Year.now().getValue();
        Map<String, Integer> graphData = new TreeMap<>();
//        graphData.put("1", 11111);
//        graphData.put("2", 1256);
//        graphData.put("3", 3856);
//        graphData.put("4", 19807);
//        graphData.put("5", 11111);
//        graphData.put("6", 1256);
//        graphData.put("7", 3856);
//        graphData.put("8", 19807);
//        graphData.put("9", 11111);
//        graphData.put("10", 1256);
//        graphData.put("11", 3856);
//        graphData.put("12", 19807);

        List<Amount> amounts;
//        amounts.stream().map(
//                amount -> {
//                    graphData.put(String.valueOf(amount.getMonth()),amount.getAmount());
//                    return amount;
//
//                }
//        );
        if (StringUtils.hasText("year")){
            amounts = orderService.getStatisticByYear(year);
        }else {
            amounts = orderService.getStatisticByYear((long) yearCurrent);
        }
        for (Amount a:amounts
             ) {
            graphData.put(String.valueOf(a.getMonth()),a.getAmount());
        }
//        System.out.println(amounts.toString());
//        System.out.println(amounts);

        model.addAttribute("chartData", graphData);
        return "charts";
    }
//    @GetMapping("/admin/{year}")
//    public String getPieChartByYear(Model model, @PathVariable("year") Long year) {
////        @PathVariable("categoryId") Long categoryId
//        int yearCurrent = Year.now().getValue();
//        Map<String, Integer> graphData = new TreeMap<>();
//
//        List<Amount> amounts;
//
////        if (StringUtils.hasText("year")){
//            amounts = orderService.getStatisticByYear(year);
////        }else {
////            amounts = orderService.getStatisticByYear((long) yearCurrent);
////        }
//        for (Amount a:amounts
//        ) {
//            graphData.put(String.valueOf(a.getMonth()),a.getAmount());
//        }
////        System.out.println(amounts.toString());
////        System.out.println(amounts);
//
//        model.addAttribute("chartData", graphData);
//        return "charts";
//    }
}
