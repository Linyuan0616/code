$(document).ready(function() {
    function t(t) {
        var o = null;
        return void 0 != window.createObjectURL ? o = window.createObjectURL(t) : void 0 != window.URL ? o = window.URL.createObjectURL(t) : void 0 != window.webkitURL && (o = window.webkitURL.createObjectURL(t)),
            o
    }
    var o = {

        loadPic: function(t) {
            var o = new FormData($(t)[0]);
            $.ajax({
                url: localStorage.url + "/user/pic/upload",
                type: "POST",
                data: o,
                async: !1,
                cache: !1,
                contentType: !1,
                processData: !1,
                xhrFields: {
                    withCredentials: !0
                },
                crossDomain: !0,
                success: function(t) {
                    if (t.status != 200) { return alert("上传失败！")}
                    return sessionStorage.picUrl = t.data ;
                }
            })
        },
        loadAddredd: function(t, o) {
            $.ajax({
                url: "http://ali-city.showapi.com/areaDetail",
                type: "GET",
                async: !1,
                dataType: "json",
                beforeSend: function(t) {
                    t.setRequestHeader("Authorization", "APPCODE 4ee961ed3a064ab68a58a427413fb8ae")
                },
                data: {
                    parentId: t
                },
                success: function(t) {
                    for (var e = t.showapi_res_body.data,
                             a = e.length,
                             s = "",
                             d = 0; d < a; d++) s += '<option data-id="' + e[d].id + '" value="' + e[d].areaName + '">' + e[d].areaName + "</option>";
                    $(o).html(s)
                }
            })
        },
        showAgencyInfo: function() {
            $.ajax({
                url: localStorage.url + "/user/getInfo",
                type: "GET",
                dataType: "json",
                xhrFields: {
                    withCredentials: !0
                },
                crossDomain: !0,
                data: {
                    userId: localStorage.userId
                },
                success: function(t) {
                    var o = t.data ;
                    sessionStorage.userId = o.userId,
                        $("#sAgencyInfoShowBox #sUserId").html(o.userId),
                        $("#sAgencyInfoShowBox #sName").html(o.userName),
                        $("#sAgencyInfoShowBox #sTel").html(o.phone),
                        $("#sAgencyInfoShowBox #sCreatTime").html(o.creatTime),
                            $("#sAgencyInfoShowBox .sendPicc").html('<img id="img0" src="' + o.picture + '" alt="暂无封面">')
                }
            })
        },
        goodsList: function(t) {
            $.ajax({
                url: localStorage.url + "/agency/getInventory.action",
                type: "GET",
                dataType: "json",
                xhrFields: {
                    withCredentials: !0
                },
                crossDomain: !0,
                data: {
                    agencyId: localStorage.l_agencyId
                },
                success: function(o) {
                    var e = (o = o.data.goods).length;
                    if (0 == e) return document.getElementById(t).innerHTML = "暂无记录",
                        !1;
                    if ("sGoodsTable" == t) for (var a = '<tr class="active"><td>图片</td><td>商品名称</td><td>规格</td><td>进货价(元)</td><td>售价(元)</td><td>上架</td>',
                                                     s = 0; s < e; s++) {
                        var d = (i = o[s]).goodsName.split("-");
                        if (1 == i.goodsStatus) var n = "checkBox",
                            r = 1;
                        else var n = "noCheckBox",
                            r = 0;
                        0 != i.salePrice ? a += '<tr><td><img id="showPic" src="' + i.goodsPic + '" /></td><td>' + d[0] + "</td><td>" + d[1] + '</td><td class="costPrice">' + i.costPrice / 100 + '</td><td><span class="editSaleInput">' + i.salePrice / 100 + '</span><button id="editSalePriceBtn" data-status="1" data-recordId="' + i.id + '" style="background: #7CA7D1;">编辑</button></td><td><span id="' + n + '" class="check" data-check="' + r + '" data-recordId="' + i.id + '"></span></td>': a += '<tr><td><img src="' + i.goodsPic + '" /></td><td>' + d[0] + "</td><td>" + d[1] + '</td><td class="costPrice">' + i.costPrice / 100 + '</td><td><input type="text" id="salePriceInput" /><button id="editSalePriceBtn" data-status="0" data-recordId="' + i.id + '" style="background: #7CA7D1;">提交</button></td><td><span id="' + n + '" class="check" data-check="' + r + '" data-recordId="' + i.id + '"></span></td>'
                    } else if ("sPolicyTable" == t) for (var a = '<tr class="active"><td>商品名称</td><td>规格</td><td>售价(元)</td><td>操作</td>',
                                                             s = 0; s < e; s++) {
                        var i = o[s];
                        a += "<tr><td>" + (d = i.goodsName.split("-"))[0] + "</td><td>" + d[1] + "</td><td>" + i.salePrice / 100 + '</td><td><button id="addPolicyBtn" data-goodsId="' + i.goodsId + '" data-toggle="modal" data-target="#myModal" data-goodsName="' + i.goodsName + '" style="background: #7CA7D1;">新增</button><button id="setPolicyBtn" data-goodsId="' + i.goodsId + '" data-goodsPrice="' + i.salePrice + '" data-toggle="modal" data-target="#setPolicyModel" style="background: #FD9BCB;">配置</button></td></tr>'
                    }
                    document.getElementById(t).innerHTML = a
                }
            })
        },
        goodsStatus: function(t, e) {
            $.ajax({
                url: localStorage.url + "/agency/updateGoods.action",
                type: "POST",
                dataType: "json",
                xhrFields: {
                    withCredentials: !0
                },
                crossDomain: !0,
                data: {
                    id: t,
                    goodsStatus: e
                },
                success: function(t) {
                    t.success ? (alert("操作成功"), o.goodsList("sGoodsTable")) : alert("操作失败")
                }
            })
        },
        sale: function(t, e) {
            $.ajax({
                url: localStorage.url + "/agency/updateGoods.action",
                type: "POST",
                dataType: "json",
                xhrFields: {
                    withCredentials: !0
                },
                crossDomain: !0,
                data: {
                    id: t,
                    salePrice: e
                },
                success: function(t) {
                    t.success ? (alert("操作成功"), o.goodsList("sGoodsTable")) : alert("操作失败")
                }
            })
        },
        setMySelfGoods: function() {
            $("#g_mySelfGoodsBtn").on("click",
                function(t) {
                    if (t.preventDefault(), t.stopPropagation(), "../images/pic.png" == $("#g_mySelfGoodsForm #img0").attr("src")) return alert("请选择图片"),
                        !1;
                    o.loadPic("#g_mySelfGoodsForm");
                    var e = $("#g_mySelfGoodsName").val();
                    if ("" == e) return alert("请填写商品名称"),
                        !1;
                    var a = $("#g_mySelfGoodsSize").val(),
                        s = $("#g_mySelfGoodsType").val();
                    if ("空桶" == s) d = 1;
                    else if ("水" == s) d = 0;
                    else var d = 2;
                    var n = $("#g_mySelfGoodsNum").val();
                    if (!/^[0-9]*$/.test(n)) return alert("请输入正确的商品数量"),
                        !1;
                    var r = $("#g_mySelfGoodsSale").val();
                    if (!/^[0-9]+(\.[0-9]{1,2})?$/.test(r)) return alert("请输入正确的售价"),
                        !1;
                    var i = $("#g_mySelfGoodsInPrice").val();
                    return /^[0-9]+(\.[0-9]{1,2})?$/.test(i) ? r - 0 < i - 0 ? (alert("售价不能低于进货价"), !1) : void $.ajax({
                        url: localStorage.url + "/agency/addGoods.action",
                        type: "POST",
                        dataType: "json",
                        xhrFields: {
                            withCredentials: !0
                        },
                        crossDomain: !0,
                        data: {
                            goodsName: e + "-" + a,
                            goodsPic: sessionStorage.picUrl,
                            goodsType: d,
                            agencyId: localStorage.l_agencyId,
                            goodsNum: n,
                            salePrice: 100 * r,
                            costPrice: 100 * i
                        },
                        success: function(t) {
                            return t.success ? (alert("添加成功"), $("#bNav2 li").eq(1).trigger("click"), !0) : (alert("添加失败"), !1)
                        }
                    }) : (alert("请输入正确的进货价"), !1)
                })
        }
    };
    $("#bNav2").on("click", "li",
        function(t) {
            t.preventDefault(),
                t.stopPropagation();
            var e = $("#bNav2 li").index($(this));
            $("#contentBox>div").eq(e).removeClass("no").siblings().addClass("no"),
                $("#bNav2 li").eq(e).addClass("on").siblings().removeClass("on"),
                1 == e ? o.goodsList("sGoodsTable") : 2 == e && o.goodsList("sPolicyTable")
        }),
        o.setMySelfGoods(),
        $.ajax({
            url: localStorage.url + "/user/getInfo",
            type: "GET",
            dataType: "json",
            xhrFields: {
                withCredentials: !0
            },
            crossDomain: !0,
            data: {
                userId: localStorage.userId
            },
            success: function(t) {
                if (t.status == 200) {
                    $("#sAgencyInfoShowBox").removeClass("no"),
                        o.showAgencyInfo()
                }
            }
        }),
        $("#sEditShopInfoBtn").on("click",
            function(t) {
                t.preventDefault(),
                    $("#sAgencyInfoShowBox").addClass("no"),
                    $("#sEditShopInfoForm").removeClass("no"),
                    $("#sEditShopInfoForm #img0").attr("src", $("#sAgencyInfoShowBox .sendPicc img").attr("src")),
                    sessionStorage.url = $("#sAgencyInfoShowBox .sendPicc img").attr("src"),
                    $("#sEditShopInfoForm #sName").val($("#sAgencyInfoShowBox #sName").text()),
                    $("#sEditShopInfoForm #sTel").val($("#sAgencyInfoShowBox #sTel").text()),
                    $("#sEditShopInfoForm #sPerson").val($("#sAgencyInfoShowBox #sPerson").text());
            }),
        $("#sEditShopInfoForm #subProviceBox").on("change",
            function(t) {
                t.preventDefault(),
                    o.loadAddredd($("#sEditShopInfoForm #subProviceBox option:checked").attr("data-id"), "#sEditShopInfoForm #subsubProviceBox")
            }),
        $("#sEditShopInfoSubmitBtn").on("click",
            function(t) {
                t.preventDefault(),
                    o.loadPic("#sEditShopInfoForm");
                var e = $("#sEditShopInfoForm #sName").val(),
                    a = $("#sEditShopInfoForm #sTel").val();
                if ("" == a) return alert("手机号码不能为空"),
                    !1;
                if (!/^1[3|4|5|7|8]\d{9}$/.test(a)) return alert("手机号码输入错误"),
                    !1;

                var p = $("#sEditShopInfoForm #sPassword").val(),
                    p1 =  $("#sEditShopInfoForm #sRPassword").val() ;
                if (p != p1) {
                    return alert("两次输入密码不一致"),
                        !1;
                }
                    d = $("#sEditShopInfoForm #proviceBox").val() + "+" + $("#sEditShopInfoForm #subProviceBox").val() + "+" + $("#sEditShopInfoForm #subsubProviceBox").val() + "+" + $("#sEditShopInfoForm #sAddress").val() + "+" + $("#sEditShopInfoForm #proviceBox option:checked").attr("data-id") + "+" + $("#sEditShopInfoForm #subProviceBox option:checked").attr("data-id");
                $("#sEditShopInfoForm #subsubProviceBox option:checked").attr("data-id"),
                null != sessionStorage.picUrl && "null" != sessionStorage.picUrl || (sessionStorage.picUrl = sessionStorage.url),
                    $.ajax({
                        url: localStorage.url + "/user/updateInfo",
                        type: "POST",
                        dataType: "json",
                        xhrFields: {
                            withCredentials: !0
                        },
                        crossDomain: !0,
                        data: {
                            userId: sessionStorage.userId,
                            userName: e,
                            phone: a,
                            password: p,
                            picture: sessionStorage.picUrl
                        },
                        success: function(t) {
                            t.status == 200 ? (alert("修改成功"), location.reload()) : alert("修改失败")
                        }
                    })
            }),
        $("#sGoodsTable").on("click", ".check",
            function(t) {
                t.preventDefault();
                var e = $(this).attr("data-check"),
                    a = $(this).attr("data-recordId");
                if (0 == e) {
                    if (! (s = confirm("确定上架该商品？"))) return ! 1;
                    $(this).attr("data-check", 1),
                        o.goodsStatus(a, 1)
                } else {
                    var s = confirm("确定下架该商品？");
                    if (!s) return ! 1;
                    $(this).attr("data-check", 0),
                        o.goodsStatus(a, 0)
                }
            }),
        $("#sGoodsTable").on("click", "#editSalePriceBtn",
            function(t) {
                t.preventDefault();
                var e = $(this).attr("data-status"),
                    a = $(this).parent("td").parent("tr");
                if (0 == e) {
                    if ("" == a.find("#salePriceInput").val() || a.find("#salePriceInput").val() <= 0) return alert("请填写正确的售价"),
                        !1;
                    if (a.find("#salePriceInput").val() - 0 < a.find(".costPrice").text() - 0) return alert("售价不能低于进货价"),
                        !1;
                    o.sale($(this).attr("data-recordId"), 100 * a.find("#salePriceInput").val())
                } else {
                    var s = $(this).parent().find(".editSaleInput").text();
                    $(this).parent().find(".editSaleInput").html('<input type="text" id="salePriceInput" value="' + s + '" style="width:35px;"/>'),
                        $(this).text("提交"),
                        $(this).attr("data-status", 0)
                }
            }),
        $("#sPolicyTable").on("click", "#addPolicyBtn",
            function(t) {
                t.preventDefault(),
                    $("#myModal input").val("");
                var o = $(this).attr("data-goodsName");
                $("#sAddPolicyModel .goodsName").html("当前选择商品：" + o),
                    sessionStorage.l_chooseToAddPolicyGoodsId = $(this).attr("data-goodsId")
            }),
        $("#sureAddPolicyBtn").on("click",
            function(t) {
                t.preventDefault();
                var o = $("#buyNum").val(),
                    e = $("#sendNum").val();
                if (!/^[0-9]*$/.test(o) || !/^[0-9]*$/.test(o)) return alert("请输入大于0的整数"),
                    !1;
                $.ajax({
                    url: localStorage.url + "/agency/addPolicy.action",
                    type: "POST",
                    dataType: "json",
                    xhrFields: {
                        withCredentials: !0
                    },
                    crossDomain: !0,
                    data: {
                        agencyId: localStorage.l_agencyId,
                        goodsId: sessionStorage.l_chooseToAddPolicyGoodsId,
                        endlineNum: o,
                        presentNum: e
                    },
                    success: function(t) {
                        t.success ? (alert("新增政策成功"), $(".close").trigger("click")) : alert("新增政策失败")
                    }
                })
            }),
        $("#sPolicyTable").on("click", "#setPolicyBtn",
            function(t) {
                t.preventDefault();
                var o = $(this).attr("data-goodsPrice");
                $.ajax({
                    url: localStorage.url + "/agency/getPolicy.action",
                    type: "GET",
                    dataType: "json",
                    xhrFields: {
                        withCredentials: !0
                    },
                    crossDomain: !0,
                    data: {
                        agencyId: localStorage.l_agencyId,
                        goodsId: $(this).attr("data-goodsId")
                    },
                    success: function(t) {
                        var e = t.data.policys,
                            a = e.length;
                        $(".currentGoods").html("当前选择商品：" + e[0].goodsName);
                        for (var s = '<tr class="active"><td>满</td><td>送</td><td>是否配置</td><td>操作</td></tr>',
                                 d = 0; d < a; d++) {
                            var n = e[d];
                            if (1 == n.policyStatus) r = "checkBox";
                            else var r = "noCheckBox";
                            s += "<tr><td>" + n.endlineNum + "</td><td>" + n.presentNum + '</td><td><span class="check" id="' + r + '"></span></td><td><button id="setPolicyEditBtn" data-policyId="' + n.policyId + '" data-goodsId="' + n.goodsId + '" data-goodsPrice="' + o + '" style="background:#7CA7D1">编辑</button></td></tr>'
                        }
                        document.getElementById("sLookPolicyTable").innerHTML = s
                    }
                })
            }),
        $("#sLookPolicyTable").on("click", "#setPolicyEditBtn",
            function(t) {
                t.preventDefault(),
                    $(this).text("完成"),
                    $(this).attr("id", "setPolicyEditOKBtn"),
                    $(this).parent().parent("tr").find("td").eq(2).attr("class", "edit")
            }),
        $("#sLookPolicyTable").on("click", ".check",
            function(t) {
                if (t.preventDefault(), "edit" != $(this).parent("td").attr("class")) return alert("请先点击‘编辑’按钮进入编辑状态"),
                    !1;
                "checkBox" == $(this).attr("id") ? $(this).attr("id", "noCheckBox") : $(this).attr("id", "checkBox")
            }),
        $("#sLookPolicyTable").on("click", "#setPolicyEditOKBtn",
            function(t) {
                t.preventDefault();
                var o = this,
                    e = $(this).attr("data-goodsPrice"),
                    a = $(this).parent().parent("tr").find("td");
                if ("checkBox" == a.eq(2).find(".check").attr("id")) s = 1;
                else if ("noCheckBox" == a.eq(2).find(".check").attr("id")) var s = 0;
                $.ajax({
                    url: localStorage.url + "/agency/updatePolicy.action",
                    type: "POST",
                    dataType: "json",
                    xhrFields: {
                        withCredentials: !0
                    },
                    crossDomain: !0,
                    data: {
                        policyId: $(o).attr("data-policyId"),
                        goodsId: $(o).attr("data-goodsId"),
                        endlineNum: a.eq(0).text(),
                        presentNum: a.eq(1).text(),
                        policyStatus: s,
                        goodsPrice: e
                    },
                    success: function(t) {
                        return t.success ? ($(o).text("编辑"), $(o).attr("id", "setPolicyEditBtn"), $(o).parent().parent("tr").find("td").eq(2).removeAttr("class", "edit"), alert("修改成功"), !0) : (alert("修改失败"), !1)
                    }
                })
            }),
        $("#sAddShopInfoForm #file0").change(function() {
            var o = t(this.files[0]);
            if (!/[.](jpg|gif|png|bmp|jpg|JPG|PNG)$/.test(this.files[0].name)) return alert("文件格式错误"),
                !1;
            o && ($("#sAddShopInfoForm #img0").attr("src", o), $(this).prev(".sendPicc").find("span").css("display", "none"), $(this).prev(".sendPicc").css("background", "#fff"))
        }),
        $("#sEditShopInfoForm #file0").change(function() {
            var o = t(this.files[0]);
            if (!/[.](jpg|gif|png|bmp|jpg|JPG|PNG)$/.test(this.files[0].name)) return alert("文件格式错误"),
                !1;
            o && ($("#sEditShopInfoForm #img0").attr("src", o), $(this).prev(".sendPicc").find("span").css("display", "none"), $(this).prev(".sendPicc").css("background", "#fff"))
        }),
        $("#g_mySelfGoodsForm #file0").change(function() {
            var o = t(this.files[0]);
            if (!/[.](jpg|gif|png|bmp|jpg|JPG|PNG)$/.test(this.files[0].name)) return alert("文件格式错误"),
                !1;
            o && ($("#g_mySelfGoodsForm #img0").attr("src", o), $(this).prev(".sendPicc").find("span").css("display", "none"), $(this).prev(".sendPicc").css("background", "#fff"))
        })
});