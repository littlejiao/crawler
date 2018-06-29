package edu.kndev.page;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class PageParse {

	/**
	 * 通过选择器来选取页面元素
	 */
	public static Elements select(Page page, String cssSelector) {

		return page.getDoc().select(cssSelector);
	}

	/**
	 * 获取期刊中从a期到b期的每一卷url
	 */
	public static List getVolumeUrl(String url, int a, int b) {

		List<String> url_list = new ArrayList<String>();

		// 获取所有卷的url
		for (int i = a; i <= b; i++) {
			String newurl_1 = url + i + "/1";
			String newurl_2 = url + i + "/2";
			url_list.add(newurl_1);
			url_list.add(newurl_2);
		}
		return url_list;
	}

	/**
	 * 获取每一篇文章的url
	 */
	public static String getArticalUrl(List<String> list) {

		StringBuffer sb = new StringBuffer();
		for (String url : list) {

			// 根据url获取每一卷的page
			Page volume_page = RequestAndResponseTool.sendRequstAndGetResponse(url);

			// 根据page获取每一篇文章的url,文章的url在class=art-list-item-title的a标签下
			Elements es = PageParse.select(volume_page, "a.art-list-item-title");
			for (Element e : es) {
				String artical_url = e.attr("href");
				sb.append(artical_url).append(System.getProperty("line.separator"));
			}
		}
		return sb.toString();

	}

	/**
	 * 从文章的url获取文章的page，从而获取文章的abstract
	 */
	public static String getAbstract(String article_url) {
		
		StringBuffer sb = new StringBuffer();
		
		Page article_page = RequestAndResponseTool.sendRequstAndGetResponse("http://iopscience.iop.org" + article_url);
			
        Elements artical_title = PageParse.select(article_page,"h1");
        Elements abstract_value = PageParse.select(article_page,"div.article-content");
         	
        //abstract在class=artical-content的div元素下，首次出现的p元素的内容（因为abstract内容在首个p元素下）
        String astr = abstract_value.select("p").first().text();
        String title = artical_title.first().text();
			
        sb.append("Title: "+title+System.lineSeparator()+"Abstract: "+astr+System.lineSeparator());
        
        return sb.toString();
		}



	/**
	 * 从存储article的url中，获取文章的page，从而通过href标签获取pdf页面的所有url
	 */
	public static String getPdfUrl(List<String> list) {

		StringBuffer sb = new StringBuffer();
		for (String url : list) {

			// 获取文章的page
			Page artical_page = RequestAndResponseTool.sendRequstAndGetResponse("http://iopscience.iop.org" + url);

			// 在文章的page中获取pdf页面的url
			Elements elements = artical_page.getDoc().select("[href$=/pdf]");
			String element = elements.attr("href");
			sb.append(element + System.lineSeparator());
		}
		return sb.toString();

	}

	/**
	 * 通过pdf的url来获取pdf的page，将page存储为list集合
	 */
	public static List getPdfPageList(List<String> list) {

		List<Page> pdfpage_list = new ArrayList<Page>();
		for (String url : list) {
			Page pdf_page = RequestAndResponseTool.sendRequstAndGetResponse("http://iopscience.iop.org" + url);
			pdfpage_list.add(pdf_page);
		}

		return pdfpage_list;

	}

}
