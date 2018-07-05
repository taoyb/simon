package com.taoyb.simon.common.tag;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by taoyb on 2016-12-11.
 */
public class TestTag extends TagSupport {
    @Override
    public int doStartTag() throws JspException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdf.format(new Date());
        try {
            pageContext.getOut().print(dateStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.doStartTag();
    }
}
