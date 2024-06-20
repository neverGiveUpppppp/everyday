NUMBER



CREATE TABLE EMP (
    EMP_ID NUMBER PRIMARY KEY,
    EMP_NM VARCHAR2(100),
    MNG_ID NUMBER
);

INSERT INTO EMP (EMP_ID, EMP_NM, MNG_ID) VALUES (1, 'John', NULL); -- CEO
INSERT INTO EMP (EMP_ID, EMP_NM, MNG_ID) VALUES (2, 'Sarah', 1);   -- John의 직속 부하
INSERT INTO EMP (EMP_ID, EMP_NM, MNG_ID) VALUES (3, 'Mike', 1);    -- John의 직속 부하
INSERT INTO EMP (EMP_ID, EMP_NM, MNG_ID) VALUES (4, 'Jim', 2);     -- Sarah의 직속 부하
INSERT INTO EMP (EMP_ID, EMP_NM, MNG_ID) VALUES (5, 'Ann', 2);     -- Sarah의 직속 부하

SELECT EMP_ID, EMP_NM, MNG_ID
FROM EMP
START WITH MNG_ID IS NULL -- 최상위 직원 (CEO)부터 시작
CONNECT BY PRIOR EMP_ID = MNG_ID; -- 상위 직원의 EMP_ID가 현재 직원의 MNG_ID와 일치하는 구조




--------------------------------------------------------


SELECT *
FROM TAB1
START WITH C2 IS NULL
CONNECT BY PRIOR C1 = C2
ORDER BY SIBLINGS BY C3 DESC;



SELECT 사원번호, 사원명, 입사일자, 매니저사원번호
FROM 사원
START WITH 매니저사원번호 IS NULL
CONNECT BY PRIOR 사원번호 = 매니저사원번호
    AND 입사일자 BETWEEN '2013-01-01' AND '2013-12-31'
ORDER SIBLINGS BY 사원번호;


SELECT A.부서코드, A.부서명, A.상위부서코드, B.매출액, A.LVL
FROM(
    SELECT 부서코드, 부서명, 상위부서코드, LEVEL AS LVL
    FROM 부서명
    START WITH 부서코드 = '120'
    CONNECT BY PRIOR 상위부서코드 = 부서코드

    UNION
    
    SELECT 부서코드, 부서명, 상위부서코드, LEVEL AS LVL
    FROM 부서
    START WITH 부서코드 = '120'
    CONNECT BY PRIOR 부서코드 = 상위부서코드) AS A
LEFT JOIN 매출 B
    ON A.부서코드 = B.부서코드;






