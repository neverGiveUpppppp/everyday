package com.example.controller.form;

import javax.validation.GroupSequence;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotEmpty;

import com.example.validation.ValidationSteps;

import lombok.Data;

@Data
@GroupSequence({ // Validation 단계별 검증을 위한 @GroupSequence추가
	BoardSaveForm.class,
	ValidationSteps.Step1.class, // ValidationSteps클래스호출 .Step1-5인터페이스 호출
	ValidationSteps.Step2.class,
	ValidationSteps.Step3.class,
	ValidationSteps.Step4.class,
	ValidationSteps.Step5.class
})
public class BoardSaveForm {
	

//	@NotEmpty
//	private int boardSeq;
//	
//	@NotEmpty(groups = ValidationSteps.Step1.class)
//	private String boardType;
//	
//	@NotEmpty(groups = ValidationSteps.Step2.class)
//	private String userName;
//	
//	@NotEmpty(groups = ValidationSteps.Step3.class)
//	@Length(groups = ValidationSteps.Step4.class, min=5, max=20) // 길이 체크 : 5-20사이
//	private String title;
//	
//	@NotEmpty(groups = ValidationSteps.Step5.class)
//	private String contents;
	
	// 위에서 원하는 메세지 출력 추가
	@NotEmpty
	private int boardSeq;
	
	// 방법1 : 직접 message 속성에 str 출력
//	@NotEmpty(groups = ValidationSteps.Step1.class, message="게시글 종류를 선택해주세요")
	// 방법2 : properties로 외부 파일에서 내용 끌어오기
	// message.properties
	@NotEmpty(groups = ValidationSteps.Step1.class, message="{BoardSaveForm.boardType.notEmpty}")
	private String boardType;
	
	@NotEmpty(groups = ValidationSteps.Step2.class,message="{BoardSaveForm.userName.notEmpty}")
	private String userName;
	
	@NotEmpty(groups = ValidationSteps.Step3.class,message="{BoardSaveForm.title.notEmpty}")
	@Length(groups = ValidationSteps.Step4.class, min=5, max=20,message="{BoardSaveForm.title.Length}") // 길이 체크 : 5-20사이
	private String title;
	
	@NotEmpty(groups = ValidationSteps.Step5.class,message="{BoardSaveForm.contents.notEmpty}")
	private String contents;

	
	
}
