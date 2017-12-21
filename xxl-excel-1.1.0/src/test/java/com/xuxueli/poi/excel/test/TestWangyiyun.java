package com.xuxueli.poi.excel.test;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;

import com.xuxueli.poi.excel.ExcelExportUtil;
import com.xuxueli.poi.excel.test.model.Wangyiyun;

import utils.WangyiyunMinyao;

public class TestWangyiyun {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		int page = 0;
		// 这里只爬取了8页数据
		ArrayList<Wangyiyun> wangyiyunArraylist=new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			// 爬取
			String url_str = "http://music.163.com/discover/playlist/?order=hot&cat=民谣&limit=35&offset=" + page;
			ArrayList<Wangyiyun> arrayList = WangyiyunMinyao.Crawl(url_str, "utf-8");
			wangyiyunArraylist.addAll(arrayList);
			for (Wangyiyun wangyiyun : arrayList) {
				System.out.println(wangyiyun);
			}
			
			/**
			 * Excel导出：Object 转换为 Excel
			 */			
			// page参数加35（这个35是分析民谣栏）
			page = page + 35;
		}
		String filePath = "wangyiyun.xls";
		ExcelExportUtil.exportToFile(filePath, wangyiyunArraylist, wangyiyunArraylist);
	}

}
