<%@include file="../common/tag.jsp"%>
<div class="pageContent">
    <form method="post" action="" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone)">
        <input type="hidden" name="" value="">
        <div class="pageFormContent" layoutH="58">
            <div class="unit">
                <label>菜单名称：</label>
                <input type="text" name="" size="30" value="" class="required" />
            </div>
            <div class="unit">
                <label>菜单路径：</label>
                <input type="text" name="" size="30" value="" class="required"/>
            </div>
        </div>
        <div class="formBar">
            <ul>
                <li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
                <li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
            </ul>
        </div>
    </form>
</div>