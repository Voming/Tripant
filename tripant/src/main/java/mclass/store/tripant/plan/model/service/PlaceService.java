package mclass.store.tripant.plan.model.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.nimbusds.jose.shaded.gson.GsonBuilder;

import mclass.store.tripant.place.domain.PlaceEntity;
import mclass.store.tripant.plan.model.repostiory.PlaceRepository;

@Service
public class PlaceService {
	@Autowired
	private PlaceRepository placeRepository;

	// XML Element 값 꺼내기
	public static String getTagValue(String tag, Element eElement) {
		Node nValue = null;

		NodeList x = eElement.getElementsByTagName(tag);
		Node test = x.item(0);
		NodeList t = null;
		if (test != null) {
			t = test.getChildNodes();
			if ((Node) t.item(0) != null) {
				nValue = (Node) t.item(0);
			}
		}
		if (nValue == null)
			return null;
		return nValue.getNodeValue();
	}

	// tour.rest.api.servicekey 받아오기
	@Value("${tour.rest.api.servicekey}")
	private String tourServiceKey;

	// api 호출
	// @Async
	private int getApi(int cId, int aCode) { // 관광타입, 지역 코드
		int result = 0;
		// api 받아온 시간
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String gettime = format.format(date);
		System.out.println("###########scheduled############" + gettime);

		int numOfRows = 1000;
		int pageNo = 0;
		int totalCount = 0;
		// int maxPageNo = 1;

		while (pageNo < totalCount / numOfRows + 1) {
			pageNo++;

			String url = String.format("https://apis.data.go.kr/B551011/KorService1/areaBasedList1?" + "numOfRows=%d"
					+ "&pageNo=%d" + "&MobileOS=ETC" + "&MobileApp=TripAnt" + "&_type=xml" + "&arrange=A"
					+ "&contentTypeId=%d" + "&areaCode=%d" + "&serviceKey=", numOfRows, pageNo, cId, aCode);
			url += tourServiceKey;

			try {
				DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
				Document doc = dBuilder.parse(url);

				// 제일 첫번째 태그
				doc.getDocumentElement().normalize();

				// 파싱할 tag
				NodeList nTotalCount = doc.getElementsByTagName("totalCount");
				try {
					totalCount = Integer.parseInt(nTotalCount.item(0).getTextContent());
					System.out.println("totalCount:" + totalCount + ", totalPage " + (totalCount / numOfRows + 1)
							+ ": pageNo:" + pageNo);
				} catch (Exception e) {
					System.out.println("!!!!!!!!!!!!!!!!!!!!!! ERROR 2 : " + totalCount);
					e.printStackTrace();
					totalCount = 0;
					continue;
				}

				// 파싱할 tag
				NodeList nList = doc.getElementsByTagName("item");

				List<PlaceEntity> dtolist = new ArrayList<PlaceEntity>();
				for (int temp = 0; temp < nList.getLength(); temp++) {
					Node nNode = nList.item(temp);
					Element eElement = (Element) nNode;

					String modifiedtime = getTagValue("modifiedtime", eElement);
//				if(modifiedtime.compareTo(gettime) < 0 ) {
					if (modifiedtime.compareTo("20240102030405") < 0) {
						continue;
					}

					String add1 = getTagValue("addr1", eElement);
					if (add1 == null || add1.length() == 0) {
//					System.out.println("!!!!!!!!!!!!!!!!!!!!!! Add1  NULL");
						continue;
					}
					String add2 = getTagValue("add2", eElement);
					String areacodeS = getTagValue("areacode", eElement); // Integer
					String booktour = getTagValue("booktour", eElement);
					String cat1 = getTagValue("cat1", eElement);
					String cat2 = getTagValue("cat2", eElement);
					String cat3 = getTagValue("cat3", eElement);
					String contentidS = getTagValue("contentid", eElement); // Integer
					String contenttypeidS = getTagValue("contenttypeid", eElement);
					String createtime = getTagValue("createtime", eElement);// 10
					String firstimage = getTagValue("firstimage", eElement);
					String firstimage2 = getTagValue("firstimage2", eElement);
					String cpyrhtDivCd = getTagValue("cpyrhtDivCd", eElement);
					String mapx = getTagValue("mapx", eElement);
					String mapy = getTagValue("mapy", eElement);
					String mlevel = getTagValue("mlevel", eElement);
					String sigungucode = getTagValue("sigungucode", eElement);
					String tel = getTagValue("tel", eElement);
					if (tel != null && tel.length() > 40) {
//					System.out.println("!!!!!!!!!!!!!!!!!!!!!! tel :" + tel);
					}
					tel = (tel != null && tel.length() > 40) ? tel.substring(0, 40) : tel;
					String title = getTagValue("title", eElement);// 20
//						String zipcodeS = getTagValue("zipcode", eElement);  //Integer   

					int areacode = Integer.parseInt(areacodeS);
					int contentid = Integer.parseInt(contentidS);
					int contenttypeid = Integer.parseInt(contenttypeidS);
//						int zipcode = Integer.parseInt(zipcodeS);

					// 관광타입ID(12:관광지, 14:문화시설, 28:레포츠, 32:숙박, 38:쇼핑, 39:음식점)
					// type(1:관광지, 2:문화시설, 3:쇼핑, 4:음식점, 5:숙박, 6:캠핑장)
					int type = 10;
					if (contenttypeid == 12) { // 관광지 1
						type = 1;
					} else if (contenttypeid == 14) { // 문화시설 2
						type = 2;
					} else if (contenttypeid == 28) { // 레포츠 -> 캠핑장 6
						if (title.contains("캠핑장")) {
							type = 6;
						}
					} else if (contenttypeid == 32) { // 숙박 5
						type = 5;
					} else if (contenttypeid == 38) { // 쇼핑 3
						type = 3;
					} else if (contenttypeid == 39) { // 음식점 4
						type = 4;
					} else {
						type = 10;
					}

					PlaceEntity dto = new PlaceEntity(type, gettime, contentid, contenttypeid, add1, add2, areacode,
							booktour, cat1, cat2, cat3, firstimage, firstimage2, cpyrhtDivCd, mapx, mapy, createtime,
							mlevel, sigungucode, tel, title, modifiedtime);
					dtolist.add(dto);
				}

///////// 			System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(dtolist));
				System.out.println("dtolist size : " + dtolist.size());
				if (dtolist.size() > 0) {
					int resultPerOnce = placeRepository.insertPlace(dtolist);
					System.out.println("DB result : " + resultPerOnce);
					result += resultPerOnce;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("!!!!!!!!!!!!!!!!!!!!!! ERROR 1 ??????");
				e.printStackTrace();
			}
		} // while
		return result;
	}

	// @Scheduled(cron = "* * 20 * * *") // 매일 20시에 실행
	// 첫 번째 * 부터 초(0-59) 분(0-59) 시간(0-23) 일(1-31) 월(1-12) 요일(0-6) (0: 일, 1: 월, 2:화, 3:수, 4:목, 5:금, 6:토)
	public int insertPlace() {
		// 반복문 돌리기(12:관광지, 14:문화시설, 28:레포츠, 32:숙박, 38:쇼핑, 39:음식점)
		int result = 0;

		int[] arrType = { 12, 14, 28, 32, 38, 39 }; // 1,2,6camp-10reports,5,3,4
		int arrTypeIdx = 0;
		while (arrTypeIdx < arrType.length) {
			for (int i = 1; ((i <= 8) || (i >= 31 && i <= 39)); /* i++ */) {
//			for(int i= 1; i<2;) {
				result += getApi(arrType[arrTypeIdx], i);
				i = (i == 8) ? i = 31 : ++i;
			}
			System.out.println(arrTypeIdx + ": result : " + result);
			arrTypeIdx++;
		}

		return result;
	}
}
