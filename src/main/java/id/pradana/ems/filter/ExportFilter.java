package id.pradana.ems.filter;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ExportFilter {
    private Integer limit = 10_000; // default limit for export
}
