app.controller("SignUpController",function($scope,$http,UserService,$window){
	$scope.userForm={};
	$scope.userForm.emailId=null;
	$scope.userForm.firstName=null;
	$scope.userForm.lastName=null;
	$scope.userForm.contactNo=null;
	$scope.userForm.dateOfBirth=null;
	$scope.userForm.gender=null;
	$scope.userForm.password=null;
	$scope.userForm.message=null;
	$scope.userForm.question=null;
	$scope.userForm.answer=null;
	$scope.confirmPassword=null;
	
	$scope.register=function(){
		$scope.userForm.message=null;
		if($scope.confirmPasswordPattern()){
			if(document.getElementById('confirmPasswordInput').value!=document.getElementById('passwordInput').value){
				$scope.userForm.message="Passwords not matching. Please try again.";
				$scope.userForm.password=null;
				$scope.confirmPassword=null;
			}
			else{
				$scope.userForm.message=null;
				$scope.userForm.dateOfBirth=$scope.userForm.dateOfBirth.toLocaleString();
				$scope.userForm.contactNo=parseInt($scope.userForm.contactNo);
				var data=JSON.stringify($scope.userForm);
				$http.post(URI+"LetsGo/register",data).then(function(response){
					$scope.userForm.message=response.data.message;
					swal({
						title: "Signup Successful",
						text: "Please login to continue",
						icon: "success"
					});
					$window.location.href="#/login";
				},function(response){
					$scope.userForm.message=response.data.message;
				});
			}
		}
		else{
			swal({
				title: "Select different password",
				text: "Password must contain minimum 8 characters having atleast one lowercase alphabet, one uppercase alphabet and one digit",
				icon: "warning"
			})
		}
	}
	
	$scope.matchPasswords=function(){
		if(document.getElementById('confirmPasswordInput').value!=document.getElementById('passwordInput').value)
			document.getElementById('confirmPasswordInput').style.borderColor="red";
		else
			document.getElementById('confirmPasswordInput').style.borderColor="#00FF00";
	}
	
	$scope.confirmPasswordPattern=function(){
		$scope.matchPasswords();
		var temp=document.getElementById('passwordInput').value;
		var patt1=new RegExp("[A-Z]+");
		var patt2=new RegExp("[a-z]+");
		var patt3=new RegExp("[0-9]+");
		if(temp.length>=8){
			if(patt1.test(temp) && patt2.test(temp) && patt3.test(temp)){
				document.getElementById('passwordInput').style.borderColor="#00FF00";
				return true;
			}
			else
				{
					document.getElementById('passwordInput').style.borderColor="red";
					return false;
				}
		}
		else{
			document.getElementById('passwordInput').style.borderColor="red";
			return false;
		}
	}
});