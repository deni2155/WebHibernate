$(document).ready(function () {
    /*
     * Добавление участника МЭДО
     */
    $('#form-add-member').submit(function () {
        //очищаю div
        $('#message-for-add-proccess').html("");
        $.ajax({
            type: 'GET',
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
    /*
     * Изменение участника МЭДО
     */
    $('#form-update-member').submit(function () {
        //очищаю div
        $('#message-for-update-proccess').html("");
        $.ajax({
            type: 'GET',
            url: 'updateMemberServlet',
            data: $('#form-update-member').serialize(),
            success: function (response) {
                //console.log(response);

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

    /**
     * Удаление участника МЭДО
     */
    $('#form-delete-member').submit(function () {
        //очищаю div
        $('#message-for-delete-proccess').html("");
        $.ajax({
            type: 'GET',
            url: 'deleteMemberServlet',
            data: $('#form-delete-member').serialize(),
            success: function (response) {
                console.log(response);
//                //отправляем ответ на страницу, ответ состоит из двух объектов - цвет текста и сообщение
                var responses = response.split("\n");
                var response1 = responses[0];
                var response2 = responses[1];
                $('#message-for-delete-proccess').css("color", response1);
                $('#message-for-delete-proccess').html(response2);
            },
            error: function () {
                console.log("Ошибка");
                $('#message-for-delete-proccess').css('color', 'red');
                $('#message-for-delete-proccess').html("При удалении участника возникла ошибка в jquery");
            }
        });
        return false;
    });
});