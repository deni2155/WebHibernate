$(document).ready(function () {
    //меняет текст input type text на странице с формой загрузки файла на странице pages/guides/downloadGuides.jsp
    $("#file-input-guides").change(function () {
        var filename = $(this).val().replace(/.*\\/, "");
        $("#file-name-guides").val(filename);
    });
/*
    // Обработчик события input для сохранения значения поля название организации
    $('#name-org').change(function () {
        localStorage.setItem('valueNameOrg', $('#name-org').val());//загружаю полученное значение в локальное хранилище
    });

    // Восстановление значений поля название организации после загрузки страницы
    $('#name-org').each(function () {
        var savedValue = localStorage.getItem('valueNameOrg');
        if (savedValue) {
            $('#name-org').val(savedValue);
        }
    });
    */
});

/*
 * 
 * копирует идентификатор записи, наименование участника, email участника, GUID участника в модальное окно для редактирования участников МЭДО
 * @returns {id участника в системе}
 * 
 */
$(function () {
    $('.href-window-for-update-member').click(function () {
        //в data('update-id-value') указан объект data-update-id-value
        $('#selector-for-member-id-update').val($(this).data('update-id-value'));
        //в data('update-name-org') указан объект data-update-name-org
        $('#selector-for-member-name-update').val($(this).data('update-name-org'));
        //в data('update-email-org') указан объект data-update-email-org
        $('#selector-for-member-email-update').val($(this).data('update-email-org'));
        //в data('update-guid-org') указан объект data-update-guid-org
        $('#selector-for-member-guid-update').val($(this).data('update-guid-org'));
    });
});

/*
 * копирует наименование организации и её идентификатор в БД
 */
$(function () {
    $('.href-window-for-delete-member').click(function () {
        //в data('delete-name-org') указан объект data-delete-name-org
        $("#name-org-for-delete-proccess").text("\"" + $(this).data('delete-name-org') + "\"?");
        //в data('update-name-org') указан объект data-update-name-org
        $('#selector-for-member-id-delete').val($(this).data('delete-id-value'));
        $('#selector-for-name-member-delete').val($(this).data('delete-name-org'));
    });
});

