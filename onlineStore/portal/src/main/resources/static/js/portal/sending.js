$(document).ready(function() {
    var t = {
        depositList: function(e, r) {
            var o = t.depositColumns();
            $(e).bootstrapTable("destroy"),
                $(e).bootstrapTable({
                    url: localStorage.url + "/sending/deposit",
                    method: "GET",
                    cache: !1,
                    pagination: !0,
                    sidePagination: "server",
                    pageNumber: 1,
                    ajaxOptions: {
                        xhrFields: {
                            withCredentials: !0
                        },
                        crossDomain: !0,
                        async: !1
                    },
                    responseHandler: function(t) {
                        return t.data
                    },
                    contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                    queryParamsType: "undefined",
                    queryParams: function(t) {
                        o = {
                            pageNumber: t.pageNumber,
                            pageSize: t.pageSize,
                            userId: localStorage.userId
                        }
                        return o
                    },
                    formatLoadingMessage: function() {
                        return "请稍等，正在加载中..."
                    },
                    columns: o
                })
        },

        sendingList: function(e, r) {
            var o = t.sendingListColumns();
            $(e).bootstrapTable("destroy"),
                $(e).bootstrapTable({
                    url: localStorage.url + "/sending/list",
                    method: "GET",
                    cache: !1,
                    pagination: !0,
                    sidePagination: "server",
                    pageNumber: 1,
                    ajaxOptions: {
                        xhrFields: {
                            withCredentials: !0
                        },
                        crossDomain: !0,
                        async: !1
                    },
                    responseHandler: function(t) {
                        return t.data
                    },
                    contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                    queryParamsType: "undefined",
                    queryParams: function(t) {
                        o = {
                            pageNumber: t.pageNumber,
                            pageSize: t.pageSize,
                            userId: localStorage.userId
                        }
                        return o
                    },
                    formatLoadingMessage: function() {
                        return "请稍等，正在加载中..."
                    },
                    columns: o
                })
        },

        depositColumns: function() {
            return [{
                title: "用户ID",
                field: "userId"
            },
                {
                    title: "商品ID",
                    field: "itemId"
                },
                {
                    title: "数量",
                    field: "num"
                }
                , {
                    title: "操作",
                    formatter: function(t, e, n) {
                        return '<button id="addSendingBtn" data-depositNum = "' + e.num + '"data-itemId="' + e.itemId + '" data-toggle="modal" data-target="#sAddBackBacketModel" style="background:#7CA7D1;">操作</button>'
                    }
                }

            ]

        },
        sendingListColumns : function(){
            return [
                {
                title: "配送单ID",
                field: "sendingId"
                 },
                {
                    title: "商品ID",
                    field: "itemId"
                },
                {
                    title: "地址",
                    field: "address"
                },
                {
                    title: "数量",
                    field: "num"
                },
                {
                    title: "手机号码",
                    field: "phone"
                },
                {
                    title: "收货人名称",
                    field: "name"
                }
            ]
        }

    };

    t.depositList("#sDepositTable", 0),

    $("#bNav2").on("click", "li",
        function(e) {
            e.preventDefault();
            var r = $("#bNav2 li").index($(this));
            $("#contentBox>div").eq(r).removeClass("no").siblings().addClass("no"),
                $("#bNav2 li").eq(r).addClass("on").siblings().removeClass("on"),
                0 == r ? t.depositList("#sDepositTable", 0) : t.sendingList("#sSendingTable", 1)
        }),
    t.depositList("#sDepositTable"),
    t.sendingList("#sSendingTable")


     $("#sDepositTable").on("click", "#addSendingBtn",
         function(t) {
             t.preventDefault(),
             sessionStorage.itemId = $(this).attr("data-itemId"),
             sessionStorage.num = $(this).attr("data-depositNum")
         })


     $("#sAddBackBacketModel").on("click", "#sureAdd",
         function(t) {
             t.preventDefault();
             var e = $("#sAddBackBacketModel #sNum").val();
             var p = $("#sAddBackBacketModel #sPhone").val();
             var n = $("#sAddBackBacketModel #sName").val();
             var a = $("#sAddBackBacketModel #sAddress").val();
             if (! (e > 0)) return alert("请输入正确的配送数量");
             var limit = sessionStorage.num;
             if ((e > limit)) return alert("配送数量超出库存，请重新输入！");
             $.ajax({
                 url: localStorage.url + "/sending/add",
                 type: "POST",
                 dataType: "json",
                 xhrFields: {
                     withCredentials: !0
                 },
                 crossDomain: !0,
                 data: {
                     itemId: sessionStorage.itemId,
                     userId: localStorage.userId,
                     num: e,
                     address: a,
                     phone: p,
                     name: n
                 },
                 success: function(t) {
                     return t.status==200 ? (alert("配送成功"), $(".btn-default").click(), !0) : (alert("配送失败"), !1)
                 }
             })



         })
});