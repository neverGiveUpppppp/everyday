


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






