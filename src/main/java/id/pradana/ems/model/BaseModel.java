package id.pradana.ems.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@MappedSuperclass
@Data
public class BaseModel {
  @Column(name = "from_date")
  @Temporal(TemporalType.DATE)
  private Date fromDate;

  @Column(name = "to_date")
  @Temporal(TemporalType.DATE)
  private Date toDate;

}
