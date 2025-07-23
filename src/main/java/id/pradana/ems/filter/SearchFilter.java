package id.pradana.ems.filter;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SearchFilter extends PageAndSortFilter {
    private String id = "";
    private String fullname = "";
    private String birthdate = "";
    private String hiredate = "";
}
