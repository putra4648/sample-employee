package id.pradana.ems.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class DepartmentEmployeePk implements Serializable {
  @Column(name = "emp_no", nullable = false)
  private Long empNo;

  @Column(name = "dept_no", nullable = false)
  private String deptNo;

}
