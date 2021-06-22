var main = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });
        $('#btn-signup').on('click', function () {
            _this.signup();
        });
        $('#btn-signin').on('click', function () {
            _this.signin();
        });
        $('#btn-cancel').on('click', function () {
            _this.cancel();
        });

    },
    save : function () {
        var data = {
            hpNumber: $('#hpNumber').val(),
            dong: $('#dong').val(),
            ho: $('#ho').val(),
            moveDate: $('#moveDate').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/reservations',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('예약처리가 완료되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    signin : function () {
         var data = {
             hpNumber: $('#hpNumber').val(),
             password: $('#password').val(),
         };

         $.ajax({
             type: 'POST',
             url: '/api/v1/user/signin',
             dataType: 'json',
             contentType:'application/json; charset=utf-8',
             data: JSON.stringify(data)
         }).done(function() {
             alert('로그인이 완료되었습니다.');
             window.location.href = '/';
         }).fail(function (error) {
             alert(JSON.stringify(error));
         });
    },
    signup : function () {
        var data = {
            hpNumber: $('#hpNumber').val(),
            dong: $('#dong').val(),
            ho: $('#ho').val(),
            password: $('#password').val(),
            role: $('#role').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/user',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('회원가입처리가 완료되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    cancel : function () {
        var data = {
            cancelReason: $('#cancelReason').val()
        };

        var id = $('#reservationId').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/reservations/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('예약이 취소되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

};

main.init();