package id.pradana.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import id.pradana.ems.model.Employee;

public interface EmployeeRepository
                extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {
}
