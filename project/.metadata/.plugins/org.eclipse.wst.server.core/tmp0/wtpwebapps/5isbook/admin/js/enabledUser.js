var xmlHttp;
var flag;
var userAccount;
function create() {
	if (window.ActiveXObject) {
		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	} else if (window.XMLHttpRequest) {
		xmlHttp = new XMLHttpRequest();
	}
}
function change(account) {
	create();
	userAccount = account;
	flag = document.getElementById("enabled_" + userAccount).firstChild.nodeValue;
	xmlHttp.onreadystatechange = changeButton;
	xmlHttp.open("POST", "/5isbook/admin/user/changeEnabled?userAccount="
			+ userAccount);
	xmlHttp.send(null);
}
function changeButton() {
	if (xmlHttp.readyState == 4) {
		if (xmlHttp.status == 200) {
			if (flag == "未激活")
				flag = "已激活";
			else
				flag = "未激活";
			document.getElementById("enabled_" + userAccount).firstChild.nodeValue = flag;
			alert("已更改为 " + flag + " 状态");
		}
	}
}
