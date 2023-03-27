


## ALTER
컬럼 추가,수정,삭제    
ALTER TABLE 테이블명 ADD 추가할컬럼명 데이터타입    
ALTER TABLE 테이블명 MODIFY 수정할컬럼명 데이터타입   
ALTER TABLE 테이블명 DROP COLUMN 삭제할컬럼명   


제약조건 추가,수정,삭제   
ALTER TABLE 테이블명 ADD CONSTRAINT 제약조건명 제약조건(컬럼명)    
ALTER TABLE 테이블명 MODIFY 컬럼명 CONSTRAINT 제약조건명    
ALTER TABLE 테이블명 DROP CONSTRAINT 제약조건명    
    ※ NOT NULL은 이미 NULL이 기본으로 있는 상태이기 때문에 MODIFY로 수정해야함    


