/*
 * 
 * меняет текст input type text на странице с формой загрузки файла на странице pages/guides/downloadGuides.jsp
 * 
 */
$(document).ready(function () {
    $("#file-input-guides").change(function () {
        var filename = $(this).val().replace(/.*\\/, "");
        $("#file-name-guides").val(filename);
    });
});

/*
 * 
 * копирует идентификатор записи в модальное окно для редактирования участников МЭДО
 * @returns {id участника в системе}
 * 
 */
$(function(){
  $('.href-window-for-update-member').click(function(){
    //$('#selector-for-member-update').text($(this).data('value'));
    $('#selector-for-member-id-update').val($(this).data('update-id-value'));
  });
});
/*
 * 
 * копирует наименование участника в модальное окно для редактирования участников МЭДО
 * @returns {наименование участника в системе}
 * 
 */
$(function(){
  $('.href-window-for-update-member').click(function(){
    //$('#selector-for-member-update').text($(this).data('value'));
    //в data('update-name-org') указан объект data-update-name-org
    $('#selector-for-member-name-update').val($(this).data('update-name-org'));
  });
});


/*
 * 
 * копирует email участника в модальное окно для редактирования участников МЭДО
 * @returns {наименование участника в системе}
 * 
 */
$(function(){
  $('.href-window-for-update-member').click(function(){
    //$('#selector-for-member-update').text($(this).data('value'));
    //в data('update-email-org') указан объект data-update-email-org
    $('#selector-for-member-email-update').val($(this).data('update-email-org'));
  });
});
/*
 * 
 * копирует GUID участника в модальное окно для редактирования участников МЭДО
 * @returns {наименование участника в системе}
 * 
 */
$(function(){
  $('.href-window-for-update-member').click(function(){
    //$('#selector-for-member-update').text($(this).data('value'));
    //в data('update-guid-org') указан объект data-update-guid-org
    $('#selector-for-member-guid-update').val($(this).data('update-guid-org'));
  });
});