package edu.kndev.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import edu.kndev.page.Page;
import edu.kndev.page.PageParse;
import edu.kndev.page.RequestAndResponseTool;
import edu.kndev.util.FileTool;

public class MyCrawler {
	
	public static void main(String[] args) {
		
		
		/**
		 * 将article的url存储到本地
		 */
//		List<String> volume_list = PageParse.getVolumeUrl("http://iopscience.iop.org/issue/2041-8205/", 843, 851);
//		String article_string = PageParse.getArticalUrl(volume_list);
//		FileTool.saveToLocal(article_string, "D:\\Tools\\crawlerdata\\url\\article_url.txt");
		
		/**
		 * 将pdf的url存储到本地
		 */
//		List article_list = FileTool.txt2List(article_string);
//		String pdfurl_string = PageParse.getPdfUrl(article_list);
//		FileTool.saveToLocal(pdfurl_string, "D:\\Tools\\crawlerdata\\url\\pdf_url.txt");
		
		/**
		 * 保存abstract
		 */
/*		List<String> article_list = FileTool.txt2List("D:\\Tools\\crawlerdata\\url\\article_url.txt");
		for(int i=0;i<=543;i++) {
			String abst = PageParse.getAbstract(article_list.get(i));
			abst = (i+1)+abst+" ";
			FileTool.saveToLocal(abst, "D:\\Tools\\crawlerdata\\abstract\\abstract_2.txt");
			System.out.println("已完成下载title+abstract "+(i+1)+" 个");
		}*/
		
		
		/**
		 * 保存没有提取到全文pdf的文章的title+期卷号
		 */
//		List<String> voulume_url = PageParse.getVolumeUrl("http://iopscience.iop.org/issue/2041-8205/", 842, 851);
//		StringBuffer sBuffer = new  StringBuffer();
//		for(String volume:voulume_url) {
//			Page volume_page = RequestAndResponseTool.sendRequstAndGetResponse(volume);
//			Elements es = PageParse.select(volume_page, "a.art-list-item-title");
//			for (int i=0;i<es.size();i++) {
//				String artical_url = es.get(i).attr("href");
//
//				Page article_page = RequestAndResponseTool.sendRequstAndGetResponse("http://iopscience.iop.org" + artical_url);
//				Elements artical_title = PageParse.select(article_page,"h1");
//				String title = artical_title.first().text();
//				
//				int time = new Random().nextInt(2000)+4000;
//				try {
//					Thread.sleep(time);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
//				Page pdf_page = RequestAndResponseTool.sendRequstAndGetResponse("http://iopscience.iop.org" + artical_url+"/pdf");
//				if(pdf_page.getContentType().contains("html")) {	
//					System.out.println(volume+"对应文章没有pdf原文");
//					System.out.println("Title: "+title+System.lineSeparator()+"卷期号: "+volume.substring(41)+"/L"+(i+1));
//					FileTool.saveToLocal("Title: "+title+System.lineSeparator()+"卷期号: "+
//							volume.substring(41)+"/第"+(i+1)+"篇"+System.lineSeparator(),"D:\\Tools\\crawlerdata\\url\\message_1.txt" );
//					continue;
//				}
//				else {
//					System.out.println(volume.substring(41)+"/L"+(i+1)+"对应文章有pdf原文");
//				}
//			}
//			
//			
//		}
		/**
		 * 保存没有pdf全文的文章编号
		 */
//		List<String> pdfurl_list = FileTool.txt2List("D:\\Tools\\crawlerdata\\url\\pdf_url.txt");
//		for(int i=0;i<=543;i++) {
//
//			
//			Page pdf_page = RequestAndResponseTool.sendRequstAndGetResponse("http://iopscience.iop.org"+pdfurl_list.get(i));
//			if(pdf_page.getContentType().contains("html")) {
//				
//				System.out.println("第"+(i+1)+"篇pdf没有对应的原文链接");
//				FileTool.saveToLocal("第"+(i+1)+"篇pdf没有对应的原文链接"+System.lineSeparator(),"D:\\Tools\\crawlerdata\\url\\message.txt" );
//				continue;
//			}
//			System.out.println("已完成下载title+abstract "+(i+1)+" 个");
//		}
		
		
		/**
		 * 保存有pdf全文链接的文章pdf到本地
		 */
//		List<String> pdfurl_list = FileTool.txt2List("D:\\Tools\\crawlerdata\\url\\pdf_url.txt");
//		
//		for(int i=400;i<543;i++) {
//			
//			//设置一个缓冲时间
//			int time = new Random().nextInt(2000)+4000;
//			try {
//				Thread.sleep(time);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			Page pdf_page = RequestAndResponseTool.sendRequstAndGetResponse("http://iopscience.iop.org"+pdfurl_list.get(i));
//			//System.out.println("请求到pdf对应page页面，正在处理...");
//			
//			if(pdf_page.getContentType().contains("html")) {
//				System.out.println("第"+(i+1)+"篇pdf没有对应的原文链接");
//				//FileTool.saveToLocal("第"+(i+1)+"篇pdf没有对应的原文链接"+System.lineSeparator(),"D:\\Tools\\crawlerdata\\url\\message.txt" );
//				continue;
//			}
//			System.out.println("第"+(i+1)+"篇有pdf全文");
//			FileTool.saveToLocal(pdf_page, "D:\\Tools\\crawlerdata\\pdf_2");
//			System.out.println("已完成下载pdf"+(i+1)+"个");
//		}
		
		
		/**
		 * 保存title+abstract+pdf全文
		 */
		List<String> voulume_url = PageParse.getVolumeUrl("http://iopscience.iop.org/issue/2041-8205/", 834, 834);
		int j=0;
		for(String volume:voulume_url) {
			Page volume_page = RequestAndResponseTool.sendRequstAndGetResponse(volume);
			Elements es = PageParse.select(volume_page, "a.art-list-item-title");
			for (int i=0;i<es.size();i++) {
			
				
				//获取文章title+abstract
				String article_url = es.get(i).attr("href");
				String title_abs = PageParse.getAbstract(article_url);
				
				FileTool.saveToLocal((++j)+" "+title_abs+"Issue&Volume: "+
						volume.substring(41)+"/Number"+(i+1)+System.lineSeparator()+System.lineSeparator(),"D:\\Tools\\crawlerdata\\url\\message_2.txt" );
				System.out.println("完成title+abstract "+j+"个" );
				
				//保存pdf
				Page pdf_page = RequestAndResponseTool.sendRequstAndGetResponse("http://iopscience.iop.org" + article_url+"/pdf");
				
				if(pdf_page.getContentType().contains("html")) {	
					System.out.println(article_url+"对应文章没有pdf原文");
					continue;
				}
				System.out.println(volume.substring(41)+"/Number"+(i+1)+"有pdf全文");
				FileTool.saveToLocal(pdf_page, "D:\\Tools\\crawlerdata\\pdf_3");
				System.out.println("已完成下载pdf "+j+"个");
				
				//设置缓冲时间
				int time = new Random().nextInt(2000)+4000;
				try {
					Thread.sleep(time);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
		}

	}
}
