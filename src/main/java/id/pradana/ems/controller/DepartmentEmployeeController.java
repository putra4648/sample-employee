package id.pradana.ems.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import id.pradana.ems.service.DepartmentEmployeeService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class DepartmentEmployeeController {

  private final DepartmentEmployeeService service;

  @GetMapping("/dept_employees")
  public ResponseEntity<Map<String, Object>> getAll(@RequestParam(name = "sortBy", required = false) String sortBy,
      @RequestParam(name = "dir", required = false) String direction,
      @RequestParam(name = "page", defaultValue = "1") int page,
      @RequestParam(name = "size", defaultValue = "25") int size) {

    return service.getAll(sortBy, direction, page, size);
  }
}
