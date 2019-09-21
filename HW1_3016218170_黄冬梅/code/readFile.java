package BigData;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class readFile {
	
	public static void process(File input, File output){
		try{
			InputStreamReader reader = new InputStreamReader(new FileInputStream(input));
			BufferedReader br = new BufferedReader(reader);
			BufferedWriter bw = new BufferedWriter(new FileWriter(output));
			String line = new String();
			line = br.readLine();
			//while循环中不能写br.readLine(),否则会被当做继续读一行
			while(line != null){
				//System.out.println(line);
				List<String> subed = store(line);
				for(int i = 0;i < 56;i++){
					br.readLine();
				}
				line = br.readLine();
				//System.out.println(line);
				List<String> sub = store(line);
				List<String> result = new ArrayList<String>();
				result.add(sub.get(0));
				result.add(sub.get(1));
				for(int i = 2;i < 6;i++){
					//此处是为了防止计算结果小数位数过长（已经将近小数点后10位了）
					DecimalFormat df = new DecimalFormat("######0.00");   
					Double num1 = Double.parseDouble(subed.get(i));
					//Double num1 = Double.valueOf(subed.get(i));
					Double num2 = Double.parseDouble(sub.get(i));
					//Double num2 = Double.valueOf(sub.get(i));
					//BigDecimal是为了防止计算结果变成科学计数法
					String num3 = new BigDecimal(df.format((num2 - num1))).toString();
					result.add(num3);
				}
				result.add(sub.get(6));
				result.add(sub.get(7));		
				for(int j = 0;j < result.size();j++){
					bw.write(result.get(j));
					bw.write(";");
				}
				bw.newLine();
				line = br.readLine();
			}
			
			bw.flush();
			bw.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	//处理分号，将每行txt数据存入List中
	//Java不能定义变长数组，只能用ArrayList代替变长数组
	public static List<String> store(String str){
		List<String> result = new ArrayList<String>();
		int num = 0;
		String content = new String();
		for(int i = 0;i < str.length();i++){
			if(str.charAt(i) == ';'){
				content = str.substring(num, i);
				result.add(content);
				num = i+1;
			}
		}
		content = str.substring(num, str.length());
		result.add(content);
		return result;
	}
	
	public static void main(String args[]){
		File input = new File("C:\\Users\\Administrator\\Desktop\\wealth1951.txt");
		File output = new File("C:\\Users\\Administrator\\Desktop\\test.txt");
		process(input,output);
	}
}
