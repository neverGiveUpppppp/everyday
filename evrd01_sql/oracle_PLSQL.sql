











/*********************************************************************/
DECLARE
    -- 상수
    c_default_deptno CONSTANT NUMBER := 20; -- 기본 부서 코드

    -- 처리 대상 사원 정보를 값으로 가지는 변수 정의
    v_empno NUMBER(4) := 7788;
    v_ename VARCHAR2(10) := 'SCOTT';
    v_job   VARCHAR2(9)  := 'ANALYST';

    -- 추가 변수
    v_cnt   NUMBER ;  -- 건수

BEGIN
    -- 주어진 사번의 존재 여부 확인
    --      v_cnt > 0 : 존재
    --            = 0 : 없음
    SELECT COUNT(*) 
        INTO v_cnt
        FROM v_empno
        WHERE empno = v_empno;
    
    -- 1.해당 사번이 emp 테이블에 존재O
    IF v_cnt > 0 THEN
        -- 1.1(사원명, 업무)를 (v_ename, v_job)으로 변경
        UPDATE emp
            SET ename = v_ename,
                job = v_job
            WHERE empno = v_empno;

        DBMS_OUTPUT.PUT_LINE('사원 "'|| v_ename || '"의 정보가 변경되었습니다.');
    -- 2.해당 사번이 emp 테이블에 존재X
    ELSE
        -- 새사원 정보를 테이블에 등록
        INSERT INTO emp(empno, ename, job, deptno)
        VALUES(v_empno, v_ename, v_job, c_default_deptno);

        DBMS_OUTPUT.PUT_LINE('신입사원 "'||v_ename||'"이(가) 등록되었습니다.');
    END IF;
    COMMIT;

EXCEPTION WHEN OTHERSK THEN
    ROLLBACK;
    DBMS_OUTPUT.PUT_LINE('응용 프로그램 오류 발생'||CHR(10)||SQLERRM);
END;
/



/*********************************************************************/

-- 저장함수
CREATE OR REPLACE FUNCTION get_dept_employee_cnt(
    a_dept_no NUMBER -- 사원 수를 계산할 부서번호
)  RETURN NUMBER -- 부서의 사원수를 반환

IS
    -- 변수
    v_cnt NUMBER; -- 건수

BEGIN
    --테이블 EMP에 들어있는 부서번호 a_deptno를 가진 사원수 계산
    SELECT COUNT(*)
        INTO v_cnt
        FROM emp
        WHERE dept_no = a_deptno;

    RETURN v_cnt;

EXCEPTION WHEN OTHERS THEN
    DBMS_OUTPUT_LINE('응용 프로그램 오류' || CHR(10)||SQLERRM);
    RETURN -1;
END; -- 함수가 생성되었습니다.

-- SELECT문을 사용한 저장 함수의 실행
SELECT deptno 부서번호
    , dname 부서명 
    , loc 위치
    , get_detp_employee_cnt(deptno) 사원수
FROM dept;

-- 익명 PL/SQL문을 사용한 저장 함수의 실행
DECLARE
    v_cnt PLS_INTEGER;
BEGIN   
    v_cnt := get_dept_employee_cnt(10);
    DBMS_OUTPUT.PUT_LINE('사원 수 : '||v_cnt);
END;
/


/*********************************************************************/









