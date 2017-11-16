/**
 * Created by zhang on 2017/3/15.
 */
function getContextPath() {
    var host = window.location.host;
    var pathname = window.location.pathname;
    var contextpath = "http://" + host + pathname.match(/\/\w*\//)[0];
    return contextpath.trim();
}
function modals(options,callback) {

    console.log(options);
    var id=options.id != undefined ? options.id : "myModal";
    var width=options.width != undefined ? options.width : 600;
    var title = options.title != undefined ? options.title : "查看";
    var body = options.body != undefined ? options.body : "内容";
    var btntext = options.btntext != undefined ? options.btntext : "提交";
    var needSubmitBtn = options.needSubmitBtn != undefined ? options.needSubmitBtn : false;
    var nobgExit = options.nobgExit != undefined ? options.nobgExit : false;

    function destory() {
        setTimeout(
            function () {
                $('#'+id).remove();
            }, 600
        )
    }
    if($('#'+id))
    $('#'+id).remove();
    var html = $('<!-- 模态框（Modal） -->\
        <div class="modal fade bs-example-modal-lg"'+id+' id="'+id+'" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">\
        <div class="modal-dialog" style="width:'+width+'px">\
        <div class="modal-content">\
        <div class="modal-header">\
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>\
    <h4 class="modal-title" id="myModalLabel">' + title + '</h4></div>\
    <div class="modal-body">' + body + '</div>\
        <div class="modal-footer">\
        <button type="button" class="btn btn-default" id="exit" data-dismiss="modal">关闭</button>\
        </div></div></div><!-- /.modal -->');
    $('body').append(html);
    if (needSubmitBtn){
        html.find('#exit').after($('<button id="submitBtn" data-dismiss="modal" type="button" class="btn btn-primary">' + btntext + '</button>'));
        $('body').on("click", "#submitBtn", function () {
            callback();
        });
    }

    // $('body').on("click", "#exit", function () {
    //     destory();
    // });
    if (nobgExit)
        $("#"+id).modal({"backdrop": "static", "keyboard": false});
    else
        $("#"+id).modal({"keyboard": false});

}
function FormToJSON(form) {
    var a = $(form);
    var jsonArray = a.serializeArray();
    var jsondata = {};
    for (var i = 0; i < jsonArray.length; i++) {
        var temp = jsonArray[i];
        var name = temp.name;
        if (temp.value != "") {
            jsondata[temp.name] = temp.value;
        }
    }
    return jsondata;
}
function checkUser(level) {
    var userid=$.session.get("userid");
    var role=$.session.get("role");
    if(userid==undefined || role==undefined){
        window.location.href="../html/login.html";
    }
    if(level!=role){
        window.location.href="../html/login.html";
    }
}
function getInfo(options) {
    var jsondata=options.data;

}
function postInfo(data,url) {

    $.ajax({
        type:"GET",
        url:url,
        dataType:"json",
        data: JSON.stringify(data),
        success:function () {

        }
    })

}
function saveForm(a,url) {
    var jsondata=FormToJSON(a);
    

}
function requestParam(param) {
    var reg=new RegExp("(^|&)"+param+"=([^&]*)(&|$)","i");
    var r=window.location.search.substr(1).match(reg);
    if(r !=null)return r[2];
    else return null;
}

