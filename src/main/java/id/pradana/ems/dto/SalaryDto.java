package id.pradana.ems.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SalaryDto extends BaseDto implements Serializable {
  private Long empNo;
  private Long salary;
}
