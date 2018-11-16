Application.$controller("ChangePasswordPageController", ["$scope", function($scope) {
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


    $scope.confirmPasswordBlur = function($event, $isolateScope) {
        if ($scope.Widgets.newPassword.datavalue === $scope.Widgets.confirmPassword.datavalue) {
            // $scope.Variables.md5InsertJava.invoke();
            // $scope.Widgets.password.datavalue = $scope.Variables.md5InsertJava.dataSet.value;
            $scope.Widgets.label4_1.caption = "Password must contain at least 1 uppercase and lowercase alphabet and 1 number"
        } else {
            $scope.Widgets.label4_1.caption = "Error Occured - Password, Confirm Password don't match";
        }
    };


    $scope.button1Tap = function($event, $isolateScope) {
        // $scope.Variables.MD5ChangePassword.invoke();
        // $data.password = $scope.Variables.MD5ChangePassword.dataSet.value;
        $scope.Variables.UpdatePWD.setInput("email", $scope.Widgets.text1.datavalue);
        $scope.Variables.UpdatePWD.setInput("newpassword", $scope.Variables.MD5Encryptpassword.dataSet.value);
        $scope.Variables.UpdatePWD.update();
    };


    $scope.oldPasswordBlur = function($event, $isolateScope) {
        $scope.Widgets.label7.caption = $scope.Variables.getOldPassword_var.dataSet[$i].password;
        if ($scope.Widgets.oldPassword.datavalue === $scope.Variables.getOldPassword_var.dataSet[$i].password) {
            $scope.Widgets.label4_1.caption = "Password must contain at least 1 uppercase and lowercase alphabet and 1 number";
        } else {
            $scope.Widgets.label4_1.caption = "Error Occured - Old password is incorrect";
        }

    };

}]);