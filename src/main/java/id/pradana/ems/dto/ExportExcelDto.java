package id.pradana.ems.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ExportExcelDto {
    private Long id;
    private String firstname;
    private String lastname;
    private String fullname;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date birthdate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date hiredate;
    private String gender;
    private String deptname;
}
