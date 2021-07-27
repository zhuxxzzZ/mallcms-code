layui.config({
    base: '../../sliderVerify/'
}).use(['form','layer','jquery','sliderVerify'],function(){
        var sliderVerify = layui.sliderVerify;
        var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer
        $ = layui.jquery;
        sliderVerify.render({
        elem: "#slider"
        })



    $(".loginBody .seraph").click(function(){
        layer.msg("这只是做个样式，至于功能，你见过哪个后台能这样登录的？还是老老实实的找管理员去注册吧",{
            time:5000
        });
    })

    //登录按钮
    form.on("submit(login)",function(data){
        let param=data.field;
        $.post("/sysuser/login.do",param,function (rs){
            if (rs.code !=200){
                console.log(rs.msg);
                layer.msg(rs.msg);
                return false;
            }
            location.href="/page/main.do";

        })


        return false;



    })

    //表单输入效果
    $(".loginBody .input-item").click(function(e){
        e.stopPropagation();
        $(this).addClass("layui-input-focus").find(".layui-input").focus();
    })
    $(".loginBody .layui-form-item .layui-input").focus(function(){
        $(this).parent().addClass("layui-input-focus");
    })
    $(".loginBody .layui-form-item .layui-input").blur(function(){
        $(this).parent().removeClass("layui-input-focus");
        if($(this).val() != ''){
            $(this).parent().addClass("layui-input-active");
        }else{
            $(this).parent().removeClass("layui-input-active");
        }
    })
})
