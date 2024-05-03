package prototype.reservationdatetimecheck;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Service
public class FacilServiceImpl {

    private static Logger logger = LoggerFactory.getLogger(FacilServiceImpl.class);

    @Autowired
    private FacilMapper opFacilDao;


    /**
     * String to Time 파싱
     * @param time
     * @return
     * @throws ParseException
     */
    public Date parseTime(String time) throws ParseException {
        if (time == null || time.isEmpty())
            return null;
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.parse(time);
    }

    /**
     * 최소최대 시간 값 도출
     * @param bgngTm1
     * @param endTm1
     * @param bgngTm2
     * @param endTm2
     * @return
     * @throws ParseException
     */
    public Date[] minMaxTime(FcltMngVO dataVo){

        Date minTime = null;
        Date maxTime = null;

        try {
            Date bgngTm1 = parseTime(dataVo.getFcltStdayBgngTm1()); // 평일 오전 개방 시작시간
            Date endTm1 = parseTime(dataVo.getFcltStdayEndTm1()); 	// 평일 오전 개방 종료시간
            Date bgngTm2 = parseTime(dataVo.getFcltStdayBgngTm2()); // 평일 오후 개방 시작시간
            Date endTm2 = parseTime(dataVo.getFcltStdayEndTm2());	// 평일 오후 개방 종료시간

            Date[] times = new Date[4];
            times[0] = bgngTm1;
            times[1] = endTm1;
            times[2] = bgngTm2;
            times[3] = endTm2;

            for (Date time : times) {
                if (time != null) {
                    if (minTime == null || time.before(minTime))
                        minTime = time;
                    if (maxTime == null || time.after(maxTime))
                        maxTime = time;
                }
            }
        }catch(Exception e) {
            logger.error("minMaxTime() Exception");
            e.printStackTrace();
        }
        return new Date[] {minTime, maxTime};
    }


    /**
     * 개방시간 체크
     * @param rsvtTm1
     * @param rsvtTm2
     * @param bgngTm
     * @param endTm
     * @return
     */
    public String resrvtionChekTime(Date rsvtTm1, Date rsvtTm2, Date bgngTm, Date endTm) {
        try {
            if (bgngTm == null || endTm == null) {
                return "N3"; // 시설 미개방
            }
            // 개방시간 안에서의 예약시간 체크
            if ((rsvtTm1.after(bgngTm) || rsvtTm1.equals(bgngTm)) &&  // 예약시간이 시작시간보다 뒤이거나,같을 경우 : true면 예약가능. 중복X
                    (rsvtTm2.before(endTm) || rsvtTm2.equals(endTm))) {   // 예약시간이 종료시간보다 전이거나,같을 경우 : true면 예약가능. 중복X
                return "Y";
            } else {
                return "N"; // 개방 시간이 아닙니다
            }
        }catch(Exception e){
            logger.error("selectFcltInfo ParseException");
            e.printStackTrace();
            return "N"; // 개방 시간이 아닙니다
        }
    }



    /**
     * enum DayOfWeek를 위한 요일 치환
     * @param dayOfWeek
     * @return
     */
    public int toCustomDayOfWeek(String dayOfWeek) {
        int dayOfWeekNum = Integer.parseInt(dayOfWeek);
        return dayOfWeekNum == 1 ? 7 : dayOfWeekNum - 1;
    }


