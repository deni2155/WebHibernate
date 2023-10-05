//$(document).ready(function () {
//    $('#doc-in-out').change(function () {
//        var selectedValue = $('#doc-in-out').val();//получаю значение выпадающего списка (входящий или исходящий)
//        localStorage.setItem('selectedValueInOutPkg', selectedValue);//загружаю полученное значение в локальное хранилище
//        $.ajax({
//            url: 'paginationArchiveServlet',
//            type: 'GET',
//            data: {
//                selectedTypePkgValue: selectedValue
//            },
//            success: function (response) {
//                location.reload();//перезагружаем страницу, что бы обновить таблицу
//                var jsonArray=JSON.parse(response);
//                console.log(jsonArray);
////                localStorage.setItem('selectedValueInOutPkg', response);//загружаю полученное значение в локальное хранилище
//            },
//            error: function () {
//                console.log("Ошибка при получении списка документов");
//            }
//        });
//    });
//    //присваиваю выпадающему списку (входящий или исходящий) значение из локального хранилища
//    $('#doc-in-out option[value=' + localStorage.getItem('selectedValueInOutPkg') + ']').prop('selected', true);
//});