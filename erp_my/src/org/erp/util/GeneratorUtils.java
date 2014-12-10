package org.erp.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;

import org.erp.auth.department.dao.dao.DepartmentDao;
import org.erp.auth.department.entity.DepartmentModel;
import org.erp.auth.department.entity.DepartmentQueryModel;
import org.erp.auth.department.service.service.DepartmentService;
import org.erp.auth.employee.entity.EmployeeModel;
import org.erp.auth.menu.entity.MenuModel;
import org.erp.auth.resource.entity.ResourceModel;
import org.erp.auth.role.entity.RoleModel;
import org.erp.invoice.goods.entity.GoodsModel;
import org.erp.invoice.goodstype.entity.GoodsTypeModel;
import org.erp.invoice.supplier.entity.SupplierModel;
import org.erp.util.base.BaseAction;
import org.erp.util.base.BaseDao;
import org.erp.util.base.BaseDaoImpl;
import org.erp.util.base.BaseModel;
import org.erp.util.base.BaseService;
import org.erp.util.exception.AppException;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

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
		//1.生成QueryModel
		
		generatorQueryModel();
		//2.生成Model.hbm.xml
		generatorHbmXml();
		
		//3.生成Dao
		generatorDao();
		//4.生成DaoImpl
		generatorDaoImpl();
		//5.生成Service
		generatorService();
		//6.生成ServiceImpl
		generatorServiceImpl();
		//7.生成Action
		generatorAction();
		//8.生成applicationContext-**.xml
		generatorApplicationContentXml();
	}
	//生成applicationContext-**.xml
	private void generatorApplicationContentXml() {
		File file = new File("resource/applicationContext-"+small+".xml");
		if(file.exists())
		{
			return;
		}
			
		try {
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			bw.newLine();
			bw.write("<beans xmlns=\"http://www.springframework.org/schema/beans\"");
			bw.newLine();
			bw.write("	xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"");
			bw.newLine();
			bw.write("	xmlns:context=\"http://www.springframework.org/schema/context\"");
			bw.newLine();
			bw.write("	xsi:schemaLocation=\"");
			bw.newLine();
			bw.write("		http://www.springframework.org/schema/beans ");
			bw.newLine();
			bw.write("		http://www.springframework.org/schema/beans/spring-beans.xsd");
			bw.newLine();
			bw.write("		http://www.springframework.org/schema/context ");
			bw.newLine();
			bw.write("		http://www.springframework.org/schema/context/spring-context.xsd");
			bw.newLine();
			bw.write("		\">  ");
			bw.newLine();
			bw.write("	<!-- 配置dao层 -->");
			bw.newLine();
			bw.write("	<bean id=\""+small+"Dao\" class=\""+pack+".dao.impl."+big+"DaoImpl\">");
			bw.newLine();
			bw.write("		<property name=\"hibernateTemp\" ref=\"hibernateTemplate\"/>");
			bw.newLine();
			bw.write("	</bean>");
			bw.newLine();
			bw.newLine();
			bw.write("	<!-- 配置service层 -->");
			bw.newLine();
			bw.write("	<bean id=\""+small+"Serv\" class=\""+pack+".service.impl."+big+"ServiceImpl\">");
			bw.newLine();
			bw.write("		<property name=\""+small+"Dao\" ref=\""+small+"Dao\"/>");
			bw.newLine();
			bw.write("	</bean>");
			bw.newLine();
			bw.newLine();
			bw.write("	<!-- 配置Action层 -->");
			bw.newLine();
			bw.write("	<bean id=\""+small+"Action\" class=\""+pack+".action."+big+"Action\" scope=\"prototype\">");
			bw.newLine();
			bw.write("		<property name=\""+small+"Serv\" ref=\""+small+"Serv\"/>");
			bw.newLine();
			bw.write("	</bean>");
			bw.newLine();
			bw.write("</beans>");
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
			
	
	}
	//生成Action文件
	private void generatorAction() {
		File file = new File(rootDirt+"/action/"+big+"Action.java");
		if(file.exists())
		{
			return;
		}
		try {
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("package "+pack+".action;");
			bw.newLine();
			bw.newLine();
			bw.write("import "+pack+".entity."+big+"Model;");
			bw.newLine();
			bw.write("import "+pack+".entity."+big+"QueryModel;");
			bw.newLine();
			bw.write("import org.erp.util.base.BaseAction;");
			bw.newLine();
			bw.newLine();
			bw.write("public class "+big+"Action extends BaseAction<"+big+"Model> {");
			bw.newLine();
			bw.write("	public "+big+"QueryModel "+little+"hq = new "+big+"QueryModel();");
			bw.newLine();
			bw.write("	public String list()");
			bw.newLine();
			bw.write("	{");
			bw.newLine();
			bw.write("		list = "+small+"Serv.findAll("+little+"hq, currPage, pageSize);");
			bw.newLine();
			bw.write("		rows = "+small+"Serv.rowCount("+little+"hq);");
			bw.newLine();
			bw.write("		totalPage = rows%pageSize ==0 ? rows/pageSize : rows/pageSize+1;");
			bw.newLine();
			bw.write("		return LIST;");
			bw.newLine();
			bw.write("	}");
			bw.newLine();
			bw.write("	public String save()");
			bw.newLine();
			bw.write("	{");
			bw.newLine();
			bw.write("		if(model.getUuid() == null)");
			bw.newLine();
			bw.write("		{");
			bw.newLine();
			bw.write("			"+small+"Serv.save(model);");
			bw.newLine();
			bw.write("		}");
			bw.newLine();
			bw.write("		else");
			bw.newLine();
			bw.write("		{");
			bw.newLine();
			bw.write("			"+small+"Serv.update(model);");
			bw.newLine();
			bw.write("		}");
			bw.newLine();
			bw.write("		return TO_LIST;");
			bw.newLine();
			bw.write("	}");
			bw.newLine();
			bw.write("	public String input()");
			bw.newLine();
			bw.write("	{");
			bw.newLine();
			bw.write("		if(model.getUuid() != null)");
			bw.newLine();
			bw.write("		{");
			bw.newLine();
			bw.write("			model = "+small+"Serv.findById(model.getUuid());");
			bw.newLine();
			bw.write("		}");
			bw.newLine();
			bw.write("		return INPUTUI;");
			bw.newLine();
			bw.write("	}");
			bw.newLine();
			bw.write("	public String delete()");
			bw.newLine();
			bw.write("	{");
			bw.newLine();
			bw.write("		"+small+"Serv.delete(model);");
			bw.newLine();
			bw.write("		return TO_LIST;");
			bw.newLine();
			bw.write("	}");
			bw.newLine();
			bw.write("}");
			bw.newLine();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	//生成serviceImpl文件
	private void generatorServiceImpl() {
		File file = new File(rootDirt+"/service/impl/"+big+"ServiceImpl.java");
		if(file.exists())
		{
			return;
		}
		try {
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("package "+pack+".service.impl;");
			bw.newLine();
			bw.newLine();
			bw.write("import java.util.List;");
			bw.newLine();
			bw.newLine();
			bw.write("import "+pack+".dao.dao."+big+"Dao;");
			bw.newLine();
			bw.write("import "+pack+".entity."+big+"Model;");
			bw.newLine();
			bw.write("import "+pack+".service.service."+big+"Service;");
			bw.newLine();
			bw.write("import org.erp.util.base.BaseModel;");
			bw.newLine();
			bw.write("import org.erp.util.exception.AppException;");
			bw.newLine();
			bw.newLine();
			bw.write("public class "+big+"ServiceImpl implements "+big+"Service");
			bw.newLine();
			bw.write("{");
			bw.newLine();
			bw.write("	private "+big+"Dao "+small+"Dao;");
			bw.newLine();
			bw.write("	@Override");
			bw.newLine();
			bw.write("	public List<"+big+"Model> findAll(BaseModel bsm, int currPage,");
			bw.newLine();
			bw.write("			int pageSize) {");
			bw.newLine();
			bw.write("		return "+small+"Dao.findAll(bsm,currPage,pageSize);");
			bw.newLine();
			bw.write("	}");
			bw.newLine();
			bw.write("	public "+big+"Dao get"+big+"Dao() {");
			bw.newLine();
			bw.write("		return "+small+"Dao;");
			bw.newLine();
			bw.write("	}");
			bw.newLine();
			bw.write("	public void set"+big+"Dao("+big+"Dao "+small+"Dao) {");
			bw.newLine();
			bw.write("		this."+small+"Dao = "+small+"Dao;");
			bw.newLine();
			bw.write("	}");
			bw.newLine();
			bw.write("	@Override");
			bw.newLine();
			bw.write("	public int rowCount(BaseModel dct) {");
			bw.newLine();
			bw.write("		return "+small+"Dao.rowCount(dct);");
			bw.newLine();
			bw.write("	}");
			bw.newLine();
			bw.write("	@Override");
			bw.newLine();
			bw.write("	public void save("+big+"Model model) {");
			bw.newLine();
			bw.write("		"+small+"Dao.save(model);");
			bw.newLine();
			bw.write("	}");
			bw.newLine();
			bw.write("	@Override");
			bw.newLine();
			bw.write("	public "+big+"Model findById(Long uuid) {");
			bw.newLine();
			bw.write("		return "+small+"Dao.findById(uuid);");
			bw.newLine();
			bw.write("	}");
			bw.newLine();
			bw.write("	@Override");
			bw.newLine();
			bw.write("	public void update("+big+"Model model) {");
			bw.newLine();
			bw.write("		"+small+"Dao.update(model);");
			bw.newLine();
			bw.write("	}");
			bw.newLine();
			bw.write("	@Override");
			bw.newLine();
			bw.write("	public void delete("+big+"Model entity) {");
			bw.newLine();
			bw.write("		"+small+"Dao.delete(entity);");
			bw.newLine();
			bw.write("	}");
			bw.newLine();
			bw.newLine();
			bw.write("}");
			
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	//生成service文件
	private void generatorService() {
		File file = new File(rootDirt+"/service/service/"+big+"Service.java");
		if(file.exists())
		{
			return;
		}
		try {
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("package "+pack+".service.service;");
			bw.newLine();
			bw.newLine();
			bw.write("import "+pack+".entity."+big+"Model;");
			bw.newLine();
			bw.write("import org.erp.util.base.BaseService;");
			bw.newLine();
			bw.write("import org.springframework.transaction.annotation.Transactional;");
			bw.newLine();
			bw.write("@Transactional");
			bw.newLine();
			bw.write("public interface "+big+"Service extends BaseService<"+big+"Model> ");
			bw.newLine();
			bw.write("{");
			bw.newLine();
			bw.newLine();
			bw.write("}");
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//生成DaoImpl实现类
	private void generatorDaoImpl() {
		File file = new File(rootDirt+"/dao/impl/"+big+"DaoImpl.java");
		if(file.exists())
		{
			return;
		}
		try {
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("package "+pack+".dao.impl;");
			bw.newLine();
			bw.newLine();
			bw.write("import "+pack+".dao.dao."+big+"Dao;");
			bw.newLine();
			bw.write("import "+pack+".entity."+big+"Model;");
			bw.newLine();
			bw.write("import "+pack+".entity."+big+"QueryModel;");
			bw.newLine();
			bw.write("import org.erp.util.base.BaseDaoImpl;");
			bw.newLine();
			bw.write("import org.erp.util.base.BaseModel;");
			bw.newLine();
			bw.write("import org.hibernate.criterion.DetachedCriteria;");
			bw.newLine();
			bw.write("import org.hibernate.criterion.Restrictions;");
			bw.newLine();
			bw.newLine();
			
			bw.write("public class "+big+"DaoImpl extends BaseDaoImpl<"+big+"Model> implements "+big+"Dao");
			bw.newLine();
			bw.write("{");
			bw.newLine();
			bw.write("	public void highQuery(BaseModel bsm,DetachedCriteria dct)");
			bw.newLine();
			bw.write("	{");
			bw.newLine();
			
			bw.write("		"+big+"QueryModel "+little+"hm = ("+big+"QueryModel) bsm;");
			bw.newLine();
			bw.write("		//TODO高级查询判断逻辑");
			bw.newLine();
			
			bw.write("	}");
			bw.newLine();
			bw.write("}");
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	//生成Dao文件
	private void generatorDao() {
		File file = new File(rootDirt+"/dao/dao/"+big+"Dao.java");
		if(file.exists())
		{
			return;
		}
		try {
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("package "+pack+".dao.dao;");
			bw.newLine();
			bw.newLine();
			bw.write("import "+pack+".entity."+big+"Model;");
			bw.newLine();
			bw.write("import org.erp.util.base.BaseDao;");
			bw.newLine();
			bw.write("public interface "+big+"Dao extends BaseDao<"+big+"Model>");
			bw.newLine();
			bw.write("{");
			bw.newLine();
			bw.newLine();
			bw.write("}");
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
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
			//写入id标签部分
			fw.write("	<id name=\"uuid\">");
			fw.write("\n");
			fw.write("		<generator class=\"native\"/>\n");
			fw.write("	</id>\n");
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
						
						fw.write("	<property name=\""+field.getName()+"\"/>");
						fw.write("\n");
						
					}
				}
				
			}
			fw.write("</class>\n");
			fw.write("</hibernate-mapping>");
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
			fw.write("import org.erp.util.base.BaseModel;\n");
			fw.write("public class "+big+"QueryModel extends "+clazz.getSimpleName()+" implements BaseModel");
			fw.write("\n{\n");
			fw.write("	//TODO添加查询范围字段\n");
			fw.write("\n}");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			try {
				fw.close();
			} catch (IOException e) {
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
		generatorDao();
		generatorDaoImpl();
		generatorService();
		generatorServiceImpl();
		generatorAction();
		generatorApplicationContentXml();
	
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
		
		new GeneratorUtils(GoodsModel.class);
	}

}
