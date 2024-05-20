package hobbiedo.member.dto.response;

import hobbiedo.member.domain.MemberRegion;
import hobbiedo.member.type.RangeType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RegionGetDetailDto {
	private String addressName;
	private double latitude;
	private double longitude;
	private RangeType currentSelectedRange;

	public static RegionGetDetailDto toRegionGetDetailDto(MemberRegion memberRegion) {
		return RegionGetDetailDto.builder()
				.addressName(memberRegion.getAddressName())
				.latitude(memberRegion.getLatitude())
				.longitude(memberRegion.getLongitude())
				.currentSelectedRange(RangeType.toType(memberRegion.getCurrentSelectedRange()))
				.build();
	}
}
