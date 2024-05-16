package hobbiedo.member.application;

import java.util.List;

import hobbiedo.member.dto.request.RegionDetailDto;
import hobbiedo.member.vo.response.RegionAddressNameVo;
import hobbiedo.member.vo.response.RegionGetDetailVo;
import hobbiedo.member.vo.response.RegionXYVO;

public interface RegionService {
	void addRegion(RegionDetailDto regionDetailDto, String uuid);

	List<RegionAddressNameVo> getAddressNames(String uuid);

	RegionGetDetailVo getRegion(Long memberRegionId);

	RegionAddressNameVo getSelectedRegion(String uuid);

	void modifyRegion(Long memberRegionId, RegionDetailDto regionDetailDto);

	void deleteRegion(Long memberRegionId);

	void modifyActiveRegion(Long memberRegionId);

	List<RegionXYVO> getRegionXY(String uuid);

}
