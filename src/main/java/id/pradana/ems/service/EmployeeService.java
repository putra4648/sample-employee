package id.pradana.ems.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.pradana.ems.dto.EmployeeDto;
import id.pradana.ems.dto.SalaryDto;
import id.pradana.ems.dto.TitleDto;
import id.pradana.ems.filter.EmployeeSpecFilter;
import id.pradana.ems.filter.SearchFilter;
import id.pradana.ems.model.Employee;
import id.pradana.ems.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmployeeService {

  private final EmployeeRepository employeeRepository;

  public List<EmployeeDto> getAll(SearchFilter searchDto) {

    try {
      // Pagination
      // Direction dir =
      // filterDto.getDirection().toLowerCase().equalsIgnoreCase("asc")
      // ? Direction.ASC
      // : Direction.DESC;
      // Pageable paging = PageRequest.of(filterDto.getPage(), filterDto.getSize(),
      // Sort.by(dir, filterDto.getSortBy().toLowerCase()));

      // Filtering
      Specification<Employee> specFilter = EmployeeSpecFilter.filterAll(searchDto);

      return employeeRepository.findAll(specFilter, PageRequest.ofSize(100))
          .stream()
          .map(this::convertToDto)
          .toList();

    } catch (Exception e) {
      e.printStackTrace();
      return Collections.emptyList();
    }
  }

  public EmployeeDto getEmployeeById(Long id) {
    Optional<Employee> emp = employeeRepository.findById(id);
    if (emp.isPresent()) {
      return convertToDtoDetail(emp.get());
    }
    return null;
  }

  private EmployeeDto convertToDtoDetail(Employee emp) {
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

    return dto;
  }
}
