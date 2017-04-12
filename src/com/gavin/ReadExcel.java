package com.gavin;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import java.io.File;
import java.util.*;

/**
 * Created by gavin on 2017/4/7.
 * 读取excel中数据类
 */
public class ReadExcel {
    private Sheet sheet;
    private Workbook book;
    private  Cell cell1,cell2,cell3;

    public List<Object> getReadExcel(String leiBie){
        List<Object> dataList = new ArrayList<Object>();
        int i;
        try {
            //明细.xls为要读取的excel文件名
            book= Workbook.getWorkbook(new File("E:/ppt/明细.xls"));

            //获得第一个工作表对象(ecxel中sheet的编号从0开始,0,1,2,3,....)
            sheet=book.getSheet(0);
            //获取左上角的单元格
            cell1=sheet.getCell(0,0);
            System.out.println("标题："+cell1.getContents());

            i=1;
            Random rand = new Random();
            while(true)
            {
                Map<String, Object> dataMap = new HashMap<String, Object>();
                //获取每一行的单元格
                cell1=sheet.getCell(1,i);//（列，行）站点名称
                cell2=sheet.getCell(2,i);//站点地址
                cell3=sheet.getCell(3,i);//CI
                String sd = sheet.getCell(0,i).getContents();
                int numCI = rand.nextInt(10)+50;//电平
                String shebei = sheet.getCell(7,i).getContents();//设备类型
                if("".equals(cell1.getContents())==true && "".equals(cell3.getContents()) ==true){
                    break;//如果读取的数据为空
                }else if("".equals(cell1.getContents())==true && !"".equals(cell3.getContents()) ==true
                        && shebei.contains(leiBie)== true){
                    dataMap.put("addressName", cell1.getContents());
                    dataMap.put("address", cell2.getContents());
                    dataMap.put("xiaoquCI", cell3.getContents());
                    dataMap.put("dianPing", numCI);
//                    System.out.println(sd+",");
                    dataList.add(dataMap);
//                    System.out.println(cell1.getContents()+"\t"+cell2.getContents()+"\t"+cell3.getContents()+"\t"+numCI+"\t"+shebei);
                }
                i++;
            }
            book.close();
            System.out.print("完毕:");
        }
        catch(Exception e)  {
            System.out.print("问题:"+e.getMessage());
            e.fillInStackTrace();
        }
        return dataList;
    }

    public static void main(String[] args) {
        ReadExcel t = new ReadExcel();
        t.getReadExcel("个人");
    }
}
