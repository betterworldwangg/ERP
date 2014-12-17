package org.erp.invoice.bill.service.impl;

import java.awt.Font;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.imageio.ImageIO;

import jxl.Workbook;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.erp.invoice.bill.dao.dao.BillDao;
import org.erp.invoice.bill.dao.impl.BillDaoImpl;
import org.erp.invoice.bill.entity.BillQueryModel;
import org.erp.invoice.bill.service.service.BillService;
import org.erp.invoice.goods.entity.GoodsModel;
import org.erp.invoice.orderdetail.entity.OrderDetailModel;
import org.erp.util.jxl.JxlUtil;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

public class BillServiceImpl implements BillService
{
	public BillDao billDao = new BillDaoImpl();

	public BillDao getBillDao() {
		return billDao;
	}

	public void setBillDao(BillDao billDao) {
		this.billDao = billDao;
	}

	@Override
	public List<Object[]> findBillList(BillQueryModel bhq) {
		return billDao.findBillList(bhq);
	}

	@Override
	public List<OrderDetailModel> findBillData(BillQueryModel bhq) {
		return billDao.findBillData(bhq);
	}
	
	static {
		StandardChartTheme theme = new StandardChartTheme("unicode") {
			public void apply(JFreeChart chart) {
				chart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
				super.apply(chart);
			}
		};
		theme.setExtraLargeFont(new Font("宋体", Font.PLAIN, 20));
		theme.setLargeFont(new Font("宋体", Font.PLAIN, 14));
		theme.setRegularFont(new Font("宋体", Font.PLAIN, 12));
		theme.setSmallFont(new Font("宋体", Font.PLAIN, 10));
		ChartFactory.setChartTheme(theme);
	}

