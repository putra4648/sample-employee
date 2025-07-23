package id.pradana.ems.view;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.view.document.AbstractXlsxStreamingView;

import id.pradana.ems.dto.ExportExcelDto;
import id.pradana.ems.filter.ExportFilter;
import id.pradana.ems.filter.SearchFilter;
import id.pradana.ems.service.ExcelService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExportExcelView extends AbstractXlsxStreamingView {
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private final ExcelService excelService;

    @Override
    protected void buildExcelDocument(
            @NonNull Map<String, Object> model,
            @NonNull Workbook workbook,
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response) throws Exception {
        SearchFilter filter = (SearchFilter) model.get("filter");
        ExportFilter exportFilter = (ExportFilter) model.get("limit");
        List<ExportExcelDto> employees = excelService.export(filter,
                exportFilter.getLimit());
        List<String> columnNames = Arrays.asList("No", "Employee ID", "Employee Name",
                "Hiring Date", "Work From Date");

        SXSSFSheet sheet = (SXSSFSheet) workbook.createSheet("List Employees");

        int rowIndex = 0;
        int colIndex = 0;
        Row row;
        Cell cell;
        CellStyle style;
        Font font;

        // Styling
        style = workbook.createCellStyle();
        font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);

        // Create column
        row = sheet.createRow(rowIndex);
        for (String col : columnNames) {
            cell = row.createCell(colIndex);
            cell.setCellValue(col.toUpperCase());
            cell.setCellStyle(style);
            sheet.trackColumnForAutoSizing(colIndex);
            colIndex++;
        }

        rowIndex++;

        int numbering = 1;

        for (ExportExcelDto data : employees) {
            row = sheet.createRow(rowIndex);

            cell = row.createCell(0);
            cell.setCellValue(numbering);
            // sheet.autoSizeColumn(0);

            cell = row.createCell(1);
            cell.setCellValue(data.getId());
            // sheet.autoSizeColumn(1);

            cell = row.createCell(2);
            cell.setCellValue(data.getFullname());
            // sheet.autoSizeColumn(2);

            cell = row.createCell(3);
            cell.setCellValue(sdf.format(data.getBirthdate()));
            // sheet.autoSizeColumn(3);

            cell = row.createCell(4);
            cell.setCellValue(sdf.format(data.getHiredate()));
            // sheet.autoSizeColumn(4);

            rowIndex++;
            numbering++;
        }

        for (int i = 0; i < columnNames.size(); i++) {
            sheet.autoSizeColumn(i);
        }

        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String filename = "List Employee " + currentDateTime;
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition",
                "attachment; filename=" + filename + ".xlsx");
        workbook.write(response.getOutputStream());

    }

    @Override
    protected @NonNull SXSSFWorkbook createWorkbook(@NonNull Map<String, Object> model,
            @NonNull HttpServletRequest request) {
        return new SXSSFWorkbook(1);
    }
}
