package com.golaxy.dao.postgres;

import java.math.BigInteger;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.golaxy.model.postgres.WebSiteInfoModel;

@Repository
public interface WebSiteInfoDao extends JpaRepository<WebSiteInfoModel, BigInteger> {
}
	
