package com.test.geonsu.domain;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.sound.sampled.AudioFormat.Encoding;
import javax.xml.crypto.URIReferenceException;

import org.apache.ibatis.executor.ReuseExecutor;
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
	
	public String makeSearch(int page)
	{
		UriComponents uriComponents =
				UriComponentsBuilder.newInstance()
				.queryParam("page",page)
				.queryParam("perPageNum",cri.getPerPageNum())
				.queryParam("searchType", ((SearchCriteria)cri).getSearchType())
				.queryParam("keyword",encoding(((SearchCriteria)cri).getKeyword()))
				.build();
				return uriComponents.toUriString();
	}
	
	private String encoding(String keyword) {		
		if(keyword ==null || keyword.trim().length() ==0)
		{return "";}
		try {
			return URLEncoder.encode(keyword, "UTF-8");
		}catch (UnsupportedEncodingException e) {
			return "";
		}
	}
	
	
}