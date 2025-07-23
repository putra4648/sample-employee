package id.pradana.ems.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class EmployeeDto {
  private Long id;
  private String firstname;
  private String lastname;
  private String fullname;
  private Date birthdate;
  private Date hiredate;
  private String gender;
  private String deptname;
  private List<TitleDto> titles;
  private List<SalaryDto> salaries;
  private List<String> departments;
}
