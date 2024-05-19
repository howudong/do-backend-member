package hobbiedo.member.dto.response;

import hobbiedo.member.domain.MemberRegion;
import hobbiedo.member.vo.response.RegionXyVo;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RegionXyDto {
	private Long memberRegionId;
	private String addressName;
	private double latitude;
	private double longitude;

	public static RegionXyDto toRegionXyDto(MemberRegion memberRegion) {
		return RegionXyDto.builder()
				.memberRegionId(memberRegion.getId())
				.addressName(memberRegion.getAddressName())
				.latitude(memberRegion.getLatitude())
				.longitude(memberRegion.getLongitude())
				.build();
	}
}
