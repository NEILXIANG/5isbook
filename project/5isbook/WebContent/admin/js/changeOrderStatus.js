var xmlHttp;
function create() {
	if (window.ActiveXObject) {
		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	} else if (window.XMLHttpRequest) {
		xmlHttp = new XMLHttpRequest();
	}
}
function changeStatus(id, status) {
	create();
	if (status == "CANCEL" || status == "RECEIVED") {
		alert("订单状态为'已收货'或'取消',不能更改");
		return;
	}
	xmlHttp.onreadystatechange = result;
	xmlHttp.open("POST", "/5isbook/admin/order/processToNextStatus?orderId="
			+ id + "&back=detail");
	xmlHttp.send(null);
}
function result() {
	if (xmlHttp.readyState == 4) {
		if (xmlHttp.status == 200) {
			location.reload();
		}
	}
}
