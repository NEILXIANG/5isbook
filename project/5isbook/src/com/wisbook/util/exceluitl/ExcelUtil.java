/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wisbook.util.exceluitl;

import java.util.Date;
import org.apache.poi.ss.usermodel.Cell;

/**
 *
 * @author willing
 */
public class ExcelUtil {

    public static Maybe<? extends Object> getCellValue(Cell cell) {
        if (cell == null) {
            return Maybe.nothing();
        }
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_BOOLEAN:
                return Maybe.just(cell.getBooleanCellValue());
            case Cell.CELL_TYPE_NUMERIC:
                return Maybe.just(cell.getNumericCellValue());
            case Cell.CELL_TYPE_STRING:
                return Maybe.just(cell.getStringCellValue());
        }
        return Maybe.nothing();
    }

    public static Maybe<String> getStringFromCell(Cell cell) {
        if (cell == null) {
            return Maybe.nothing();
        }
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_BOOLEAN:
                return Maybe.just(String.valueOf(cell.getBooleanCellValue()));
            case Cell.CELL_TYPE_NUMERIC:
                return Maybe.just(String.valueOf(cell.getNumericCellValue()));
            case Cell.CELL_TYPE_STRING:
                return Maybe.just(cell.getStringCellValue());
        }
        return Maybe.nothing();
    }

    public static void setValueToCell(Cell cell, Object value) {
        if (value instanceof Number) {
            Number numberValue = (Number) value;
            cell.setCellValue(numberValue.doubleValue());
        } else if (value instanceof Boolean) {
            Boolean booleanValue = (Boolean) value;
            cell.setCellValue(booleanValue);
        } else if (value instanceof Date) {
            Date dateValue = (Date) value;
            cell.setCellValue(dateValue);
        } else {
            String stringValue = value.toString();
            cell.setCellValue(stringValue);
        }
    }

    private ExcelUtil() {
    }
}