    /**
     * 기존 장기예약 날짜
     * @param longAplyList
     * @return
     */
    public Map<Integer,Map<LocalDate,LocalTime[]>> oldLongRsvtSelectedDayTimes(List<FacilLongVO> longAplyList) {

        Map<Integer,Map<LocalDate,LocalTime[]>> allReservations = new HashMap<>();

        int index = 0;
        for (FacilLongVO dataVo : longAplyList) {
            Map<LocalDate, LocalTime[]> reservation = new HashMap<>();
            Set<DayOfWeek> dayOfWeekSet = new HashSet<>();
            String[] dayCdId = dataVo.getRsvtDayCdId().split(",");
            LocalDate startDate = LocalDate.parse(dataVo.getRsvtBgngDt().substring(0, 10));
            LocalDate endDate = LocalDate.parse(dataVo.getRsvtEndDt().substring(0, 10));
            LocalTime startTime = LocalTime.parse(dataVo.getRsvtBgngTm());
            LocalTime endTime = LocalTime.parse(dataVo.getRsvtEndTm());

            // 요일 넣기
            for(String dayNum : dayCdId) {
                int dayIdx = toCustomDayOfWeek(dayNum);
                dayOfWeekSet.add(DayOfWeek.of(dayIdx));
            }

            // 기간 안에 체크된 요일 별로 날짜시간 생성
            for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
                if (dayOfWeekSet.contains(date.getDayOfWeek())) {
                    LocalDate currentDay = date;
                    LocalTime[] times = new LocalTime[2];
                    times[0] = startTime;
                    times[1] = endTime;
                    reservation.put(currentDay, times);
                }
            }
            allReservations.put(index, reservation);
            index++;
        }
        return allReservations;
    }

    /**
     * 신규 장기예약 날짜
     * @param paramVo
     * @return
     */
    public Map<LocalDate,LocalTime[]> newLongRsvtSelectedDayTimes(FacilLongVO paramVo) {

        Map<LocalDate, LocalTime[]> reservation = new HashMap<>();
        Set<DayOfWeek> dayOfWeekSet = new HashSet<>();
        String[] dayCdId = paramVo.getRsvtDayCdId().split(",");
        LocalDate startDate = LocalDate.parse(paramVo.getRsvtBgngDt().substring(0, 10));
        LocalDate endDate = LocalDate.parse(paramVo.getRsvtEndDt().substring(0, 10));
        LocalTime startTime = LocalTime.parse(paramVo.getRsvtBgngTm());
        LocalTime endTime = LocalTime.parse(paramVo.getRsvtEndTm());

        // 요일 넣기
        for(String dayNum : dayCdId) {
            int dayIdx = toCustomDayOfWeek(dayNum);
            dayOfWeekSet.add(DayOfWeek.of(dayIdx));
        }

        // 기간 안에 체크된 요일 별로 날짜시간 생성
        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            if (dayOfWeekSet.contains(date.getDayOfWeek())) {
                LocalDate currentDay = date;
                LocalTime[] times = new LocalTime[2];
                times[0] = startTime;
                times[1] = endTime;
                reservation.put(currentDay, times);
            }
        }
        return reservation;
    }

    /**
     * 날짜,시간 비교
     * @param rsvtTm1
     * @param rsvtTm2
     * @param bgngTm
     * @param endTm
     * @param result
     * @return
     */
    public String resrvtionChekDayTime(LocalDateTime rsvtTm1, LocalDateTime rsvtTm2, LocalDateTime bgngTm, LocalDateTime endTm, String result) {
        try {
            // 새 예약이 기존 예약을 전체적으로 포함하는 경우
            if (rsvtTm1.isBefore(bgngTm) && rsvtTm2.isAfter(endTm)) {
                return "N2";
            }
            if (rsvtTm1.isAfter(bgngTm) && rsvtTm1.isBefore(endTm) || rsvtTm1.equals(bgngTm)||
                    rsvtTm2.isAfter(bgngTm) && rsvtTm2.isBefore(endTm) || rsvtTm2.equals(endTm)) {
                return result = "N2";
            }
        }catch(Exception e){
            logger.error("resrvtionChekDayTime() Exception");
            e.printStackTrace();
            return "N2";
        }
        return result;
    }

    /**
     * 기존 장기예약과 신규 장기예약 날짜,시간 비교
     * @param oldReservations
     * @param newReservation
     * @param result
     * @return
     */
    public String checkNewOldLongRsvt(Map<Integer, Map<LocalDate, LocalTime[]>> oldReservations, Map<LocalDate, LocalTime[]> newReservation, String result) {

        // 신규 예약의 모든 날짜와 시간을 순회
        for (Map.Entry<LocalDate, LocalTime[]> newEntry : newReservation.entrySet()) {
            LocalDate newDate = newEntry.getKey();
            LocalDateTime newStart = newDate.atTime(newEntry.getValue()[0]);
            LocalDateTime newEnd = newDate.atTime(newEntry.getValue()[1]);

            // 기존 예약의 모든 항목(예약 번호) 순회
            for (Map.Entry<Integer, Map<LocalDate, LocalTime[]>> oldEntry : oldReservations.entrySet()) {
                Map<LocalDate, LocalTime[]> dateReservations = oldEntry.getValue();

                // 해당 예약 번호에 대한 모든 날짜와 시간을 검사
                for (Map.Entry<LocalDate, LocalTime[]> dateEntry : dateReservations.entrySet()) {
                    LocalDate oldDate = dateEntry.getKey();
                    LocalDateTime oldStart = oldDate.atTime(dateEntry.getValue()[0]);
                    LocalDateTime oldEnd = oldDate.atTime(dateEntry.getValue()[1]);

                    result = resrvtionChekDayTime(newStart, newEnd, oldStart, oldEnd, result);
                }
            }
        }
        return result;
    }



    public String selectFcltInfo(FcltMngVO fcltMngVo) {
        String result = "Y";
        FcltMngVO dataVo = opFacilDao.selectFcltInfo(fcltMngVo); // 시설관리의 상시개방 시간 끌어오기 (facilSn,fcltCdId)

        String[] dayCdId = StringUtil.getString(fcltMngVo.getParam("q_rsvtDayCdId")).split(",");// 예약 요일
        String rsvtBgngTm = StringUtil.getString(fcltMngVo.getParam("q_rsvtBgngTm")); // 예약 시작시간
        String rsvtEndTm = StringUtil.getString(fcltMngVo.getParam("q_rsvtEndTm"));   // 예약 종료시간
        Date rsvtTm1 = null;
        Date rsvtTm2 = null;
        try {
            rsvtTm1 = parseTime(rsvtBgngTm); // 예약시작시간
            rsvtTm2 = parseTime(rsvtEndTm);  // 예약종료시간
        } catch (ParseException e) {
            logger.error("selectFcltInfo() rsvtTm ParseException Error");
            e.printStackTrace();
            return "N";
        }

        // 시설관리(상시개방) 전체 미개방인 경우, 예약가능 불가 리턴
        if(dataVo.getFcltWkdysBgngTm1().equals("") && dataVo.getFcltWkdysEndTm1().equals("") &&
                dataVo.getFcltWkdysBgngTm2().equals("") && dataVo.getFcltWkdysEndTm2().equals("") &&
                dataVo.getFcltStdayBgngTm1().equals("") && dataVo.getFcltStdayEndTm2().equals("") &&
                dataVo.getFcltStdayBgngTm2().equals("") && dataVo.getFcltStdayEndTm2().equals("") &&
                dataVo.getFcltSndayBgngTm1().equals("") && dataVo.getFcltSndayEndTm2().equals("") &&
                dataVo.getFcltSndayBgngTm2().equals("") && dataVo.getFcltSndayEndTm2().equals("")) {
            return result = "N";
        }

        // 1일 2월~6금 7 토
        if (Validate.isNotEmpty(dataVo)) {
            for (String dayCd : dayCdId) {
                Integer dayInt = StringUtil.getInt(dayCd);
                // 평일
                if (dayInt >= 2 && dayInt <= 6) {
                    // 시설관리(상시개방) 평일 미개방인 경우, 예약가능 불가 리턴
                    if(dataVo.getFcltWkdysBgngTm1().equals("") && dataVo.getFcltWkdysEndTm1().equals("") &&
                            dataVo.getFcltWkdysBgngTm2().equals("") && dataVo.getFcltWkdysEndTm2().equals("")){
                        return result = "N";
                    }
                    Date[] minMaxTime = minMaxTime(dataVo);
                    Date bgngTm = minMaxTime[0];
                    Date endTm = minMaxTime[1];
                    return resrvtionChekTime(rsvtTm1, rsvtTm2, bgngTm, endTm);
                }
                // 토요일
                if (dayInt.equals(7)) {
                    // 시설관리(상시개방) 토요일 미개방인 경우, 예약가능 불가 리턴
                    if(dataVo.getFcltStdayBgngTm1().equals("") && dataVo.getFcltStdayEndTm1().equals("") &&
                            dataVo.getFcltStdayBgngTm2().equals("") && dataVo.getFcltStdayEndTm2().equals("")){
                        return result = "N";
                    }
                    Date[] minMaxTime = minMaxTime(dataVo);
                    Date bgngTm = minMaxTime[0];
                    Date endTm = minMaxTime[1];
                    return resrvtionChekTime(rsvtTm1, rsvtTm2, bgngTm, endTm);
                }

                // 일요일
                if (dayInt.equals(1)) {
                    // 시설관리(상시개방) 일요일 미개방인 경우, 예약가능 불가 리턴
                    if(dataVo.getFcltSndayBgngTm1().equals("") && dataVo.getFcltSndayEndTm1().equals("") &&
                            dataVo.getFcltSndayBgngTm2().equals("") && dataVo.getFcltSndayEndTm2().equals("")){
                        return result = "N";
                    }
                    Date[] minMaxTime = minMaxTime(dataVo);
                    Date bgngTm = minMaxTime[0];
                    Date endTm = minMaxTime[1];
                    return resrvtionChekTime(rsvtTm1, rsvtTm2, bgngTm, endTm);
                }
            }
        } else {
            result = "N3";
        }
        return result;
    }


    /**
     * 시설예약신청 내역
     */
    @Override
    public String selectAplyTmList(FacilAplyVO facilAplyVo) {
        String result = "Y";
        List<FacilAplyVO> dataList = opFacilDao.selectAplyTmList(facilAplyVo);

        LocalDate newStartDay = LocalDate.parse(StringUtil.getString(facilAplyVo.getParam("q_rsvtBgngDt")));
        LocalDate newEndDay = LocalDate.parse(StringUtil.getString(facilAplyVo.getParam("q_rsvtEndDt")));
        LocalTime newStartTime = LocalTime.parse(StringUtil.getString(facilAplyVo.getParam("q_rsvtBgngTm")));
        LocalTime newEndTime = LocalTime.parse(StringUtil.getString(facilAplyVo.getParam("q_rsvtEndTm")));
        LocalDateTime newRsvtStart = newStartDay.atTime(newStartTime);
        LocalDateTime newRsvtEnd = newEndDay.atTime(newEndTime);

        if (Validate.isNotEmpty(dataList)) {
            for (FacilAplyVO dataVo : dataList) {
                LocalDate oldDay = LocalDate.parse(dataVo.getFacilAplyDt());
                LocalTime oldStartTime = LocalTime.parse(dataVo.getFacilAplyBgngTm());
                LocalTime oldEndTime = LocalTime.parse(dataVo.getFacilAplyEndTm());
                LocalDateTime oldRsvtStart = oldDay.atTime(oldStartTime);
                LocalDateTime oldRsvtEnd = oldDay.atTime(oldEndTime);

                // localDatetimeParsing() 생성

                result = resrvtionChekDayTime(newRsvtStart, newRsvtEnd, oldRsvtStart, oldRsvtEnd, result);
            }
        } else {
            result = "Y";
        }
        return result;
    }


    /**
     * 장기예약 체크
     */
    @Override
    public String selectLongAplyTmList(FacilLongVO paramVo) {
        String result = "Y";

        List<FacilLongVO> longAplyList = opFacilDao.selectLongAplyTmList(paramVo); // 장기예약신청 내역(intra 신청관리 리스트)
        if (Validate.isNotEmpty(longAplyList)) {
            // 1.기존 장기예약 전체 리스트 값을 요일별로 환산
            Map<Integer,Map<LocalDate,LocalTime[]>> oldLongAplyListEachDay = oldLongRsvtSelectedDayTimes(longAplyList);
            // 2.예약할려는 신규장기예약값을 요일별로 환산
            Map<LocalDate,LocalTime[]> newLongAplyListEachDay = newLongRsvtSelectedDayTimes(paramVo);
            // 3.기존 예약과 신규예약 중복 체크
            result = checkNewOldLongRsvt(oldLongAplyListEachDay, newLongAplyListEachDay, result);

        } else {
            result = "Y";
        }
        return result;
    }



}
