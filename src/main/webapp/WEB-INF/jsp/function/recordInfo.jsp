<%--
  Created by IntelliJ IDEA.
  User: taoyb
  Date: 2017-08-30
  Time: 下午 1:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div>
    <textarea id="recordDesc" name="recordDesc" style="width: 100%;">${record.recordDesc}</textarea>
    <script type="text/javascript">
        var editor = new UE.ui.Editor({scaleEnabled:true,initialFrameHeight:600,toolbars:[['FullScreen', 'Source', 'Undo', 'Redo','bold','test']]});
        editor.render('recordDesc');
    </script>
</div>