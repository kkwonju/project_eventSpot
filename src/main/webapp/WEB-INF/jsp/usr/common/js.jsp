<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
	var historyBack = '${historyBack}' == 'true';
	var resultMsg = '${resultMsg}'.trim();
	
	if(resultMsg){
		alert(resultMsg);
	}
	
	if(historyBack){
		history.back();
	}

	var replaceUri = '${replaceUri}'.trim();
	
	if(replaceUri){
		location.replace(replaceUri);
	}
</script>