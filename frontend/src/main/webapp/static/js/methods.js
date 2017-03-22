/* Common CRUD */
var prefix = '/api/';
var get = function (urlPostfix) {
    var data = null;
    $.ajax({
        type: 'GET',
        url: prefix + urlPostfix,
        success: function (response) {
            data = response;
        },
        error: function (error) {
            console.log('Error: ' + error);
        }
    });
    return data;
};

var post = function (urlPostfix, data) {
    $.ajax({
        type: 'POST',
        url: prefix + urlPostfix,
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

var put = function (urlPostfix, data) {
    $.ajax({
        type: 'PUT',
        url: prefix + urlPostfix,
        data: data,
        error: function (error) {
            console.log('Error: ' + error);
        }
    });
};


var getUsers = function () {
    return get('users');
};

var getUser = function (userId) {
    return get('users/' + userId);
};

