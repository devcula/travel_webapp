app.controller("IndexController",function($scope,UserService,$http,$window){
	
	
	$scope.loggedIn=null;
	$scope.email=null;
	$scope.name=null;
	$scope.init=function(){
		UserService.verifyLogin();
		$scope.loggedIn=UserService.loggedIn;
		$scope.emailId=UserService.emailId;
		$scope.name=UserService.userName;
		//$scope.checkHotelBookings();
		setTimeout($scope.checkHotelBookings,4000);
	}

	$scope.logout=function(){
		UserService.logout();
		$scope.init();
	}
	$scope.hotel=null;
	$scope.checkHotelBookings=function(){
		$scope.email=UserService.emailId;
		if($scope.email!=null){
			var data=JSON.stringify({"emailId":$scope.email});
			$http.post(URI+"LetsGo/checkHotelBookings",data).then(function(response){
				$scope.hotel=response.data;
				document.getElementById('rateHotelModal').style.display='block';
			},function(response){
				console.log("Can't check for Hotel Bookings");
				console.log(response.data.message);
			});
		}
	}
	$scope.submitHotelRating=function(){
		if($scope.hotel.rating>5 || $scope.hotel.rating<0){
			swal({
				title: "Kindly enter a valid rating between 0 and 5",
				icon: "error"
			});
		}
		else{
			var data=JSON.stringify($scope.hotel);
			$http.post(URI+"LetsGo/submitHotelRating",data).then(function(response){
				swal({
					title: "Thank You!",
					text: response.data.message,
					icon: "success"
				});
				document.getElementById('rateHotelModal').style.display='none';
			},function(response){
				swal({
					title: "Thank You",
					icon: "success"
				});
				console.log(response.data.message);
				document.getElementById('rateHotelModal').style.display='none';
			});
		}
	}

});
