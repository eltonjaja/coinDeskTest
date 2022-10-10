package com.project.MyProject.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.MyProject.vo.CoinVo;

@Repository
public interface CoinDao extends JpaRepository<CoinVo, String> {

}
