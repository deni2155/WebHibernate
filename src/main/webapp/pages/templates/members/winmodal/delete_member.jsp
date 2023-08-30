<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--
Модальное окно для подтверждения удаления участника МЭДО
-->
<div class="modal fade" id="delete-medo-participant" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="deleteParticipantLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <form id="form-delete-member">
                <div class="modal-header">
                    <h5 class="modal-title">Удаление участника МЭДО</h5>
                    <button type="button" onclick="location.reload(); return false;" class="btn-close" data-bs-dismiss="modal" aria-label="Закрыть"></button>
                </div>
                <div class="modal-body">
                    <input type="hidden" class="id-member-delete-id-value" name="idDeleteMember" id="selector-for-member-id-delete"/>
                    <input type="hidden" class="member-delete-member-value" name="nameDeleteMember" id="selector-for-name-member-delete"/>
                    Вы действительно хотите удалить участника МЭДО
                    <div id="name-org-for-delete-proccess"></div>
                </div>
                <!--Сообщение после удаления участника МЭДО-->
                <div id="message-for-delete-proccess" class="text-center pb-3"></div>
                <div class="modal-footer">
                    <button type="button" onclick="location.reload(); return false;" class="btn btn-secondary" data-bs-dismiss="modal">Отмена</button>
                    <button type="summit" class="btn btn-custom">Oк</button>
                </div>
            </form>
        </div>
    </div>
</div>