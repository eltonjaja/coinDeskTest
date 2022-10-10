package com.project.MyProject.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.project.MyProject.dao.CoinDao;
import com.project.MyProject.util.parseCoinCnameUtil;
import com.project.MyProject.vo.CoinVo;

@Service
public class ReadJsonService {

	@Autowired
	private CoinDao coinDao;

	public String readCoindeskApi() throws Exception {
		String url = "https://api.coindesk.com/v1/bpi/currentprice.json";
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(url, String.class);

		return result;
	}

	public void readApiToDb() throws Exception {

		String jsonStr = readCoindeskApi();

		JSONObject jsonObject = new JSONObject(jsonStr);

		JSONObject jsonObjectBpi = (JSONObject) jsonObject.get("bpi");

		Iterator<String> keys = jsonObjectBpi.keys();

		while (keys.hasNext()) {
			String key = keys.next();

			if (jsonObjectBpi.get(key) instanceof JSONObject) {
				JSONObject jsonObjectCode = (JSONObject) jsonObjectBpi.get(key);

				String coinCname = "";
				if (StringUtils.isNotBlank((String) jsonObjectCode.get("code"))) {
					coinCname = parseCoinCnameUtil.getStatusText((String) jsonObjectCode.get("code"));
				}

				coinDao.save(new CoinVo((String) jsonObjectCode.get("code"), coinCname,
						(String) jsonObjectCode.get("symbol"), (String) jsonObjectCode.get("rate"),
						(String) jsonObjectCode.get("description"), getDateTimeStr()));
			}

		}

	}

	public String newReturnApi() throws Exception {

		String jsonStr = readCoindeskApi();

		JSONObject jsonObject = new JSONObject(jsonStr);

		JSONObject jsonObjectTime = (JSONObject) jsonObject.get("time");
		

		
		SimpleDateFormat ft = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss Z",Locale.US);
		Date updateDate = ft.parse(jsonObjectTime.getString("updated"));

		OffsetDateTime odt = OffsetDateTime.parse(jsonObjectTime.getString("updatedISO"));

		Date date = Date.from(odt.toInstant());

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");

//		jsonObjectTime.put("updateTime", formatter.format(updateDate));

		JSONObject jsonObjectBpi = new JSONObject();

		List<CoinVo> findCoinAll = coinDao.findAll();

		for (CoinVo coinVo : findCoinAll) {

			JSONObject jsonObj = new JSONObject();

			jsonObj.put("code", coinVo.getCode());
			jsonObj.put("cName", coinVo.getCname());
			jsonObj.put("rate", coinVo.getRate());

			jsonObjectBpi.put(coinVo.getCode().toUpperCase(), jsonObj);
		}

		return new JSONObject() {
			{
				put("time", new JSONObject().put("updateTime", formatter.format(updateDate)));
				put("bpi", jsonObjectBpi);
			}
		}.toString();

	}

	private String getDateTimeStr() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		return formatter.format(new Date());
	}

	public static void main(String[] args) throws ParseException {

		String time1 = "Oct 7, 2022 16:35:00 UTC";
//		Calendar cal = Calendar.getInstance();
//		SimpleDateFormat simpleformat = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss",Locale.US);
//		System.out.println("Today's date and time = " + simpleformat.format(cal.getTime()));

		SimpleDateFormat ft = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss Z",Locale.US);
		Date sourceDate = ft.parse(time1);
		System.out.println(sourceDate);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		System.out.println(formatter.format(sourceDate));

		String time = "2022-10-07T16:35:00+00:00";

		OffsetDateTime odt = OffsetDateTime.parse(time);

		Date date = Date.from(odt.toInstant());

		System.out.println(formatter.format(date));
	}

}
