/* Common CRUD */
var urlPrefix = '/api/';
var ajaxGet = function (urlPostfix) {
    var data = null;
    $.ajax({
        type: 'GET',
        url: urlPrefix + urlPostfix,
        success: function (response) {
            data = response;
        },
        error: function (error) {
            console.log('Error: ' + error);
        }
    });
    return data;
};

var ajaxPost = function (urlPostfix, data) {
    $.ajax({
        type: 'POST',
        url: urlPrefix + urlPostfix,
        data: data,
        success: function (response) {
            data = response;
        },
        error: function (error) {
            console.log('Error: ' + error);
        }
    });
    return data;
};

var ajaxPut = function (urlPostfix, data) {
    $.ajax({
        type: 'PUT',
        url: urlPrefix + urlPostfix,
        data: data,
        error: function (error) {
            console.log('Error: ' + error);
        }
    });
};

var ajaxDelete = function (urlPostfix) {
    $.ajax({
        type: 'DELETE',
        url: urlPrefix + urlPostfix,
        error: function (error) {
            console.log('Error: ' + error);
        }
    });
};

/* User CRUD */
var getUsers = function () {
    return get('users');
};

var getUser = function (userId) {
    return get('users/' + userId);
};

var postUser = function (user) {
    user = ajaxPost('users', user);
    return user;
};

var putUser = function (user) {
    ajaxPut('users/' + user.id, user);
};

var deleteUser = function (userId) {
    ajaxDelete('users/' + userId);
};

