/*

 */
$(function () {
    var d0 = {
        "userCode": "В",
        "date": "08.04.2017",
        "account": "в.тинькофф",
        "category": "продукты",
        "amount": "10.00",
        "description": "в магните"
    };
    $('#btn1').click(saveData);
    $('#btn2').click(loadData);
    $('#btn3').click(printData);

    function printData() {
        var json = JSON.stringify(d0);
        console.log(json);
    }

    function loadData() {
        $('#frm1 input').each(function (a, b) {
            b.value = d0[b.id];
        });
    }

    function saveData() {
        $('#frm1 input').each(function (a, b) {
            d0[b.id] = b.value;
        });
    }
    loadData();
});