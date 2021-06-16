/**
 * 
 */
function movePage(pageNum) {
	$('#currentPage').val(pageNum);
	$('#searchForm').submit();
}
function move_url(url){
	document.location.href=url;
}
function confirm_delete(url){
	var result = confirm("정말 삭제하시겠습니까?");
	if(result){
		document.location.href=url;
	}
}