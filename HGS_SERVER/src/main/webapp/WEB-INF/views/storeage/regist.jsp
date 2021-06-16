<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/tagLib.jsp"%>
<script>

$(document).ready(function(e){
	$("#pageTitle").text('스토리지 등록');
});

$(document).ready(
	function() {
	jQuery.ajax({
		type : "GET",
		url : "${pageContext.request.contextPath}/customer/get/list?pIdx=${pIdx}",
		dataType : "JSON", // 옵션이므로 JSON으로 받을게 아니면 안써도 됨
		success : function(data) {
			// 통신이 성공적으로 이루어졌을 때 이 함수를 타게 된다.
			// TODO
			$('#ctmIdx').append("<option value=\"0\">선택</option>");
			$.each(data, function(index, item) {
				$('#ctmIdx').append("<option value='" + item.id + "'>"+ item.customerName + "</option>");
				
			});
		},
		complete : function(data) {
			// 통신이 실패했어도 완료가 되었을 때 이 함수를 타게 된다.
			//$('#ctmIdx').append("<option value=\"0\">선택</option>");
			//alert("서버와 통신에 실패했습니다. 계속 실패할 경우 관리자에게 문의하세요.");
		},
		error : function(xhr, status, error) {
			$('#ctmIdx').append("<option value=\"0\">선택</option>");
			alert("에러발생");
		}
	});
});


function submit_stg_regist(){
	if($('#ctmIdx').val() == 0){
		showModal('message', '고객사를 선택하세요.');
		return false;
	}else if($('#modelName').val() == ''){
		showModal('message', '모델명을 입력하세요.');
		return false;
	}else if($('#storeageSn').val() == ''){
		showModal('message', '스토리지 S/N 을 입력하세요.');
		return false;
	}else if($('#productSpec').val() == ''){
		showModal('message', '제품 spec을 선택하세요.');
		return false;
	}else if($('#installDate').val() == ''){
		showModal('message', '납품(설치)일자를 입력하세요.');
		return false;
	}else if($('#installArea').val() == ''){
		showModal('message', '설치장소를 입력하세요.');
		return false;
	}else if($('#stgIp').val() == ''){
		showModal('message', 'IP Address(IPv4) 를 입력하세요.');
		return false;
	}else{
		
		jQuery.ajax({
			type : "POST",
			url : "${pageContext.request.contextPath}/storeage/duplicate/check",
			data: { stgIp: $('#stgIp').val(), modelName : $('#modelName').val() }, 
			dataType : "JSON", // 옵션이므로 JSON으로 받을게 아니면 안써도 됨
			success : function(data) {
				if(data){
					$('#form1').submit();
				}else{
					showModal('message', '동일한 모델(아이피)의 스토리지가 존재합니다.');
				}
			},
			complete : function(data) {
			},
			error : function(xhr, status, error) {
			}
		});
	}
	return false;
}
</script>
<form:form method="POST" id="form1" commandName="domain">
	<fieldset>
		<div class="form-group">
			<label for="ctmIdx">■ 고객사 선택</label>
			<select id="ctmIdx" name="ctmIdx" class="form-control">
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
				<form:input path="installDate" class="form-control" />
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
		<div class="col-md-12">
			<span class="pull-right">
				<input type="hidden" id="pIdx" name="pIdx" value="${pIdx}">
				<button type="submit" class="btn btn-primary mb-2" onclick="return submit_stg_regist();">등록</button>
				<button type="button" class="btn btn-secondary" onclick="javascript:history.back(-1);">취소</button>
			</span>
		</div>
	</fieldset>
</form:form>

<!-- Modal -->
<div class="modal fade" id="newModal" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">×</button>
				<h4 class="modal-title">Sample Modal</h4>
			</div>
			<div class="modal-body">
				<p>This is demo text.</p>
			</div>
		</div>
	</div>
</div>

<script>
function showModal(title, content) {
	var modal = $('#newModal');
	modal.find('.modal-body p').text(content);
	modal.find('.modal-header h4').html(title);
	//modal.find('.modal-footer a.btn').text('Remove');
	$('#newModal').modal('show');
	$("#button1").click(function() {
		$("#newModal").modal("hide");
	});
};
</script>