package com.blank.util.pagination;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.blank.util.pagination.Pagination.Page;

public class PaginationTag extends SimpleTagSupport{
	
	private Pagination pagination; 
	
	public void doTag() throws JspException, IOException { 
        PageContext pageContext = (PageContext) getJspContext(); 
        JspWriter out = pageContext.getOut(); 
        if(pagination!=null && pagination.getPages()!=null && pagination.getPages().size()>1){
	        out.println("<ul class=\"pagination\">");
	        Page previous = pagination.getPrevious();
	        Page next = pagination.getNext();
	        
	        if(previous!=null){
	        	out.println("<li><a href=\""+previous.getPath()+"\">&laquo;</a></li>");
	        }
	        if(pagination.isOverflowLeft()){
	        	out.println("<li><a href=\""+pagination.getFirst().getPath()+"\">"+pagination.getFirst().getNumber()+"</a></li>");
	        	out.println("<li><a href=\"#\">...</a></li>");
	        }
	        for (Page page : pagination.getPages()) {
	        	out.println("<li><a href=\""+page.getPath()+"\">" +
	        			(pagination.getPage().equals(page.getNumber()) ? "<strong>": "") +
	        			page.getNumber() +
	        			(pagination.getPage().equals(page.getNumber()) ? "</strong>": "") +
	        			"</a></li>");
			}
	        if(pagination.isOverflowRight()){
	        	out.println("<li><a href=\"#\">...</a></li>");
	        	out.println("<li><a href=\""+pagination.getLast().getPath()+"\">"+pagination.getLast().getNumber()+"</a></li>");
	        }
	        if(next!=null){
	        	out.println("<li><a href=\""+next.getPath()+"\">&raquo;</a></li>");
	        	
	        }
	        out.println("</ul>"); 
        }

    }

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	} 
	
	
}
