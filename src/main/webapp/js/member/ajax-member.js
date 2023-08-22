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
/*
 * Добавление участника МЭДО
 */
$(document).ready(function () {
    $('#formAddMember').submit(function (event) {
        //event.preventDefault();
        $.ajax({
            type: 'POST',
            url: 'addMemberServlet',
            data: $('#formAddMember').serialize(),
            success: function (response) {
                console.log(response);
                //очищаем поля формы
                $('#formAddMember')[0].reset();
                //отправляем ответ на страницу, ответ состоит из двух объектов - цвет текста и сообщение
                var responses = response.split("\n");
                var response1 = responses[0];
                var response2 = responses[1];
                $('#message-for-add-proccess').css("color", response1);
                $('#message-for-add-proccess').html(response2);
            },
            error: function () {
                console.log("Ошибка");
                $('#message-for-add-proccess').css('color', 'red');
                $('#message-for-add-proccess').html("При добавлении участника возникла ошибка в jquery");
            }
        });
        return false;
    });
});