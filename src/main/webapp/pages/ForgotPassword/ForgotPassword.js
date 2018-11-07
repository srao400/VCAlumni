Application.$controller("ForgotPasswordPageController", ["$scope", function($scope) {
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

    $scope.emailaddressBlur = function($event, $isolateScope) {
        $scope.Widgets.label3.caption = $scope.Widgets.label5.caption.charAt(Math.floor(Math.random() * $scope.Widgets.label5.caption.length)) + Math.random().toString(36).slice(-7);

    };


    $scope.button3Tap = function($event, $isolateScope) {
        $scope.Variables.MD5Encryptpassword.invoke();
    };


    $scope.MD5EncryptpasswordonSuccess = function(variable, data, options) {
        $scope.Widgets.label4_1.caption = $scope.Variables.MD5Encryptpassword.dataSet.value;
    };

}]);