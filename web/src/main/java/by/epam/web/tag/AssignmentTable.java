package by.epam.web.tag;

import by.epam.web.entity.Entity;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;

public class AssignmentTable extends TagSupport {
    private String head;
    private int rows;
    private List<? extends Entity> list;

    public void setHead(String head) {
        this.head = head;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            JspWriter out = pageContext.getOut();
            out.write("<table border='1'>");
            out.write("<caption>" + head + "</caption");
            out.write("<thead><tr><th>ID</th><th>Name</th><th>Description</th></tr></thead>");
            out.write("<td>");
        } catch (IOException e){
            throw new JspException(e.getMessage());
        }
        return EVAL_BODY_INCLUDE;
    }

    @Override
    public int doAfterBody() throws JspException {
        if(rows-- > 1){
            try {
                pageContext.getOut().write("</td><td>");
            } catch (IOException e){
                throw new JspException(e.getMessage());
            }
            return EVAL_BODY_AGAIN;
        } else {
            return SKIP_BODY;
        }
    }

    @Override
    public int doEndTag() throws JspException {
        try {
            pageContext.getOut().write("</td></table>");
        } catch (IOException e){
            throw new JspException(e.getMessage());
        }
        return EVAL_PAGE;
    }
}
