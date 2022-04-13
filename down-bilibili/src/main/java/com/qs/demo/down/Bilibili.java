package com.qs.demo.down;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Bilibili {
    public static void main(String[] args) {
        String bv = "BV1P44y1N7QG";
        BilibiliDO bilibiliDO = JSONUtil.toBean(HttpUtil.get("https://api.bilibili.com/x/player/pagelist?bvid=" + bv), BilibiliDO.class);
        Runtime runtime = Runtime.getRuntime();
        try {
            for (int i = 0; i < bilibiliDO.getData().size(); i++) {
                String cmd = "lux https://www.bilibili.com/video/" + bv + "?p=" + (i + 1);
                Process exec = runtime.exec(cmd);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exec.getErrorStream(), "utf-8"));
                String info;
                System.out.println(cmd);
                while ((info = bufferedReader.readLine()) != null) {
                    System.out.println(info);
                }
                bufferedReader.close();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
