

/**
 * 确定所选行商品的 ID
 * @returns {string}
 */
function getSelectionsIds(){
    var sendingList = $("#sendingList");
    var sels = sendingList.datagrid("getSelections");
    var ids = [];
    for(var i in sels){
        ids.push(sels[i].sendingId);
    }
    ids = ids.join(",");
    return ids;
}

/**
 * 商品编辑组件
 * @type {*[]}
 */
var toolbar1 = [{
    text:'删除',
    iconCls:'icon-cancel',
    handler:function(){
        var ids = getSelectionsIds();
        if(ids.length == 0){
            $.messager.alert('提示','未选中商品!');
            return ;
        }
        $.messager.confirm('确认','确定删除ID为 '+ids+' 的配送单吗？',function(r){
            if (r){
                var params = {"ids":ids};
                $.post("/sending/delete" , params, function(data){
                    if(data.status == 200){
                        $.messager.alert('提示','删除商品成功!',undefined,function(){
                            $("#itemList").datagrid("reload");
                        });
                    }
                });
            }
        });
    }
},'-',{
    text:'配送',
    iconCls:'icon-remove',
    handler:function(){
        var ids = getSelectionsIds();
        if(ids.length == 0){
            $.messager.alert('提示','未选中商品!');
            return ;
        }
        $.messager.confirm('确认','确定配送ID为 '+ids+' 的商品吗？',function(r){
            if (r){
                var params = {"ids":ids};
                $.post("/sending/send",params, function(data){
                    if(data.status == 200){
                        $.messager.alert('提示','下架商品成功!',undefined,function(){
                            $("#itemList").datagrid("reload");
                        });
                    }
                });
            }
        });
    }

}];