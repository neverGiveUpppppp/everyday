package com.example.mapper;

import com.example.controller.form.MemberSaveForm;

public interface MemberMapper {

	int selectmemberAccountCount(String account);	
	
	void insertMember(MemberSaveForm form);

	int selectMemberAccountCount(String account);
	
	
}
