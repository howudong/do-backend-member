package hobbiedo.member.presentation;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hobbiedo.global.base.ApiResponse;
import hobbiedo.global.base.status.SuccessStatus;
import hobbiedo.member.application.RegionService;
import hobbiedo.member.dto.request.RegionDetailDto;
import hobbiedo.member.vo.request.RegionDetailVo;
import hobbiedo.member.vo.response.RegionAddressNameVo;
import hobbiedo.member.vo.response.RegionGetDetailVo;
import hobbiedo.member.vo.response.RegionXyVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@Tag(name = "활동 지역", description = "Region API")
@RequestMapping("/v1/region")
public class RegionController {

	private final RegionService regionService;
	private final String uuid = "uuid1234";

	@PostMapping
	@Operation(summary = "활동 지역 등록", description = "회원이 등록할 수 있는 활동 지역(동네)를 등록(추가)한다.")
	public ApiResponse<Void> addRegion(
			@RequestBody RegionDetailVo regionDetailVo/*, String uuid*/) {
		regionService.addRegion(RegionDetailDto.toRegionDetailDto(regionDetailVo), uuid);
		return ApiResponse.onSuccess(SuccessStatus.CREATE_REGION, null);
	}

	@Operation(summary = "활동 지역 이름명 리스트 조회", description = "한 회원에 대한 활동 지역(1~3개)의 이름명(읍동면) 리스트를 조회한다.")
	@GetMapping("/address-name/list")
	public ApiResponse<List<RegionAddressNameVo>> getAddressNameList(/*, String uuid*/) {
		return ApiResponse.onSuccess(SuccessStatus.FIND_ADDRESS_NAME_LIST,
				RegionAddressNameVo.toRegionAddressNameVoList(regionService.getAddressNames(uuid)));
	}

	@Operation(summary = "한 활동 지역 정보 조회", description = "1개의 활동 지역에 대한 정보를 조회한다.")
	@GetMapping("/{member_region_id}")
	public ApiResponse<RegionGetDetailVo> getRegionDetail(
			@PathVariable(value = "member_region_id") Long memberRegionId) {
		return ApiResponse.onSuccess(SuccessStatus.FIND_REGION_DETAIL,
				RegionGetDetailVo.toRegionGetDetailVo(regionService.getRegion(memberRegionId)));
	}

	@Operation(summary = "현재 선택 활동 지역 이름명 조회", description = "회원이 현재 기본으로 선택해놓은 활동 지역의 id,이름을 조회한다.")
	@GetMapping("/selected/address-name")
	public ApiResponse<RegionAddressNameVo> getSelectedAddressName(/*, String uuid*/) {
		return ApiResponse.onSuccess(SuccessStatus.FIND_SELECTED_ADDRESS_NAME,
				RegionAddressNameVo.toRegionAddressNameVo(regionService.getSelectedRegion(uuid)));
	}

	@Operation(summary = "활동 지역 수정", description = "1개의 활동 지역 정보를 수정한다.")
	@PutMapping("/{member_region_id}")
	public ApiResponse<Void> modifyRegion(
			@PathVariable(value = "member_region_id") Long memberRegionId,
			@RequestBody RegionDetailVo regionDetailVo) {
		regionService.modifyRegion(memberRegionId,
				RegionDetailDto.toRegionDetailDto(regionDetailVo));
		return ApiResponse.onSuccess(SuccessStatus.UPDATE_REGION, null);
	}

	@Operation(summary = "활동 지역 삭제", description = "1개의 활동 지역을 삭제한다.")
	@DeleteMapping("/{member_region_id}")
	public ApiResponse<Void> deleteRegion(
			@PathVariable(value = "member_region_id") Long memberRegionId) {
		regionService.deleteRegion(memberRegionId);
		return ApiResponse.onSuccess(SuccessStatus.DELETE_REGION, null);
	}

	@Operation(summary = "활성화된 활동 지역 변경",
			description = "활성화된 활동 지역(id)을 다른 활동 지역(id)으로 변경한다. url의 {member_region_id}는 기존 것이 아니라 새로 설정하려는 id")
	@PostMapping("/active/{member_region_id}")
	public ApiResponse<Void> changeActiveRegion(
			@PathVariable(value = "member_region_id") Long memberRegionId /*, String uuid*/) {
		regionService.changeActiveRegion(memberRegionId, uuid);
		return ApiResponse.onSuccess(SuccessStatus.CHANGE_ACTIVE_REGION, null);
	}

	@Operation(summary = "소모임 생성 시, 활동 지역 목록 조회", description = "회원이 소모임을 생성할때 필요한, 1~3개의 소모임 정보를 조회한다.")
	@GetMapping
	public ApiResponse<List<RegionXyVo>> getRegionXY(/*, String uuid*/) {
		return ApiResponse.onSuccess(SuccessStatus.FIND_REGION_XY,
				RegionXyVo.toRegionXyVoList(regionService.getRegionXY(uuid)));
	}
}
