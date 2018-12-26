$(document).ready(function() {
    var e = {
        salingAnalysis: function(e, t) {
            0 == e && 0 == t && (t = (new Date).toLocaleDateString(), e = new Date((new Date).getTime() - 6048e5).toLocaleDateString()),
                $("#salingAnalysis").bootstrapTable("destroy"),
                $("#salingAnalysis").bootstrapTable({
                    url: localStorage.url + "/agency/goodsSale.action",
                    method: "GET",
                    cache: !1,
                    pagination: !1,
                    pageNumber: 1,
                    ajaxOptions: {
                        xhrFields: {
                            withCredentials: !0
                        },
                        crossDomain: !0,
                        async: !1
                    },
                    responseHandler: function(e) {
                        return e.data.goodsPrice
                    },
                    contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                    queryParamsType: "undefined",
                    queryParams: function(n) {
                        return {
                            agencyId: localStorage.l_agencyId,
                            startTime: e,
                            endTime: t
                        }
                    },
                    columns: [{
                        title: "商品名称",
                        field: "goodsName"
                    },
                        {
                            title: "进货价",
                            field: "costPrice"
                        },
                        {
                            title: "总销售额",
                            formatter: function(e, t, n) {
                                return t.sale_price / 100
                            }
                        },
                        {
                            title: "净利润",
                            formatter: function(e, t, n) {
                                return t.sale_price / 100 - t.costPrice
                            }
                        }]
                })
        },
        inStoreRecord: function() {
            $("#sInStoreOrder").bootstrapTable({
                url: localStorage.url + "/agency/sendingList.action",
                method: "GET",
                cache: !1,
                pagination: !1,
                pageNumber: 1,
                ajaxOptions: {
                    xhrFields: {
                        withCredentials: !0
                    },
                    crossDomain: !0,
                    async: !1
                },
                responseHandler: function(e) {
                    return e.data.sending
                },
                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                queryParamsType: "undefined",
                queryParams: function(e) {
                    return {
                        agencyId: localStorage.l_agencyId
                    }
                },
                columns: [{
                    title: "时间",
                    formatter: function(e, t, n) {
                        return t.creatTime.slice(0, 16)
                    }
                },
                    {
                        title: "商品清单",
                        formatter: function(e, t, n) {
                            return '<button id="slookGoodsDetailBtn" data-sendingId="' + t.sendingId + '" data-toggle="modal" data-target="#sLookGoodsListModel" style="background:#7CA7D1;" >查看</button>'
                        }
                    },
                    {
                        title: "状态",
                        formatter: function(e, t, n) {
                            if (0 == t.sendingStatus) a = "待配送";
                            else if (1 == t.sendingStatus) a = "配送中...";
                            else var a = "已收货";
                            return a
                        }
                    }]
            })
        },
        lookGoodsDetail: function() {
            $("#sInStoreOrder").on("click", "#slookGoodsDetailBtn",
                function(e) {
                    e.preventDefault(),
                        $.ajax({
                            url: localStorage.url + "/agency/sendingGoods.action",
                            type: "GET",
                            dataType: "json",
                            xhrFields: {
                                withCredentials: !0
                            },
                            crossDomain: !0,
                            data: {
                                sendingId: $(this).attr("data-sendingId")
                            },
                            success: function(e) {
                                var t = (e = e.data.sendingGoods).length;
                                if (0 == t) return $("#sGoodsList").html("暂无数据"),
                                    !1;
                                for (var n = '<tr class="active"><td>商品名称</td><td>商品数量</td></tr>',
                                         a = 0; a < t; a++) {
                                    var o = e[a];
                                    n += "<tr><td>" + o.goodsName + "</td><td>" + o.goodsNum + "</td></tr>"
                                }
                                $("#sGoodsList").html(n)
                            }
                        })
                })
        },
        realStore: function() {
            $("#sRealStore").bootstrapTable({
                url: localStorage.url + "/agency/inventory.action",
                method: "GET",
                cache: !1,
                pagination: !1,
                pageNumber: 1,
                ajaxOptions: {
                    xhrFields: {
                        withCredentials: !0
                    },
                    crossDomain: !0,
                    async: !1
                },
                responseHandler: function(e) {
                    return e.data.inventory
                },
                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                queryParamsType: "undefined",
                queryParams: function(e) {
                    return {
                        agencyId: localStorage.l_agencyId
                    }
                },
                columns: [{
                    title: "商品名称",
                    field: "goodsName"
                },
                    {
                        title: "总库存",
                        field: "goodsNum"
                    }]
            })
        }
    };
    $("#bNav2").on("click", "li",
        function(t) {
            t.preventDefault();
            var n = $("#bNav2 li").index($(this));
            $("#contentBox>div").eq(n).removeClass("no").siblings().addClass("no"),
                $("#bNav2 li").eq(n).addClass("on").siblings().removeClass("on"),
                0 == n ? e.salingAnalysis(0, 0) : 1 == n ? e.inStoreRecord() : e.realStore()
        }),
        $("#txtBeginDate").calendar({
            controlId: "divDate"
        }),
        $("#txtEndDate").calendar({
            controlId: "divEndDate"
        }),
        e.salingAnalysis(0, 0),
        e.lookGoodsDetail(),
        $("#sSearchSalingAnalysisBtn").on("click",
            function(t) {
                return t.preventDefault(),
                    startTime = $("#txtBeginDate").val(),
                    endTime = $("#txtEndDate").val(),
                    "" == startTime ? (alert("请输入开始时间"), !1) : "" == endTime ? (alert("请输入结束时间"), !1) : void e.salingAnalysis(startTime, endTime)
            })
});