package cn.guet.util;

import java.util.List;

public class PageModel<T> {

	private int totalPage;
	private int currentPage;
	private List<T> list;

	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	@Override
	public String toString() {
		return "PageModel{" +
				"totalPage=" + totalPage +
				", currentPage=" + currentPage +
				", list=" + list +
				'}';
	}
}
