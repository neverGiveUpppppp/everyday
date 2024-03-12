<%@page contentType="text/html;charset=UTF-8"%>

<script>
$(document).ready(function() {
    $.ajax({
        /* url: '/user/ga4/ND_ga4Visitor.do', */
        url: '/user/ga4OAuth2/ND_ga4Visitor.do',
        type: 'POST',
        dataType: 'json',
        success: function(data) {
            console.log("JSON.stringify(data) : " + JSON.stringify(data));

            /* var today = data.todayVisitors;
            var all = data.allVisitors; */
            var today = data.today;
            var all = data.all;
            $("#visitorCounts").text("방문자수 (오늘/전체 : " + today + " / " + all + ")");
        },
        error: function(xhr, status, error) {
            console.log('[Error] :', error);
        }
    });
});




</script>
 
<li id="visitorCounts" style="margin-left: 20px">방문자수 (오늘/전체 : today / all )</li><br>
<li id="visitorCountsTest" style="margin-left: 20px">방문자수 (오늘/전체 : response.totalUsers )</li>
	