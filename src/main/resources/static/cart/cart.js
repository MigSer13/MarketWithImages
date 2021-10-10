angular.module('app').controller('cartController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189/summer';

    $scope.loadCart = function () {
        $http({
            url: contextPath + '/api/v1/cart/' + $localStorage.guestCartUuid,
            method: 'GET'
        }).then(function (response) {
            $scope.cart = response.data;
        });
    }

    $scope.addToCart = function (productId) {
        $http({
            url: contextPath + '/api/v1/cart/' + $localStorage.guestCartUuid + '/add/' + productId,
            method: 'GET'
        }).then(function (response) {
            $scope.loadCart();
        });
    }

    $scope.incrementCartPosition = function (productId) {
        $http({
            url: contextPath + '/api/v1/cart/' + $localStorage.guestCartUuid + '/add/' + productId,
            method: 'GET'
        }).then(function (response) {
            $scope.loadCart();
        });
    }

    $scope.decrementCartPosition = function (productId) {
        $http({
            url: contextPath + '/api/v1/cart/' + $localStorage.guestCartUuid + '/decrement/' + productId,
            method: 'GET'
        }).then(function (response) {
            $scope.loadCart();
        });
    }

    $scope.clearCart = function () {
        $http({
            url: contextPath + '/api/v1/cart/' + $localStorage.guestCartUuid + '/clear',
            method: 'GET'
        }).then(function (response) {
            $scope.cart = null;
        });
    }

    $scope.removeItemFromCart = function (productId) {
        $http({
            url: contextPath + '/api/v1/cart/' + $localStorage.guestCartUuid + '/remove/' + productId,
            method: 'GET'
        }).then(function (response) {
            $scope.loadCart();
        });
    }

    $scope.createOrder = function () {
        $http({
            url: contextPath + '/api/v1/orders',
            method: 'POST',
            params: {
                firstName: $scope.order_info.firstName,
                lastName: $scope.order_info.lastName,
                address1: $scope.order_info.address1,
                address2: $scope.order_info.address2,
                city:     $scope.order_info.city,
                country:  $scope.order_info.country,
                phone:    $scope.order_info.phone
            }
        }).then(function successCallback(response) {
            alert('Заказ создан');
            $scope.loadCart();
        }, function errorCallback(response) {
            alert(response.data.messages);
        });
    }

    $scope.loadCart();
    console.log("cart page loaded");
});