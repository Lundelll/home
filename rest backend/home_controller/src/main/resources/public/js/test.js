function Test($scope, $http) {
	$http.get('http://localhost:8080/lamp/1').
		success(function(data) {
			console.log("does it work?");
			$scope.info = data;
		});
}