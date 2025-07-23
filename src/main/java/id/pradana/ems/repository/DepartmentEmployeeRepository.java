package id.pradana.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import id.pradana.ems.model.DepartmentEmployee;
import id.pradana.ems.model.DepartmentEmployeePk;

public interface DepartmentEmployeeRepository
                extends JpaRepository<DepartmentEmployee, DepartmentEmployeePk> {
}
