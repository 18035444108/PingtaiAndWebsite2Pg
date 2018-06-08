package com.golaxy.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Service;

import com.golaxy.dao.postgres.PingtaiInfoDao;
import com.golaxy.dao.postgres.WebSiteInfoDao;
import com.golaxy.model.postgres.PingtaiInfoModel;
import com.golaxy.model.postgres.WebSiteInfoModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class WebSiteInfoService {
	@Autowired
	private PingtaiInfoDao pingtaiInfoDao;
	@Autowired
	private WebSiteInfoDao webSiteInfoDao;
	@Autowired
	@Qualifier("mysqlFundsTaskEntityManagerFactory")
	private EntityManagerFactory entityManagerFactory;
	@Autowired
	@Qualifier("postgresEntityManagerFactory")
	private LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean;
	

	@Transactional
	public void weisite_info(int ptid) {
		
		String sql = "select t1.siteId,t1.ptid,t1.siteName,t1.domainName,t1.url,t1.host,t2.ytid,t1.ICPNo from ( select wzid as siteId,ptid,wzmc as siteName,ym as domainName,wwsywz as url,fxip as 'host',baxx as ICPNo from qrjrwz qr,(select ptid pid from pt where ptid >"+ptid+") p where qr.ptid = p.pid ) t1 LEFT JOIN (select wzid wid,ytfl ytid from wz_jryt) t2 on t1.siteId = t2.wid";
//		String sql ="select t1.siteId,t1.ptid,t1.siteName,t1.domainName,t1.url,t1.host,t2.ytid,t1.ICPNo"
//                    +"from "
//				    +"( select wzid as siteId,ptid,wzmc as siteName,ym as domainName,wwsywz as url,fxip as host,baxx as ICPNo "
//				    + "from qrjrwz qr,(select ptid pid from pt where ptid >"+ptid+") p where qr.ptid = p.pid ) t1 "
//				    +"LEFT JOIN "
//				    +"(select wzid wid,ytfl ytid from wz_jryt) t2 "
//				    +"on t1.siteId = t2.wid";
		@SuppressWarnings("unchecked")
		List<Object[]> webs = entityManagerFactory.createEntityManager().createNativeQuery(sql).getResultList();
		insert(webs);
	}

	/**
	 * 插入表website_info
	 */
	public void insert(List<Object[]> webs) {
		int i=0;
		for (Object[] o : webs) {
			WebSiteInfoModel siteInfoModel = new WebSiteInfoModel();
			siteInfoModel.setSiteId(toBigInteger(o[0]));
			siteInfoModel.setPtid(toBigInteger(o[1]));
			siteInfoModel.setSiteName(toStr(o[2]));
			siteInfoModel.setDomainName(toStr(o[3]));
			siteInfoModel.setUrl(toStr(o[4]));
			siteInfoModel.setHost(toStr(o[5]));
			siteInfoModel.setYtid(bytetoInt(o[6]));
			siteInfoModel.setICPNo(toStr(o[7]));
			siteInfoModel.setStatus(toInt(0));
			siteInfoModel.setInsertTime(new Date());
			siteInfoModel.setUpdateTime(new Date());
			siteInfoModel.setDeleteFlag(0);
			
//			webSiteInfoDao.save(siteInfoModel.getSiteId(), siteInfoModel.getPtid(), siteInfoModel.getSiteName(), siteInfoModel.getDomainName(), siteInfoModel.getUrl(), siteInfoModel.getHost(), siteInfoModel.getYtid(), siteInfoModel.getICPNo(), siteInfoModel.getStatus(), siteInfoModel.getInsertTime(), siteInfoModel.getUpdateTime(), siteInfoModel.getDeleteFlag());
			webSiteInfoDao.save(siteInfoModel);
		}
	}

	public Integer bytetoInt(Object ob) {
		if (ob == null) {
			return null;
		} else {
			byte t = (byte) ob;

			return (int) t;
		}
	}

	public Integer toInt(Object ob) {
		if (ob == null) {
			return null;
		} else {
			return (Integer) ob;
		}
	}

	public Long toLong(Object ob) {
		if (ob == null) {
			return null;
		} else {
			return (Long) ob;
		}
	}

	public String toStr(Object ob) {
		if (ob == null) {
			return null;
		} else {
			return (String) ob;
		}
	}

	public BigInteger toBigInteger(Object ob) {
		if (ob == null) {
			return null;
		} else {
			if (ob instanceof Integer) {
				return BigInteger.valueOf(((Integer) ob).longValue());
			} else {
				return (BigInteger) ob;
			}
		}
	}

	public Integer[] getIntArray(Object ob) {
		if (ob == null) {
			return new Integer[0];
		} else {
			String s = (String) ob;
			String[] ytss = s.trim().split(",");
			Integer[] yts = new Integer[ytss.length];
			for (int i = 0; i < ytss.length; i++) {
				yts[i] = Integer.parseInt(ytss[i]);
			}
			return yts;
		}
	}
}
