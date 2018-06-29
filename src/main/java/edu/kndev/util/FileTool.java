package edu.kndev.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.kndev.page.Page;
import jdk.nashorn.internal.ir.BreakableNode;


public class FileTool {
	
	 private static String dirPath;

	    /**
	     * 根据 URL 和网页类型生成需要保存的网页的文件名，去除 URL 中的非文件名字符
	     */
	    public static String getFileNameByUrl(String url, String contentType) {
	        //去除 http://
	        url = url.substring(7);
	        //text/html 类型
	        if (contentType.indexOf("html") != -1) {
	            url = url.replaceAll("[\\?/:*|<>\"]", "_") + ".html";
	            return url;
	        }
	        //如 application/pdf 类型
	        else {
	            return url.replaceAll("[\\?/:*|<>\"]", "_") + "." +
	                    contentType.substring(contentType.lastIndexOf("/") + 1);
	        }
	    }


	    /**
	     * 保存String数据到本地文件，即保存文章的title+abstract到本地filepath
	     */
	    public static void saveToLocal(String string,String filepath) {
	    	
	    	byte[] data = string.getBytes();
	        try {
	            DataOutputStream out = new DataOutputStream(new FileOutputStream(new File(filepath),true));
	            for (int i = 0; i < data.length; i++) {
	                out.write(data[i]);
	            }
	            out.flush();
	            out.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    /**
	     * 将pdf全文存储到本地
	     * @param page
	     * @param url
	     */
	    public static void saveToLocal(Page page,String url) {
	    	
	        String fileName = getFileNameByUrl(page.getUrl(), page.getContentType()) ;
	        String filePath = url+"\\"+fileName;
	        byte[] data = page.getContent();
	        try {
	            //Files.lines(Paths.get("D:\\jd.txt"), StandardCharsets.UTF_8).forEach(System.out::println);
	            DataOutputStream out = new DataOutputStream(new FileOutputStream(new File(filePath)));

	            for (int i = 0; i < data.length; i++) {
	                out.write(data[i]);
	            }
	            out.flush();
	            out.close();
	            System.out.println("PDF: "+ fileName + "已经被存储" );
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    
		
		/**
		 * 读取存储文章url的txt,存储为list
		 */
		public static List txt2List(String filepath){
	        List<String> pdfurl_list = new ArrayList<String>();
	        try{
	            BufferedReader br = new BufferedReader(new FileReader(new File(filepath)));//构造一个BufferedReader类来读取文件
	            String s = null;
	            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
	                pdfurl_list.add(s);
	            }
	            br.close();    
	        }catch(Exception e){
	            e.printStackTrace();
	        }
	        return pdfurl_list;
	    }
}
