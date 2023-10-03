<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--
модальное окно для добавления нового участника МЭДО
-->
<div class="modal fade" id="add-medo-participant" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="addMedoParticipantLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <form id="form-add-member">
                <div class="modal-header">
                    <h5 class="modal-title">Добавление нового участника МЭДО</h5>
                    <button type="button" onclick="location.reload(); return false;" class="btn-close" data-bs-dismiss="modal" aria-label="Закрыть"></button>
                </div>
                <div class="modal-body">
                    <label for="nameAddOrgMemberListGuides" class="form-label">Наименование организации</label>
                    <input type="text" class="form-control valid-input-type" name="nameOrgAddMember" id="nameAddOrgMemberListGuides" placeholder=" " pattern="^[А-Яа-яЁё\s\(\)«»,]+$" required/>
                    <label for="emailAddMemberListGuides" class="form-label">Адресат</label>
                    <input type="text" class="form-control valid-input-type" name="emailAddMember" id="emailAddMemberListGuides" placeholder=" " pattern="^[A-Z~_\-]+$" required/>
                    <label for="guidAddMemberListGuides" class="form-label">Уникальный идентификатор участника</label>
                    <input type="text" class="form-control valid-input-type" name="guidAddMember" id="guidAddMemberListGuides" placeholder=" " pattern="[a-z0-9\-]+" required/>
                </div>
                <!--Сообщение после добавления участника МЭДО-->
                <div id="message-for-add-proccess" class="text-center pb-3"></div>
                <div class="modal-footer">
                    <button type="button" onclick="location.reload(); return false;" class="btn btn-secondary" data-bs-dismiss="modal">Закрыть</button>
                    <button type="submit" class="btn btn-custom">Сохранить</button>
                </div>
            </form>
        </div>
    </div>
</div>