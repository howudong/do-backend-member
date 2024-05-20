package hobbiedo.member.dto.request;

import hobbiedo.member.domain.MemberRegion;
import hobbiedo.member.type.RangeType;
import hobbiedo.member.vo.request.RegionDetailVo;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RegionDetailDto {
	private String addressName;
	private String legalCode;
	private double latitude;
	private double longitude;
	private RangeType currentSelectedRange;

	public static RegionDetailDto toRegionDetailDto(RegionDetailVo regionDetailVo) {
		return RegionDetailDto.builder()
				.addressName(regionDetailVo.getAddressName())
				.legalCode(regionDetailVo.getLegalCode())
				.latitude(regionDetailVo.getLatitude())
				.longitude(regionDetailVo.getLongitude())
				.currentSelectedRange(RangeType.toType(regionDetailVo.getCurrentSelectedRange()))
				.build();
	}

	public MemberRegion toCreateMemberRegion(String uuid) {
		return MemberRegion.builder()
				.uuid(uuid)
				.currentSelectedRange(currentSelectedRange.getKilometer())
				.latitude(latitude)
				.longitude(longitude)
				.addressName(addressName)
				.legalCode(legalCode)
				.build();
	}

	public MemberRegion toModifyMemberRegion(MemberRegion memberRegion) {
		return MemberRegion.builder()
				.id(memberRegion.getId())
				.uuid(memberRegion.getUuid())
				.currentSelectedRange(currentSelectedRange.getKilometer())
				.latitude(latitude)
				.longitude(longitude)
				.addressName(addressName)
				.legalCode(legalCode)
				.build();
	}
}
