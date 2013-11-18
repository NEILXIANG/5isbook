var xmlHttp;
function create() {
	if (window.ActiveXObject) {
		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	} else if (window.XMLHttpRequest) {
		xmlHttp = new XMLHttpRequest();
	}
}
//清空库存
function clearStoreCount(id) {
	create();
	var answer = confirm("确定要清空库存？你要意识到一旦清空就无法恢复了！");
	if (answer == false)
		return;
	xmlHttp.onreadystatechange = clearResult;
	xmlHttp.open("GET", "/5isbook/admin/goods/clearGoodsStoreCount?entityId="
			+ id);
	xmlHttp.send(null);
}
//根据商品id和类型改变它的库存数量
function changeStoreCount(id, type) {
	create();
	entityType = type;
	var now = document.getElementById(entityType).value;
	if (now < 0 || isNaN(now)) {  
		alert("非法输入");
		return;
	}
	xmlHttp.onreadystatechange = result;
	xmlHttp.open("POST", "changeStoreCount?entityId=" + id + "&typeName="
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

function clearResult() {
	if (xmlHttp.readyState == 4) {
		if (xmlHttp.status == 200) {
			document.getElementById("authorised_edition").value=0;
			document.getElementById("pirate_edition").value=0;
			document.getElementById("copy_edition").value=0;
			alert("更新成功");
		}
	}
}
