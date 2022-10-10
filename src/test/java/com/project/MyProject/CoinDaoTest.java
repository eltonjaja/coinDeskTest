package com.project.MyProject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.MyProject.dao.CoinDao;
import com.project.MyProject.service.CoinService;
import com.project.MyProject.service.ReadJsonService;
import com.project.MyProject.vo.CoinVo;

@SpringBootTest
public class CoinDaoTest {

	@Autowired
	private CoinDao coinDao;

	@Autowired
	ReadJsonService readJsonService;

	@Autowired
	CoinService coinService;

	// 1. 測試呼叫查詢幣別對應表資料API，並顯示其內容。
	@Test
	public void queryTest() throws Exception {

		System.out.println("==============" + "queryTest Start " + "==============");
		readJsonService.readApiToDb();

		refrshPrintTableInfo();
		System.out.println("==============" + "queryTest End " + "==============");

	}

	// 2.測試呼叫新增幣別對應表資料API。
	@Test
	public void insertTest() throws Exception {
		System.out.println("==============" + "InsertTest Start " + "==============");

		readJsonService.readApiToDb();

		coinService.insert(new CoinVo("TWD", "台幣", "", "", "", ""));

		refrshPrintTableInfo();
		System.out.println("==============" + "InsertTest End " + "==============");

	}

	// 3. 測試呼叫更新幣別對應表資料API，並顯示其內容。
	@Test
	public void updateTest() throws Exception {
		System.out.println("==============" + "updateTest Start " + "==============");

		readJsonService.readApiToDb();

		String updateCode = "USD";

		Optional<CoinVo> findCoinVo = coinDao.findById(updateCode);

		findCoinVo.get().setDescription(findCoinVo.get().getDescription() + " Update Test");
		findCoinVo.get().setUpdateTime(getDateTimeStr());

		coinService.update(findCoinVo.get());

		refrshPrintTableInfo();
		System.out.println("==============" + "updateTest End " + "==============");

	}

	// 4. 測試呼叫刪除幣別對應表資料API。
	@Test
	public void deleteTest() throws Exception {
		System.out.println("==============" + "deleteTest Start " + "==============");

		readJsonService.readApiToDb();

		String deleteCode = "USD";

		coinService.deleteVo(new CoinVo(deleteCode));

		refrshPrintTableInfo();
		System.out.println("==============" + "deleteTest End " + "==============");

	}

	
	// 5.測試呼叫coindesk API，並顯示其內容。
	@Test
	public void readCoindeskApiPrint() throws Exception {
		System.out.println("==============" + "readCoindeskApiPrint Start " + "==============");
		System.out.println(" ");

		System.out.println(readJsonService.readCoindeskApi());

		System.out.println(" ");

		System.out.println("==============" + "readCoindeskApiPrint End " + "==============");
	}

	// 6. 測試呼叫資料轉換的API，並顯示其內容。
	@Test
	public void returnNewCoindeskAp() throws Exception {
		System.out.println("==============" + "returnNewCoindeskAp Start " + "==============");
		System.out.println(" ");

		System.out.println(readJsonService.newReturnApi());

		System.out.println(" ");

		System.out.println("==============" + "returnNewCoindeskAp End " + "==============");
	}

	public void refrshPrintTableInfo() throws Exception {
		List<CoinVo> findAll = coinService.queryAll();

		for (CoinVo coinVo : findAll) {
			System.out.println(coinVo.toString());
		}
	}

	private String getDateTimeStr() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		return formatter.format(new Date());
	}

}
