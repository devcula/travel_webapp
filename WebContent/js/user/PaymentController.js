app.controller("PaymentController",function($scope,$http,$window,UserService){
	
	$scope.paymentForm={
			amount:  null,
			cardNumber: null,
			cardType: null,
			cvv: null,
			cardholderName:null,
			cardExpiryDate:null
	};
	
	$scope.init=function(){
		if(UserService.bookingObject!=null){
			$scope.paymentForm.amount=UserService.bookingObject.price;
			$scope.bookingType=UserService.bookingType;
			$scope.paymentForm.cardType="Credit";
		}
		else{
			$window.location.href="#/";
		}
	}
	
	
	$scope.pay=function(){
		$scope.paymentForm.cardNumber=$scope.paymentForm.cardNumber.toString();
		$scope.paymentForm.cvv=$scope.paymentForm.cvv.toString();
		var data=JSON.stringify($scope.paymentForm);
		$http.post(URI+"Payment/pay",data).then(function(response){
			if(response.data.message=='success'){
				$scope.confirmBooking();
				swal({
					title: "Payment Successful",
					icon: "success"
				});
			}
		},function(response){
			swal({
				title: response.data.message,
				icon: "error"
			});
			$scope.message=response.data.message;
		});
	}
	
	$scope.confirmBooking=function(){
		var data={"bookingObject":angular.toJson(UserService.bookingObject),"passengerArray":angular.toJson(UserService.passengerArray),"bookingType":UserService.bookingType,"userEmailId":UserService.emailId};
		if($scope.bookingType=='flight'){
		      $http.post(URI+"Flight/book",data).then(function(response){
		        UserService.booking=response.data;
				$window.location.href="#/fbd";
				UserService.bookingObject=null;
		      },function(response){
					swal(response.data.message);
					swal({
							title: "Payment Refund initiated",
							icon: "warning",
						});
		      });
		    }
		if($scope.bookingType=='train'){
		      $http.post(URI+"Train/book",data).then(function(response){
		        UserService.booking=response.data;
				$window.location.href="#/tbd";
				UserService.bookingObject=null;
		      },function(response){
					swal(response.data.message);
					swal({
							title: "Payment Refund initiated",
							icon: "warning",
						});
		      });
		    }
		if(UserService.bookingType=='bus'){
		      $http.post(URI+"Bus/book",data).then(function(response){
		        UserService.booking=response.data;
				$window.location.href="#/bbd";
				UserService.bookingObject=null;
		      },function(response){
					swal(response.data.message);
					swal({
							title: "Payment Refund initiated",
							icon: "warning",
						});
		      });
		    }
		if(UserService.bookingType=='hotel'){
		      $http.post(URI+"Hotel/book",data).then(function(response){
		        UserService.booking=response.data;
				$window.location.href="#/hbd";
				UserService.bookingObject=null;
		      },function(response){
					swal(response.data.message);
					swal({
							title: "Payment Refund initiated",
							icon: "warning",
						});
		      });
		    }
		if(UserService.bookingType=='cab'){
			$http.post(URI+"Cab/book",data).then(function(response){
		        UserService.booking=response.data;
				$window.location.href="#/cbd";
				UserService.bookingObject=null;
		      },function(response){
					swal(response.data.message);
					swal({
							title: "Payment Refund initiated",
							icon: "warning",
						});
		      });
		}
	}
});