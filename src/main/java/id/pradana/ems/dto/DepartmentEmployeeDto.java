package id.pradana.ems.dto;

import java.util.Date;

import lombok.Data;

@Data
public class DepartmentEmployeeDto {
  private Long employeeNo;
  private String departmentNo;
  private Date fromDate;
  private Date toDate;

}
