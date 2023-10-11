$(document).ready(function () {
    /*
     * Записывает в локальное хранилище выбор пользователя типа документов для вывода и возвращает выбор пользователя после перезагрузки страницы
     */
    $('#doc-in-out').change(function () {
        localStorage.setItem('docSelectedValueInOutPkg', $('#doc-in-out').val());//загружаю полученное значение в локальное хранилище
    });
    $('#doc-in-out').each(function () {
        if (localStorage.getItem('docSelectedValueInOutPkg')) {
            //присваиваю выпадающему списку (входящий или исходящий) значение из локального хранилища
            $('#doc-in-out option[value=' + localStorage.getItem('docSelectedValueInOutPkg') + ']').prop('selected', true);
        }
    });
    /*
     * Записывает в локальное хранилище выбор пользователя типа уведомлений для вывода и возвращает выбор пользователя после перезагрузки страницы
     */
    $('#notif-in-out').change(function () {
        localStorage.setItem('notifSelectedValueInOutPkg', $('#notif-in-out').val());//загружаю полученное значение в локальное хранилище
    });
    $('#notif-in-out').each(function () {
        if (localStorage.getItem('notifSelectedValueInOutPkg')) {
            //присваиваю выпадающему списку (входящий или исходящий) значение из локального хранилища
            $('#notif-in-out option[value=' + localStorage.getItem('notifSelectedValueInOutPkg') + ']').prop('selected', true);
        }
    });
});