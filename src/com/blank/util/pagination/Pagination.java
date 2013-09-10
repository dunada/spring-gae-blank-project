package com.blank.util.pagination;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.blank.util.Constant;

/**
 * 
 * @author eduardo.rodrigues
 * 
 */
public class Pagination {

	private Integer step =Constant.DEFAULT_PAGE_STEP;
	private Integer pageSize =Constant.DEFAULT_PAGE_SIZE;
	
	private Integer page=1;
	private Integer total;
	
	private Page last;
	private Page first;
	private Page next;
	private Page previous;
	private boolean overflowRight;
	private boolean overflowLeft;
	private List<Page> pages;
	private String path;
	
	public Pagination()	{
		
	}
	
	public Pagination(String path)	{
		this.path = path;
	}
	
	public Pagination(Integer pageSize)	{
		this.pageSize = pageSize;
	}
	
	public Pagination(Integer pageSize, Integer step)	{
		this.pageSize = pageSize;
		this.step = step;
	}	

	private void addPage(Integer page)	{
		this.pages.add(new Page(page,getPath(page) ));
	}
	
	private String getPath(Integer page){
		return StringUtils.isNotBlank(path) ? path.replaceAll("\\$page", page.toString()) : null;
	}
	
	public Integer getStartPage(){
		return page==1?0:(page-1)*pageSize;
	}

	public void build()	{
		//Verificando se existem itens
		if(total!=null && total>0){
			//Verificando se vai ter paginação
			if(total>pageSize){
				pages = new LinkedList<Page>();
				double remainder =total%pageSize;
				Integer totalPages=total/pageSize;
				//TODO roy ta uma merda arrumar
				if(remainder>0){
					totalPages++;
				}
				first=new Page(1,getPath(1) );
				last=new Page(totalPages,getPath(totalPages) );
				if(step != null && step>0){
					Integer halfStep = step/2;
					Integer pStart = null;
					Integer pEnd = null;
					if(totalPages>step){
					
						//Descobrindo a pagina inicial
						pStart=page;
						if(page-halfStep>0){
							pStart=page-halfStep;
						}else if(page-halfStep<=0){
							pStart=1;
						}
						//Descobrindo a pagina final
						pEnd=pStart-1+step;
						if(pEnd>totalPages){
							pEnd=totalPages;
							if((pStart-(pEnd-totalPages))>=1){
								pStart=pStart-(pEnd-totalPages);
							}
							
							
						}
					}else{
						pStart=1;
						pEnd=totalPages;
					}
					for (int i = pStart; i <= pEnd; i++) {
						if(i==pStart && i>1){
							overflowLeft=true;
							continue;
						}
						if(i==pEnd && i<totalPages){
							overflowRight=true;
							continue;
						}
						addPage(i);
					}
				}
				if(page+1<=totalPages){
					next=new Page(page+1,getPath(page+1) );
				}
				if(page>1){
					previous=new Page(page-1,getPath(page-1) );
				}
			}
		}
	}
	

	

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page==null?page=1:page;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Number total) {
		this.total = total.intValue();
	}
	
	public Integer getStep() {
		return step;
	}

	public Integer getPageSize() {
		return pageSize;
	}


	public Page getLast() {
		return last;
	}

	public Page getFirst() {
		return first;
	}

	public Page getNext() {
		return next;
	}

	public Page getPrevious() {
		return previous;
	}

	public boolean isOverflowRight() {
		return overflowRight;
	}

	public boolean isOverflowLeft() {
		return overflowLeft;
	}

	public List<Page> getPages() {
		return pages;
	}

	public class Page{
		private final Integer number;
		private final String path;
		public Page(Integer number, String path) {
			super();
			this.number = number;
			this.path = path;
		}
		public Integer getNumber() {
			return number;
		}
		public String getPath() {
			return path;
		}
		
		
		
	}



	
}
