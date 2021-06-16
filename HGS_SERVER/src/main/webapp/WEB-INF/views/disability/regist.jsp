<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/tagLib.jsp"%>
<script>
$(document).ready(function(e){
	$("#pageTitle").text('스토리지 등록');
});
</script>
<form:form method="POST" commandName="domain">
	<fieldset>
		<div class="form-group">
			<label for="ctmIdx">■ 고객사 선택</label>
			<select id="ctmIdx" name="ctmIdx"  class="form-control">
			</select>
		</div>
		<div class="form-group">
			<label for="modelName">■ 모델 명</label> 
			<form:input path="modelName" class="form-control" placeholder="모델 명"/>
		</div>
		<div class="form-group">
			<label for="storeageSn">■ 스토리지 S/N</label> 
			<form:input path="storeageSn" class="form-control" placeholder="스토리지 S/N"/>
		</div>
		<%-- <div class="form-group">
			<label for="storeageName">■ 스토리지 Name</label> 
			<form:input path="storeageName" class="form-control" placeholder="스토리지 Name"/>
		</div> --%>
		<div class="form-group">
			<label for="productSpec">■ 제품 spec</label> 
			<form:select path="productSpec" class="form-control">
				<form:option value="" label="선택" />
				<form:option value="SAN or NAS" label="SAN or NAS" />
				<form:option value="HDD 용량 및 캐시" label="HDD 용량 및 캐시" />
			</form:select>
		</div>
		<div class="form-group">
			<label for="installDate">■ 납품(설치) 일자 </label>
			<div class='input-group date' id='datetimepicker1'>
				<form:input path="installDate" type='text' class="form-control" />
				<span class="input-group-addon"> <span
					class="glyphicon glyphicon-calendar"></span>
				</span>
			</div>
		</div>
		<script type="text/javascript">
			$(function() {
				$.datepicker.setDefaults({
					dateFormat : 'yy-mm-dd',
					prevText : '이전 달',
					nextText : '다음 달',
					monthNames : [ '1월', '2월', '3월', '4월', '5월', '6월', '7월',
							'8월', '9월', '10월', '11월', '12월' ],
					monthNamesShort : [ '1월', '2월', '3월', '4월', '5월', '6월',
							'7월', '8월', '9월', '10월', '11월', '12월' ],
					dayNames : [ '일', '월', '화', '수', '목', '금', '토' ],
					dayNamesShort : [ '일', '월', '화', '수', '목', '금', '토' ],
					dayNamesMin : [ '일', '월', '화', '수', '목', '금', '토' ],
					showMonthAfterYear : true,
					yearSuffix : '년'
				});

				$("#installDate").datepicker();
			});
		</script>
		<div class="form-group">
			<label for="installArea">■ 설치장소</label> 
			<form:input path="installArea" class="form-control" placeholder="설치 장소"/>
		</div>
		<div class="form-group">
			<label for="stgIp">■ IP Address(IPv4)</label> 
			<form:input path="stgIp" class="form-control" placeholder="설치 장소"/>
		</div>
	</fieldset>
</form:form>

