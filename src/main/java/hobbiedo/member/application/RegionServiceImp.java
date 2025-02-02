package hobbiedo.member.application;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hobbiedo.global.code.status.ErrorStatus;
import hobbiedo.global.exception.handler.ExampleHandler;
import hobbiedo.member.domain.ActiveMemberRegion;
import hobbiedo.member.domain.MemberRegion;
import hobbiedo.member.dto.request.RegionDetailDto;
import hobbiedo.member.dto.response.RegionAddressNameDto;
import hobbiedo.member.dto.response.RegionGetDetailDto;
import hobbiedo.member.dto.response.RegionXyDto;
import hobbiedo.member.infrastructure.ActiveMemberRegionRepository;
import hobbiedo.member.infrastructure.MemberRegionRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RegionServiceImp implements RegionService {

	private final MemberRegionRepository memberRegionRepository;
	private final ActiveMemberRegionRepository activeMemberRegionRepository;

	@Override
	@Transactional
	public void addRegion(RegionDetailDto regionDetailDto, String uuid) {
		memberRegionRepository.save(regionDetailDto.toCreateMemberRegion(uuid));
	}

	@Override
	public List<RegionAddressNameDto> getAddressNames(String uuid) {
		return Optional.of(memberRegionRepository.findByUuid(uuid))
			.filter(memberRegionList -> !memberRegionList.isEmpty())
			.orElseThrow(() -> new ExampleHandler(ErrorStatus.NO_EXIST_MEMBER_REGION))
			.stream()
			.map(RegionAddressNameDto::toRegionAddressNameDto)
			.toList();
	}

	@Override
	public RegionGetDetailDto getRegion(Long memberRegionId) {
		return RegionGetDetailDto.toRegionGetDetailDto(getMemberRegion(memberRegionId));
	}

	@Override
	public RegionAddressNameDto getSelectedRegion(String uuid) {
		ActiveMemberRegion activeMemberRegion = activeMemberRegionRepository.findByUuid(uuid)
			.orElseThrow(() -> new ExampleHandler(ErrorStatus.NO_EXIST_ACTIVE_MEMBER_REGION));
		return RegionAddressNameDto.toRegionAddressNameDto(
			getMemberRegion(activeMemberRegion.getMemberRegionId()));
	}

	@Override
	@Transactional
	public void modifyRegion(Long memberRegionId, RegionDetailDto regionDetailDto) {
		memberRegionRepository.save(
			regionDetailDto.toModifyMemberRegion(getMemberRegion(memberRegionId)));
	}

	@Override
	@Transactional
	public void deleteRegion(Long memberRegionId) {
		memberRegionRepository.deleteById(memberRegionId);
	}

	@Override
	@Transactional
	public void changeActiveRegion(Long memberRegionId, String uuid) {
		// 기존 활성화된 회원 지역 찾기
		ActiveMemberRegion nowActiveMemberRegion = activeMemberRegionRepository.findByUuid(uuid)
			.orElseThrow(() -> new ExampleHandler(ErrorStatus.NO_EXIST_ACTIVE_MEMBER_REGION));
		// 활성화된 회원 지역 수정
		ActiveMemberRegion newActiveMemberRegion = ActiveMemberRegion.builder()
			.id(nowActiveMemberRegion.getId())
			.uuid(nowActiveMemberRegion.getUuid())
			.memberRegionId(memberRegionId)
			.build();
		activeMemberRegionRepository.save(newActiveMemberRegion);
	}

	@Override
	public List<RegionXyDto> getRegionXY(String uuid) {
		return Optional.of(memberRegionRepository.findByUuid(uuid))
			.filter(memberRegionList -> !memberRegionList.isEmpty())
			.orElseThrow(() -> new ExampleHandler(ErrorStatus.NO_EXIST_MEMBER_REGION))
			.stream()
			.map(RegionXyDto::toRegionXyDto)
			.toList();
	}

	private MemberRegion getMemberRegion(Long memberRegionId) {
		return memberRegionRepository.findById(memberRegionId)
			.orElseThrow(() -> new ExampleHandler(ErrorStatus.NO_EXIST_MEMBER_REGION));
	}
}
