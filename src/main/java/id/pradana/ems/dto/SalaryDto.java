package id.pradana.ems.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SalaryDto extends BaseDto {
  private Long empNo;
  private Long salary;

}
