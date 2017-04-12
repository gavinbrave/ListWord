package com.gavin.util;

import sun.misc.BASE64Encoder;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by gavin on 2017/4/7.
 */
public class Base64Utils {
    private static BASE64Encoder encoder = new BASE64Encoder();
    private Base64Utils() {}
    /**
     * 本地图片转 base64 编码输出
     */
    public static List<String>  localImage2Str(String imagePath) {
        List<String> dataList = new ArrayList<String>();
        File imageFile = new File(imagePath);
        File[] files = imageFile.listFiles();
        int [] arr = {1,5,9,13,17,21,25,29,
                33,37,41,45,49,53,57,61,65,69,73,77,81,85,89,93,
                97,101,105,109,113,117,121,125,129,133,137,141,145,149,153,157,
                161,165,169,173,177,181,185,189,193,197,201,205,209,213,217,221,225,
                229,233,237,241,245,249,253,257,261,265,269,273,277,281,285,289,293,297,
                301,305,309,313,317,321,325,329,333,337,341,345,349,353,357,361,365,369,373,
                377,381,385,389,393,397};
//        for (int i=0;i<files.length;i++) {
            int index=(int)(Math.random()*arr.length);
            int s = arr[index];
            for(int j=0;j<4;j++){
                File file2 = files[s-1+j];
                if (file2.isDirectory()) {
                    System.out.println("不管");
                } else {
                System.out.println("文件:" + file2.getName());
                    if (file2.exists() && file2.isFile()) {
                        try {
                            dataList.add(image2Str(new FileInputStream(file2)));

                        } catch (FileNotFoundException e) {
                            // ignore
                        }
                    }
                }
            }

//        }
        return dataList;
    }
    /**
     * 网络图片转 base64 编码输出
     */
    public static String webImage2Str(String urlPath) {
        InputStream in = null;
        try {
            URL url = new URL(urlPath);
            URLConnection connection = url.openConnection();
            connection.connect();

            in = connection.getInputStream();
            return image2Str(in);
        } catch (IOException e) {
            // ignore
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    // ignore
                }
            }
        }
        return "";
    }
    /**
     * 图片输入流转 base 64 编码
     */
    public static String image2Str(InputStream stream) {
        if (stream != null) {
            try {
                byte[] data = new byte[stream.available()];
                int length = stream.read(data);

                if (length > 0) {
                    return encoder.encode(data);
                }
            } catch (IOException e) {
                // ignore
            }
        }
        return "";
    }
}
