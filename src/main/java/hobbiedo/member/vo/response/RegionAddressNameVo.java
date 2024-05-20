package hobbiedo.member.vo.response;

import java.util.List;
import java.util.stream.Collectors;

import hobbiedo.member.dto.response.RegionAddressNameDto;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RegionAddressNameVo {
	private Long memberRegionId;
	private String addressName;

	public static List<RegionAddressNameVo> toRegionAddressNameVoList(List<RegionAddressNameDto> dtoList) {
		return dtoList.stream()
				.map(RegionAddressNameVo::toRegionAddressNameVo)
				.toList();
	}

	public static RegionAddressNameVo toRegionAddressNameVo(RegionAddressNameDto regionAddressNameDto) {
		return RegionAddressNameVo.builder()
				.memberRegionId(regionAddressNameDto.getMemberRegionId())
				.addressName(regionAddressNameDto.getAddressName())
				.build();
	}
}
