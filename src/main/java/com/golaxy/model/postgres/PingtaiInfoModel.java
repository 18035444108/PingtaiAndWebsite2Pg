package com.golaxy.model.postgres;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import com.vladmihalcea.hibernate.type.array.IntArrayType;
import com.vladmihalcea.hibernate.type.array.StringArrayType;

import lombok.Data;

@TypeDefs({ @TypeDef(name = "string-array", typeClass = StringArrayType.class),
		@TypeDef(name = "int-array", typeClass = IntArrayType.class) })
@Data
@Entity(name = "PingtaiInfoModel")
@Table(name = "pingtai_info", catalog = "workdb")
public class PingtaiInfoModel implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "ptid", nullable = false)
	private BigInteger ptid;
	@Column(name = "ptmc")
	private String ptmc;

	/**
	 * 集合类型
	 */
	@Type(type = "int-array")
	@Column(name = "ytfl", columnDefinition = "integer[]")
	private Integer[] ytfl;
	@Column(name = "\"taskId\"")
	private BigInteger taskId;
	@Column(name = "jgid")
	private BigInteger jgid;
	// 机构名称
	@Column(name = "jgmc")
	private String jgmc;
	// 机构简称
	@Column(name = "jgjc")
	private String jgjc;

	@Column(name = "province")
	private String province;

	// 市
	@Column(name = "city")
	private String city;

	// 区
	@Column(name = "district")
	private String district;

	// 公司法人
	@Column(name = "\"legalPerson\"")
	private String legalPerson;

	// 联系方式
	@Column(name = "contacts")
	private String contacts;

	// 工商注册号
	@Column(name = "\"businessRegNo\"")
	private String businessRegNo;

	// 登记机构
	@Column(name = "\"regAuthorities\"")
	private String regAuthorities;

	// 工商注册地
	@Column(name = "\"regAddress\"")
	private String regAddress;

	// 注册资金
	@Column(name = "\"regCapital\"")
	private String regCapital;

	@Column(name = "\"insertTime\"")
	private Date insertTime;

	@Column(name = "\"updateTime\"")
	private Date updateTime;

	@Column(name = "\"deleteFlag\"")
	private Integer deleteFlag;

	public BigInteger getPtid() {
		return ptid;
	}

	public void setPtid(BigInteger ptid) {
		this.ptid = ptid;
	}

	public String getPtmc() {
		return ptmc;
	}

	public void setPtmc(String ptmc) {
		this.ptmc = ptmc;
	}

	public Integer[] getYtfl() {
		return ytfl;
	}

	public void setYtfl(Integer[] ytfl) {
		this.ytfl = ytfl;
	}

	public BigInteger getTaskId() {
		return taskId;
	}

	public void setTaskId(BigInteger taskId) {
		this.taskId = taskId;
	}

	public BigInteger getJgid() {
		return jgid;
	}

	public void setJgid(BigInteger jgid) {
		this.jgid = jgid;
	}

	public String getJgmc() {
		return jgmc;
	}

	public void setJgmc(String jgmc) {
		this.jgmc = jgmc;
	}

	public String getJgjc() {
		return jgjc;
	}

	public void setJgjc(String jgjc) {
		this.jgjc = jgjc;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getBusinessRegNo() {
		return businessRegNo;
	}

	public void setBusinessRegNo(String businessRegNo) {
		this.businessRegNo = businessRegNo;
	}

	public String getRegAuthorities() {
		return regAuthorities;
	}

	public void setRegAuthorities(String regAuthorities) {
		this.regAuthorities = regAuthorities;
	}

	public String getRegAddress() {
		return regAddress;
	}

	public void setRegAddress(String regAddress) {
		this.regAddress = regAddress;
	}

	public String getRegCapital() {
		return regCapital;
	}

	public void setRegCapital(String regCapital) {
		this.regCapital = regCapital;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
