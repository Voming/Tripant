package mclass.store.tripant.plan.model.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import mclass.store.tripant.plan.domain.PlaceApiEntity;


@Service
public class PlaceService {
	// 스케줄쪽에서 해야할 일
	// 1. API로 place 정보 받아오기
	// 2.
	
	
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

	// @Scheduled(cron = "*/10 * * * * *")
	public void getPlace() {
		System.out.println("####################scheduled############" + new Date());
		
		//관광타입ID(12:관광지, 14:문화시설, 15:축제공연행사, 25:여행코스, 28:레포츠, 32:숙박, 38:쇼핑, 39:음식점)
		//type(1:관광지, 2:문화시설, 3:쇼핑, 4:음식점, 5:숙박, 6:캠핑장) 
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
			
			List<PlaceApiEntity> dtolist = new ArrayList<PlaceApiEntity>();
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				Element eElement = (Element) nNode;
				
				System.out.println(getTagValue("addr1", eElement));
				
				String add1 = getTagValue("addr1", eElement);         
				String add2 = getTagValue("add2", eElement);         
				String areacodeS = getTagValue("areacode", eElement);    //Integer 
				String booktour = getTagValue("booktour", eElement);     
				String cat1 = getTagValue("cat1", eElement);         
				String cat2 = getTagValue("cat2", eElement);         
				String cat3 = getTagValue("cat3", eElement);         
				String contentidS = getTagValue("contentid", eElement);   //Integer
				String contenttypeid = getTagValue("contenttypeid", eElement);
				String createtime = getTagValue("createtime", eElement);//10
				String firstimage = getTagValue("firstimage", eElement);   
				String firstimage2 = getTagValue("firstimage2", eElement);  
				String cpyrhtDivCd = getTagValue("cpyrhtDivCd", eElement);  
				String mapx = getTagValue("mapx", eElement);         
				String mapy = getTagValue("mapy", eElement);         
				String mlevel = getTagValue("mlevel", eElement);       
				String modifiedtime = getTagValue("modifiedtime", eElement); 
				String sigungucode = getTagValue("sigungucode", eElement);  
				String tel = getTagValue("tel", eElement);          
				String title = getTagValue("title", eElement);//20
				String zipcodeS = getTagValue("zipcode", eElement);  //Integer   
				
				int areacode = Integer.parseInt(areacodeS);
				int contentid = Integer.parseInt(contentidS);
				int zipcode = Integer.parseInt(zipcodeS);

				PlaceApiEntity dto = new PlaceApiEntity(add1,add2, areacode,booktour,cat1,cat2,cat3,contentid,contenttypeid,createtime,
						firstimage,firstimage2,cpyrhtDivCd,mapx,mapy,mlevel,modifiedtime,sigungucode,tel,title,zipcode);
				dtolist.add(dto);
			}
			
 		// 장소에 값 넣기 따로 호출
			System.out.println(dtolist);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
