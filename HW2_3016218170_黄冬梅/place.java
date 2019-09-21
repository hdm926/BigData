package practice;

import java.io.File;
import java.io.IOException;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;



public class place {
	public static void main(String[] arg) throws BiffException, IOException, RowsExceededException, WriteException {

	Workbook book = Workbook.getWorkbook(new File("C:\\Users\\h\\Desktop\\data.xls"));
	Sheet sheet = book.getSheet(0);
	Cell cell = null;
	int rows = sheet.getRows();
	int cols = sheet.getColumns();
	
	String[] province= new String[]{"河北省","山西省" ,"辽宁省" ,"吉林省","黑龙江省","江苏省","浙江省",
			"安徽省","福建省" ,"江西省", "山东省", "河南省","湖北省",
			"湖南省" ,"广东省" ,"海南省" ,"四川省" ,"贵州省" ,"云南省" ,"陕西省" ,"甘肃省",
			"青海省","北京市","上海市","重庆市","天津市","广西省","宁夏省","西藏省","新疆省",
			"内蒙古"};
	
	Map<String,Integer> map = new HashMap<String,Integer>();
	
	for(int i=0; i<31; i++) {
		map.put(province[i],0);
	}
	
	for (int i = 2; i < 205; i++) {
		String str = sheet.getCell(3, i).getContents().substring(0, 2);		
		//System.out.println(str);
		for(int j=0;j<31;j++) {
			String str2=province[j].substring(0, 2);
			if(str.equals(str2)){
				int val=map.get(province[j]);
				//System.out.println(val);
				map.put(province[j], val+1);
			}
		         
	      }
	}
	Set<String> keys=map.keySet();
	Iterator<String> it=keys.iterator();
	while(it.hasNext()) {
		String key=it.next();
		int value=map.get(key);
		//System.out.println(key+" "+value);
	// 1、创建工作簿(WritableWorkbook)对象，打开excel文件，若文件不存在，则创建文件  
    WritableWorkbook writeBook = Workbook.createWorkbook(new File("C:\\Users\\h\\Desktop\\籍贯统计.xls"));  
	  
	// 2、新建工作表(sheet)对象，并声明其属于第几页  
	WritableSheet firstSheet = writeBook.createSheet("sheet1", 1);// 第一个参数为工作簿的名称，第二个参数为页数           
	  
	// 3、创建单元格(Label)对象，
	for(int i=0;i<31;i++) {
		Label label1 = new Label(0+i, 0, province[i]);// 第一个参数指定单元格的列数、第二个参数指定单元格的行数，第三个指定写的字符串内容  
		firstSheet.addCell(label1); 
		Label label2 = new Label(0+i,1,map.get(province[i]).toString());
		firstSheet.addCell(label2);
	}       	        
	// 4、打开流，开始写文件  
	writeBook.write();  	 	        
	writeBook.close(); 
	}
 }
}
