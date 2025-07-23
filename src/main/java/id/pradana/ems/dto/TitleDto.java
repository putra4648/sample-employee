package id.pradana.ems.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class TitleDto extends BaseDto implements Serializable {
  private Long employeeNo;
  private String title;

}
