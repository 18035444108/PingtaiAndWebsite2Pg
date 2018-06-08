package com.golaxy.model.postgres;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import com.vladmihalcea.hibernate.type.array.IntArrayType;
import com.vladmihalcea.hibernate.type.array.StringArrayType;

import lombok.Data;

@TypeDefs({ @TypeDef(name = "string-array", typeClass = StringArrayType.class),
	@TypeDef(name = "int-array", typeClass = IntArrayType.class) })
@Data
@Entity(name = "WebSiteInfoModel")
@Table(name = "website_info", catalog = "workdb")
public class WebSiteInfoModel {

	@Id
	@Column(name = "\"siteId\"")
	private BigInteger siteId;

	@Column(name = "ptid")
	private BigInteger ptid;

	@Column(name = "\"siteName\"")
	private String siteName;

	@Column(name = "\"domainName\"")
	private String domainName;

	@Column(name = "url")
	private String url;

	@Column(name = "host")
	private String host;

	@Column(name = "ytid")
	private Integer ytid;

	@Column(name = "\"ICPNo\"")
	private String ICPNo;

	/**
	 * isopen
	 */
	@Column(name = "status")
	private Integer status;

	@Column(name = "\"insertTime\"")
	private Date insertTime;

	@Column(name = "\"updateTime\"")
	private Date updateTime;

	@Column(name = "\"deleteFlag\"")
	private Integer deleteFlag;

	public WebSiteInfoModel(BigInteger siteId, BigInteger ptid, String siteName, String domainName, String url,
			String host, Integer ytid, String iCPNo, Integer status, Date insertTime, Date updateTime,
			Integer deleteFlag) {
		this.siteId = siteId;
		this.ptid = ptid;
		this.siteName = siteName;
		this.domainName = domainName;
		this.url = url;
		this.host = host;
		this.ytid = ytid;
		ICPNo = iCPNo;
		this.status = status;
		this.insertTime = insertTime;
		this.updateTime = updateTime;
		this.deleteFlag = deleteFlag;
	}

	
	public BigInteger getSiteId() {
		return siteId;
	}


	public void setSiteId(BigInteger siteId) {
		this.siteId = siteId;
	}


	public BigInteger getPtid() {
		return ptid;
	}


	public void setPtid(BigInteger ptid) {
		this.ptid = ptid;
	}


	public String getSiteName() {
		return siteName;
	}


	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}


	public String getDomainName() {
		return domainName;
	}


	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getHost() {
		return host;
	}


	public void setHost(String host) {
		this.host = host;
	}


	public Integer getYtid() {
		return ytid;
	}


	public void setYtid(Integer ytid) {
		this.ytid = ytid;
	}


	public String getICPNo() {
		return ICPNo;
	}


	public void setICPNo(String iCPNo) {
		ICPNo = iCPNo;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	public Date getInsertTime() {
		return insertTime;
	}


	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}


	public Date getUpdateTime() {
		return updateTime;
	}


	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}


	public Integer getDeleteFlag() {
		return deleteFlag;
	}


	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}


	public WebSiteInfoModel() {
	}

}
