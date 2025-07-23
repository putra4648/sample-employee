package id.pradana.ems.filter;

import lombok.Data;

@Data
public class PageAndSortFilter {
    private String sortBy;
    private String direction;
    private int page = 1;
    private int size = 25;
}
