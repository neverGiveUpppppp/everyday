package prototype.reservationdatetimecheck;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class FcltMngVO {

        private static final long serialVersionUID = 6657551667623973547L;

        /** 시설관리일련번호  */
        private Integer fcltMngSn;

        /** 시설예약일련번호  */
        private Integer facilSn;

        /** 시설코드아이디  */
        private String fcltCdId;

        /** 시설면적  */
        private String fcltArea;

        /** 시설인원수 */
        private String fcltNope;

        /** 파일일련번호  */
        private Integer fileSn;

        /** 시설평일시작시각1  */
        private String fcltWkdysBgngTm1;

        /** 시설평일종료시각1  */
        private String fcltWkdysEndTm1;

        /** 시설평일시작시각2  */
        private String fcltWkdysBgngTm2;

        /** 시설평일종료시각2  */
        private String fcltWkdysEndTm2;

        /** 시설토요일시작시각1 */
        private String fcltStdayBgngTm1;

        /** 시설토요일종료시각1 */
        private String fcltStdayEndTm1;

        /** 시설토요일시작시각2 */
        private String fcltStdayBgngTm2;

        /** 시설토요일종료시각2 */
        private String fcltStdayEndTm2;

        /** 시설일요일시작시각1 */
        private String fcltSndayBgngTm1;

        /** 시설일요일종료시각1 */
        private String fcltSndayEndTm1;

        /** 시설일요일시작시각2 */
        private String fcltSndayBgngTm2;

        /** 시설일요일종료시각2 */
        private String fcltSndayEndTm2;

        /** 시설사용옵션코드1 */
        private String fcltUseOptCd1;

        /** 시설사용옵션1 */
        private String fcltUseOpt1;

        /** 시설수수료1 */
        private String fcltFee1;

        /** 시설가산금액1 */
        private String fcltAdtnAmt1;

        /** 시설사용옵션코드2 */
        private String fcltUseOptCd2;

        /** 시설사용옵션2 */
        private String fcltUseOpt2;

        /** 시설수수료2 */
        private String fcltFee2;

        /** 시설가산금액2  */
        private String fcltAdtnAmt2;



        /** 시설특이사항 */
        private String fcltExcptnMttr;

        /** 시설요주의사항 */
        private String fcltAtntMttr;

        /** 등록자아이디 */
        private String rgtrId;

        /** 수정자아이디 */
        private String mdfrId;

        /** 등록일시 */
        private Date regDt;

        /** 갱신일시 */
        private Date updtDt;

        /** 파일 리스트 */
//        private List<FileVO> fileList;

        /** 파일 ID 목록 */
        private String[] fileIds;

    }
