/*
 * 
 * меняет текст input type text на странице с формой загрузки файла на странице pages/guides/downloadGuides.jsp
 * */
/*
$('#nameOrgMemberListGuides').on('input', function() {
  // проверяем, содержит ли поле ввода меньше 4 символов
  if ($(this).val().length < 4) {
    // если да, то задаем соответствующее сообщение об ошибке
    this.setCustomValidity('Введите не менее 4 символов');
  } else {
    // иначе, очищаем сообщение об ошибке
    this.setCustomValidity('');
  }
});

/*
 * 
 * меняет текст input type text на странице с формой загрузки файла на странице pages/guides/downloadGuides.jsp
 * 
 */
$(document).ready(function () {
    $("#file-input-guides").change(function () {
        //var filename = 'Выбран файл:'+$(this).val().replace(/.*\\/, "") || 'Файл не выбран';
        //$("#file-name-guides1").html(filename);
        var filename = $(this).val().replace(/.*\\/, "");
        $("#file-name-guides").val(filename);
    });
});

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