package id.pradana.ems.model;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.Formula;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Table(name = "employees")
@Data
public class Employee {
  @Id
  @Column(name = "emp_no", nullable = false, length = 11)
  private Long id;

  @Column(name = "first_name", nullable = false, length = 14)
  private String firstname;

  @Column(name = "last_name", nullable = false, length = 16)
  private String lastname;

  @Column(name = "gender", nullable = false)
  private Gender gender;

  @Column(name = "hire_date", nullable = false)
  @Temporal(TemporalType.DATE)
  private Date hiredate;

  @Column(name = "birth_date", nullable = false)
  @Temporal(TemporalType.DATE)
  private Date birthdate;

  /* Custom Formula */
  @Formula("CONCAT(first_name, ' ', last_name)")
  private String fullname;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "emp_no")
  @OrderBy(value = "from_date")
  private List<Title> titles;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "emp_no")
  private Set<Salary> salaries;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "emp_no")
  private Set<DepartmentEmployee> departments;

}
