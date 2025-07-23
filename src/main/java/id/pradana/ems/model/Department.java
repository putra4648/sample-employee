package id.pradana.ems.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "departments")
@Data
public class Department {
  @Id
  @Column(name = "dept_no", nullable = false)
  private String deptno;

  @Column(name = "dept_name")
  private String deptname;

}
