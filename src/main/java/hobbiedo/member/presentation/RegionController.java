package hobbiedo.member.presentation;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hobbiedo.base.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
//@Tag(name = "활동 지역", description = "Region API")
@RequestMapping("/v1/region")
public class RegionController {
	//@Operation(summary = "활동 지역 등록", description = "회원이 등록할 수 있는 활동 지역(동네)를 등록(추가)한다.")
	@PostMapping
	public ApiResponse<> (vo) {
		return new ApiResponse<>();
	}
	//@Operation(summary = "활동 지역 이름명 리스트 조회", description = "한 회원에 대한 활동 지역(1~3개)의 이름명(읍동면) 리스트를 조회한다.")
	@GetMapping("/address-name/list")
	public ApiResponse<> () {
		return new ApiResponse<>();
	}
	//@Operation(summary = "한 활동 지역 정보 조회", description = "1개의 활동 지역에 대한 정보를 조회한다.")
	@GetMapping("/{member_region_id}")
	public ApiResponse<> (p) {
		return new ApiResponse<>();
	}
	//@Operation(summary = "현재 선택 활동 지역 이름명 조회", description = "회원이 현재 기본으로 선택해놓은 활동 지역의 id,이름을 조회한다.")
	@GetMapping("/selected/address-name")
	public ApiResponse<> () {
		return new ApiResponse<>();
	}
	//@Operation(summary = "활동 지역 수정", description = "1개의 활동 지역 정보를 수정한다.")
	@PutMapping("/{member_region_id}")
	public ApiResponse<> (p) {
		return new ApiResponse<>();
	}
	//@Operation(summary = "활동 지역 삭제", description = "1개의 활동 지역을 삭제한다.")
	@DeleteMapping("/{member_region_id}")
	public ApiResponse<> (p) {
		return new ApiResponse<>();
	}
	//@Operation(summary = "활성화된 활동 지역 변경", description = "활성화된 활동 지역(id)을 다른 활동 지역(id)으로 변경한다.")
	@PosteMapping("/active/{member_region_id}")
	public ApiResponse<> (p) {
		return new ApiResponse<>();
	}
	//@Operation(summary = "소모임 생성 시, 활동 지역 목록 조회", description = "회원이 소모임을 생성할때 필요한, 1~3개의 소모임 정보를 조회한다.")
	@GetMapping
	public ApiResponse<> () {
		return new ApiResponse<>();
	}
}
