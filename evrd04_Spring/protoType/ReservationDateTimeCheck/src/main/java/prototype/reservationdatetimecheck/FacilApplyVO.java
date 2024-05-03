package prototype.reservationdatetimecheck;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class FacilApplyVO {
    /** serialVersionUID */
    private static final long serialVersionUID = 6584554644404004193L;

    /** 시설예약신청일련번호 */
    private Integer facilAplySn;

    /** 시설예약일련번호 */
    private Integer facilSn;

    /** 시설코드아이디 */
    private String fcltCdId;

    /** 계정명 */
    private String acntNm;

    /** 시설예약신청일시 */
    private String facilAplyDt;

    /** 시설예약신청시작시각 */
    private String facilAplyBgngTm;

    /** 시설예약종료시각 */
    private String facilAplyEndTm;

    /** 신청방식시각 */
    private String aplyPrgrsTm;

    /** 시설사용옵션일련번호1 */
    private Integer fcltUseOptSn1;

    /** 시설사용옵션일련번호2 */
    private Integer fcltUseOptSn2;

    /** 감면옵션일련번호1 */
    private Integer dscntSn1;

    /** 감면옵션일련번호2 */
    private Integer dscntSn2;

    /** 감면옵션일련번호3 */
    private Integer dscntSn3;

    /** 시설예약사용수 */
    private String facilUseNo;

    /** 시설예약기자재사용여부 */
    private String facilEquipUseYn;

    /** 시설예약기자재명 */
    private String facilEquipNm;

    /** 시설사용료 */
    private String fcltChrg;

    /** 시설예약신청자명 */
    private String facilAplcntNm;

    /** 신청자지역전화번호 */
    private String aplcntRgnTelno;

    /** 신청자국전화번호 */
    private String aplcntTelofcTelno;

    /** 신청자개별전화번호 */
    private String aplcntIndivTelno;

    /** 시설예약신청단체명 */
    private String facilAplyGrpNm;

    /** 시설예약신청인원수 */
    private Integer facilAplyNope;

    /** 시설사용목적 */
    private String fcltUsePrps;

    private String sttsCdNm;

    /** 등록자명 */
    private String rgtrNm;

    /** 수정자명 */
    private String mdfrNm;

    /** 등록일시 */
    private Date regDt;

    /** 갱신일시 */
    private Date updtDt;

    /** 사용자구분코드 */
    private String userSeCd;

    /** 시설사용옵션코드1 */
    private String fcltUseOptCd1;
    /** 시설사용옵션코드2 */
    private String fcltUseOptCd2;
    /** 시설예약 학교명 */
    private String facilSchlNm;

    private String facilAplySns;

    private String fcltNm;

    private String telno;
}
