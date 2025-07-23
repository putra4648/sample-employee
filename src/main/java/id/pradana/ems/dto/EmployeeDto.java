package id.pradana.ems.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeDto implements Serializable {
  private Long id;
  private String firstname;
  private String lastname;
  private String fullname;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date birthdate;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date hiredate;
  private String gender;
  private String deptname;
  private List<TitleDto> titles;
  private List<SalaryDto> salaries;
  private List<String> departments;
}
