<!--

Модальное окно

-->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="modal fade" id="modal-win-download-listguides" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="modal-title-text" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="modal-title-text">Загрузка файла со списком участников МЭДО</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <!--form action="" method="">
                <div class="modal-body text-center">
                    <input type="file" class="form-control-file" id="formFile" accept=".xlsx,.xls,.csv"/>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-custom col-12" data-bs-dismiss="modal">Загрузить</button>
                </div>
            </form-->
            <form action="downloadListMembers" method="post" enctype="multipart/form-data">
                <div class="modal-body text-center">
                    <input type="file" class="form-control-file" id="formFile" name="listMembers" accept=".xlsx,.xls,.csv"/>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-custom col-12" data-bs-dismiss="modal">Загрузить</button>
                </div>
            </form>
        </div>
    </div>
</div>
