package com.golaxy.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityManagerFactory;

import org.apache.coyote.http11.filters.VoidInputFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.golaxy.utils.FileUtils;
import com.netflix.discovery.converters.jackson.builder.StringInterningAmazonInfoBuilder;

import lombok.extern.slf4j.Slf4j;
import scala.inline;

@Slf4j
@Service
public class KeywordRuleServie {
	@Autowired
	@Qualifier("mysqlFundsTaskEntityManagerFactory")
	private EntityManagerFactory entityManagerFactory;
	
	public void ruleCreate(int ptid){
		String sql = "select p.ptid,p.ptmc,group_concat(DISTINCT(wz.wmc)) from pt p,(select DISTINCT(wzmc) wmc,ptid pid from qrjrwz where ptid > "+ptid+") wz where p.ptid = wz.pid GROUP BY p.ptmc ORDER BY ptid";
		@SuppressWarnings("unchecked")
		List<Object[]> rules = entityManagerFactory.createEntityManager().createNativeQuery(sql).getResultList();
		FileUtils.writeFile("./conf/ptid.txt", false, rules.get(rules.size()-1)[0]+"");
		for (Object[] objects : rules) {
			String ptid_s = objects[0]+"";
			String ptmc = toStr(objects[1]).trim().replaceAll("\\pP","");
			String wzmc = toStr(objects[2]).trim();
			String question = "正常";
			if(ptmc.equals(wzmc)){
				if(judgeLength(ptmc)==-1){
					question = "异常";
				}
				FileUtils.writeFile("./conf/KeywordRule.txt", true, ptid_s+"\t"+ptmc+"\t"+"@title("+ptmc+")"+"\t"+question);
			}else{
				String []wzs = wzmc.split(",");
				if(wzs.length==1){
					String handlerStr = removePunt(wzs[0]).trim();
					if(judgeLength(handlerStr)==-1){
						question = "异常";
					}
					if(ptmc.equals(handlerStr)){
						FileUtils.writeFile("./conf/KeywordRule.txt", true, ptid_s+"\t"+ptmc+"\t"+"@title("+ptmc+")"+"\t"+question);
					}else{
						FileUtils.writeFile("./conf/KeywordRule.txt", true, ptid_s+"\t"+ptmc+"\t"+"@title("+ptmc+"|"+handlerStr+")"+"\t"+question);
					}
					
				}else{
					StringBuffer wzrule = new StringBuffer();
					for(int i=0;i<wzs.length;i++){
						String handlerStr = removePunt(wzs[i]).trim();
						if(!ptmc.equals(handlerStr)){
							if(judgeLength(handlerStr)==-1){
								question = "异常";
							}
							wzrule.append(handlerStr);
							if(i!=wzs.length-1){
								wzrule.append("|");
							}
						}
					}
					if(wzrule.length()!=0){
						FileUtils.writeFile("./conf/KeywordRule.txt", true, ptid_s+"\t"+ptmc+"\t"+"@title("+ptmc+"|"+wzrule+")"+"\t"+question);
					}else{
						FileUtils.writeFile("./conf/KeywordRule.txt", true, ptid_s+"\t"+ptmc+"\t"+"@title("+ptmc+")"+"\t"+question);
					}
				}
			}
		}
	}
	
	public String toStr(Object ob) {
		if (ob == null) {
			return null;
		} else {
			return (String) ob;
		}
	}
	
	public String removePunt(String original){
		return original.trim().replaceAll("\\pP","");
	}
	
	
	public int judgeLength(String dataStr){
		 String regEx = "[\\u4e00-\\u9fa5]";  
		  Pattern p = Pattern.compile(regEx);  
		  Matcher m = p.matcher(dataStr); 
		  if(m.find()){
			  if(dataStr.length()<=20 && dataStr .length()>=2){
					return 1;
				}
		  }else{
			  if(dataStr.length()<=50 && dataStr .length()>=3){
					return 1;
				}
		  }
		  return -1;
	}
}
