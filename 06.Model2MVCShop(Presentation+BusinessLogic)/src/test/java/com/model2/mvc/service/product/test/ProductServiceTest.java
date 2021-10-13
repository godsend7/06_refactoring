 package com.model2.mvc.service.product.test;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;


/*
 *	FileName :  UserServiceTest.java
 * �� JUnit4 (Test Framework) �� Spring Framework ���� Test( Unit Test)
 * �� Spring �� JUnit 4�� ���� ���� Ŭ������ ���� ������ ��� ���� �׽�Ʈ �ڵ带 �ۼ� �� �� �ִ�.
 * �� @RunWith : Meta-data �� ���� wiring(����,DI) �� ��ü ����ü ����
 * �� @ContextConfiguration : Meta-data location ����
 * �� @Test : �׽�Ʈ ���� �ҽ� ����
 */
@RunWith(SpringJUnit4ClassRunner.class)

//==> Meta-Data �� �پ��ϰ� Wiring ����...
@ContextConfiguration(locations = { "classpath:config/context-common.xml", 
									"classpath:config/context-aspect.xml",
									"classpath:config/context-mybatis.xml",
									"classpath:config/context-transaction.xml" })
public class ProductServiceTest {

	//==>@RunWith,@ContextConfiguration �̿� Wiring, Test �� instance DI
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;

	//@Test
	public void testAddProduct() throws Exception {
		
		Product product = new Product();
		//product.setProdNo(1);
		product.setProdName("testProductName");
		product.setProdDetail("testProdDetail");
		product.setManuDate("20211010");
		product.setPrice(50000);
		product.setFileName("testImageFile");
		
		
		productService.addProduct(product);
		//product = productService.getProduct(10022);

		//==> console Ȯ��
		System.out.println(product);
		
		//==> API Ȯ�� 
		//Assert.assertEquals("testProductName", product.getProdName());
		//Assert.assertEquals("testProdDetail", product.getProdDetail());
		//Assert.assertEquals("20211006", product.getManuDate());
		//Assert.assertEquals(50000, product.getPrice());
		//Assert.assertEquals("testImageFile", product.getFileName());
	}
	
	@Test
	public void testGetProduct() throws Exception {
		
		Product product = new Product();
		//==> �ʿ��ϴٸ�...
		//product.setProdName("testProductName");
		//product.setProdDetail("testProdDetail");
		//product.setManuDate("20211006");
		//product.setPrice(50000);
		//product.setFileName("testImageFile");
		
		//productService.getProduct(10040);
		product = productService.getProduct(10040);
//		product.getProdName();
//		product.getProdDetail();
//		product.getManuDate();
//		product.getPrice();
//		product.getFileName();
	
		//==> console Ȯ��
		System.out.println(product);
		
		//==> API Ȯ��
		//Assert.assertEquals("testProductName", product.getProdName());
		//Assert.assertEquals("testProdDetail", product.getProdDetail());
		//Assert.assertEquals("20211006", product.getManuDate());
		//Assert.assertEquals(50000, product.getPrice());
		//Assert.assertEquals("testImageFile", product.getFileName());

		//Assert.assertNotNull(productService.getUser("user02"));
		//Assert.assertNotNull(productService.getUser("user05"));
	}
	
	 //@Test
	 public void testUpdateProduct() throws Exception{
		 
		Product product = productService.getProduct(10041);
		Assert.assertNotNull(product);
		
		Assert.assertEquals("testProductName", product.getProdName());
		Assert.assertEquals("testProdDetail", product.getProdDetail());
		Assert.assertEquals("20211006", product.getManuDate());
		Assert.assertEquals(50000, product.getPrice());
		Assert.assertEquals("testImageFile", product.getFileName());


		product.setProdName("�ٲ�");
		product.setProdDetail("test�ٲ�");
		product.setManuDate("19991007");
		product.setPrice(60000);
		
		productService.updateProduct(product);
		
		product = productService.getProduct(10041);
		Assert.assertNotNull(product);
		
		//==> console Ȯ��
		System.out.println(product);
			
		//==> API Ȯ��
		//Assert.assertEquals("change", product.getProdName());
		//Assert.assertEquals("test", product.getProdDetail());
		//Assert.assertEquals("20211007", product.getManuDate());
		//Assert.assertEquals(60000, product.getPrice());
	 }
	
	 //==>  �ּ��� Ǯ�� �����ϸ�....
	 //@Test
	 public void testGetProductListAll() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	Map<String,Object> map = productService.getProductList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
		//==> console Ȯ��
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword("");
	 	map = productService.getProductList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
	 	//==> console Ȯ��
	 	System.out.println("list : " + list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println("totalCount : " + totalCount);
	 }
	 
	 //@Test
	 public void testGetProductListByProdNo() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword("10016");
	 	Map<String,Object> map = productService.getProductList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(1, list.size());
	 	
		//==> console Ȯ��
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println("totalCount : "+totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword("10016");
	 	map = productService.getProductList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(1, list.size());
	 	
		//==> console Ȯ��
	 	System.out.println("list : " + list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println("totalCount : " + totalCount);
	 }
	 
	 //@Test
	 public void testGetProductListByProdName() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("1");
	 	search.setSearchKeyword("ġ�����");
	 	Map<String,Object> map = productService.getProductList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(1, list.size());
	 	
		//==> console Ȯ��
	 	System.out.println("list : " + list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println("totalCount : "+totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setSearchCondition("1");
	 	search.setSearchKeyword("ġ�����");
	 	map = productService.getProductList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(1, list.size());
	 	
		//==> console Ȯ��
	 	System.out.println("list : " + list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println("totalCount : "+totalCount);
	 }	 
}