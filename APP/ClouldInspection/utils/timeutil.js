export function getTime(timestamp){
	var date = null;
	if(timestamp == null){
		date = new Date();
	} else {
		date = new Date(timestamp)
	}
	var Y = date.getFullYear() + '-';
	var M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-'
	var D = date.getDate() < 10 ? '0' + date.getDate() : date.getDate() + ' '
	var hh = date.getHours() < 10 ? '0' + date.getHours() : date.getHours()
	var mm = date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes()
	var ss = date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds()
	return Y + M + D + ' '  + hh + ':' + mm + ':' + ss
}