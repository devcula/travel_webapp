app.controller("HotelController",function($scope,$window,$http,UserService){
	$scope.hotelForm={
		hotelId:null,
		city:null,
		hotelName: null,
		checkInDate: null,
		checkInDate: null,
		quantity: null,
		roomsType: null,
		rating: null,
		bookingDate: null,
		message: null,
		timesRated: null,
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
		if(UserService.checkForHotels=='true'){
			$scope.hotelForm.city=UserService.hotelSuggestionDetails.city;
			$scope.hotelForm.checkInDate=UserService.hotelSuggestionDetails.checkInDate;
			$scope.hotelForm.checkOutDate=UserService.hotelSuggestionDetails.checkOutDate;
			$scope.hotelForm.quantity=UserService.hotelSuggestionDetails.quantity;
			$scope.hotelForm.roomsType=UserService.hotelSuggestionDetails.roomsType;
			$scope.checkAvailability();
			UserService.checkForHotels='false';
			$scope.hotelForm.checkInDate=UserService.hotelSuggestionDetails.checkInDate;
			$scope.hotelForm.checkOutDate=UserService.hotelSuggestionDetails.checkOutDate;
		}
	}
	
	$scope.cities=UserService.cities;
	$scope.backupCheckInDate=null;
	$scope.checkAvailability=function(){
		document.getElementById('loadingModal').style.display='block';
		$scope.hotelForm.hotelId=null;
		$scope.hotelForm.hotelName=null;
		$scope.hotelForm.message=null;
		$scope.hotelForm.bookingDate=null;
		$scope.hotelForm.price=null;

		$scope.results=null;
		document.getElementById('hotelResults').style.visibility="hidden";

		if($scope.hotelForm.checkInDate<new Date(Date.now()-1000*60*60*24)){
			document.getElementById('loadingModal').style.display='none';
			swal({
  				title: "Invalid Check In Date",
  				text: "We can't time travel, can we? ",
  				icon: "error",
				});
		}
		else if($scope.hotelForm.checkOutDate<new Date(Date.now()-1000*60*60*24)){
			document.getElementById('loadingModal').style.display='none';
			swal({
  				title: "Invalid Check Out Date",
  				text: "We can't time travel, can we? ",
  				icon: "error",
				});
		}
		else if($scope.hotelForm.checkOutDate<=$scope.hotelForm.checkInDate){
			document.getElementById('loadingModal').style.display='none';
			swal({
  				title: "Check Out date should always be greater than Check In date.",
  				icon: "error",
				});
		}
		else{
			$scope.backupCheckInDate=$scope.hotelForm.checkInDate.toLocaleString();
			$scope.backupCheckOutDate=$scope.hotelForm.checkOutDate.toLocaleString();
			$scope.hotelForm.checkInDate=$scope.hotelForm.checkInDate.toLocaleString();
			$scope.hotelForm.checkOutDate=$scope.hotelForm.checkOutDate.toLocaleString();
			var data=JSON.stringify($scope.hotelForm);
			$http.post(URI+"Hotel/checkAvailability",data)
			.then(function(response){
				$scope.results=response.data;
				document.getElementById('loadingModal').style.display='none';
				for(i=0;i<$scope.results.length;i++){
					$scope.results[i].checkInDate=$scope.backupCheckInDate.split(",")[0];
					$scope.results[i].checkOutDate=$scope.backupCheckOutDate.split(",")[0];
				}
				document.getElementById('hotelResults').style.visibility="visible";
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
			$scope.hotelForm=$scope.results[input];
			//$scope.hotelForm.checkInDate=$scope.backupCheckInDate;
			//$scope.hotelForm.checkOutDate=$scope.backupCheckOutDate;
			console.log($scope.hotelForm.checkInDate);
			$scope.baseFare=$scope.hotelForm.price*$scope.hotelForm.quantity;
			if($scope.hotelForm.roomsType=='standard'){
				$scope.gst=12;
			}
			else {
				$scope.gst=18;
			}
			$scope.totalFare=$scope.baseFare+($scope.gst*$scope.baseFare/100);
			$scope.passengerArray=[];
			for(i=0;i<$scope.results[input].quantity;i++){
				$scope.passengerArray.push(new Object());
				}
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
			$scope.hotelForm.checkInDate=$scope.backupCheckInDate;
			$scope.hotelForm.checkOutDate=$scope.backupCheckOutDate;
			$scope.hotelForm.price=$scope.totalFare;
			UserService.bookingType='hotel';
			UserService.passengerArray=$scope.passengerArray;
			UserService.bookingObject=$scope.hotelForm;
			$window.location.href="#/payment";
		} else {
			swal("Payment not Successful. Please try again.");
			}
	});
	}

});