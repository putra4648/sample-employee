package id.pradana.ems.service;

import java.util.Collections;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.pradana.ems.dto.ExportExcelDto;
import id.pradana.ems.filter.EmployeeSpecFilter;
import id.pradana.ems.filter.SearchFilter;
import id.pradana.ems.model.Employee;
import id.pradana.ems.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ExcelService {

    private final EmployeeRepository employeeRepository;

    public List<ExportExcelDto> export(SearchFilter filter, int limit) {
        try {
            Specification<Employee> specFilter = null;

            specFilter = EmployeeSpecFilter.filterAll(filter);

            return employeeRepository.findAll(specFilter, PageRequest.ofSize(limit))
                    .stream()
                    .map(this::convertToDto)
                    .toList();

        } catch (Exception _) {
            return Collections.emptyList();
        }
    }

    private ExportExcelDto convertToDto(Employee emp) {
        ExportExcelDto dto = new ExportExcelDto();

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
