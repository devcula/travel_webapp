app.controller("ProfileController",function($scope,UserService,$http,$window){
	$scope.userForm={};
	$scope.userForm.emailId=null;
	$scope.userForm.firstName=null;
	$scope.userForm.lastName=null;
	$scope.userForm.contactNo=null;
	$scope.userForm.dateOfBirth=null;
	$scope.userForm.gender=null;
	$scope.userForm.password=null;
	$scope.message=null;
	
	$scope.changePasswordForm={};
	$scope.changePasswordForm.oldPassword=null;
	$scope.changePasswordForm.newPassword=null;
	$scope.changePasswordForm.emailId=null;
	$scope.changePasswordForm.message=null;
	$scope.confirmNewPassword=null;
	
	$scope.init=function(){
		document.getElementById('loadingModal').style.display='block';
		$scope.message="Please be patient. Your profile is being loaded......."
		$scope.userForm.password=null;
		UserService.verifyLogin();
		if(UserService.emailId){
			$scope.userForm.emailId=UserService.emailId;
			var data=JSON.stringify($scope.userForm);
			$http.post(URI+"LetsGo/fetchUserDetails",data).then(function(response){
				$scope.userForm.firstName=response.data.firstName;
				$scope.userForm.lastName=response.data.lastName;
				$scope.userForm.contactNo=response.data.contactNo;
				$scope.userForm.dateOfBirth=response.data.dateOfBirth.toString().split(' ')[0].split(',')[0];
				$scope.userForm.gender=response.data.gender;
				$scope.message="Your profile is loaded successfully.";
				document.getElementById('loadingModal').style.display='none';
			},function(response){
				$scope.userForm.message=response.data.message;
			});
		}
		else{
			document.getElementById('loadingModal').style.display='none';
			swal({
				title: "You are Logged out",
				text: "Sign in again to see your profile.",
				icon: "error"
			});
			$window.location.href="#/login";
		}
	}
	
	$scope.changePassword=function(){
		var patt1=new RegExp("[A-Z]+");
		var patt2=new RegExp("[a-z]+");
		var patt3=new RegExp("[0-9]+");
		var temp=$scope.changePasswordForm.newPassword;
		if(temp.length>=8 && patt1.test(temp) && patt2.test(temp) && patt3.test(temp)){
			if($scope.changePasswordForm.newPassword!=$scope.confirmNewPassword){
				$scope.changePasswordForm.message="New passwords not matching. Please try again."
				$scope.changePasswordForm.newPassword=null;
				$scope.confirmNewPassword=null;
			}
			else{
				$scope.changePasswordForm.emailId=$scope.userForm.emailId;
				$scope.changePasswordForm.message="Please be patient...";
				var data=JSON.stringify($scope.changePasswordForm);
				$http.post(URI+"LetsGo/changePassword",data).then(function(response){
					document.getElementById('pwdModal').style.display='none';
					swal({
						title: "Password changed successfully",
						icon: "success"
					});
				},function(response){
					$scope.changePasswordForm.message=response.data.message;
				});
			}
		}
		else{
			$scope.changePasswordForm.message="Password must contain minimum 8 characters having atleast one lowercase alphabet, one uppercase alphabet and one digit";
		}
	}
	
	$scope.enableFields=function(){
		document.getElementById('firstNameInput').disabled=false;
		document.getElementById('lastNameInput').disabled=false;
		document.getElementById('contactNoInput').disabled=false;
	}
	
	$scope.disableFields=function(){
		document.getElementById('firstNameInput').disabled=true;
		document.getElementById('lastNameInput').disabled=true;
		document.getElementById('contactNoInput').disabled=true;
	}
	
	$scope.updateProfile=function(){
		var temp=$scope.userForm;
		temp.dateOfBirth=null;
		var data=JSON.stringify(temp);
		temp.dateOfBirth=$scope.userForm.dateOfBirth;
		$http.post(URI+"LetsGo/updateProfile",data).then(function(response){
			swal({
				title: "Kudos!",
				text: response.data.message,
				icon: "success"
			});
			$scope.init();
			$scope.disableFields();
			document.getElementById('profileModal').style.display='none';
		},function(response){
			swal({
				title: response.data.message,
				icon: "error"
			});
		});
	}
	
	$scope.validateFields=function(){
		var patt=new RegExp("^[a-zA-Z]+$");
		if(patt.test(document.getElementById('firstNameInput').value))
		{
			patt=new RegExp("^[a-zA-Z]*$");
			if(patt.test(document.getElementById('lastNameInput').value)){
				patt=new RegExp("[1-9]([0-9]){9}");
				if(document.getElementById('contactNoInput').value<=9999999999 && document.getElementById('contactNoInput').value>=1000000000){
					document.getElementById('profileModal').style.display='block';
				}
				else{
					swal({
						title: "Invalid Contact number",
						icon: "error"
					});
				}
			}
			else{
				swal({
					title: "Invalid Last Name",
					icon: "error"
				});
			}
		}
		else{
			swal({
				title: "Invalid First Name",
				icon: "error"
			});
		}
	}
});