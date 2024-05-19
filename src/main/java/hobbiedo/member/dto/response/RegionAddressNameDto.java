package hobbiedo.member.dto.response;

import hobbiedo.member.domain.MemberRegion;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RegionAddressNameDto {
	private Long memberRegionId;
	private String addressName;

	public static RegionAddressNameDto toRegionAddressNameDto(MemberRegion memberRegion) {
		return RegionAddressNameDto.builder()
				.memberRegionId(memberRegion.getId())
				.addressName(memberRegion.getAddressName())
				.build();
	}
}
