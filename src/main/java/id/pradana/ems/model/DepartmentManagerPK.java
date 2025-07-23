package id.pradana.ems.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class DepartmentManagerPK implements Serializable {
  @Column(name = "emp_no", nullable = false)
  private Integer empno;

  @Column(name = "dept_no", nullable = false)
  private String deptno;

}
