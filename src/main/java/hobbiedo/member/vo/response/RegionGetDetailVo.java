package hobbiedo.member.vo.response;

import hobbiedo.member.dto.response.RegionGetDetailDto;
import hobbiedo.member.type.RangeType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RegionGetDetailVo {
	private String addressName;
	private double latitude;
	private double longitude;
	private int currentSelectedRange;

	public static RegionGetDetailVo toRegionGetDetailVo(RegionGetDetailDto regionGetDetailDto) {
		return RegionGetDetailVo.builder()
				.addressName(regionGetDetailDto.getAddressName())
				.latitude(regionGetDetailDto.getLatitude())
				.longitude(regionGetDetailDto.getLongitude())
				.currentSelectedRange(regionGetDetailDto.getCurrentSelectedRange().getKilometer())
				.build();
	}
}
