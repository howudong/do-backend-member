package hobbiedo.member.vo.response;

import java.util.List;

import hobbiedo.member.dto.response.RegionXyDto;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RegionXyVo {
	private Long memberRegionId;
	private String addressName;
	private double latitude;
	private double longitude;

	public static List<RegionXyVo> toRegionXyVoList(
			List<RegionXyDto> regionXyDtoList) {
		return regionXyDtoList.stream()
				.map(regionXyDto -> RegionXyVo.builder()
						.memberRegionId(regionXyDto.getMemberRegionId())
						.addressName(regionXyDto.getAddressName())
						.latitude(regionXyDto.getLatitude())
						.longitude(regionXyDto.getLongitude())
						.build()).toList();
	}
	// public RegionXyVo toRegionXyVo(RegionXyDto regionXyDto) {
	// 	return RegionXyVo.builder()
	// 			.memberRegionId(regionXyDto.getMemberRegionId())
	// 			.addressName(regionXyDto.getAddressName())
	// 			.latitude(regionXyDto.getLatitude())
	// 			.longitude(regionXyDto.getLongitude())
	// 			.build();
	// }
}
