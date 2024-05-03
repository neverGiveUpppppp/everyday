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
     * 최소최대 시간 값 도출
     * @param bgngTm1
     * @param endTm1
     * @param bgngTm2
     * @param endTm2
     * @return
     * @throws ParseException
     */
    public LocalTime[] minMaxTime(String moning1, String moning2, String afternon1, String afternon2){

        LocalTime minTime = null;
        LocalTime maxTime = null;

        String[] timeArr = new String[] {moning1, moning2, afternon1, afternon2};

        try {
            for (String str : timeArr) {
                if (str != null && !str.isEmpty()) {
                    LocalTime time = LocalTime.parse(str);
                    if (minTime == null || time.isBefore(minTime)) {
                        minTime = time;
                    }
                    if (maxTime == null || time.isAfter(maxTime)) {
                        maxTime = time;
                    }
                }
            }
        }catch(Exception e) {
            logger.error("minMaxTime() Exception");
            e.printStackTrace();
        }
        return new LocalTime[] {minTime, maxTime};
    }


    /**
     * 개방시간 체크
     * @param rsvtTm1
     * @param rsvtTm2
     * @param bgngTm
     * @param endTm
     * @return
     */
    public String resrvtionChekTime(LocalTime rsvtTm1, LocalTime rsvtTm2, LocalTime bgngTm, LocalTime endTm) {
        try {
            // 시설 미개방 체크
            if (bgngTm == null || endTm == null) {
                return "N3"; // 시설 미개방
            }
            // 개방시간 안에서의 예약시간 체크
            if ((rsvtTm1.isAfter(bgngTm) || rsvtTm1.equals(bgngTm)) &&  // 예약시간이 시작시간보다 뒤이거나,같을 경우 : true면 예약가능. 중복X
                    (rsvtTm2.isBefore(endTm) || rsvtTm2.equals(endTm))) {   // 예약시간이 종료시간보다 전이거나,같을 경우 : true면 예약가능. 중복X
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
     * 요일 처리
     * @param dayCdId
     * @return
     */
    public Set<DayOfWeek> dayOfWeekSet(String[] dayCdId) {
        Set<DayOfWeek> dayOfWeekSet = new HashSet<>();
        // 요일 넣기
        for(String dayNum : dayCdId) {
            // enum DayOfWeek를 위한 요일 치환
            int dayIdx = Integer.parseInt(dayNum);
            dayIdx = dayIdx == 1 ? 7 : dayIdx - 1;
            dayOfWeekSet.add(DayOfWeek.of(dayIdx));
        }
        return dayOfWeekSet;
    }

    /**
     * 기간 안에 체크된 요일 별, 날짜시간 생성
     * @param startDate
     * @param endDate
     * @param dayOfWeekSets
     * @param startTime
     * @param endTime
     * @param reservation
     * @return
     */
    public Map<LocalDate, LocalTime[]> chkedDayTimes(LocalDate startDate, LocalDate endDate, Set<DayOfWeek> dayOfWeekSets, LocalTime startTime, LocalTime endTime, Map<LocalDate, LocalTime[]> reservation) {
        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            if (dayOfWeekSets.contains(date.getDayOfWeek())) {
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
     * 기존 장기예약 날짜
     * @param longAplyList
     * @return
     */
    public Map<Integer,Map<LocalDate,LocalTime[]>> oldLongRsvtSelectedDayTimes(List<FacilLongVO> longAplyList) {

        Map<Integer,Map<LocalDate,LocalTime[]>> allReservations = new HashMap<>();

        int index = 0;
        for (FacilLongVO dataVo : longAplyList) {
            Map<LocalDate, LocalTime[]> reservation = new HashMap<>();
            String[] dayCdId = dataVo.getRsvtDayCdId().split(",");
            LocalDate startDate = LocalDate.parse(dataVo.getRsvtBgngDt().substring(0, 10));
            LocalDate endDate = LocalDate.parse(dataVo.getRsvtEndDt().substring(0, 10));
            LocalTime startTime = LocalTime.parse(dataVo.getRsvtBgngTm());
            LocalTime endTime = LocalTime.parse(dataVo.getRsvtEndTm());

            // 요일 데이터 처리
            Set<DayOfWeek> dayOfWeekSets = dayOfWeekSet(dayCdId);

            // 기간 안에 체크된 요일 별, 날짜시간 생성
            reservation = chkedDayTimes(startDate,endDate,dayOfWeekSets,startTime,endTime,reservation);

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
    @Override
    public Map<LocalDate,LocalTime[]> newLongRsvtSelectedDayTimes(FacilLongVO paramVo) {

        Map<LocalDate, LocalTime[]> reservation = new HashMap<>();
        String[] dayCdId = paramVo.getRsvtDayCdId().split(",");
        LocalDate startDate = LocalDate.parse(paramVo.getRsvtBgngDt().substring(0, 10));
        LocalDate endDate = LocalDate.parse(paramVo.getRsvtEndDt().substring(0, 10));
        LocalTime startTime = LocalTime.parse(paramVo.getRsvtBgngTm());
        LocalTime endTime = LocalTime.parse(paramVo.getRsvtEndTm());

        // 요일 데이터 처리
        Set<DayOfWeek> dayOfWeekSets = dayOfWeekSet(dayCdId);

        // 기간 안에 체크된 요일 별, 날짜시간 생성
        reservation = chkedDayTimes(startDate,endDate,dayOfWeekSets,startTime,endTime,reservation);

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



    @Override
    public String selectFcltInfo(FcltMngVO fcltMngVo) {
        String result = "Y";
        FcltMngVO dataVo = opFacilDao.selectFcltInfo(fcltMngVo); // 시설관리의 상시개방 시간 끌어오기 (facilSn,fcltCdId)

        String[] dayCdId = StringUtil.getString(fcltMngVo.getParam("q_rsvtDayCdId")).split(",");// 예약 요일
        String rsvtBgngTm = StringUtil.getString(fcltMngVo.getParam("q_rsvtBgngTm")); // 예약 시작시간
        String rsvtEndTm = StringUtil.getString(fcltMngVo.getParam("q_rsvtEndTm"));   // 예약 종료시간
        LocalTime rsvtTm1 = LocalTime.parse(rsvtBgngTm);
        LocalTime rsvtTm2 = LocalTime.parse(rsvtEndTm);

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
                    String moning1 = dataVo.getFcltWkdysBgngTm1();
                    String moning2 = dataVo.getFcltWkdysEndTm1();
                    String afternon1 = dataVo.getFcltWkdysBgngTm2();
                    String afternon2 = dataVo.getFcltWkdysEndTm2();

                    // 시설관리(상시개방) 평일 미개방인 경우, 예약가능 불가 리턴
                    if(moning1.equals("") && moning2.equals("") && afternon1.equals("") && afternon2.equals("")){
                        return result = "N";
                    }
                    LocalTime[] minMaxTime = minMaxTime(moning1,moning2,afternon1,afternon2);
                    result =  resrvtionChekTime(rsvtTm1, rsvtTm2, minMaxTime[0], minMaxTime[1]);
                }
                // 토요일
                if (dayInt.equals(7)) {
                    String moning1 = dataVo.getFcltStdayBgngTm1();
                    String moning2 = dataVo.getFcltStdayEndTm1();
                    String afternon1 = dataVo.getFcltStdayBgngTm2();
                    String afternon2 = dataVo.getFcltStdayEndTm2();

                    // 시설관리(상시개방) 토요일 미개방인 경우, 예약가능 불가 리턴
                    if(moning1.equals("") && moning2.equals("") && afternon1.equals("") && afternon2.equals("")){
                        return result = "N";
                    }
                    LocalTime[] minMaxTime = minMaxTime(moning1,moning2,afternon1,afternon2);
                    result =  resrvtionChekTime(rsvtTm1, rsvtTm2, minMaxTime[0], minMaxTime[1]);
                }

                // 일요일
                if (dayInt.equals(1)) {
                    String moning1 = dataVo.getFcltSndayBgngTm1();
                    String moning2 = dataVo.getFcltSndayEndTm1();
                    String afternon1 = dataVo.getFcltSndayBgngTm2();
                    String afternon2 = dataVo.getFcltSndayEndTm2();

                    // 시설관리(상시개방) 일요일 미개방인 경우, 예약가능 불가 리턴
                    if(moning1.equals("") && moning2.equals("") && afternon1.equals("") && afternon2.equals("")){
                        return result = "N";
                    }
                    LocalTime[] minMaxTime = minMaxTime(moning1,moning2,afternon1,afternon2);
                    result = resrvtionChekTime(rsvtTm1, rsvtTm2, minMaxTime[0], minMaxTime[1]);
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

                result = resrvtionChekDayTime(newRsvtStart, newRsvtEnd, oldRsvtStart, oldRsvtEnd, result);
            }
        } else {
            result = "Y";
        }
        return result;
    }


    /**
     * 특정일 개방 체크
     */
    public String selectSpecificOpenAplyTmList(FacilOpenMngVO facilOpenMngVo, Map<LocalDate, LocalTime[]> newReservation) {
        String result = "Y";

        List<FacilOpenMngVO> openAplyList = opFacilDao.selectSpecificOpenAplyTmList(facilOpenMngVo); // 특정일 개방 데이터 select

        if (Validate.isNotEmpty(openAplyList)) {
            // 1.기존 특정일개방 전체 리스트 값을 요일별로 환산
            Map<Integer,Map<LocalDate,LocalTime[]>> allReservations = new HashMap<>();

            int index = 0;
            for (FacilOpenMngVO dataVo : openAplyList) {
                // 요일로 치환
                ArrayList<String> dayArrlist = new ArrayList<>();
                if(!dataVo.getFcltWkdysBgngTm1().isEmpty() || !dataVo.getFcltWkdysEndTm1().isEmpty() || !dataVo.getFcltWkdysBgngTm2().isEmpty() || !dataVo.getFcltWkdysEndTm2().isEmpty()) {
                    // 평일
                    dayArrlist.add("2"); dayArrlist.add("3"); dayArrlist.add("4"); dayArrlist.add("5"); dayArrlist.add("6");
                }
                if(!dataVo.getFcltStdayBgngTm1().isEmpty() || !dataVo.getFcltStdayEndTm1().isEmpty() || !dataVo.getFcltStdayBgngTm2().isEmpty() || !dataVo.getFcltStdayEndTm2().isEmpty()) {
                    // 토요일
                    dayArrlist.add("7");
                }
                if(!dataVo.getFcltSndayBgngTm1().isEmpty() || !dataVo.getFcltSndayEndTm1().isEmpty() || !dataVo.getFcltSndayBgngTm2().isEmpty() || !dataVo.getFcltSndayEndTm2().isEmpty()){
                    // 일요일
                    dayArrlist.add("1");
                }

                Map<LocalDate, LocalTime[]> reservations = new HashMap<>();
                String[] dayCdId = dayArrlist.toArray(new String[dayArrlist.size()]); // 요일 코드

                // 요일 데이터 처리
                Set<DayOfWeek> dayOfWeekSets = dayOfWeekSet(dayCdId);

                LocalDate startDate = LocalDate.parse(dataVo.getFcltBgngDt().substring(0, 10));
                LocalDate endDate = LocalDate.parse(dataVo.getFcltEndDt().substring(0, 10));
                LocalDate currentDay = null;
                String moning1 = null;
                String moning2 = null;
                String afternon1 = null;
                String afternon2 = null;
                for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
                    currentDay = date;
                    if (dayOfWeekSets.contains(date.getDayOfWeek()) && date.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
                        moning1 = dataVo.getFcltStdayBgngTm1();
                        moning2 = dataVo.getFcltStdayEndTm1();
                        afternon1 = dataVo.getFcltStdayBgngTm2();
                        afternon2 = dataVo.getFcltStdayEndTm2();

                        LocalTime[] times = minMaxTime(moning1,moning2,afternon1,afternon2);
                        reservations.put(currentDay, times);
                    }
                    else if (dayOfWeekSets.contains(date.getDayOfWeek()) && date.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
                        moning1 = dataVo.getFcltSndayBgngTm1();
                        moning2 = dataVo.getFcltSndayEndTm1();
                        afternon1 = dataVo.getFcltSndayBgngTm2();
                        afternon2 = dataVo.getFcltSndayEndTm2();

                        LocalTime[] times = minMaxTime(moning1,moning2,afternon1,afternon2);
                        reservations.put(currentDay, times);
                    }else {
                        moning1 = dataVo.getFcltWkdysBgngTm1();
                        moning2 = dataVo.getFcltWkdysEndTm1();
                        afternon1 = dataVo.getFcltWkdysBgngTm2();
                        afternon2 = dataVo.getFcltWkdysEndTm2();

                        LocalTime[] times = minMaxTime(moning1,moning2,afternon1,afternon2);
                        reservations.put(currentDay, times);
                    }
                }

                allReservations.put(index, reservations);
                index++;
            }

            // 2.기존 예약과 신규예약 중복 체크
            result = checkNewOldLongRsvt(allReservations, newReservation, result);

        } else {
            result = "Y";
        }
        return result;
    }


    /**
     * 장기예약 체크
     */
    @Override
    public String selectLongAplyTmList(FacilLongVO paramVo, Map<LocalDate, LocalTime[]> newReservation) {
        String result = "Y";

        List<FacilLongVO> longAplyList = opFacilDao.selectLongAplyTmList(paramVo); // 장기예약신청 내역(intra 신청관리 리스트)
        if (Validate.isNotEmpty(longAplyList)) {
            // 1.기존 장기예약 전체 리스트 값을 요일별로 환산
            Map<Integer,Map<LocalDate,LocalTime[]>> oldLongAplyListEachDay = oldLongRsvtSelectedDayTimes(longAplyList);
            // 2.기존 예약과 신규예약 중복 체크
            result = checkNewOldLongRsvt(oldLongAplyListEachDay, newReservation, result);

        } else {
            result = "Y";
        }
        return result;
    }



}
