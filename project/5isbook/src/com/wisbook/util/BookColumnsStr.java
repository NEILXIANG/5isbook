package com.wisbook.util;

public enum BookColumnsStr {
    isbn("isbn"),
    
    category_id("分类编号"),
    numOfAuthorisedEdition("正版本数"),
    numOfPirateEdition("盗版本数"),
    numOfCopyEdition("复印版本数"),
    price("价格");
    
    public String value;
    BookColumnsStr(String value) {
        this.value = value;
    }
    
    public static BookColumnsStr[] getExcelColumns() {
        return new BookColumnsStr[]{isbn,numOfAuthorisedEdition,numOfPirateEdition,numOfCopyEdition,price,category_id};
    }
}
