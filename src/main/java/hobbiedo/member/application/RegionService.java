package hobbiedo.member.application;

import java.util.List;

import hobbiedo.member.dto.request.RegionDetailDto;
import hobbiedo.member.dto.response.RegionAddressNameDto;
import hobbiedo.member.dto.response.RegionGetDetailDto;
import hobbiedo.member.dto.response.RegionXyDto;
import hobbiedo.member.vo.request.RegionDetailVo;
import hobbiedo.member.vo.response.RegionAddressNameVo;
import hobbiedo.member.vo.response.RegionGetDetailVo;
import hobbiedo.member.vo.response.RegionXyVo;

public interface RegionService {
	void addRegion(RegionDetailDto regionDetailDto, String uuid);

	List<RegionAddressNameDto> getAddressNames(String uuid);

	RegionGetDetailDto getRegion(Long memberRegionId);

	RegionAddressNameDto getSelectedRegion(String uuid);

	void modifyRegion(Long memberRegionId, RegionDetailDto regionDetailDto);

	void deleteRegion(Long memberRegionId);

	void changeActiveRegion(Long memberRegionId, String uuid);

	List<RegionXyDto> getRegionXY(String uuid);

}
