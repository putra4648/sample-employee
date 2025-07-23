package id.pradana.ems.filter;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class EmployeeFilterDTO {
  @JsonProperty("employee_id")
  private String id;

  @JsonProperty("employee_fullname")
  private String fullname;

  @JsonProperty("employee_birthdate")
  @JsonFormat(pattern = "dd-MMM-yyyy")
  private Date birthdate;

  @JsonProperty("employee_hiredate")
  @JsonFormat(pattern = "dd-MMM-yyyy")
  private Date hiredate;

}