	@Override
	public void getPieDataImage(OutputStream outStr, BillQueryModel bhq) throws IOException{
		DefaultPieDataset dataSet = new DefaultPieDataset();
		
		List<Object[]> list = billDao.findBillList(bhq);
		
		for (Object[] obj : list) {
			GoodsModel goods = (GoodsModel) obj[0];
			Long num = (Long) obj[1];
			
			dataSet.setValue(goods.getName(), new Double(num));
		}
		
		JFreeChart jfc = ChartFactory.createPieChart("采购报表", dataSet, true, false, false);
		
		PiePlot plot = (PiePlot) jfc.getPlot();
		
		plot.setLabelFont(new Font("SansSerif", 0, 12));
		
		plot.setNoDataMessage("对不起，没有对应的信息！");
		
		plot.setCircular(true);
		
		plot.setLabelGap(0.02D);
		
		BufferedImage bi = jfc.createBufferedImage(500, 370);
		ImageIO.write(bi , "PNG", outStr);		
	}
	@Override
	public InputStream writeExecel(BillQueryModel bqm) throws Exception {
		//将excel文件写入流对象，发送出去
		//jxl仅支持将数据写入输出流，不支持写入输入流
		//最终程序必须将文件中对应的信息装在到一个输入流中
		//输出流->输入流
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		WritableWorkbook w = Workbook.createWorkbook(bos); 
		
		WritableSheet s = w.createSheet("总括", 0);
		
		//行高列宽
		JxlUtil.sColumnSize(s, 1, 8);
		JxlUtil.sColumnSize(s, 2, 8);
		JxlUtil.sColumnSize(s, 3, 25);
		JxlUtil.sColumnSize(s, 4, 25);
		JxlUtil.sColumnSize(s, 5, 25);
		
		JxlUtil.sRowSize(s, 1, 15);
		JxlUtil.sRowSize(s, 2, 37);
		JxlUtil.sRowSize(s, 3, 6);
		JxlUtil.sRowSize(s, 4, 23);
		
		//合并单元格
		JxlUtil.sMerge(s, 2, 2, 2, 4);
		JxlUtil.sMerge(s, 3, 2, 3, 5);
		
		//单元格格式，内容
		Label lab22 = JxlUtil.cLabel(2, 2, "进货统计报表");
		JxlUtil.sLabelStyle(lab22, "黑体", 24, Colour.BLACK, Colour.LIGHT_BLUE, 1, "2020");
		JxlUtil.aLabelToSheet(lab22, s);
		
		Label lab25 = JxlUtil.cLabel(2, 5, "不限");
		JxlUtil.sLabelStyle(lab25, "黑体", 12, Colour.BLACK, Colour.LIGHT_BLUE, 1, "2002");
		JxlUtil.aLabelToSheet(lab25, s);
		
		Label lab32 = JxlUtil.cLabel(3, 2, "");
		JxlUtil.sLabelStyle(lab32, "黑体", 1, Colour.BLACK, Colour.GRAY_25, 1, "2022");
		JxlUtil.aLabelToSheet(lab32, s);
		
		Label lab42 = JxlUtil.cLabel(4, 2, "编号");
		JxlUtil.sLabelStyle(lab42, "黑体", 18, Colour.BLACK, Colour.WHITE, 1, "2220");
		JxlUtil.aLabelToSheet(lab42, s);
		
		Label lab43 = JxlUtil.cLabel(4, 3, "厂商");
		JxlUtil.sLabelStyle(lab43, "黑体", 18, Colour.BLACK, Colour.WHITE, 1, "2220");
		JxlUtil.aLabelToSheet(lab43, s);

		Label lab44 = JxlUtil.cLabel(4, 4, "商品名");
		JxlUtil.sLabelStyle(lab44, "黑体", 18, Colour.BLACK, Colour.WHITE, 1, "2220");
		JxlUtil.aLabelToSheet(lab44, s);
		
		Label lab45 = JxlUtil.cLabel(4, 5, "数量");
		JxlUtil.sLabelStyle(lab45, "黑体", 18, Colour.BLACK, Colour.WHITE, 1, "2222");
		JxlUtil.aLabelToSheet(lab45, s);
		
		int row = 4;
		int i = 1;
		
		List<Object[]> billList = billDao.findBillList(bqm);
		Long sum2 = 0L;
		for(Object[] objs:billList){
			GoodsModel gm = (GoodsModel)objs[0];
			Long sum = (Long)objs[1];
			
			JxlUtil.sRowSize(s, row+i, 19);
			
			Label lab_data1 = JxlUtil.cLabel(row+i, 2, i+"");
			JxlUtil.sLabelStyle(lab_data1, "宋体", 14, Colour.BLACK, Colour.WHITE, 1, "0120");
			JxlUtil.aLabelToSheet(lab_data1, s);
			
			Label lab_data2 = JxlUtil.cLabel(row+i, 3, gm.getGoodTypeMode().getSupplierM().getName());
			JxlUtil.sLabelStyle(lab_data2, "宋体", 14, Colour.BLACK, Colour.WHITE, 1, "0110");
			JxlUtil.aLabelToSheet(lab_data2, s);
			
			Label lab_data3 = JxlUtil.cLabel(row+i, 4, gm.getName());
			JxlUtil.sLabelStyle(lab_data3, "宋体", 14, Colour.BLACK, Colour.WHITE, 1, "0110");
			JxlUtil.aLabelToSheet(lab_data3, s);
			
			Label lab_data4 = JxlUtil.cLabel(row+i, 5, sum.toString());
			JxlUtil.sLabelStyle(lab_data4, "宋体", 14, Colour.BLACK, Colour.WHITE, 1, "0112");
			JxlUtil.aLabelToSheet(lab_data4, s);
			
			sum2+=sum;
			i++;
		}
		
		JxlUtil.sMerge(s, row+i, 2, row+i, 4);
		
		Label lab_end1 = JxlUtil.cLabel(row+i, 2, "总计:");
		JxlUtil.sLabelStyle(lab_end1, "黑体", 18, Colour.BLACK, Colour.WHITE, 1, "2220");
		JxlUtil.aLabelToSheet(lab_end1, s);
		
		Label lab_end2 = JxlUtil.cLabel(row+i, 5, sum2.toString());
		JxlUtil.sLabelStyle(lab_end2, "黑体", 18, Colour.BLACK, Colour.WHITE, 1, "2222");
		JxlUtil.aLabelToSheet(lab_end2, s);
		
		w.write();
		w.close(); 
		//所有的文件内容已经写入到了bos对象中
		//输出流->输入流
		
		ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray()); 
		return bis;
	}
}