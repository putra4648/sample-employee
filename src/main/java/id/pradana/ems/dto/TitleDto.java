package id.pradana.ems.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class TitleDto extends BaseDto {
  private Long employeeNo;
  private String title;

}
