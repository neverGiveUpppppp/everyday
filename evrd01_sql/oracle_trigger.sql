CREATE TABLE PRODUCT(
    PCODE NUMBER PRIMARY KEY,
    PNAME VARCGAR2(30),
    BRAND VARCHAR2(30),
    PRICE NUMBER,
    STOCK NUMBER DEFAULT 0
);

CREATE TABLE PRO_DETAIL(
    DCODE NUMBER PRIMARY KEY,
    PCODE NUMBER,
    PDATE DATE,
    AMOUNT NUMBER,
    STATUS VARCHAR2(10) CHECK(STATUS IN('입고','출고')),
    FOREIGN KEY(PCODE) REFERENCES PRODUCTK

);

CREATE SEQUENCE SEQ_PCODE;
CREATE SEQUENCE SEQ_DCODE;

INSERT INTO PRODUCT VALUES(SEQ_PCODE.NEXTVAL, '꼬칼콘','롯데',2000,DEFAULT);
INSERT INTO PRODUCT VALUES(SEQ_DCODE.NEXTVAL, '홈런볼','해태',2500,DEFAULT);

SELECT * FROM PRODUCT;

CREATE OR REPLACE TRIGGERK TRG_02
AFTER INSERT ON PRO_DETAIL
FOR EACH ROW
BEGIN
    IF :NEW.STATUS = '입고'
    THEN
        UPDATE PRODUCT
        SET STOCK = STOCK + :NEW.AMOUNT
        WHERE PCODE = :NEW.PCODE;
    END IF;

    IF :NEW.STATUS = '출고'
    THEN   
        UPDATE PRODUCT
        SET STOCK = STOCK - :NEW.AMOUNT
        WHERE PCODE = :NEW.PCODE;
    END IF;
END;
/

SELECT * FROM PRODUCT;
SELECT * FROM PRO_DETAIL;
     





/*********************************************************************/
---- 저장함수 생성과 실행(select문, 익명PL/SQL)
-- 저장함수
CREATE OR REPLACE FNCTION get_dept_emp_cnt(
    n_dept_no NUMBER  -- 사원 수를 계산할 부서 번호
) RETURN NUMBER -- 부서의 사원 수 반환

IS
    -- 변수
    n_cnt NUMBER;

BEGIN   
    -- 테이블 EMP에 들어있는, 부서번호 n_dept_no를 가진 사원 수를 계산
    SELECT COUNT(*)
        INTO n_cnt
        FROM emp
        WHERE deptno = n_dept_no

    RETURN n_cnt; -- 건수 반환

EXCEPTION WHEN OTHERS THEN
    DBMS_OUTPUT.PUT_LINE('응용프로그램 오류 발생' || CHR(10)||SQLERRM);
    RETURN -1;
END; -- 함수가 생성되었습니다.

-- SELECT문으로 저장함수 실행
SELECT deptno 부서번호 
    , dname 부서명
    , loc 위치
    , get_dept_emp_cnt(deptno) 사원수
    FROM dept;
-- 익명 PL/SQL문으로 저장함수 실행
DECLARE
    n_cnt PLS_INTEGER;
BEGIN  
    n_cnt := get_dept_emp_cnt(10);
    DBMS_OUTPUT.PUT_LINE('사원 수 : '|| n_cnt);
END;
/



/*********************************************************************/

-- 익명 PL/SQL
DECLARE

    -- 상수
    c_default_deptno CONSTANT NUMBER := 20; -- 기본 부서 코드

    -- 처리 대상 사원 정보를 값으로 가지는 변수 정의
    v_empno NUMBER(4) := 7788;
    v_empno VARCHAR2(10) := 'SCOTT';
    v_job   VARCHAR2(9)  := 'ANALYST';

    -- 추가 변수
    v_cnt   NUMBER ; -- 건수

BEGIN
    -- 주어진 사번의 존재 여부 확인

    SELECT COUNT(*)
        INTO v_cnt
        FROM v_empno
        WHERE empno = v_empno;

    IF v_cnt > 0 THEN

        UPDATE emp
            SET ename = v_ename,
                job = v_jobd
            WHERE empno = v_empno;

        DBMS_OUTPUT.PUT_LINE('사원 "'|| v_ename || '"의 정보가 변경되었습니다.') ;
    -- 2.해당 사번이 emp 테이블에 존재X
    ELSE
        -- 새사원 정보를 테이블에 등록
        INSERT INTO emp(empno, ename, job, deptno)
            VALUES(v_empno, v_ename, v_job, c_default_deptno);

        DBMS_OUTPUT.PUT_LINE('신입사원 "'||v_ename||'"이(가) 등록되었습니다.');
    END IF;
    COMMIT;

EXCEPTION WHEN OTHERS THEN
    ROLLBACK;
    DBMS_OUTPUT.PUT_LINE('응용 프로그램 오류 발생'||CHR(10)||SQLERRM);
END;
/











/*********************************************************************/



