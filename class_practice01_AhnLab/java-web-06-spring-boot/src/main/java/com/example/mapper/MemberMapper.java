package com.example.mapper;

import com.example.controller.form.MemberSaveForm;

public interface MemberMapper {

	int selectMemberAccountCount(String account);

	void insertMember(MemberSaveForm form);
	
	
}
