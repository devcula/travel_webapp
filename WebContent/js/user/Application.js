var app=angular.module("Application",['ngCookies','ngRoute','ngAnimate']);
var URI=getURI();

app.factory("UserService",function($cookies,$window,$http){
	var factory={};
	factory.emailId=null;
	factory.userName=null;
	factory.tassss=null;
	factory.loggedIn=null;
	factory.cities=null;
	factory.rememberMe=null;
	factory.passengerArray=null;
	factory.bookingObject=null;
	factory.bookingType=null;
	
	factory.hotelSuggestionDetails=null;
	factory.checkForHotels=null;
	factory.verifyLogin = function(){
		factory.emailId=$cookies.get('_email');
		factory.userName=$cookies.get('_name');
		factory.tassss=$cookies.get('_tassss');
		if(factory.emailId && factory.userName && factory.tassss){
			factory.loggedIn=true;
			var data={"emailId":factory.emailId,"name":factory.userName,"hash":factory.tassss};
			data=JSON.stringify(data);
			$http.post(URI+"LetsGo/authenticate",data).then(function(response){
				if(response.data.message==factory.tassss){
					factory.loggedIn=true;
					factory.userName=response.data.name;
				}
				else{
					factory.loggedIn=false;
					factory.logout();
				}
				if(factory.cities==null){
					$http.get(URI+"City/getCities").then(function(response){
						factory.cities=response.data;
						console.log(factory.cities);
					},function(response){
						/*document.getElementById('navBar').style.display='none';
						$window.location.href="#/maintain";
						swal({
							title: "Website under maintenance",
							text: "Try again later",
							icon: "warning"
						});*/
						console.log("fetching cities failed. Will try again during booking page load");
					});
				}
			},function(response){
				factory.loggedIn=false;
				factory.logout();
				if(factory.cities==null){
					$http.get(URI+"City/getCities").then(function(response){
						factory.cities=response.data;
						console.log(factory.cities);
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
			});
		}
		else{
			if(factory.cities==null){
				$http.get(URI+"City/getCities").then(function(response){
					factory.cities=response.data;
					console.log(factory.cities);
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
	}
	
	//factory.checkHotelBookings=function(data){
		
	//}
	
	factory.login=function(obj){
		if(factory.rememberMe=='Y'){
			var expiry=new Date(Date.now()+1000*60*60*24*7);
			$cookies.put('_email',obj.emailId,{expires:expiry});
			$cookies.put('_name',obj.name,{expires:expiry});
			$cookies.put('_tassss',obj.hash,{expires:expiry});
		}
		else{
			$cookies.put('_email',obj.emailId);
			$cookies.put('_name',obj.name);
			$cookies.put('_tassss',obj.hash);
		}
		
	}

	factory.logout=function(){
		$cookies.remove('_email');
		$cookies.remove('_name');
		$cookies.remove('_tassss');
		$window.location.href="#/index";
		$window.location.reload();
	}

	return factory;
});

app.config(['$locationProvider', function($locationProvider) {
	  $locationProvider.hashPrefix('');
	}]);

app.config(["$routeProvider",function($routeProvider){
	$routeProvider.when("#/index",{
		templateUrl: "index.html"
	}).when("/flight",{
		templateUrl: "partials/flight.html",
		controller:  "FlightController"
	}).when("/login",{
		templateUrl: "partials/login.html",
		controller: "LoginController"
	}).when("/signup",{
		templateUrl: "partials/signup.html",
		controller: "SignUpController"
	}).when("/train",{
		templateUrl: "partials/train.html",
		controller: "TrainController"
	}).when("/bus",{
		templateUrl: "partials/bus.html",
		controller: "BusController"
	}).when("/cab",{
		templateUrl: "partials/cab.html",
		controller: "CabController"
	}).when("/hotel",{
		templateUrl: "partials/hotel.html",
		controller: "HotelController"
	}).when("/profile",{
		templateUrl: "partials/profile.html",
		controller: "ProfileController"
	}).when("/fbd",{
		templateUrl: "partials/flightBookingDetails.html",
		controller: "BookingController"
	}).when("/tbd",{
		templateUrl: "partials/trainBookingDetails.html",
		controller: "BookingController"
	}).when("/bbd",{
		templateUrl: "partials/busBookingDetails.html",
		controller: "BookingController"
	}).when("/cbd",{
		templateUrl: "partials/cabBookingDetails.html",
		controller: "BookingController"
	}).when("/hbd",{
		templateUrl: "partials/hotelBookingDetails.html",
		controller: "BookingController"
	}).when("/maintain",{
		templateUrl: "partials/maintenance.html",
		controller: "IndexController"
	}).when("/bookings",{
		templateUrl: "partials/myBookings.html",
		controller: "BookingController"
	}).when("/payment",{
		templateUrl: "partials/payment.html",
		controller: "PaymentController"
	}).when("/terms",{
		templateUrl: "partials/termsofuse.html",
		controller: "PaymentController"
	}).when("/maps",{
		templateUrl: "partials/maps.html",
	}).when("/privacy",{
		templateUrl: "partials/privacy.html",
		}).when("/team",{
			templateUrl: "partials/team.html",
	}).when("/privacy",{
		templateUrl: "partials/privacy.html",
	}).when("/privacy",{
		templateUrl: "partials/privacy.html",
	}).when("/termsOfUse",{
		templateUrl: "partials/termsofuse.html",
	}).when("/aboutUs",{
		templateUrl: "partials/aboutus.html",
	}).when("/team",{
		templateUrl: "partials/team.html",
	}).otherwise({
		redirectTo: "/",
		templateUrl: "partials/default.html",
		controller: "IndexController"
	});
}]);
