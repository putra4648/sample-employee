package id.pradana.ems.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import id.pradana.ems.dto.EmployeeDto;
import id.pradana.ems.dto.SalaryDto;
import id.pradana.ems.dto.TitleDto;
import id.pradana.ems.filter.EmployeeFilterDTO;
import id.pradana.ems.filter.EmployeeSpecFilter;
import id.pradana.ems.model.Employee;
import id.pradana.ems.repository.DepartmentManagerRepository;
import id.pradana.ems.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService {

  private final EmployeeRepository employeeRepository;
  private final DepartmentManagerRepository departmentManagerRepository;

  /**
   * Get all employee with advanced filter, paging and sorting
   *
   * @param filterJsonString
   * @param sortBy
   * @param direction
   * @param page
   * @param size
   * @return
   */
  public ResponseEntity<Map<String, Object>> getAll(String filterJsonString,
      String sortBy,
      String direction, int page,
      int size) {
    Map<String, Object> response;
    try {
      // Pagination
      Direction dir = direction.toLowerCase().equalsIgnoreCase("asc")
          ? Direction.ASC
          : Direction.DESC;
      Pageable paging = PageRequest.of(page, size, Sort.by(dir, sortBy.toLowerCase()));

      // Filtering
      Page<EmployeeDto> pageEmployee = null;
      Specification<Employee> specFilter = null;

      // Param to filter
      final ObjectMapper mapper = new ObjectMapper();
      EmployeeFilterDTO filter = mapper.readValue(filterJsonString, EmployeeFilterDTO.class);

      specFilter = EmployeeSpecFilter.filterAll(filter);

      pageEmployee = getPageEmployee(specFilter, paging);

      response = new HashMap<>();
      response.put("data", pageEmployee.getContent());
      response.put("currentPage", pageEmployee.getNumber());
      response.put("recordsTotal", pageEmployee.getTotalElements());
      response.put("recordsFiltered", pageEmployee.getTotalPages());

      return ResponseEntity.ok().body(new TreeMap<>(response));

    } catch (Exception e) {
      response = new HashMap<>();
      response.put("errorMessage", e.toString());
      return new ResponseEntity<>(new TreeMap<>(response),
          HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * Get all employee data with {@link EmployeeFilterDTO} as filter
   *
   * @param filter
   * @return
   */
  public List<EmployeeDto> getAll(EmployeeFilterDTO filter) {
    try {
      Specification<Employee> specFilter = null;

      specFilter = EmployeeSpecFilter.filterAll(filter);

      return employeeRepository.findAll(specFilter, PageRequest.ofSize(1000))
          .stream()
          .map(this::convertToDto)
          .toList();

    } catch (Exception e) {
      return Collections.emptyList();
    }
  }

  /**
   * Get single employee data with ID
   *
   * @param id
   * @return
   */
  public ResponseEntity<Map<String, Object>> getEmployeeById(Long id) {
    Optional<Employee> emp = employeeRepository.findById(id);
    Map<String, Object> response;
    try {
      if (emp.isPresent()) {
        response = new HashMap<>();

        response.put("errorMessage", null);
        response.put("data", this.convertToDto(emp.get()));
        return new ResponseEntity<>(new TreeMap<>(response), HttpStatus.OK);
      } else {
        response = new HashMap<>();
        response.put("errorMessage", "No Employee available with id " + id);
        response.put("data", null);
        return new ResponseEntity<>(new TreeMap<>(response), HttpStatus.OK);
      }
    } catch (Exception e) {
      response = new HashMap<>();
      response.put("errorMessage", e.toString());
      return new ResponseEntity<>(new TreeMap<>(response),
          HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  private Page<EmployeeDto> getPageEmployee(Specification<Employee> filter,
      Pageable paging) {
    return employeeRepository.findAll(filter, paging)
        .map(this::convertToDto);
  }

  private EmployeeDto convertToDto(Employee emp) {
    EmployeeDto dto = new EmployeeDto();

    // Set employee
    dto.setId(emp.getId());
    dto.setFirstname(emp.getFirstname());
    dto.setLastname(emp.getLastname());
    dto.setFullname(emp.getFullname());
    dto.setBirthdate(emp.getBirthdate());
    dto.setHiredate(emp.getHiredate());
    dto.setGender(emp.getGender().name());

    // Set salaries
    List<SalaryDto> salariesDto = emp.getSalaries()
        .stream()
        .map(sal -> {
          SalaryDto salaryDto = new SalaryDto();
          salaryDto.setEmpNo(sal.getEmpNo());
          salaryDto.setSalary(sal.getSalary());
          salaryDto.setFromDate(sal.getFromDate().getTime());
          salaryDto.setToDate(sal.getToDate().getTime());
          return salaryDto;
        })
        .toList();
    dto.setSalaries(salariesDto);

    // Set title
    List<TitleDto> titleDtos = emp.getTitles()
        .stream()
        .map(t -> {
          TitleDto titleDto = new TitleDto();
          titleDto.setEmployeeNo(t.getEmployeeNo());
          titleDto.setTitle(t.getTitleName());
          titleDto.setFromDate(t.getFromDate().getTime());
          titleDto.setToDate(t.getToDate().getTime());
          return titleDto;
        })
        .toList();

    dto.setTitles(titleDtos);

    // Set departments
    dto.setDepartments(emp.getDepartments()
        .stream()
        .map(deptEmp -> deptEmp.getDepartmentEmployeePk().getDeptNo())
        .toList());

    return dto;
  }
}
