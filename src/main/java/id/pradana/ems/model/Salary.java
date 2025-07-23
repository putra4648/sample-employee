package id.pradana.ems.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Table(name = "salaries")
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Salary extends BaseModel {
  @Id
  @Column(name = "emp_no")
  private Long empNo;

  @Column(name = "salary")
  private Long salary;

}
