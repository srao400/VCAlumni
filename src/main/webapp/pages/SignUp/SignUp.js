Application.$controller("SignUpPageController", ["$scope", function($scope) {
    "use strict";

    /* perform any action on widgets/variables within this block */
    $scope.onPageReady = function() {
        /*
         * variables can be accessed through '$scope.Variables' property here
         * e.g. to get dataSet in a staticVariable named 'loggedInUser' use following script
         * $scope.Variables.loggedInUser.getData()
         *
         * widgets can be accessed through '$scope.Widgets' property here
         * e.g. to get value of text widget named 'username' use following script
         * '$scope.Widgets.username.datavalue'
         */
    };


    $scope.UsersLiveForm1Beforeservicecall = function($event, $operation, $data, options) {
        $scope.Variables.md5InsertJava.invoke();
        $data.password = $scope.Variables.md5InsertJava.dataSet.value;
        $data.role = 'user';
    };

}]);


Application.$controller("UsersLiveForm1Controller", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.password1Blur = function($event, $isolateScope) {
            if ($scope.Widgets.password.datavalue === $scope.Widgets.password1.datavalue) {
                // $scope.Variables.md5InsertJava.invoke();
                // $scope.Widgets.password.datavalue = $scope.Variables.md5InsertJava.dataSet.value;
                $scope.Widgets.label1.caption = "Password must contain at least 1 uppercase and lowercase alphabet and 1 number"
            } else {
                $scope.Widgets.label1.caption = "Error Occured - Password, Confirm Password don't match";
            }
        };

    
        $scope.passwordBlur = function ($event, $isolateScope) { 

        };
 
    }
]);