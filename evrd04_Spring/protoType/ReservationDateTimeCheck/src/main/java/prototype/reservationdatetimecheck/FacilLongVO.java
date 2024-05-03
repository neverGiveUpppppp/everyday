package prototype.reservationdatetimecheck;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class FacilLongVO {

    /** serialVersionUID */
    private static final long serialVersionUID = 6584554644404104193L;

    /** 장기예약일련번호 */
    private Integer longRsvtSn;

    /** 시설예약일련번호 */
    private Integer facilSn;

    /** 시설코드아이디 */
    private String fcltCdId;

    /** 예약시작일시 */
    private String rsvtBgngDt;

    /** 예약종료일시 */
    private String rsvtEndDt;

    /** 예약요일코드아이디 */
    private String rsvtDayCdId;

    /** 예약시작시각  */
    private String rsvtBgngTm;

    /** 예약종료시각 */
    private String rsvtEndTm;

    /** 예약수수료 */
    private String rsvtChrg;

    /** 등록자명  */
    private String rgtrNm;

    /** 예약그룹명  */
    private String rsvtGroupNm;

    /** 휴대지역전화번호 */
    private String mblRgnTelno;

    /** 휴대국전화번호 */
    private String mblTelofcTelno;

    /** 휴대개별전화번호  */
    private String mblIndivTelno;

    /** 시설사용인원수 */
    private Integer fcltUseNope;

    /** 시설사용목적 */
    private String fcltUsePrps;

    /** 등록자아이디  */
    private String rgtrId;

    /** 수정자아이디 */
    private String mdfrId;

    /** 등록일시 */
    private Date regDt;

    /** 갱신일시 */
    private Date updtDt;

    /** 사용자구분코드 */
    private String userSeCd;

    /** 상태코드명칭 */
    private String sttsCdNm;







}