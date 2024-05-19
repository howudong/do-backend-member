package hobbiedo.global.base.status;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SuccessStatus {

	// REGION 활동지역
	CREATE_REGION("활동 지역을 등록에 성공하였습니다."),
	FIND_ADDRESS_NAME_LIST("활동 지역 이름명 리스트 조회에 성공하였습니다."),
	FIND_REGION_DETAIL("한 활동 지역 정보 조회에 성공하였습니다."),
	FIND_SELECTED_ADDRESS_NAME("현재 선택 활동 지역 이름명 조회에 성공하였습니다."),
	UPDATE_REGION("활동 지역 수정 성공하였습니다."),
	DELETE_REGION("활동 지역 삭제 성공하였습니다."),
	CHANGE_ACTIVE_REGION("기본 활동 지역 변경에 성공하였습니다."),
	FIND_REGION_XY("소모임 생성 시, 활동 지역 목록 조회에 성공하였습니다.");

	private final String message;

	/**
	 * getReason() 함수를 사용해야 할 때
	 * 1. 간결성과 보안: API 응답에서 꼭 필요한 정보만을 포함시키기 위해 사용(ex: API 응답의 크기 감소, 민감한 데이터 제한)
	 * 2. 내부 처리용: API 응답 전송 전,내부 로그나 모니터링 시스템에 오류 정보를 기록할때 사용 로그 데이터의 크기를 줄이거나 처리를 단순화
	 * 3. HTTP 상태 코드 분리: getReason()을 사용하여 오류 코드와 메시지만을 전달하고, HTTP 상태 코드는 별도로 처리 가능
	 * 4. 성능 최적화: 성능이 중요한 환경에서는 가능한 한 응답을 간단하게 유지하여 이러한 오버헤드를 최소화
	 * 5. API 설계의 일관성: 오류 처리를 통합하여 여러 다른 API에서 일관된 방식으로 오류를 보고하고 싶을 때 유용
	 */
	// @Override
	// public ReasonDto getReason() {
	// 	return ReasonDto
	// 			.builder()
	// 			.code(status)
	// 			.message(message)
	// 			.build();
	// }
	//
	// @Override
	// public ReasonDto getReasonHttpStatus() {
	// 	return ReasonDto
	// 			.builder()
	// 			.httpStatus(httpStatus)
	// 			.code(status)
	// 			.message(message)
	// 			.build();
	// }
}
