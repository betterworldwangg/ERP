package org.erp.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.erp.auth.department.entity.DepartmentModel;
import org.erp.auth.employee.entity.EmployeeModel;

public class GeneratorUtils {
	//EmployeeModel.class
	 private Class clazz; 
	 //Employee
	 private String big;
	 //employee
	 private String small;
	 //e
	 private String little;
	 //org.erp.auto.emp
	 private String pack;
	 //org/erp/auto/emp
	 private String rootDirt;
	
	public GeneratorUtils() {
		//TODO 1.生成QueryModel
		
		generatorQueryModel();
		//TODO 2.生成Model.hbm.xml
		generatorHbmXml();
		
		//TODO 3.生成Dao
		//TODO 4.生成DaoImpl
		//TODO 5.生成Service
		//TODO 6.生成ServiceImpl
		//TODO 7.生成Action
		//TODO 8.生成applicationContext-**.xml
	}
	//生成hbm.xml文件
	private void generatorHbmXml() {
		File file = new File("hbm.txt");
		File outFile = new File(rootDirt+"/entity/"+clazz.getSimpleName()+".hbm.xml");
		if(outFile.exists())
		{
			return;
		}
		
		try {
			//写入.hbm.xml头部分
			FileInputStream fis = new FileInputStream(file);
			FileOutputStream fos = new FileOutputStream(outFile);
			byte[] buffer = new byte[64];
			int hasRead = 0;
			while ((hasRead = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, hasRead);
			}
			fis.close();
			fos.close();
			//写入class标签部分
			FileWriter fw = new FileWriter(outFile,true);
			fw.write("\n");
			fw.write("<class name="+"\""+clazz.getName()+"\""+" table=\""+small+"\">");
			fw.write("\n");
			//遍历Model类中的属性，生成<property>标签
			
			Field[] fields = clazz.getDeclaredFields();
			
			for (Field field : fields) {
				
				if(field.getName().equals("uuid") || field.getName().contains("View"))
				{
					
					continue;
				}
				if(field.getModifiers() == Modifier.PRIVATE)
				{
					if(field.getType().equals(Integer.class)
							|| field.getType().equals(String.class)
							|| field.getType().equals(Double.class)
							|| field.getType().equals(Long.class))
					{
						
						fw.write("<property name=\""+field.getName()+"\"/>");
						fw.write("\n");
						
					}
				}
				
			}
			
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	//生成QueryModel类文件
	private void generatorQueryModel(){
		File file = new File(rootDirt+"/entity/"+big+"QueryModel.java");
		FileWriter fw = null;
		if(file.exists())
		{
			return;
		}
		try {
		
			file.createNewFile();
			fw= new FileWriter(file);
			fw.write("package "+pack+".entity;\n");
			fw.write("public class "+big+"QueryModel extends "+clazz.getSimpleName()+" implements BaseModel{}");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			try {
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	}
	public GeneratorUtils(Class entityClass)
	{
		//数据初始化
		initData(entityClass);
		generatorDirectories();
		generatorQueryModel();
		
		generatorHbmXml();
	
	}
	private void initData(Class entityClass) {
		this.clazz = entityClass;
		initData();
	}
	//依据生成的基本数据生成文件结构
	public void generatorDirectories()
	{
		File file = new File(rootDirt+"/dao/dao");
		file.mkdirs();
		file = new File(rootDirt+"/dao/impl");
		file.mkdirs();
		file = new File(rootDirt+"/service/service");
		file.mkdirs();
		file = new File(rootDirt+"/service/impl");
		file.mkdirs();
		file = new File(rootDirt+"/action");
		file.mkdirs();
		
	}
	//初始化基本数据
	private void initData() {
		//获得类名如EmployeeModel
		String entityName = clazz.getSimpleName();
		//切掉Model子串
		big = entityName.substring(0, entityName.length()-5);
		//获得开头字母小写如e
		little = (char)(big.charAt(0)+32)+"";
		//获得开头字母小写的如employee
		small = little + big.substring(1);
		//获得包结构字符串
		String packLasInd = clazz.getPackage().getName();
		//切掉包结构字符串最后一层
		int lastIndex = packLasInd.lastIndexOf(".");
		pack = packLasInd.substring(0, lastIndex);
		//转换为路径
		rootDirt ="src/"+ pack.replace('.', '/');
	
	 
	}
	public static void main(String[] args) {
		
		new GeneratorUtils(DepartmentModel.class);
	}

}
