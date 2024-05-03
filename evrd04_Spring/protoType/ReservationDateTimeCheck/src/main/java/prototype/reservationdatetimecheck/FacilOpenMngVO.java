package prototype.reservationdatetimecheck;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class FacilOpenMngVO {
    private static final long serialVersionUID = 6657551667603973547L;


    /** 개방관리일련번호  */
    private Integer openMngSn;

    /** 시설예약일련번호  */
    private Integer facilSn;

    /** 시설시작일시 */
    private String fcltBgngDt;

    /** 시설종료일시 */
    private String fcltEndDt;

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

    /** 시설개방사유 */
    private String fcltOpenRsn;

    /** 시설개방코드아이디*/
    private String fcltOpenCdId;

    private String fcltUnopenYn;

    /** 등록자아이디 */
    private String rgtrId;

    /** 수정자아이디 */
    private String mdfrId;

    /** 등록일시 */
    private Date regDt;

    /** 수정일시 */
    private Date updtDt;
}
