app.controller("BookingController",function(UserService,$http,$scope,$window){
  $scope.booking={
    userEmailId:null,
    bookingId:null,
    bookingDate:null,
    transactionId:null,
    bookingType:null,
    noOfPassengers:null,
    journeyDate:null,
    source:null,
    destination:null,
    fare:null,
    entityId:null,
    entityName:null,
    feedback:null
  };

  $scope.allBookings=[];
  $scope.message=null;
	  
  $scope.init=function(){
	  if(UserService.booking!=null){
		  $scope.booking=UserService.booking;
		    if(UserService.checkForHotels=='true'){
		    	var data=JSON.stringify({"city":$scope.booking.destination});
		        $http.post(URI+"Hotel/find",data).then(function(response){
		        	$scope.noOfStandards=response.data.hotelId;
		        	$scope.noOfDeluxes=response.data.quantity;
		        },function(response){
		        	$scope.noOfStandards=null;
		        	$scope.noOfDeluxes=null;
		        	console.log(response.data.message);
		        });
		        
		        
		        setTimeout(function() {
		        	swal({
		      		  title: "We found " + $scope.noOfStandards + 
		      " standard and "+
		      $scope.noOfDeluxes +
		       " deluxe hotels in your destination where you can stay.Do you want to see?",
		      			icon: "warning",
		      			buttons: true,
		      			dangerMode: true,
		      		})
		      		.then((yes) => {
		      			if (yes) {
		      				$scope.showHotels();
		      		} else {
		      			swal("Happy journey");
		      			}
		      	});
		        }, 2000);
		    }
	  }
	  else{
		  $window.location.href="#/";
	  }
    
  }
  $scope.bookingType=null;
  
  $scope.showHotels=function(){
	  UserService.hotelSuggestionDetails={};
	  UserService.hotelSuggestionDetails.city=$scope.booking.destination;
	  UserService.hotelSuggestionDetails.roomsType="standard";
	  UserService.hotelSuggestionDetails.quantity=1;
	  UserService.hotelSuggestionDetails.checkInDate=new Date($scope.booking.journeyDate);
	  UserService.hotelSuggestionDetails.checkOutDate=new Date(UserService.hotelSuggestionDetails.checkInDate.getTime()+2*24*60*60*1000);
	  $window.location.href="#/hotel";
  }
  
  $scope.changeResults=function(){
	  $scope.bookings=[];
	  $scope.categoryMessage=null;
	  for(i=0;i<$scope.allBookings.length;i++){
		  if($scope.allBookings[i].bookingType==$scope.bookingType)
			  $scope.bookings.push($scope.allBookings[i]);
	  }
	  if($scope.bookings.length==0)
		  $scope.categoryMessage="No bookings to show in this category";
  }
  
  $scope.acceptRating=function(input){
	  $scope.hotel={
			  hotelId:null,
			  hotelName:null,
			  city:null,
			  price:null,
			  rating:null
	  }
	  $scope.bookingId=$scope.bookings[input].bookingId;
	  $scope.hotel.hotelId=parseInt($scope.bookings[input].entityId);
	  $scope.hotel.hotelName=$scope.bookings[input].entityName;
	  $scope.hotel.city=$scope.bookings[input].source;
	  $scope.hotel.price=$scope.bookings[input].bookingId;
	  document.getElementById('rateHotelModal2').style.display='block';
  }
  
  $scope.submitHotelRating=function(){
	 
	  	data=JSON.stringify($scope.hotel);
	  	console.log(data);
		$http.post(URI+"LetsGo/submitHotelRating",data).then(function(response){
			swal({
				title: "Thank You!",
				text: response.data.message,
				icon: "success"
			});
			document.getElementById('rateHotelModal2').style.display='none';
			//$window.location.reload();
			for(i=0;i<$scope.allBookings.length;i++){
				  if($scope.allBookings[i].bookingId==$scope.bookingId){
					  $scope.allBookings[i].rated='Y';
					  break;
				  }
			  }
			  for(i=0;i<$scope.bookings.length;i++){
				  if($scope.bookings[i].bookingId==$scope.bookingId){
					  $scope.bookings[i].rated='Y';
					  break;
				  }
			  }
			$scope.bookingType="HOTEL";
		},function(response){
			swal({
				title: "Thank You",
				icon: "success"
			});
			console.log(response.data.message);
			document.getElementById('rateHotelModal2').style.display='none';
		});
  }
  
  $scope.viewBookings=function(){
	  UserService.verifyLogin();
	  if(UserService.loggedIn){
		  $scope.allBookings=null;
		  $scope.message="Please wait while we fetch all your booking details....";
		  var data=JSON.stringify({"emailId":UserService.emailId});
		  $http.post(URI+"LetsGo/fetchBookings",data).then(function(response){
			 $scope.allBookings=response.data; 
			 $scope.bookings=$scope.allBookings;
			 $scope.message=null;
		  },function(response){
			  $scope.message=response.data.message;
		  });
	  }
	  else{
		  swal({
				title: "You are Logged out",
				text: "Sign in again to see your bookings.",
				icon: "error"
			});
			$window.location.href="#/login";
	  }
  }
  $scope.feedback=null;
  $scope.bookingId=null;
  $scope.acceptFeedback=function(input){
	  $scope.feedback=null;
	  $scope.bookingId=$scope.bookings[input].bookingId;
	  document.getElementById('feedbackModal').style.display='block';
  }
  
  $scope.saveFeedback=function(){
	  var data=JSON.stringify({"bookingId":$scope.bookingId,"feedback":$scope.feedback});
	  $http.post(URI+"LetsGo/saveFeedback",data).then(function(response){
		  for(i=0;i<$scope.allBookings.length;i++){
			  if($scope.allBookings[i].bookingId==$scope.bookingId){
				  $scope.allBookings[i].feedback=$scope.feedback;
				  break;
			  }
		  }
		  for(i=0;i<$scope.bookings.length;i++){
			  if($scope.bookings[i].bookingId==$scope.bookingId){
				  $scope.bookings[i].feedback=$scope.feedback;
				  break;
			  }
		  }
		  document.getElementById('feedbackModal').style.display='none';
		  swal({
			  title: response.data.message,
			  icon: "success"
		  });
	  },function(response){
		  swal({
			  title:response.data.message,
			  icon: "error"
		  });
	  });
  }
  
  $scope.showFeedback=function(input){
	  swal({
		  title: $scope.bookings[input].feedback
	  });
  }
  
  $scope.cancelBooking=function(input){
	  $scope.bookingId=$scope.bookings[input].bookingId;
	  swal({
		  title: "Are you sure you wanna cancel this booking?",
			icon: "warning",
			buttons: true,
			dangerMode: true,
		})
		.then((willPay) => {
			if (willPay) {
				var data=JSON.stringify({"bookingId":$scope.bookingId});
				$http.post(URI+"LetsGo/cancel",data).then(function(response){
					swal({
						title: "Booking cancelled successfully",
						icon: "success"
					});
					$scope.bookings.splice(input,1);
					for(i=0;i<$scope.allBookings.length;i++){
						if($scope.allBookings[i].bookingId==$scope.bookingId){
							$scope.allBookings.splice(i,1);
							break;
						}
					}
				},function(response){
					swal({
						title: response.data.message,
						icon: "error"
					});
				});
		} else {
			swal({
				title: "Good decision",
				icon: "success"
			});
			}
	});
  }
});
