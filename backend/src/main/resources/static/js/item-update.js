var itemEditEditor1 ;
$(function(){
    //实例化编辑器
    itemEditEditor1 = KindEditor.create("#itemEditForm [name=desc]");
});

function submitForm(){
    if(!$('#itemeEditForm').form('validate')){
        $.messager.alert('提示','表单还未填写完成!');
        return ;
    }
    $("#itemeEditForm [name=price]").val(eval($("#itemeEditForm [name=priceView]").val()) * 1000);
    itemEditEditor1.sync();

    $.post("/rest/item/update",$("#itemeEditForm").serialize(), function(data){
        if(data.status == 200){
            $.messager.alert('提示','修改商品成功!','info',function(){
                $("#itemEditWindow").window('close');
                $("#itemList").datagrid("reload");
            });
        }
    });
}
