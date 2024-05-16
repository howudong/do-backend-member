package hobbiedo.member.application;

import java.util.List;

import org.springframework.stereotype.Service;

import hobbiedo.member.dto.request.RegionDetailDto;
import hobbiedo.member.vo.response.RegionAddressNameVo;
import hobbiedo.member.vo.response.RegionGetDetailVo;
import hobbiedo.member.vo.response.RegionXYVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegionServiceImp implements RegionService {
	@Override
	public void addRegion(RegionDetailDto regionDetailDto, String uuid) {

	}

	@Override
	public List<RegionAddressNameVo> getAddressNames(String uuid) {
		return null;
	}

	@Override
	public RegionGetDetailVo getRegion(Long memberRegionId) {
		return null;
	}

	@Override
	public RegionAddressNameVo getSelectedRegion(String uuid) {
		return null;
	}

	@Override
	public void modifyRegion(Long memberRegionId, RegionDetailDto regionDetailDto) {

	}

	@Override
	public void deleteRegion(Long memberRegionId) {

	}

	@Override
	public void modifyActiveRegion(Long memberRegionId) {

	}

	@Override
	public List<RegionXYVO> getRegionXY(String uuid) {
		return null;
	}
}
