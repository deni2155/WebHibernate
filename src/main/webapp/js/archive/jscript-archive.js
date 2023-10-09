$(document).ready(function () {
    $('#doc-in-out').change(function () {
//        var selectedValue = $('#doc-in-out').val();//получаю значение выпадающего списка (входящий или исходящий)
        localStorage.setItem('selectedValueInOutPkg', $('#doc-in-out').val());//загружаю полученное значение в локальное хранилище
    });
    $('#doc-in-out').each(function () {
        if (localStorage.getItem('selectedValueInOutPkg')) {
            //присваиваю выпадающему списку (входящий или исходящий) значение из локального хранилища
            $('#doc-in-out option[value=' + localStorage.getItem('selectedValueInOutPkg') + ']').prop('selected', true);
        }
    });
});