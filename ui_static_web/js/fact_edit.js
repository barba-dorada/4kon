var d0={"un2":"В","date":"08.04.2017","account":"в.тинькофф","category":"продукты","amount":"10.00","description":"в магните"};
function loadData() {
    $('#frm1 input').each(function (a,b){
        b.value=d0[b.id];
    });
}
$(function () {
    console.log('dd123');
    $('#btn1').click(function () {
        console.log('@@@');
        var o={};
        $('#frm1 input').each(function (a,b) {
            o[b.id]=b.value;
        });

        var json=JSON.stringify(o);
        console.log(json);
    });
    $('#btn2').click(loadData);
})