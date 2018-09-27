app.controller("CabController",function($scope,$http,UserService,$window){
	$scope.cabForm={
		city:null,
		source:null,
		destination:null,
		journeyDate:null,
		carType:null,
		cabNumber: null,
		cabName:null,
		driverName:null,
		bookingDate:null,
		message:null,
		price: null
	};
	
	$scope.init=function(){
		if(UserService.cities==null){
			$http.get(URI+"City/getCities").then(function(response){
				UserService.cities=response.data;
				console.log(UserService.cities);
			},function(response){
				document.getElementById('navBar').style.display='none';
				$window.location.href="#/maintain";
				swal({
					title: "Website under maintenance",
					text: "Try again later",
					icon: "warning"
				});
			});
		}
	}
	$scope.cities=UserService.cities;
	
	$scope.fetchPlaces=function(){
		$scope.places=[];
		if($scope.cities.indexOf($scope.cabForm.city)!=-1){
			var data=JSON.stringify({"city":$scope.cabForm.city});
			$http.post(URI+"Cab/fetchPlaces",data).then(function(response){
				$scope.places=response.data;
			},function(response){
				console.log(response.data.message);
				swal({
					title: "Something went wrong",
					text: "Please try again later",
					icon: "error"
				});
			});
		}
	}
	
	$scope.checkAvailability=function(){
		document.getElementById('loadingModal').style.display='block';
		$scope.cabForm.cabNumber=null;
		$scope.cabForm.cabName=null;
		$scope.cabForm.message=null;
		$scope.cabForm.bookingDate=null;
		$scope.cabForm.price=null;

		$scope.results=null;
		document.getElementById('cabResults').style.visibility="hidden";
		if($scope.cabForm.source==$scope.cabForm.destination){
			document.getElementById('loadingModal').style.display='none';
			swal({
  				title: "Same Source and Destination",
  				icon: "error"
				});
		}
		else if($scope.cabForm.journeyDate<new Date(Date.now()-1000*60*60*24)){
			document.getElementById('loadingModal').style.display='none';
			swal({
  				title: "Invalid Date",
  				text: "We can't time travel, can we? ",
  				icon: "error",
				});
		}
		else{
			$scope.backupDate=$scope.cabForm.journeyDate;
			$scope.cabForm.journeyDate=$scope.cabForm.journeyDate.toLocaleString();
			var data=JSON.stringify($scope.cabForm);
			$http.post(URI+"Cab/checkAvailability",data)
			.then(function(response){
				$scope.results=response.data;
				document.getElementById('loadingModal').style.display='none';
				document.getElementById('cabResults').style.visibility="visible";
				for(i=0;i<$scope.results.length;i++){
					$scope.results[i].journeyDate=$scope.results[i].journeyDate.split(",")[0];
				}
			},function(response){
				document.getElementById('loadingModal').style.display='none';
				swal({
	  				title: response.data.message,
	  				icon: "error",
					});
			});
		}
	}
	
	$scope.book=function(input){
		UserService.verifyLogin();
		if(UserService.loggedIn){
			$scope.cabForm=$scope.results[input];
			$scope.baseFare=$scope.cabForm.price;
			$scope.gst=5;
			$scope.totalFare=$scope.baseFare+($scope.gst*$scope.baseFare/100);
			$scope.passengerArray=[];
			$scope.passengerArray.push(new Object());
			document.getElementById('passengerModal').style.display='block';
			}
			else{
				$window.location.href="#/login";
				swal({
  				title: "Kindly login to continue!",
  				icon: "info",
				});
			}
		}
	
	$scope.proceedToPayment=function(){
		swal({
		  title: "Are you sure you wanna pay?",
			icon: "warning",
			buttons: true,
			dangerMode: true,
		})
		.then((willPay) => {
			if (willPay) {
				$scope.cabForm.journeyDate=$scope.backupDate.toLocaleString();
				$scope.cabForm.price=$scope.totalFare;
				UserService.bookingType="cab";
				UserService.passengerArray=$scope.passengerArray;
				UserService.bookingObject=$scope.cabForm;
				$window.location.href="#/payment";
		} else {
			swal("Payment not Successful. Please try again.");
			}
	});
	}
	
});