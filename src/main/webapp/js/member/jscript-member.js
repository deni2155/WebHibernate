/*
 * 
 * выводит идентификатор записи в модальное окно для редактирования участников МЭДО
 * @returns {id участника в системе}
 * 
 */
$(function(){
  $('.href-window-for-update-member').click(function(){
    //$('#selector-for-member-update').text($(this).data('value'));
    $('#selector-for-member-update').val($(this).data('value'));
  });
});