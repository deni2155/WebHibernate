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
    $('#form-add-member').submit(function (event) {
        //event.preventDefault();
        $.ajax({
            type: 'POST',
            url: 'addMemberServlet',
            data: $('#form-add-member').serialize(),
            success: function (response) {
//                console.log(response);
                //очищаем поля формы
                $('#form-add-member')[0].reset();
                //отправляем ответ на страницу, ответ состоит из двух объектов - цвет текста и сообщение
                var responses = response.split("\n");
                var response1 = responses[0];
                var response2 = responses[1];
                $('#message-for-add-proccess').css("color", response1);
                $('#message-for-add-proccess').html(response2);
            },
            error: function () {
//                console.log("Ошибка");
                $('#message-for-add-proccess').css('color', 'red');
                $('#message-for-add-proccess').html("При добавлении участника возникла ошибка в jquery");
            }
        });
        return false;
    });

    $('#form-update-member').submit(function (event) {
        //event.preventDefault();
        $.ajax({
            type: 'POST',
            url: 'updateMemberServlet',
            data: $('#form-update-member').serialize(),
            success: function (response) {
                console.log(response);
//                //отправляем ответ на страницу, ответ состоит из двух объектов - цвет текста и сообщение
                var responses = response.split("\n");
                var response1 = responses[0];
                var response2 = responses[1];
                $('#message-for-update-proccess').css("color", response1);
                $('#message-for-update-proccess').html(response2);
            },
            error: function () {
                console.log("Ошибка");
                $('#message-for-update-proccess').css('color', 'red');
                $('#message-for-update-proccess').html("При изменнии участника возникла ошибка в jquery");
            }
        });
        return false;
    });
});
///*
// * Изменение участника МЭДО
// */
//$(document).ready(function () {
//
//});