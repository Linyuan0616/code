

$(document).ready(function() {
    var t = {
        itemList: function(e, r) {
            var o = t.itemListColumns();
            $(e).bootstrapTable("destroy"),
                $(e).bootstrapTable({
                    url: localStorage.url + "/item/list",
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
                        return t
                    },
                    contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                    queryParamsType: "undefined",
                    queryParams: function(t) {
                        o = {
                            pageNumber: t.pageNumber,
                            pageSize: t.pageSize,
                            userId: localStorage.userId
                        };
                        return o
                    },
                    formatLoadingMessage: function() {
                        return "请稍等，正在加载中..."
                    },
                    columns: o
                })
        },
        itemListColumns: function() {
            return [
                {
                    title: "编号",
                    field: "id"
                },
                {
                    title: "名称",
                    field: "title"
                },
                {
                    title: "规格",
                    field: "param"
                },
                {
                    field: "num",
                    title: "数量"
                },
                {
                    field: "price",
                    title: "价格",
                    formatter: function(t, e, r) {
                        return e.price / 100
                    }
                },
                {
                    title: "操作",
                    formatter: function(t, e) {
                        return '<button id="purchaseBtn" data-itemId="' + e.id + '" style="background:#FD9BCB;" >购买</button>'
                    }
                }

            ]

        },

        purchase: function(e) {
            $(e).on("click", "#purchaseBtn",
                function(r) {
                    r.preventDefault();
                    var o = prompt("请输入购买数量：");
                    $.ajax({
                        url: localStorage.url + "/order/add",
                        type: "POST",
                        dataType: "json",
                        xhrFields: {
                            withCredentials: !0
                        },
                        crossDomain: !0,
                        data: {
                            itemId: $(this).attr("data-itemId"),
                            userId: localStorage.userId,
                            num: o
                        },
                        success: function(r) {
                            return r.status==200 ? (alert("购买成功！！"), t.itemList("#pOrderTable", 2)) : (alert("购买失败！！"), !1)
                        }
                    })
                })
        }
    };
    t.itemList("#pOrderTable", 2),
        $("#bNav2").on("click", "li",
            function(e) {
                e.preventDefault();
                var r = $("#bNav2 li").index($(this));
                $("#contentBox>div").eq(r).removeClass("no").siblings().addClass("no"),
                    $("#bNav2 li").eq(r).addClass("on").siblings().removeClass("on"),
                    t.itemList("#pOrderTable", 2)
            }),

        t.purchase("#pOrderTable")
});