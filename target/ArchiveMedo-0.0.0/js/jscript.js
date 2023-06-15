/*Авторизация, отправка данных формы авторизации через ajax*/
/*$(document).ready(function(){
    $('#sending-form-singin').click(function(){
        var username=$('#username').val();
        var password=$('#password').val();
        $.ajax({
            type:'POST',
            data:{username:username},
            url:'mainClass',
            success: function(result){
                //поля формы не заполнены
                if($("#username").empty() || $("#password").empty()){
                    $('#info-ajax-msg-sign-in').html("Заполните логин и пароль");
                    $('#info-ajax-msg-sign-in').css({"display":"block"});
                }else{
                    console.log(result);
                    $('#info-ajax-msg-sign-in').html(result);
                    $('#info-ajax-msg-sign-in').css({"display":"block"});
                } 
//                console.log("result");
//                $('#info-ajax-msg-sign-in').html(result);
//                $('#info-ajax-msg-sign-in').css({"display":"block"});
            }
        });
    });
 });*/
/*$(document).ready(function(){
    $("#sending-form-singin").click(function(){
        var data = {};
        data = {"username":$("#username").val(), "password":$("#pwd").val()};
        //
        $.ajax
        ({
            type: "POST",//Метод передачи
            data: data,//Передаваемые данные в JSON - формате
            url: 'SignIn',//Название сервлета
            success:function(serverData)//Если запрос удачен
            {
                //тут и в сервлете стили должны быть
                //$("#info-ajax-msg-sign-in").css({"display":"block"});
                $("#info-ajax-msg-sign-in").html(serverData.serverInfo);
            },
            error: function(e)//Если запрос не удачен
            {
                //$("#info-ajax-msg-sign-in").css({"display":"block"});
                $("#info-ajax-msg-sign-in").html("Ошибка при отправки запроса");
            }
        });
    });
});*/