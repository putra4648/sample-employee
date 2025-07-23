package id.pradana.ems.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Table(name = "dept_manager")
@Data
public class DepartmentManager {
  @EmbeddedId
  private DepartmentManagerPK departmentManagerPK;

  @Temporal(TemporalType.DATE)
  @Column(name = "from_date")
  private Date fromdate;

  @Temporal(TemporalType.DATE)
  @Column(name = "to_date")
  private Date todate;

  @OneToOne
  @JoinColumn(name = "emp_no", insertable = false, updatable = false)
  private Department departments;

}
