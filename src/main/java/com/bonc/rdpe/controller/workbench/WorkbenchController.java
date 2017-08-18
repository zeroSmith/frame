package com.bonc.rdpe.controller.workbench;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.SimpleFormatter;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 作者: jxw
 * @date 创建时间: 2016-10-4 上午11:31:02
 * @version 版本: 1.0 描述：工作台页面
 */

@Controller
@RequestMapping("/workbench")
public class WorkbenchController {

	// 工作台首页
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String main(Model model) {
		return "workbench/index";
	}

	@ResponseBody
	@RequestMapping("getSparkClusterInfoList")
	public List<Map<String, String>> getSparkClusterInfoList() {
		HttpClient httpClient = new HttpClient();
		HttpConnectionManagerParams params = httpClient.getHttpConnectionManager().getParams();
		params.setConnectionTimeout(5000);
		ArrayList<String> urlList = new ArrayList<>();
		urlList.add("http://rdpe1:23188/ws/v1/cluster/apps");
		urlList.add("http://rdpe2:23188/ws/v1/cluster/apps");
		ArrayList<Map<String, String>> sparkClusterInfoList = new ArrayList<>();
		for (String url : urlList) {
			GetMethod getMethod = new GetMethod(url);
			// 请求格式是xml
			getMethod.addRequestHeader("accept", "application/xml");
			getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5000);
			// 使用系统提供的默认的恢复策略
			getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
			try {
				int statusCode = httpClient.executeMethod(getMethod);
				if (statusCode != HttpStatus.SC_OK) {
					System.err.println("Method failed: " + getMethod.getStatusLine());
				}
				byte[] responseBody = getMethod.getResponseBody();// 读取为字节数组
				String response = new String(responseBody, "utf-8");
				if (!response.contains("<apps>")) {
					continue;
				}
				Document parseText = DocumentHelper.parseText(response);
				List<Element> selectNodes = parseText.selectNodes("/apps/app");
				SimpleDateFormat simpleFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				for (Element e : selectNodes) {
					Map<String, String> sparkClusterInfo = new LinkedHashMap<>();
					Iterator elementIterator = e.elementIterator();
					while (elementIterator.hasNext()) {
						Element element = (Element) elementIterator.next();
						String name = element.getName();
						String text = element.getText();
						if (name.equals("startedTime") || name.equals("finishedTime")) {
							text = simpleFormatter.format(new Date(Long.parseLong(text)));
						}
						sparkClusterInfo.put(name, text);
					}
					sparkClusterInfoList.add(sparkClusterInfo);
				}
				if (sparkClusterInfoList.size() > 0) {
					// 集群信息已经有数据
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				// 释放资源
				getMethod.releaseConnection();
			}
		}
		return sparkClusterInfoList;
	}
}
