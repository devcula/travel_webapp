var config={
		"protocol": 'http',
		"hostName": 'localhost',
		"port": '8080',
		"projectName": 'LetsGo'
};

function getURI(){
	return config.protocol+"://"+config.hostName+":"+config.port+"/"+config.projectName+"/api/";
}