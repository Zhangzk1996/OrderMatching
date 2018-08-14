function isPhoneNum(phone) {
    var pattern = /^1[34578]\d{9}$/;
    return pattern.test(phone);
}

function isEmail(email) {
    var pattern = /^[0-9A-Za-z][\.-_0-9A-Za-z]*@[0-9A-Za-z]+(\.[0-9A-Za-z]+)+$/;
    return pattern.test(email);
}

function checkPassword() {
    if($.trim($('#password').val()).length == 0) {
        $("#passwordError").html("Please Inter you password");
        $("#password").focus();
        return false;
    }else {
        $("#passwordError").html("");
        var rePass = $.trim($("rePassword").val());
        var pass = $.trim($("#password").val());
        if(rePass.length != 0) {
            if(rePass != pass) {
                $("#rePasswordError").html("Different Password");
                return false;
            }
            else{
                $("rePasswordError").html("");
                return true;
            }
        }
    }
}

function checkRepassword() {
    var rePass = $.trim($("#rePassword").val());
    if(rePass.length == 0){
        $("#rePasswordError").html("No password");
        $("#rePassword").focus();
        return false;
    }
    else {
        $("rePasswordError").html("");
        var pass = $.trim($("#password").val());
        if(pass.length == 0) {
            $("#rePassword").val("");
            $("#rePasswordError").html("No password");
            $("#rePassword").focus();
            return false;
        }
        else if(rePass != pass) {
            $("#rePasswordError").html("Different Password");
            return false;
        }
        else {
            $("#rePasswordError").html("");
            return true;
        }
    }
}

function checkEmail(myEmail) {
    if ($.trim($('#email').val()).length == 0) {
        $('#emailError').html('No Email');
        $("email").focus();
        return false;
    }
    else {
        $('#emailError').html('');
        if(isEmail(myEmail) == false) {
            $('#emailError').html('Worry Email format');
            $('#email').focus();
            return false;
        }
        else {
            $('#emailError').html('');
            return true;
        }
    }
}

function checkName() {
    if ($.trim($('#name').val()).length == 0) {
        $('#nameError').html('No name input');
        $('#name').focus();
        return false;
    }
    else {
        $('#nameError').html('');
        return true;
    }
}

function checkRegister() {
    if(checkEmail(myEmail) == false ) {
        return false;
    }
    if(checkPassword() == false) {
        return false;
    }
    if(checkRepassword() == false) {
        return false;
    }
    if(checkName() == false) {
        return false;
    }
}