<!--

Модальное окно

-->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="modal fade" id="modal-win-download-listguides" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title fs-5" id="modal-title-text"">Загрузка файла со списком участников МЭДО</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Закрыть"></button>
            </div>
            <div class="modal-body">
                <div class="modal-body text-center">
                    <form id="uploadForm" enctype="multipart/form-data">
                        <input type="file" name="file" id="fileInput" />
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-custom" data-bs-dismiss="modal" id="uploadButton" onClick="uploadFile()">Загрузить</button>
                <button type="button" class="btn btn-secondary">Закрыть</button>
            </div>

            <!--form action="downloadListMembers" method="post" enctype="multipart/form-data">
                <div class="modal-body">
                    <div class="modal-body text-center">
                        <input type="file" class="form-control-file" id="formFile" name="listMembers" accept=".xlsx,.xls,.csv"/>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-custom" data-bs-dismiss="modal">Загрузить</button>
                    <button type="submit" class="btn btn-secondary">Закрыть</button>
                </div>
            </form-->
        </div>
    </div>
</div>
<!--div class="modal fade" id="modal-win-download-listguides" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="modal-title-text" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="modal-title-text">Загрузка файла со списком участников МЭДО</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
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
</div-->
