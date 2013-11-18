var xmlHttp;
var entityType;
var entityId;
function create() {
	if (window.ActiveXObject) {
		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	} else if (window.XMLHttpRequest) {
		xmlHttp = new XMLHttpRequest();
	}
}//根据商品id和类型改变它的库存数量
function changeStoreCount(id, type) {
	create();
	entityType = type;
	var now = document.getElementById(entityType).value;
	if (now < 0 || isNaN(now)) {
		alert("非法输入");
		return;
	}
	xmlHttp.onreadystatechange = result;
	xmlHttp.open("POST", "admin/goods/changeStoreCount?entityId=" + id + "&typeName="
			+ type + "&count=" + now);
	xmlHttp.send(null);
}
function result() {
	if (xmlHttp.readyState == 4) {
		if (xmlHttp.status == 200) {
			alert("更新成功");
		}
	}
}
//跳转
function go(id) {
	var pageIndex = document.getElementById("jump").value;
	if (pageIndex > 0)
		window.location.href = "/5isbook/admin/goods/showEntities?id=" + id
				+ "&pageIndex=" + pageIndex;
}
