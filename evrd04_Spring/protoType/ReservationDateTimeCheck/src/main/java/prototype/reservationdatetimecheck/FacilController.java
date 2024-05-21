package prototype.reservationdatetimecheck;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FacilController {



    /** 예약가능시간 확인 기능
     *
     * @param request
     * @param model
     * @param facilLongVo
     * @return
     */
    @OpenworksAuth(authType = AuthType.READ)
    @RequestMapping(value = "ND_selectCheckTime.do")
    public String selectCheckTime(HttpServletRequest request, Model model, FacilLongVO facilLongVo) {

        String result = "N";
        ValidateResultHolder holder = ValidateUtil.validate(facilLongVo);
        if (holder.isValid()) {
            try {
                // 이미 신청 내역이 있고 같은 일시일 경우, 예약가능 반환
                if(facilLongVo.getLongRsvtSn() != null) {
                    FacilLongVO duplChek = opFacilService.selectFacilLongAply(facilLongVo);
                    if(duplChek.getRsvtBgngDt() != null || facilLongVo.getRsvtBgngDt() != "" || duplChek.getRsvtBgngTm() != null || facilLongVo.getRsvtBgngTm() != "") {
                        if( duplChek.getRsvtBgngDt().substring(0, 10).equals(facilLongVo.getRsvtBgngDt().substring(0, 10)) && duplChek.getRsvtEndDt().substring(0, 10).equals(facilLongVo.getRsvtEndDt().substring(0, 10)) &&
                                duplChek.getRsvtBgngTm().equals(facilLongVo.getRsvtBgngTm()) && duplChek.getRsvtEndTm().equals(facilLongVo.getRsvtEndTm())){
                            result = "Y";
                            return responseJson(model,result);
                        }
                    }
                }

                // 개방시간 안에 예약시간이 있는 지 체크
                Integer facilSn = StringUtil.getInt(facilLongVo.getParam("q_facilSn"));
                FcltMngVO fcltMngVo = new FcltMngVO();
                fcltMngVo.setFacilSn(facilSn);
                fcltMngVo.setFcltCdId(facilLongVo.getFcltCdId());
                fcltMngVo.addParam("q_rsvtBgngTm",facilLongVo.getRsvtBgngTm()); // fcltMngVo가 아닌 parent PageVO의 Map에 저장한 것
                fcltMngVo.addParam("q_rsvtEndTm",facilLongVo.getRsvtEndTm());
                fcltMngVo.addParam("q_rsvtDayCdId",facilLongVo.getRsvtDayCdId());
                String resultOpnTm = opFacilService.selectFcltInfo(fcltMngVo); // 시설관리의 상시개방 시간 끌어오기

                // 개방시간 안에 다른 예약과 겹치는지 중복 체크
                if(resultOpnTm.equals("Y")) {
                    FacilAplyVO facilAplyVo = new FacilAplyVO();
                    facilAplyVo.setFacilSn(facilSn);
                    facilAplyVo.setFcltCdId(facilLongVo.getFcltCdId());
                    facilAplyVo.addParam("q_rsvtBgngDt",facilLongVo.getRsvtBgngDt());
                    facilAplyVo.addParam("q_rsvtEndDt",facilLongVo.getRsvtEndDt());
                    facilAplyVo.addParam("q_rsvtBgngTm",facilLongVo.getRsvtBgngTm());
                    facilAplyVo.addParam("q_rsvtEndTm",facilLongVo.getRsvtEndTm());
                    facilAplyVo.addParam("q_rsvtDayCdId",facilLongVo.getRsvtDayCdId());
                    resultOpnTm = opFacilService.selectAplyTmList(facilAplyVo);     // 시설예약신청 내역
                }

                Map<LocalDate, LocalTime[]> newReservation = opFacilService.newLongRsvtSelectedDayTimes(facilLongVo); // 비교를 위한  신규장기예약 데이터
                if(newReservation.isEmpty()) {  // 사용자의 요일 체크가 잘못 되었을 경우
                    result = "N4"; // 요일 잘못 선택
                    return responseJson(model,result);
                }

                // 특정일 개방 일시 체크
                FacilOpenMngVO facilOpenMngVo = new FacilOpenMngVO();
                facilOpenMngVo.setFacilSn(facilSn);
                facilOpenMngVo.addParam("q_rsvtBgngDt",facilLongVo.getRsvtBgngDt());
                facilOpenMngVo.addParam("q_rsvtEndDt",facilLongVo.getRsvtEndDt());
                String resultSpOpnTm = opFacilService.selectSpecificOpenAplyTmList(facilOpenMngVo, newReservation);

                // 상시개방 or 특정일개방 둘 중 하나 Y라면, 중복체크 계속
                if(resultOpnTm.equals("N") && resultSpOpnTm.equals("N")){
                    result = "N";
                    return responseJson(model, result);
                }else if(resultOpnTm.equals("Y") || resultSpOpnTm.equals("Y")){
                    result = "Y";
                }

                // 기존 장기예약과  중복 체크
                facilLongVo.setFacilSn(facilSn);
                result = opFacilService.selectLongAplyTmList(facilLongVo, newReservation); // 장기예약신청 내역(intra 신청관리 리스트)

            }catch(Exception e){
                e.printStackTrace();
                result = "N2";
                return responseJson(model,result);
            }
        } else {
            return responseJson(model, Boolean.FALSE, MessageUtil.getMessage("common.validateFail"));
        }
        return responseJson(model,result);
    }




}
