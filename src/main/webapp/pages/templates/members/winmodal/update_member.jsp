<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--
Модальное окно для редактирования участника МЭДО
-->
<div class="modal fade" id="update-medo-participant" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="updateMedoParticipantLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <form id="form-update-member">
                <div class="modal-header">
                    <h5 class="modal-title">Изменение участника МЭДО</h5>
                    <button type="button" onclick="location.reload(); return false;" class="btn-close" data-bs-dismiss="modal" aria-label="Закрыть"></button>
                </div>
                <div class="modal-body">
                    <input type="hidden" class="id-member-update-id-value" name="idUpdateMember" id="selector-for-member-id-update"/>
                    <label for="nameUpdateOrgMemberListGuides" class="form-label">Наименование организации</label>
                    <input type="text" class="form-control valid-input-type name-update-org-member-value" name="nameOrgUpdateMember" id="selector-for-member-name-update" placeholder=" " pattern="^[А-Яа-яЁё\s\(\)«»,]+$" required/>
                    <label for="emailUpdateMemberListGuides" class="form-label">Адресат</label>
                    <input type="text" class="form-control valid-input-type email-org-update-value" name="emailUpdateMember" id="selector-for-member-email-update" placeholder=" " pattern="^[A-Z~_\-]+$" required/>
                    <label for="guidUpdateMemberListGuides" class="form-label">Уникальный идентификатор участника</label>
                    <input type="text" class="form-control valid-input-type" name="guidUpdateMember" id="selector-for-member-guid-update" placeholder=" " pattern="[a-z0-9\-]+" required/>
                </div>
                <!--Сообщение после изменения участника МЭДО-->
                <div id="message-for-update-proccess" class="text-center pb-3"></div>
                <div class="modal-footer">
                    <button type="button" onclick="location.reload(); return false;" class="btn btn-secondary" data-bs-dismiss="modal">Закрыть</button>
                    <button type="submit" class="btn btn-custom">Сохранить</button>
                </div>
            </form>
        </div>
    </div>
</div>