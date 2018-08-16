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

function checkTraderName() {
    if ($.trim($('#traderName').val()).length == 0) {
        $('#nameError').html('No name input');
        $('#traderName').focus();
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

function checkSelectSide(side){
    if(side == "-") {
        $("#sideError").html("select a side");
        return false;
    }
    else{
        $("#sideError").html(" ");
        return true;
    }
}

function checkQty(QTY) {
    if(parseInt(QTY) <= 0 || QTY == "") {
        $("#qtyError").html("input a qty");
        return false;
    }
    $("#qtyError").html(" ");
    return true;
}

function checkPrice(price) {
    if(parseFloat(price) <= 0 || price == "") {
        $("#priceError").html("input a price");
        return false;
    }
    $("#priceError").html(" ");
    return true;
}

function checkOtherPrice(otherPrice) {
    if(parseFloat(otherPrice) <= 0 || otherPrice == "") {
        $("#otherPriceError").html("input a price");
        return false;
    }
    $("#otherPriceError").html(" ");
    return true;
}

function checkFok(FOK) {
    if(FOK == "-"){
        $("#fokError").html("select a FOK");
        return false;
    }
    $("#fokError").html(" ");
    return true;
}

function checkCondition(condition) {
    if(condition == "-") {
        $("#conditionError").html("select a condition");
        return false;
    }
    $("#conditionError").html(" ");
    return true;
}

function checkOrderInformation(){
    var symbol = $("#symbol").val();
    var side = $("#side").val();
    var FOK = $("#fok").val();
    var price = $("#price").val();
    var condition = $("#cond").val();
    var QTY = $("#qty").val()
    var otherPrice = $("#othePrice").val();
    // console.log(symbol + side + FOK + price + condition + QTY);
    if(checkSelectSymbol(symbol) == false) {
        return false;
    }
    if(checkSelectSide(side) ==  false) {
        return false;
    }
    if(checkQty(QTY) == false) {
        return false;
    }
    if(checkPrice(price) == false) {
        return false;
    }
    if(checkOtherPrice(otherPrice) == false) {
    	return false;
    }
    if(checkFok(FOK) == false) {
        return false;
    }
    if(checkCondition(condition) == false) {
        return false;
    }
}