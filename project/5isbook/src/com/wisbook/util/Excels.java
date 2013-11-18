package com.wisbook.util;



import com.wisbook.service.goods.detail.GoodsDetail; 
import com.wisbook.service.goods.detail.GoodsDetailImpl;
import com.wisbook.util.exceluitl.AbsObj;
import com.wisbook.util.exceluitl.ExcelReader;
import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author acer
 */
public class Excels {

    /**
     * 读取单元格中的整型
     * @param a
     * @param name
     * @return
     */
    private static int getInt(AbsObj a, String name) {
        String d = getString(a, name);
        if (d.length() == 0) {
            return 0;
        } else {
            d = d.replace(",", "");
            if (d.length() == 0) {
                return 0;
            } else {
                return (int) Double.parseDouble(d);
            }
        }
    }

    /**
     * 读取单元格中的double类型
     * @param a
     * @param name
     * @return
     */
    private static double getDouble(AbsObj a, String name) {
        String d = getString(a, name);
        if (d.length() == 0) {
            return 0;
        } else {
            return  Double.parseDouble(d);
        }
    }

    /**
     * 读取excel单元格中的字符串
     * @param a
     * @param name
     * @return
     */
    private static String getString(AbsObj a, String name) {
        String d = a.getString(name);
        if (d == null) {
            return "";
        } else {
            return d.trim();
        }
    }

    /**
     *
     * @param columnSet
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    private static List<AbsObj> readColumnsFromFile(FileInputStream excel, BookColumnsStr[] columnNames, String sheetName) {
        Set<String> columnSet = new HashSet<String>(columnNames.length);
        for (BookColumnsStr col : columnNames) {
            columnSet.add(col.value);
        }
        if (Utils.isEmpty(sheetName)) {
            try {
                return ExcelReader.fromInputStream(excel, columnSet);
            } catch (IOException ex) {
                Logger.getLogger(Excels.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                return ExcelReader.fromInputStream(excel, sheetName, columnSet);
            } catch (IOException ex) {
                Logger.getLogger(Excels.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return new ArrayList<AbsObj>(0);
    }
    /**
     * 从excel中获取书的信息
     * @param excel
     * @param errorIsbnList
     * @return
     */
    public static List<GoodsDetail> getGoodsDetailList(FileInputStream excel,List<String> errorIsbnList) {
        List<AbsObj> colDatas = readColumnsFromFile(excel, BookColumnsStr.getExcelColumns(), null);
        List<GoodsDetail> goodsDetailList = new ArrayList<GoodsDetail>(colDatas.size());
        for (AbsObj a : colDatas) {
        	GoodsDetailImpl gdi = new GoodsDetailImpl();
           
            String isbn = getString(a, BookColumnsStr.isbn.value);
            
            try{
            gdi.getContent().put(BookColumnsStr.isbn.value, isbn);
            
            
            if (Utils.isEmpty(isbn)) {//忽略空行
                continue;
            }
            int numOfAuthorisedEdition=getInt(a, BookColumnsStr.numOfAuthorisedEdition.value);//取正版本数
            gdi.getContent().put(BookColumnsStr.numOfAuthorisedEdition.value, numOfAuthorisedEdition);
            
            int numOfPirateEdition=getInt(a, BookColumnsStr.numOfPirateEdition.value);        //盗版本数
            gdi.getContent().put(BookColumnsStr.numOfPirateEdition.value,numOfPirateEdition);
            
            int  numOfCopyEdition=getInt(a, BookColumnsStr.numOfCopyEdition.value);           //复印版本数
            gdi.getContent().put(BookColumnsStr.numOfCopyEdition.value,numOfCopyEdition);
            
            double price=getDouble(a,BookColumnsStr.price.value);//价格
            gdi.setPrice(new BigDecimal(price));
          
            String categoryId=getString(a, BookColumnsStr.category_id.value);//获取分类编号
            gdi.getContent().put(BookColumnsStr.category_id.value, categoryId);
            
            goodsDetailList.add(gdi);
            }catch(Exception e){
            	errorIsbnList.add(isbn);
            }
        }
        return goodsDetailList;
    }


//    /**
//     * 
//     * @param sheet
//     * @param col
//     * @param row
//     * @param label
//     */
//    private static void writeCell(WritableSheet sheet, int col, int row, String label) {
//        try {
//            sheet.addCell(new Label(col, row, label));
//        } catch (WriteException ex) {
//            Logger.getLogger(Excels.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

  

}
