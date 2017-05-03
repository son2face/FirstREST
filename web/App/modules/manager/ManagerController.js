/**
 * Created by Son on 4/28/2017.
 */
angular.module('managerApp').controller('databaseController', function ($scope, $http, NgTableParams) {
    $scope.database = [];
    $scope.getData = function () {
        $http.get("http://skylab.uet.vnu.edu.vn:8080/api/v1/managers/database")
            .then(function (response) {
                $scope.database = response.data.data;
                $scope.xxx = new NgTableParams({}, {dataset: $scope.database});
                console.log($scope.database);
            });
    };
    $scope.delete = function (data) {
        var answer = confirm('Bạn có muốn xóa không?');
        if (answer) {
            $http.delete("http://skylab.uet.vnu.edu.vn:8080/api/v1/managers/database/" + data.id)
                .then(function (response) {
                    var index = $scope.database.indexOf(data);
                    if (index !== -1) {
                        $scope.database.splice(index, 1);
                    }
                    $scope.xxx = new NgTableParams({}, {dataset: $scope.database});
                });
        }
    };
    $scope.show = function (data) {
        $http.get("http://skylab.uet.vnu.edu.vn:8080/api/v1/managers/database")
            .then(function (response) {
                $scope.database = response.data.data;
                console.log($scope.database);
            });
    };
    $scope.edit = function (data) {
        $http.put("http://skylab.uet.vnu.edu.vn:8080/api/v1/managers/database" + data.id)
            .then(function (response) {
                $scope.database = response.data.data;
                console.log($scope.database);
            });
    };
    $scope.setDB = function (data) {
        $http.get("http://skylab.uet.vnu.edu.vn:8080/api/v1/managers/database/setdatabase/" + data.id)
            .then(function (response) {
                console.log("success");
            });
    };
    $scope.create = function () {
        var x = "url=" + $scope.url + "&username=" + $scope.user + "&password=" + $scope.pass + "&dbName=" + $scope.schema + "&type=" + $scope.type;
        $http.post("http://skylab.uet.vnu.edu.vn:8080/api/v1/managers/database", x, {headers: {'Content-Type': 'application/x-www-form-urlencoded'}})
            .then(function (response) {
                $scope.getData();
                console.log("success");
            });
    };
    $scope.getData();
});