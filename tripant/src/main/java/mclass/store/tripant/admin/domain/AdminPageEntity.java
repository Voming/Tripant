package mclass.store.tripant.admin.domain;




import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AdminPageEntity {


	//페이징처리
			private int startNum; //시작페이지
			private int endNum; //마지막페이지
			private int pnum; //페이지넘버
	
}
