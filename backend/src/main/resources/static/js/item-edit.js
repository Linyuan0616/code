var itemEditEditor ;

$(function(){
    //实例化编辑器
    itemEditEditor =  KindEditor.create("#itemEditForm [name=desc]");
});
/**
 * 确定所选行商品的 ID
 * @returns {string}
 */
function getSelectionsIds(){
    var itemList = $("#itemList");
    var sels = itemList.datagrid("getSelections");
    var ids = [];
    for(var i in sels){
        ids.push(sels[i].id);
    }
    ids = ids.join(",");
    return ids;
}

/**
 * 商品编辑组件
 * @type {*[]}
 */
var toolbar = [{
    text:'添加',
    iconCls:'icon-add',
    handler:function(){
        $(".tree-title:contains('新增商品')").parent().click();
    }
},{
    text:'编辑',
    iconCls:'icon-edit',
    handler:function(){
        var ids = getSelectionsIds();
        if(ids.length == 0){
            $.messager.alert('提示','必须选择一个商品!');
            return ;
        }
        if(ids.indexOf(',') > 0){
            $.messager.alert('提示','只能选择一个商品!');
            return ;
        }

        $("#itemEditWindow").window({
            onLoad :function(){
                //回显数据
                var data = $("#itemList").datagrid("getSelections")[0];
                data.priceView = WATERMANAGER.formatPrice(data.price);
                $("#itemEditForm").form("load",data);

                // 加载商品描述
                $.getJSON('/item/desc/'+data.id,function(_data){
                    if(_data.status == 200){
                        //UM.getEditor('itemeEditDescEditor').setContent(_data.data.itemDesc, false);
                        itemEditEditor.html(_data.data.itemDesc);
                    }
                });


                WATERMANAGER.init({
                    "pics" : data.image,
                    "cid" : data.cid,
                    fun:function(node){
                        WATERMANAGER.changeItemParam(node, "itemEditForm");
                    }
                });
            }
        }).window("open");
    }
},{
    text:'删除',
    iconCls:'icon-cancel',
    handler:function(){
        var ids = getSelectionsIds();
        if(ids.length == 0){
            $.messager.alert('提示','未选中商品!');
            return ;
        }
        $.messager.confirm('确认','确定删除ID为 '+ids+' 的商品吗？',function(r){
            if (r){
                var params = {"ids":ids};
                $.post("/item/delete" , params, function(data){
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
    text:'下架',
    iconCls:'icon-remove',
    handler:function(){
        var ids = getSelectionsIds();
        if(ids.length == 0){
            $.messager.alert('提示','未选中商品!');
            return ;
        }
        $.messager.confirm('确认','确定下架ID为 '+ids+' 的商品吗？',function(r){
            if (r){
                var params = {"ids":ids};
                $.post("/item/unstock",params, function(data){
                    if(data.status == 200){
                        $.messager.alert('提示','下架商品成功!',undefined,function(){
                            $("#itemList").datagrid("reload");
                        });
                    }
                });
            }
        });
    }
},{
    text:'上架',
    iconCls:'icon-remove',
    handler:function(){
        var ids = getSelectionsIds();
        if(ids.length == 0){
            $.messager.alert('提示','未选中商品!');
            return ;
        }
        $.messager.confirm('确认','确定上架ID为 '+ids+' 的商品吗？',function(r){
            if (r){
                var params = {"ids":ids};
                $.post("/item/instock",params, function(data){
                    if(data.status == 200){
                        $.messager.alert('提示','上架商品成功!',undefined,function(){
                            $("#itemList").datagrid("reload");
                        });
                    }
                });
            }
        });
    }
}];