package id.pradana.ems.controller;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import id.pradana.ems.filter.ExportFilter;
import id.pradana.ems.filter.SearchFilter;
import id.pradana.ems.service.EmployeeService;
import id.pradana.ems.service.ExcelService;
import id.pradana.ems.view.ExportExcelView;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PageController {
  private final EmployeeService employeeService;
  private final ExcelService excelService;

  @RequestMapping("/")
  public String main(@ModelAttribute("searchEmployee") SearchFilter searchEmployee,
      @ModelAttribute("export") ExportFilter filter,
      Model model) {
    model.addAttribute("limit_export", 1000);

    model.addAttribute("employees", employeeService.getAll(searchEmployee));

    return "index";
  }

  @GetMapping("/detail/{id}")
  public String detailPage(@PathVariable String id, Model model) {
    model.addAttribute("emp", employeeService.getEmployeeById(Long.parseLong(id)));

    return "detail";
  }

  @RequestMapping("/export")
  public ModelAndView exportPage(@ModelAttribute("export") ExportFilter limit) {

    var model = new HashMap<String, Object>();
    model.put("limit", limit);
    model.put("filter", new SearchFilter());

    return new ModelAndView(new ExportExcelView(excelService), model);
  }
}
