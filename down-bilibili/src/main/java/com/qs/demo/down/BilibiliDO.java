package com.qs.demo.down;

import lombok.Data;

import java.util.List;

@Data
public class BilibiliDO {
    private int code;
    private String message;
    private long ttl;
    private List<?> data;
}
