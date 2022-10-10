package com.project.MyProject.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "COIN")
public class CoinVo implements java.io.Serializable {

	private String code;

	private String cname;

	private String symbol;

	private String rate;

	private String description;

	private String updatetime;

	public CoinVo() {

	}

	public CoinVo(String code) {
		this.code = code;
	}

	public CoinVo(String code, String cname, String symbol, String rate, String description, String updatetime) {
		this.code = code;
		this.cname = cname;
		this.symbol = symbol;
		this.rate = rate;
		this.description = description;
		this.updatetime = updatetime;
	}

	@Id
	@Column(name = "code", unique = true, nullable = false, length = 10)

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "cname", nullable = false, length = 30)
	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	@Column(name = "symbol", nullable = false, length = 20)
	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	@Column(name = "rate", nullable = false, length = 30)
	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	@Column(name = "description", nullable = false, length = 200)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "updatetime", nullable = false, length = 14)
	public String getUpdateTime() {
		return updatetime;
	}

	public void setUpdateTime(String updatetime) {
		this.updatetime = updatetime;
	}

	@Override
	public String toString() {
		return "CoinVo [code=" + code + ", cname=" + cname + ", symbol=" + symbol + ", rate=" + rate + ", description="
				+ description + ", updatetime=" + updatetime + "]";
	}

}
