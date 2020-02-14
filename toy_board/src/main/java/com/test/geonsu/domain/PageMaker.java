package com.test.geonsu.domain;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class PageMaker {

	private int totalCount;
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;

	private int displayPageNum = 10;

	private Criteria cri;

	public void setCri(Criteria cri) {
		this.cri = cri;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calcData();
	}

	public int getTotalCount() {
		return totalCount;
	}

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public boolean isNext() {
		return next;
	}

	public int getDisplayPageNum() {
		return displayPageNum;
	}

	public Criteria getCri() {
		return cri;
	}

	private void calcData() {
		endPage = (int) (Math.ceil(cri.getPage() / (double) displayPageNum) * displayPageNum);
		System.out.println("cripage"+cri.getPage());
		System.out.println("엔드페이지"+endPage);
		startPage = (endPage - displayPageNum) + 1;

		int tempEndPage = (int) (Math.ceil(totalCount / (double) cri.getPerPageNum()));
		System.out.println("템프페이지"+tempEndPage);
		if (endPage > tempEndPage) {
			endPage = tempEndPage;
		}
		prev = startPage == 1 ? false : true;
		System.out.println(prev);
		next = endPage * cri.getPerPageNum() >= totalCount ? false : true;
		System.out.println(next);
	}

	public String makeQuery(int page)
	{
	 UriComponents uriComponents =
	   UriComponentsBuilder.newInstance()
	   .queryParam("page", page)
	   .queryParam("perPageNum", cri.getPerPageNum())
	   .build();
	   System.out.println("uricomponents"+uriComponents);
	 return uriComponents.toUriString();
	}
	
	
}