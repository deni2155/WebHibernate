/*
 *Работает с перезагрузкой страницы 
 */
/*$('#addMember_click').submit(function(){
 //Чтобы заблокировать отправку и перезагрузку страницы, функция возвращает false
 $.post(
 'addMemberServlet',
 {
 nameOrgAddMember:$('#nameOrgAddMember').val(),
 emailAddMemberList:$('#emailAddMemberList').val(),
 guidAddMember:$('#guidAddMember').val()
 }
 );
 return false;
 });*/
$(document).ready(function(){
    $('#formAddMember').submit(function(event){
        //event.preventDefault();
        $.ajax({
            type: 'POST',
            url: 'addMemberServlet',
            data: $('#formAddMember').serialize(),
            success: function(response){
                console.log(response);
                //очищаем поля формы
                $('#formAddMember')[0].reset();
                $('#message-for-proccess').html(response);
            },
            error: function(){
                console.log("Ошибка");
                $('#message-for-proccess').css('color', 'red');
                $('#message-for-proccess').html("При добавлении участника возникла ошибка в jquery");
            }
        });
        return false;
    });
});