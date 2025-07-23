package id.pradana.ems.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Table(name = "dept_emp")
@Entity
@Data
public class DepartmentEmployee {
  @EmbeddedId
  private DepartmentEmployeePk departmentEmployeePk;

  @Column(name = "from_date")
  @Temporal(TemporalType.DATE)
  private Date fromDate;

  @Column(name = "to_date")
  @Temporal(TemporalType.DATE)
  private Date toDate;

}
