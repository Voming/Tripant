package mclass.store.tripant.plan.model.service;

import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


@Service
public class PlaceService {
	// 스케줄쪽에서 해야할 일
	// 1. API로 place 정보 받아오기
	// 2.
	
	
	public static String getTagValue(String tag, Element eElement) {
		// 결과를 저장할 result 변수 선언
		String result = "";
		NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
		result = nlList.item(0).getTextContent();
		return result;
	}
	// tour.rest.api.servicekey 받아오기
	@Value("${tour.rest.api.servicekey}")
	private String tourServiceKey;

	// @Scheduled(cron = "*/10 * * * * *")
	public void getPlace() {
		System.out.println("####################scheduled############" + new Date());
		
		//관광타입(12:관광지, 14:문화시설, 15:축제공연행사, 25:여행코스, 28:레포츠, 32:숙박, 38:쇼핑, 39:음식점) ID
		int contentTypeId = 12;

		String url = String.format("https://apis.data.go.kr/B551011/KorService1/areaBasedList1?"
				+ "numOfRows=10"
				+ "&pageNo=1"
				+ "&MobileOS=ETC"
				+ "&MobileApp=TripAnt"
				+ "&_type=xml"
				+ "&arrange=A"
				+ "&contentTypeId=%d"
				+ "&areaCode=1"
				+ "&serviceKey=",
				contentTypeId);
		url += tourServiceKey;

		try {
			DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
			Document doc = dBuilder.parse(url);

			// 제일 첫번째 태그
			doc.getDocumentElement().normalize();

			// 파싱할 tag
			NodeList nList = doc.getElementsByTagName("item");
			
			System.out.println(nList);
			
//			List<ProengineerDto> dtolist = new ArrayList<ProengineerDto>();
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				Element eElement = (Element) nNode;
				
				System.out.println(getTagValue("addr1", eElement));
//

//				ProengineerDto dto = new ProengineerDto(0, description, docregstartdt, docregenddt, docexamdt,
//						docpassdt, pracregstartdt, pracregenddt, pracexamstartdt, pracexamenddt, pracpassdt);
//				dtolist.add(dto);
			}
			
 		// 장소에 값 넣기 따로 호출

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
