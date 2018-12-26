$(document).ready(function() {
    var t = {
        purchaseList: function(e, r) {
            var o = t.purchaseListColumns();
            $(e).bootstrapTable("destroy"),
                $(e).bootstrapTable({
                    url: localStorage.url + "/order/list",
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
                         if ("#pOrderTable" == e )o = {
                             pageNumber: t.pageNumber,
                             pageSize: t.pageSize,
                             userId: localStorage.userId,
                             status: 0
                        }; else  var o = {
                             pageNumber: t.pageNumber,
                             pageSize: t.pageSize,
                             userId: localStorage.userId,
                             status: 1
                         };
                        return o
                    },
                    formatLoadingMessage: function() {
                        return "请稍等，正在加载中..."
                    },
                    columns: o
                })
        },
        purchaseListColumns: function() {
            return [

                {
                    title: "订单id",
                    field: "orderId"
                },
                {
                    title: "商品id",
                    field: "itemId"
                },
                {
                    title: "数量",
                    field: "num"
                },
                {
                    title: "创建时间",
                    field: "creatTime"
                },
                {
                    title: "更新时间",
                    field: "updateTime"
                }
               ]
        },


    };
    t.purchaseList("#pUnOrderTable", 1),
        $("#bNav2").on("click", "li",
            function(e) {
                e.preventDefault();
                var r = $("#bNav2 li").index($(this));
                $("#contentBox>div").eq(r).removeClass("no").siblings().addClass("no"),
                    $("#bNav2 li").eq(r).addClass("on").siblings().removeClass("on"),
                    0 == r ? t.purchaseList("#pOrderTable", 2) : 1 == r ? t.purchaseList("#pUnOrderTable", 1) : t.purchaseList("#pUnOrderTable", 1)
            })

});