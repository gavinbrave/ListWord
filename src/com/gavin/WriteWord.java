package com.gavin;

import com.gavin.util.Base64Utils;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gavin on 2017/4/7.
 * 修改word内容并批量输出word文档类
 */
public class WriteWord {
    private Configuration configuration = null;

    public WriteWord() {
        configuration = new Configuration();
        configuration.setDefaultEncoding("UTF-8");
    }

    public void createWord(String leiBie) {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        ReadExcel re = new ReadExcel();
        List<Object> dataList = new ArrayList<Object>();
        List<String> picList = new ArrayList<String>();
        dataList = re.getReadExcel(leiBie);
        try {
            for(int i = 0;i<dataList.size();i++){
                dataMap = (Map<String, Object>)dataList.get(i);
                picList = Base64Utils
                        .localImage2Str("D:/ListWord/images");
                dataMap.put("image01",picList.get(2));
                dataMap.put("image02",picList.get(0));
                dataMap.put("image03",picList.get(3));
                dataMap.put("image04",picList.get(1));
                configuration.setClassForTemplateLoading(this.getClass(), "/template"); // FTL文件所存在的位置
                Template template = configuration.getTemplate("Product.ftl");

                File outFile = new File("E:/ppt/Femswords/"+ dataMap.get("addressName")+"报告.doc");
                Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile),"UTF-8"));
                template.process(dataMap, out);
                out.close();
//                if(i==4){
//                    break;
//                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        WriteWord test = new WriteWord();
//        Test.createWord("企业级");
//        Test.createWord("家庭级");
    }
}
