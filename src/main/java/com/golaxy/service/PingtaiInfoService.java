package com.golaxy.service;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Service;

import com.golaxy.dao.postgres.PingtaiInfoDao;
import com.golaxy.dao.postgres.WebSiteInfoDao;
import com.golaxy.model.postgres.PingtaiInfoModel;
import com.golaxy.utils.FileUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PingtaiInfoService {
	@Autowired
	private PingtaiInfoDao pingtaiInfoDao;
	@Autowired
	@Qualifier("mysqlFundsTaskEntityManagerFactory")
	private EntityManagerFactory entityManagerFactory;
	@Autowired
	@Qualifier("postgresEntityManagerFactory")
	private LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean;
	@Autowired
	private WebSiteInfoDao webSiteInfoDao;

	@Transactional
	public void pingtai_info(int ptid) {
		
//		String sql = "select p.pid,p.pmc,wz2.ytfl as ytfl, j.jgid,j.jgmc,j.jgjc,j.smc as province,j.dsmc as city,j.qmc as district,j.fr as legalPerson,j.lxfs as contacts,j.gszch as businessRegNo,j.djjg as regAuthorities,j.gszcd as regAddress,j.zczj as regCapital from jg j,(select ptid pid,cyjg c,ptmc pmc from pt where ptid >"+ptid+" ) p,(select ptid,group_concat(DISTINCT(ytfl)) as ytfl from wz_jryt where ptid > "+ptid+" GROUP BY ptid) wz2 where  j.jgid=p.c  and wz2.ptid=p.pid;";
		String sql = "select t1.pid,t1.pmc,t2.ytfl,t1.jgid,t1.jgmc,t1.jgjc,t1.province,t1.city,t1.district,t1.legalPerson,t1.contacts,t1.businessRegNo,t1.regAuthorities,t1.regAddress,t1.regCapital "
				     +"from "
				     +"(select p.pid,p.pmc, j.jgid,j.jgmc,j.jgjc,j.smc as province,j.dsmc as city,j.qmc as district,j.fr as legalPerson,j.lxfs as contacts,j.gszch as businessRegNo,j.djjg as regAuthorities,j.gszcd as regAddress,j.zczj as regCapital from jg j,(select ptid pid,cyjg c,ptmc pmc from pt where ptid >"+ptid+" ) p where  j.jgid=p.c) t1 "
				     +"LEFT JOIN "
				     +"(select ptid,group_concat(DISTINCT(ytfl)) as ytfl from wz_jryt where ptid >"+ptid+" GROUP BY ptid) t2 "
				     +"on t2.ptid=t1.pid";
		@SuppressWarnings("unchecked")
		List<Object[]> a = entityManagerFactory.createEntityManager().createNativeQuery(sql).getResultList();
//		FileUtils.writeFile("./conf/ptid.txt", false, a.get(a.size()-1)[0]+"");
		insert(a);
	}

	/**
	 * 插入表pingtai_info
	 */
	public void insert(List<Object[]> a) {
		PingtaiInfoModel pim = new PingtaiInfoModel();
		int i=0;
		for (Object[] o : a) {
		
		pim.setPtid(toBigInteger(o[0]));
		
		pim.setPtmc(toStr(o[1]));
		pim.setYtfl(getIntArray(o[2]));
		pim.setTaskId(null);
		pim.setJgid(toBigInteger(o[3]));
		pim.setJgmc(toStr(o[4]));
		pim.setJgjc(toStr(o[5]));
		pim.setProvince(toStr(o[6]));
		pim.setCity(toStr(o[7]));
		pim.setDistrict(toStr(o[8]));
		pim.setLegalPerson(toStr(o[9]));
		pim.setContacts(toStr(o[10]));
		pim.setBusinessRegNo(toStr(o[11]));
		pim.setRegAuthorities(toStr(o[12]));
		pim.setRegAddress(toStr(o[13]));
		pim.setRegCapital(toStr(o[14]));
		pim.setInsertTime(new Date());
		pim.setUpdateTime(new Date());
		pim.setDeleteFlag(0);
		
//		pingtaiInfoDao.save(pim.getPtid(), pim.getPtmc(),pim.getYtfl(),pim.getTaskId(),pim.getJgid(), pim.getJgmc(), pim.getJgjc(), pim.getProvince(),
//				pim.getCity(), pim.getDistrict(), pim.getLegalPerson(), pim.getContacts(), pim.getBusinessRegNo(),
//				pim.getRegAuthorities(), pim.getRegAddress(), pim.getRegCapital(), pim.getInsertTime(),
//				pim.getUpdateTime(),pim.getDeleteFlag());
		
		pingtaiInfoDao.save(pim);
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
