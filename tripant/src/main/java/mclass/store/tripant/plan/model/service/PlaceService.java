package mclass.store.tripant.plan.model.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

	// @Scheduled(cron = "*/20 * * * * *")
	public int insertPlace() {
		int result = 0;
		
		//api 받아온 시간
		Date date = new Date();
	    SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
	    String gettime = format.format(date);
		System.out.println("####################scheduled############" + gettime);
		
		//반복문 돌리기(12:관광지, 14:문화시설, 28:레포츠, 32:숙박, 38:쇼핑, 39:음식점)
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
			
			List<PlaceEntity> dtolist = new ArrayList<PlaceEntity>();
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
//				String zipcodeS = getTagValue("zipcode", eElement);  //Integer   
				
				int areacode = Integer.parseInt(areacodeS);
				int contentid = Integer.parseInt(contentidS);
//				int zipcode = Integer.parseInt(zipcodeS);
				
				//관광타입ID(12:관광지, 14:문화시설, 28:레포츠, 32:숙박, 38:쇼핑, 39:음식점)
				//type(1:관광지, 2:문화시설, 3:쇼핑, 4:음식점, 5:숙박, 6:캠핑장) 
				int type = 10;
				if(contentTypeId == 12) { //관광지 1
					type = 1;
				} else if(contentTypeId == 14) { //문화시설 2
					type = 2;
				} else if(contentTypeId == 28) { //레포츠 -> 캠핑장 6
					if(title.contains("캠핑장")) {
						type = 6;
					}
				} else if(contentTypeId == 32) { // 숙박 5
					type = 5;
				} else if(contentTypeId == 38) { // 쇼핑 3
					type = 3;
				} else if(contentTypeId == 39) { // 음식점 4
					type = 4;
				} else {
					type = 10;
				}
				

				PlaceEntity dto = new PlaceEntity(type, gettime, contentid, contenttypeid, add1, add2,
						areacode, booktour, cat1, cat2, cat3, firstimage,
						firstimage2, cpyrhtDivCd, mapx, mapy, createtime, mlevel,
						sigungucode, tel, title, modifiedtime);
				dtolist.add(dto);
			}

			System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(dtolist));
			result = placeRepository.insertPlace(dtolist);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
