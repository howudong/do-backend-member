package hobbiedo.member.vo.request;

import lombok.Getter;

@Getter
public class RegionDetailVo {
	private String addressName;
	private String legalCode;
	private double latitude;
	private double longitude;
	private int currentSelectedRange;
}
