<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Problem Report</title>
<style>
pre {
	margin: 0;
	padding: 0;
}
</style>
</head>
<form>
	<body class="mybody">

	<table width="75%">
		<tr>
			<td></td>
		</tr>
	</table>
	</body>
</form>
<script type="text/javascript">
	var errorMsg = '<%=request.getParameterValues("errorMsg")%>';
	if(errorMsg.trim()==''||errorMsg==null){
		errorMsg = '${errorMsg}';
	}
	if(errorMsg!=''&&errorMsg!=null){
		alert(errorMsg);
		if (window.history.length > 0) {
			history.back();
		} else {
			window.parent.frames.handleTabAction();
		}
	}
</script>
</html>