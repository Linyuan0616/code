var itemAddEditor ;
//页面初始化完毕后执行此方法
$(function(){
    itemAddEditor = KindEditor.create("#itemAddForm [name=desc]");
    //初始化类目选择和图片上传器
    WATERMANAGER.init({fun:function(node){
        }});
});
//提交表单
function submitForm(){
    //有效性验证
    if(!$('#itemAddForm').form('validate')){
        $.messager.alert('提示','表单还未填写完成!');
        return ;
    }
    //取商品价格，单位为“分”
    $("#itemAddForm [name=price]").val(eval($("#itemAddForm [name=priceView]").val()) * 100);
    //同步文本框中的商品描述
    itemAddEditor.sync();

    //ajax的post方式提交表单
    //$("#itemAddForm").serialize()将表单序列号为key-value形式的字符串
    $.post("/item/add",$("#itemAddForm").serialize(), function(data){
        if(data.status == 200){
            $.messager.alert('提示','新增商品成功!');
        }
    });
}

/**
 * 重置表单
 */
function clearForm(){
    $('#itemAddForm').form('reset');
    itemAddEditor.html('');
}