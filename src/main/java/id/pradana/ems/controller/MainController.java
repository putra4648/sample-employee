package id.pradana.ems.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import id.pradana.ems.filter.EmployeeFilterDTO;
import id.pradana.ems.service.EmployeeService;
import id.pradana.ems.view.ExportExcelView;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {
  private final EmployeeService employeeService;

  @GetMapping("/")
  public String main(ModelMap model) {
    model.put("limit_export", 1000);
    return "index";
  }

  @GetMapping("/detail")
  public String detailPage(@RequestParam(name = "id") String id,
      ModelMap model) {

    ResponseEntity<Map<String, Object>> response = employeeService.getEmployeeById(Long.valueOf(id));
    if (response.getStatusCode() == HttpStatus.OK) {
      model.put("emp", response.getBody().get("data"));
    }
    return "detail";
  }

  @GetMapping("/export")
  public ModelAndView exportPage(@RequestParam(name = "employee_id", required = false) String id,
      @RequestParam(name = "employee_fullname", required = false) String fullname,
      @RequestParam(name = "employee_birthdate", required = false) String birthdate,
      @RequestParam(name = "employee_hiredate", required = false) String hiredate) throws ParseException {

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");

    EmployeeFilterDTO filter = new EmployeeFilterDTO();
    filter.setId(id);
    filter.setFullname(fullname);
    if (birthdate != null && StringUtils.isNotBlank(birthdate)) {
      filter.setBirthdate(sdf.parse(birthdate));
    }

    if (hiredate != null && StringUtils.isNotBlank(hiredate)) {
      filter.setBirthdate(sdf.parse(hiredate));
    }

    Map<String, Object> model = new HashMap<>();
    model.put("employees", employeeService.getAll(filter));
    return new ModelAndView(new ExportExcelView(), model);
  }
}
