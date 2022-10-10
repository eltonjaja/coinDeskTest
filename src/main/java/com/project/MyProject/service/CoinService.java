package com.project.MyProject.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.project.MyProject.dao.CoinDao;
import com.project.MyProject.vo.CoinVo;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class CoinService {

	@Autowired
	private CoinDao coinDao;

	public List<CoinVo> queryAll() throws Exception {

		return coinDao.findAll();
	}

	public void insert(CoinVo coinVo) throws Exception {
		coinVo.setUpdateTime(getDateTimeStr());
		coinDao.save(coinVo);
	}

	public void update(CoinVo coinVo) throws Exception {
		coinVo.setUpdateTime(getDateTimeStr());
		coinDao.save(coinVo);
	}
	
	public void deleteVo(CoinVo coinVo) throws Exception {
		coinDao.deleteById(coinVo.getCode());
	}

	private String getDateTimeStr() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		return formatter.format(new Date());
	}

}
