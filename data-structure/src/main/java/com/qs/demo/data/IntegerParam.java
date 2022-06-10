package com.qs.demo.data;

/**
 * @Description:
 * @Author:qiaoshen
 * @Email:qiaoshen@iristar.com.cn
 * @Date 2022/6/1
 */
public class IntegerParam {

    public IntegerParam(String zipCode) {
        System.out.println(zipCode);
    }

    public IntegerParam(PrintId printId) {
        this(convertIdToZipCode(printId.print()));
    }

    private static String convertIdToZipCode(String id) {
        return id + "000";
    }

    public interface PrintId {
        String print();
    }
}


