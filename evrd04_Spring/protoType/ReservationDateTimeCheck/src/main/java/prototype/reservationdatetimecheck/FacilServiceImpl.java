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
     *
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

    public String resrvtionChek(Date rsvtTm1, Date rsvtTm2, Date bgngTm, Date endTm) {
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





    @Override
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

//				try {
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
                    return resrvtionChek(rsvtTm1, rsvtTm2, bgngTm, endTm);
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
                    return resrvtionChek(rsvtTm1, rsvtTm2, bgngTm, endTm);
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
                    return resrvtionChek(rsvtTm1, rsvtTm2, bgngTm, endTm);
                }
            }

        } else {
            result = "N3";
        }
        return result;
    }



    public String resrvtionChekCompareInside(Date rsvtTm1, Date rsvtTm2, Date bgngTm, Date endTm) {
        String result = "N";
        try {
            if (rsvtTm1.after(bgngTm) && rsvtTm1.before(endTm) || rsvtTm1.equals(bgngTm)) {
                return result = "N2";
            } else if (result.equals("Y")) {
                if (rsvtTm2.after(bgngTm) && rsvtTm2.before(endTm) || rsvtTm2.equals(endTm)) {  // 예약종료시간이 기존예약종료시간과 같을 경우는 안되는게 맞음
                    return result = "N2";
                }
            }
        }catch(Exception e){
            logger.error("selectFcltInfo ParseException");
            e.printStackTrace();
            return "N"; // 개방 시간이 아닙니다
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

        String[] dayCdId = StringUtil.getString(facilAplyVo.getParam("q_rsvtDayCdId")).split(","); // 요일
        String rsvtBgngDt = StringUtil.getString(facilAplyVo.getParam("q_rsvtBgngDt"));
        String rsvtEndDt = StringUtil.getString(facilAplyVo.getParam("q_rsvtEndDt"));
        String rsvtBgngTm = StringUtil.getString(facilAplyVo.getParam("q_rsvtBgngTm"));
        String rsvtEndTm = StringUtil.getString(facilAplyVo.getParam("q_rsvtEndTm"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        if (Validate.isNotEmpty(dataList)) {
            for (FacilAplyVO dataVo : dataList) {
                try {
                    Date bgngTm = sdf.parse(dataVo.getFacilAplyDt() + " " + dataVo.getFacilAplyBgngTm()); // 기존 예약 시작 일시
                    Date endTm = sdf.parse(dataVo.getFacilAplyDt() + " " + dataVo.getFacilAplyEndTm());   // 기존 예약 종료 일시

                    Date rsvtTm1 = sdf.parse(rsvtBgngDt + " " + rsvtBgngTm); // 신규 예약 시작 일시
                    Date rsvtTm2 = sdf.parse(rsvtEndDt + " " + rsvtEndTm);   // 신규 예약 종료 일시

                    result = resrvtionChekCompareInside(rsvtTm1, rsvtTm2, bgngTm, endTm);

//					if (rsvtTm1.after(bgngTm) && rsvtTm1.before(endTm) || rsvtTm1.equals(bgngTm)) {
//						return result = "N2";
//					} else if (result.equals("Y")) {
//						if (rsvtTm2.after(bgngTm) && rsvtTm2.before(endTm) || rsvtTm2.equals(endTm)) {  // 예약종료시간이 기존예약종료시간과 같을 경우는 안되는게 맞음
//							return result = "N2";
//						}
//					}

//					if (rsvtTm1.after(bgngTm1) && rsvtTm1.before(endTm1) || rsvtTm1.equals(bgngTm1)
//							|| rsvtTm1.equals(endTm1)) { // 예약시작시간이 기존예약종료시간과 같을 경우는 통과되는게 맞는 듯
//						return result = "N2";
//					} else if (result.equals("Y")) {
//						if (rsvtTm2.after(bgngTm1) && rsvtTm2.before(endTm1) || rsvtTm2.equals(bgngTm1) // 예약종료시간이 기존예약시작시간과 같을 경우는 통과되는게 맞는 듯
//								|| rsvtTm2.equals(endTm1)) {  // 예약종료시간이 기존예약종료시간과 같을 경우는 안되는게 맞음
//							return result = "N2";
//						}
//					}

//					// 개방시간 안에서의 예약시간 체크
//			        if ((rsvtTm1.after(bgngTm1) || rsvtTm1.equals(bgngTm1)) &&  // 예약시간이 시작시간보다 뒤이거나,같을 경우 : true면 예약가능. 중복X
//			        	(rsvtTm2.before(endTm1) || rsvtTm2.equals(endTm1))) {   // 예약시간이 종료시간보다 전이거나,같을 경우 : true면 예약가능. 중복X
//			            return "Y";
//			        } else {
//			            return "N"; // 개방 시간이 아닙니다
//			        }
                } catch (ParseException e) {
                    logger.error("selectFcltInfo ParseException");
                    e.printStackTrace();
                    return "N";
                }
            }
        } else {
            result = "Y";
        }

        return result;
    }



    public int toCustomDayOfWeek(String dayOfWeek) {
        int dayOfWeekNum = Integer.parseInt(dayOfWeek);
        return dayOfWeekNum == 1 ? 7 : dayOfWeekNum - 1;
    }



    public Map<Integer, Map<LocalDate, LocalTime[]>> oldLongRsvtSelectedDayTimes(List<FacilLongVO> longAplyList) {

        Map<Integer,Map<LocalDate,LocalTime[]>> allReservations = new HashMap<>();

        // 1.리스트 안에 있는 기존 장기예약 데이터 리스트 하나마다 날짜데이터 추출하기 for 요일 환산
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

    public Map<LocalDate,LocalTime[]> newLongRsvtSelectedDayTimes(FacilLongVO paramVo) {
//		Map<Integer,Map<LocalDate,LocalTime[]>> allReservations = new HashMap<>();

        // 1.리스트 안에 있는 기존 장기예약 데이터 리스트 하나마다 날짜데이터 추출하기 for 요일 환산
//		int index = 0;
//		for (FacilLongVO dataVo : paramVo) {
//            Map<LocalDate, LocalTime[]> reservation = new HashMap<>();
//            Set<DayOfWeek> dayOfWeekSet = new HashSet<>();
//            String[] dayCdId = dataVo.getRsvtDayCdId().split(",");
//            LocalDate startDate = LocalDate.parse(dataVo.getRsvtBgngDt().substring(0, 10));
//            LocalDate endDate = LocalDate.parse(dataVo.getRsvtEndDt().substring(0, 10));
//            LocalTime startTime = LocalTime.parse(dataVo.getRsvtBgngTm());
//            LocalTime endTime = LocalTime.parse(dataVo.getRsvtEndTm());

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
//            allReservations.put(index, reservation);
//            index++;
//		}
//	    return allReservations;
        return reservation;
    }

//	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); // 마지막에 날짜 비교 다하고 LocalDateTime에서 한번 더 파싱할 떄 필요
//
//	    // 1.기존 장기예약 전체 리스트 값을 요일별로 환산
//	    for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) { // 요일 번호 변환 필요 : 일요일1번으로
//	    	// 예약기간 중에 해당 요일 포함되면 //  ex:금토일
//	        if (dayOfWeekSet.contains(date.getDayOfWeek())) {
//
//	        	Map<LocalDate,LocalTime[]> reservation = new HashMap<>();
//	        	LocalTime[] times = new LocalTime[2];
//
//	        	LocalDate currentDay = date;
//	            times[0] = startTime;
//	            times[1] = endTime;
//
//	            reservation.put(currentDay, times); // 시작 시간
//	            reservations.add(reservation);
//	        	LocalTime[] times = new LocalTime[2];
//	        	LocalDate currentDay = date;
//	            LocalTime start = startTime;
//	            LocalTime end = endTime;
//
//	            LocalDateTime formattedStart = currentDay.atTime(start);
//	            LocalDateTime formattedEnd = currentDay.atTime(end);
//
//	            reservation.put(currentDay, formattedStart); // 시작 시간
//	    		reservation.put(1, formattedEnd); // 종료 시간
//	    		reservations.add(reservation);
//	        }
//	    }
//	    return reservations;
//	}

//	public boolean checkReservationAvailability(Map<Integer, Map<LocalDate, LocalTime[]>> oldReservations, Map<LocalDate, LocalTime[]> newReservation) {
//	    for (Map.Entry<LocalDate, LocalTime[]> newEntry : newReservation.entrySet()) {
//	        LocalDate newDate = newEntry.getKey();
//	        LocalTime newStart = newEntry.getValue()[0];
//	        LocalTime newEnd = newEntry.getValue()[1];
//
//	        if (oldReservations.containsKey(newDate.getDayOfWeek().getValue())) {
//	            Map<LocalDate, LocalTime[]> dayReservations = oldReservations.get(newDate.getDayOfWeek().getValue());
//	            for (Map.Entry<LocalDate, LocalTime[]> oldEntry : dayReservations.entrySet()) {
//	                LocalTime oldStart = oldEntry.getValue()[0];
//	                LocalTime oldEnd = oldEntry.getValue()[1];

    public String resrvtionChekCompareInside(LocalDateTime rsvtTm1, LocalDateTime rsvtTm2, LocalDateTime bgngTm, LocalDateTime endTm) {
        String result = "N";
        try {
            if (rsvtTm1.isAfter(bgngTm) && rsvtTm1.isBefore(endTm) || rsvtTm1.equals(bgngTm)) {
                return result = "N2";
            } else if (result.equals("Y")) {
                if (rsvtTm2.isAfter(bgngTm) && rsvtTm2.isBefore(endTm) || rsvtTm2.equals(endTm)) {  // 예약종료시간이 기존예약종료시간과 같을 경우는 안되는게 맞음
                    return result = "N2";
                }
            }
        }catch(Exception e){
            logger.error("resrvtionChekCompareInside() Exception");
            e.printStackTrace();
            return "N2";
        }
        return result;
    }

    public String checkNewOldLongRsvt(Map<Integer, Map<LocalDate, LocalTime[]>> oldReservations, Map<LocalDate, LocalTime[]> newReservation) {
        String result = "N";

        // 신규 예약의 모든 날짜와 시간을 순회
        for (Map.Entry<LocalDate, LocalTime[]> newEntry : newReservation.entrySet()) {
            LocalDate newDate = newEntry.getKey();
            LocalTime newStart = newEntry.getValue()[0];
            LocalTime newEnd = newEntry.getValue()[1];
            LocalDateTime newRsvtStart = newDate.atTime(newStart);
            LocalDateTime newRsvtEnd = newDate.atTime(newEnd);

            // 기존 예약의 모든 항목(예약 번호) 순회
            for (Map.Entry<Integer, Map<LocalDate, LocalTime[]>> oldEntry : oldReservations.entrySet()) {
                Map<LocalDate, LocalTime[]> dateReservations = oldEntry.getValue();

                // 해당 예약 번호에 대한 모든 날짜와 시간을 검사
                for (Map.Entry<LocalDate, LocalTime[]> dateEntry : dateReservations.entrySet()) {
                    LocalDate oldDate = dateEntry.getKey();
                    LocalTime oldStart = dateEntry.getValue()[0];
                    LocalTime oldEnd = dateEntry.getValue()[1];
                    LocalDateTime oldRsvtStart = oldDate.atTime(oldStart);
                    LocalDateTime oldRsvtEnd = oldDate.atTime(oldEnd);

//	                if (!newDate.equals(oldDate)) {
//	                    continue;
//	                }
                    // 같은 날짜에 예약 시간이 겹치는지 확인
                    result = resrvtionChekCompareInside(newRsvtStart, newRsvtEnd, oldRsvtStart, oldRsvtEnd);
                }
            }
        }
        return result;
    }


    @Override
    public String selectLongAplyTmList(FacilLongVO paramVo) {
        String result = "Y";

        List<FacilLongVO> longAplyList = opFacilDao.selectLongAplyTmList(paramVo); // 장기예약신청 내역(intra 신청관리 리스트)
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        // 1.기존 장기예약 전체 리스트 값을 요일별로 환산
        Map<Integer,Map<LocalDate,LocalTime[]>> oldLongAplyListEachDay = oldLongRsvtSelectedDayTimes(longAplyList);
        // 2.예약할려는 신규장기예약값을 요일별로 환산
        Map<LocalDate,LocalTime[]> newLongAplyListEachDay = newLongRsvtSelectedDayTimes(paramVo);
        // 3.기존 예약과 신규예약 중복 체크
        result = checkNewOldLongRsvt(oldLongAplyListEachDay, newLongAplyListEachDay);


        // 2.예약할려는 신규장기예약값을 요일별로 환산
        List<FacilLongVO> dataList = opFacilDao.selectLongAplyTmList(paramVo); // 장기예약신청 내역(intra 신청관리 리스트)
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        if (Validate.isNotEmpty(dataList)) {
            try {
                String[] dayCdId = paramVo.getRsvtDayCdId().split(","); // 요일
                String aplyBgngTm = paramVo.getRsvtBgngTm();
                String aplyEndTm = paramVo.getRsvtEndTm();
                String aplyBgngDt = paramVo.getRsvtBgngDt().substring(0, 10);
                String aplyEndDt = paramVo.getRsvtEndDt().substring(0, 10);

                Date aplyTm1 = sdf.parse(aplyBgngDt + " " + aplyBgngTm);
                Date aplyTm2 = sdf.parse(aplyEndDt + " " + aplyEndTm);

                // 위의 개방시간(timy only),신청서 체크(하루 비교)는 요일,시간이면 되지만, 기존예약체크부터는 연월일,시간 +요일(?) 다 체크해야함
                // 각 날짜별로 띄엄띄엄이라 between식으로 비교하면 안됨
                for (FacilLongVO dataVo : dataList) {
                    String rsvtBgngTm = dataVo.getRsvtBgngTm();
                    String rsvtEndTm = dataVo.getRsvtEndTm();
                    String rsvtBgngDt = dataVo.getRsvtBgngDt().substring(0, 10);
                    String rsvtEndDt = dataVo.getRsvtEndDt().substring(0, 10);

                    Date rsvtTm1 = sdf.parse(rsvtBgngDt + " " + rsvtBgngTm);
                    Date rsvtTm2 = sdf.parse(rsvtEndDt + " " + rsvtEndTm);


                    if (aplyTm1.after(rsvtTm1) && aplyTm1.before(rsvtTm2) || aplyTm1.equals(rsvtTm1)
                            || aplyTm1.equals(rsvtTm2)) {
                        result = "N2";
                    } else if (result.equals("Y")) {
                        if (aplyTm2.after(rsvtTm1) && aplyTm2.before(rsvtTm2) || aplyTm2.equals(rsvtTm1)
                                || aplyTm2.equals(rsvtTm2)) {
                            result = "N2";
                        }
                    }

                }
            } catch (ParseException e) {
                logger.error("selectFcltInfo ParseException");
                e.printStackTrace();
                return "N";
            }
        } else {
            result = "Y";
        }
        return result;
    }



}
