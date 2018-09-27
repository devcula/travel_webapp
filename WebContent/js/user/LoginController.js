app.controller("LoginController",function($scope,$http,UserService,$window){
	$scope.loginForm={};
	$scope.loginForm.emailId=null;
	$scope.loginForm.password=null;
	$scope.loginForm.name=null;
	$scope.loginForm.message=null;
	$scope.loginForm.hash=null;
	$scope.rememberMe=null;
	$scope.init=function(){
		UserService.verifyLogin();
		if(UserService.emailId){
			$window.location.href="#/";
		}
	}
	
	$scope.login=function(){
		
		$scope.loginForm.name=null;
		$scope.loginForm.hash=null;
		$scope.loginForm.message="Logging in...";
		UserService.rememberMe=$scope.rememberMe;
		var data=JSON.stringify($scope.loginForm);
		
		$http.post(URI+"LetsGo/login",data).then(function(response){
			$scope.loginForm.name=response.data.name;
			UserService.login(response.data);
			$scope.loginForm.message=response.data.message;
			$window.location.href="#/index";
			//$window.location.reload();
			$window.location.href="index.html";
		},function(response){
			$scope.loginForm.message=response.data.message;
		});
	}
	
	$scope.emailId=null;
	$scope.answer=null;
	$scope.question=null;
	$scope.tempAnswer=null;
	
	$scope.fetchQuestion=function(){
		$scope.question=null;
		$scope.answer=null;
		var data=JSON.stringify({"emailId":$scope.emailId});
		$http.post(URI+"LetsGo/forgotPassword",data).then(function(response){
			$scope.question=response.data.question;
			$scope.answer=response.data.answer;
			document.getElementById('emailModal').style.display='none';
			document.getElementById('answerModal').style.display='block';
		},function(response){
			swal({
				title: response.data.message,
				icon: "error"
			});
		});
	}
	
	$scope.verifyAnswer=function(){
		if($scope.answer==$scope.tempAnswer){
			document.getElementById('answerModal').style.display='none';
			$scope.newPassword=null;
			$scope.confirmNewPassword=null;
			document.getElementById('newPasswordModal').style.display='block';
		}
		else
			swal({
				title: "Wrong answer! Try again.",
				icon: "warning"
			});
	}
	
	$scope.setNewPassword=function(){
		var patt1=new RegExp("[A-Z]+");
		var patt2=new RegExp("[a-z]+");
		var patt3=new RegExp("[0-9]+");
		var temp=$scope.newPassword;
		if(temp.length>=8 && patt1.test(temp) && patt2.test(temp) && patt3.test(temp)){
			if($scope.newPassword!=$scope.confirmNewPassword){
				swal("New passwords not matching. Please try again.");
				$scope.newPassword=null;
				$scope.confirmNewPassword=null;
			}
			else{
				var data=JSON.stringify({"emailId":$scope.emailId,"password":$scope.newPassword,"answer":$scope.answer});
				$http.post(URI+"LetsGo/resetPassword",data).then(function(response){
					document.getElementById('newPasswordModal').style.display='none';
					swal({
						title: "Kudos! Password recovered",
						icon: "success"
					});
				},function(response){
					swal({
						title: response.data.message,
						icon: "error"
					});
				});
			}
		}
		else{
			swal("Password must contain minimum 8 characters having atleast one lowercase alphabet, one uppercase alphabet and one digit");
		}
	}
});